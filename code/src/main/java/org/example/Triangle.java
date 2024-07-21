package org.example;
import java.awt.*;
import java.awt.geom.Line2D;

public class Triangle extends Shape {
    private int width, height;

    public Triangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x, y + height, x + width / 2, y);
        g.drawLine(x + width / 2, y, x + width, y + height);
        g.drawLine(x, y + height, x + width, y + height);
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
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }
}
