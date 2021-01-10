package org.ieti.laborator;

import org.ieti.laborator.util.FileUtility;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    private static final int LABEL_WIDTH = 300;
    private static final int INPUT_WIDTH = 165;

    private static final int BUTTON_ALLIGNMENT = 30;
    private static final int INPUT_ALLIGNMENT = 120;
    private static final int LABEL_ALLIGNMENT = 20;
    private static final int FIELD_HEIGHT = 25;

    private static final int ROW_1 = 20;
    private static final int ROW_2 = 60;
    private static final int ROW_3 = 100;
    private static final int ROW_4 = 140;
    private static final int ROW_5 = 180;
    private static final int ROW_6 = 220;
    private static final int ROW_7 = 260;
    private static final int ROW_8 = 300;
    private static final int ROW_9 = 340;

    private static int ENTRIES_NUMBER;

    private static final int BUTTON_WIDTH = 200;
    private static final int LIST_BUTTONS_ALLIGNMENT = 380;
    private static final int LIST_BUTTON_WIDTH = 150;

    private static JButton buttonAddCar;
    private static JButton buttonListCar;
    private static JButton buttonExit;
    private static JButton buttonModifyCar;
    private static JButton buttonDeleteCar;
    private static JButton buttonBackToMenu;
    private static JButton buttonSaveCar;
    private static JButton buttonCancelCar;

    private static JTextField brandInput;
    private static JTextField modelInput;
    private static JTextField yearInput;
    private static JTextField powerInput;
    private static JTextField mileageInput;

    private static JRadioButton isAutomatic;
    private static JRadioButton isManual;

    private static JList<String> jList;
    private static DefaultListModel<String> carListModel;

    private static JFrame frame;

    private static List<Car> carList = new ArrayList<>();

    public static JPanel buildPanel() {
        carList = FileUtility.readFromFile();

        frame = new JFrame("Meniu proiect:");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        frame.add(panel);
        showMenuPage(panel);

        return panel;
    }

    private static void showAddPage(JPanel panel) {
        JLabel brandLabel = new JLabel("Brand:");
        brandLabel.setBounds(LABEL_ALLIGNMENT, ROW_1, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(brandLabel);

        brandInput = new JTextField(20);
        brandInput.setBounds(INPUT_ALLIGNMENT, ROW_1, INPUT_WIDTH, FIELD_HEIGHT);
        panel.add(brandInput);

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(LABEL_ALLIGNMENT, ROW_2, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(modelLabel);

        modelInput = new JTextField(20);
        modelInput.setBounds(INPUT_ALLIGNMENT, ROW_2, INPUT_WIDTH, FIELD_HEIGHT);
        panel.add(modelInput);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(LABEL_ALLIGNMENT, ROW_3, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(yearLabel);

        yearInput = new JTextField(20);
        yearInput.setBounds(INPUT_ALLIGNMENT, ROW_3, INPUT_WIDTH, FIELD_HEIGHT);
        panel.add(yearInput);

        JLabel gearLabel = new JLabel("Gearbox type:");
        gearLabel.setBounds(LABEL_ALLIGNMENT, ROW_4, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(gearLabel);

        isAutomatic = new JRadioButton();
        isAutomatic.setText("Automatic");
        isAutomatic.setBounds(INPUT_ALLIGNMENT,ROW_4,INPUT_WIDTH,FIELD_HEIGHT);
        panel.add(isAutomatic);

        isManual = new JRadioButton();
        isManual.setText("Manual");
        isManual.setBounds(INPUT_ALLIGNMENT,ROW_5,INPUT_WIDTH,FIELD_HEIGHT);
        panel.add(isManual);

        ButtonGroup gearButtonGroup = new ButtonGroup();
        gearButtonGroup.add(isAutomatic);
        gearButtonGroup.add(isManual);

        JLabel mileageLabel = new JLabel("Mileage:");
        mileageLabel.setBounds(LABEL_ALLIGNMENT, ROW_6, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(mileageLabel);

        mileageInput = new JTextField(20);
        mileageInput.setBounds(INPUT_ALLIGNMENT, ROW_6, INPUT_WIDTH, FIELD_HEIGHT);
        panel.add(mileageInput);

        JLabel powerLabel = new JLabel("Power:");
        powerLabel.setBounds(LABEL_ALLIGNMENT, ROW_7, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(powerLabel);

        powerInput = new JTextField(20);
        powerInput.setBounds(INPUT_ALLIGNMENT, ROW_7, INPUT_WIDTH, FIELD_HEIGHT);
        panel.add(powerInput);

        buttonSaveCar = new JButton("Save");
        buttonSaveCar.setBounds(BUTTON_ALLIGNMENT, ROW_8, BUTTON_WIDTH, FIELD_HEIGHT);
        buttonSaveCar.addActionListener(
                e -> {
                    boolean noError = true;

                    Car car = new Car();
                    car.setBrand(brandInput.getText());
                    car.setModel(modelInput.getText());

                    if(isAutomatic.isSelected())
                        car.setAutomatic(true);
                    else if(isManual.isSelected())
                        car.setAutomatic(false);
                    else {
                        noGearButtonSelected();
                        noError = false;
                    }

                    try {
                        car.setYear(Integer.parseInt(yearInput.getText()));
                    } catch (NumberFormatException f) {
                        wrongDataType("Year", "Integer");
                        noError = false;
                    }

                    try {
                        car.setOdometer(Double.parseDouble(mileageInput.getText()));
                    } catch (NumberFormatException f) {
                        wrongDataType("Mileage", "Double");
                        noError = false;
                    }

                    try {
                        car.setHorsePower(Integer.parseInt(powerInput.getText()));
                    } catch (NumberFormatException f) {
                        wrongDataType("Power", "Integer");
                        noError = false;
                    }

                    if(noError) {
                        carList.add(car);
                        clearPanel(panel);
                        showMenuPage(panel);
                    }
                }
        );
        panel.add(buttonSaveCar);

        buttonCancelCar = new JButton("Cancel");
        buttonCancelCar.setBounds(BUTTON_ALLIGNMENT, ROW_9, BUTTON_WIDTH, FIELD_HEIGHT);
        buttonCancelCar.addActionListener(
                e -> {
                    clearPanel(panel);
                    showMenuPage(panel);
                }
        );
        panel.add(buttonCancelCar);

    }

    private static void showModifyPage(JPanel panel, Car car) {
        JLabel brandLabel = new JLabel("Brand:");
        brandLabel.setBounds(LABEL_ALLIGNMENT, ROW_1, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(brandLabel);

        brandInput = new JTextField(20);
        brandInput.setBounds(INPUT_ALLIGNMENT, ROW_1, INPUT_WIDTH, FIELD_HEIGHT);
        brandInput.setText(car.getBrand());
        panel.add(brandInput);

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(LABEL_ALLIGNMENT, ROW_2, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(modelLabel);

        modelInput = new JTextField(20);
        modelInput.setBounds(INPUT_ALLIGNMENT, ROW_2, INPUT_WIDTH, FIELD_HEIGHT);
        modelInput.setText(car.getModel());
        panel.add(modelInput);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(LABEL_ALLIGNMENT, ROW_3, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(yearLabel);

        yearInput = new JTextField(20);
        yearInput.setBounds(INPUT_ALLIGNMENT, ROW_3, INPUT_WIDTH, FIELD_HEIGHT);
        yearInput.setText(String.valueOf(car.getYear()));
        panel.add(yearInput);

        JLabel gearLabel = new JLabel("Gearbox type:");
        gearLabel.setBounds(LABEL_ALLIGNMENT, ROW_4, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(gearLabel);

        isAutomatic = new JRadioButton();
        isAutomatic.setText("Automatic");
        isAutomatic.setBounds(INPUT_ALLIGNMENT,ROW_4,INPUT_WIDTH,FIELD_HEIGHT);
        if(car.isAutomatic())
            isAutomatic.setSelected(true);
        panel.add(isAutomatic);

        isManual = new JRadioButton();
        isManual.setText("Manual");
        isManual.setBounds(INPUT_ALLIGNMENT,ROW_5,INPUT_WIDTH,FIELD_HEIGHT);
        if(!car.isAutomatic())
            isManual.setSelected(true);
        panel.add(isManual);

        ButtonGroup gearButtonGroup = new ButtonGroup();
        gearButtonGroup.add(isAutomatic);
        gearButtonGroup.add(isManual);

        JLabel mileageLabel = new JLabel("Mileage:");
        mileageLabel.setBounds(LABEL_ALLIGNMENT, ROW_6, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(mileageLabel);

        mileageInput = new JTextField(20);
        mileageInput.setBounds(INPUT_ALLIGNMENT, ROW_6, INPUT_WIDTH, FIELD_HEIGHT);
        mileageInput.setText(String.valueOf(car.getOdometer()));
        panel.add(mileageInput);

        JLabel powerLabel = new JLabel("Power:");
        powerLabel.setBounds(LABEL_ALLIGNMENT, ROW_7, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(powerLabel);

        powerInput = new JTextField(20);
        powerInput.setBounds(INPUT_ALLIGNMENT, ROW_7, INPUT_WIDTH, FIELD_HEIGHT);
        powerInput.setText(String.valueOf(car.getHorsePower()));
        panel.add(powerInput);

        buttonSaveCar = new JButton("Save");
        buttonSaveCar.setBounds(BUTTON_ALLIGNMENT, ROW_8, BUTTON_WIDTH, FIELD_HEIGHT);
        buttonSaveCar.addActionListener(
                e -> {
                    boolean noError = true;
                    car.setBrand(brandInput.getText());
                    car.setModel(modelInput.getText());

                    if(isAutomatic.isSelected())
                        car.setAutomatic(true);
                    else if(isManual.isSelected())
                        car.setAutomatic(false);
                    else {
                        noGearButtonSelected();
                        clearPanel(panel);
                        showModifyPage(panel, car);
                        noError = false;
                    }

                    try {
                        car.setHorsePower(Integer.parseInt(powerInput.getText()));
                    } catch (NumberFormatException f) {
                        wrongDataType("Power", "Integer");
                        clearPanel(panel);
                        showModifyPage(panel, car);
                        noError = false;
                    }

                    try {
                        car.setYear(Integer.parseInt(yearInput.getText()));
                    } catch (NumberFormatException f) {
                        wrongDataType("Year", "Integer");
                        clearPanel(panel);
                        showModifyPage(panel, car);
                        noError = false;
                    }

                    try {
                        car.setOdometer(Double.parseDouble(mileageInput.getText()));
                    } catch (NumberFormatException f) {
                        wrongDataType("Mileage", "Double");
                        clearPanel(panel);
                        showModifyPage(panel, car);
                        noError = false;
                    }
                    if(noError) {
                        clearPanel(panel);
                        showListPage(panel);
                    }
                }
        );
        panel.add(buttonSaveCar);

        buttonCancelCar = new JButton("Cancel");
        buttonCancelCar.setBounds(BUTTON_ALLIGNMENT, ROW_9, BUTTON_WIDTH, FIELD_HEIGHT);
        buttonCancelCar.addActionListener(
                e -> {
                    clearPanel(panel);
                    showListPage(panel);
                }
        );
        panel.add(buttonCancelCar);

    }

    private static void showMenuPage(JPanel panel) {
        buttonAddCar = new JButton("Add Car");
        buttonAddCar.setBounds(BUTTON_ALLIGNMENT + 100, ROW_4, BUTTON_WIDTH, FIELD_HEIGHT);
        buttonAddCar.addActionListener(
                e -> {
                    clearPanel(panel);
                    showAddPage(panel);
                }
        );
        panel.add(buttonAddCar);

        buttonListCar = new JButton("List Car");
        buttonListCar.setBounds(BUTTON_ALLIGNMENT + 100, ROW_5, BUTTON_WIDTH, FIELD_HEIGHT);
        buttonListCar.addActionListener(
                e -> {
                    clearPanel(panel);
                    showListPage(panel);
                }
        );
        panel.add(buttonListCar);

        buttonExit = new JButton("Exit Program");
        buttonExit.setBounds(BUTTON_ALLIGNMENT + 100, ROW_6, BUTTON_WIDTH, FIELD_HEIGHT);
        buttonExit.addActionListener(
                e -> {
                    FileUtility.writeToFile(carList);
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
        );
        panel.add(buttonExit);

    }

    private static void showListPage(JPanel panel) {
        carListModel = new DefaultListModel<>();
        addCarsToJList();

        JLabel entriesLabel = new JLabel("Total entries: " + ENTRIES_NUMBER);
        entriesLabel.setBounds(LABEL_ALLIGNMENT, ROW_1, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(entriesLabel);

        jList = new JList<>(carListModel);
        jList.setBounds(BUTTON_ALLIGNMENT, ROW_2, 300, 500);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(jList);

        buttonModifyCar = new JButton("Modify");
        buttonModifyCar.setBounds(LIST_BUTTONS_ALLIGNMENT, ROW_1, LIST_BUTTON_WIDTH, FIELD_HEIGHT);
        buttonModifyCar.addActionListener(
                e -> {
                    int selectedIndex = jList.getSelectedIndex();

                    if (selectedIndex < 0) {
                        noCarSelectedError();
                        System.out.println("Please select a car!");
                    } else {
                        clearPanel(panel);
                        showModifyPage(panel, carList.get(selectedIndex));
                    }
                }
        );
        panel.add(buttonModifyCar);

        buttonDeleteCar = new JButton("Delete");
        buttonDeleteCar.setBounds(LIST_BUTTONS_ALLIGNMENT, ROW_2, LIST_BUTTON_WIDTH, FIELD_HEIGHT);
        buttonDeleteCar.addActionListener(
                e -> {
                    int selectedIndex = jList.getSelectedIndex();

                    if (selectedIndex < 0) {
                        noCarSelectedError();
                        System.out.println("Please select a car!");
                    } else {
                        carList.remove(selectedIndex);
                        carListModel.clear();
                        addCarsToJList();
                        clearPanel(panel);
                        showListPage(panel);
                    }
                }
        );
        panel.add(buttonDeleteCar);

        buttonBackToMenu = new JButton("Back to menu");
        buttonBackToMenu.setBounds(LIST_BUTTONS_ALLIGNMENT, ROW_3, LIST_BUTTON_WIDTH, FIELD_HEIGHT);
        buttonBackToMenu.addActionListener(
                e -> {
                    clearPanel(panel);
                    showMenuPage(panel);
                }
        );
        panel.add(buttonBackToMenu);

    }

    private static void addCarsToJList() {
        ENTRIES_NUMBER = 0;
        carList.forEach(car -> {
            carListModel.addElement(car.getBrand() + " " + car.getModel() + " - " + car.getYear());
            ENTRIES_NUMBER++;
        });
    }

    private static void clearPanel(JPanel panel) {
        panel.removeAll();
        panel.updateUI();
    }

    private static void noCarSelectedError() {
        JOptionPane.showMessageDialog(frame, "No car is selected.", "Selection error", JOptionPane.ERROR_MESSAGE);
    }

    private static void noGearButtonSelected() {
        JOptionPane.showMessageDialog(frame, "You have to select the gear type.", "Selection error", JOptionPane.WARNING_MESSAGE);
    }

    private static void wrongDataType(String wrongField, String rightInput) {
        JOptionPane.showMessageDialog(frame, "'" + wrongField + "' field expected '" + rightInput + "' type, but found something else.", "Input error", JOptionPane.ERROR_MESSAGE);
    }
}
