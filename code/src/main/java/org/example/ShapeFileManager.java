package org.example;

import java.io.*;
import java.util.List;

public class ShapeFileManager {
    public static void saveShapes(List<Shape> shapes, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(shapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Shape> loadShapes(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Shape>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
