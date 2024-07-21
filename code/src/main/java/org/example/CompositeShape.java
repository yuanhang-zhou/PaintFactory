package org.example;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompositeShape extends Shape {
    private List<Shape> shapes = new ArrayList<>();

    public CompositeShape(int x, int y, Color color) {
        super(x, y, color);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public void draw(Graphics g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    @Override
    public void resize(int width, int height) {
        for (Shape shape : shapes) {
            shape.resize(width, height);
        }
    }

    @Override
    public boolean contains(int x, int y) {
        for (Shape shape : shapes) {
            if (shape.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setPosition(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;
        this.x = x;
        this.y = y;
        for (Shape shape : shapes) {
            shape.setPosition(shape.x + dx, shape.y + dy);
        }
    }

    @Override
    public Shape clone() {
        CompositeShape copy = new CompositeShape(x, y, color);
        for (Shape shape : shapes) {
            copy.addShape(shape.clone());
        }
        return copy;
    }
}
