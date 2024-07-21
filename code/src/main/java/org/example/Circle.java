package org.example;
import java.awt.*;

public class Circle extends Shape {
    private int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        if (!description.isEmpty()) {
            g.drawString(description, x - radius, y - radius - 10);
        }
    }

    @Override
    public void resize(int width, int height) {
        this.radius = Math.min(width, height) / 2;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean contains(int x, int y) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(radius, 2);
    }
}
