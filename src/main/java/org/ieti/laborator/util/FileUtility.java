package org.ieti.laborator.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.ieti.laborator.Car;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtility {

    public static void writeToFile(List<Car> carList) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        File file = new File("src/main/resources/cars.json");

        try {
            writer.writeValue(file, carList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Car> readFromFile() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/cars.json")));
            return mapper.readValue(json, new TypeReference<List<Car>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}