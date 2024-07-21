package org.example;
import java.awt.*;

public class ShapeFactory {
    public static Shape createShape(String type, int x, int y, Color color) {
        switch (type.toLowerCase()) {
            case "circle":
                return new Circle(x, y, 50, color);
            case "triangle":
                return new Triangle(x, y, 100, 50, color);
            case "rectangle":
                return new Rectangle(x, y, 100, 50, color);
            case "ellipse":
                return new Ellipse(x, y, 100, 50, color);
            case "line":
                return new Line(x, y, x + 100, y + 50, color);
            case "composite":
                return new CompositeShape(x, y, color);
            default:
                throw new IllegalArgumentException("Unknown shape type");
        }
    }
}
