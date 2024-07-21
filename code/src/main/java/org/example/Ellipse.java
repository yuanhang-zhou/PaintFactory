package org.example;

import java.awt.*;

public class Ellipse extends Shape {
    private int width, height;

    public Ellipse(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(x, y, width, height);
        if (!description.isEmpty()) {
            g.drawString(description, x, y - 10);
        }
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean contains(int x, int y) {
        double dx = (double)(x - this.x) / width;
        double dy = (double)(y - this.y) / height;
        return dx * dx + dy * dy <= 1.0;
    }
}
