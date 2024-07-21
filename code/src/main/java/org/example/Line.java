package org.example;
import java.awt.*;
import java.awt.geom.Line2D;

public class Line extends Shape {
    private int x2, y2;

    public Line(int x, int y, int x2, int y2, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x2, y2);
        if (!description.isEmpty()) {
            g.drawString(description, (x + x2) / 2, (y + y2) / 2 - 10);
        }
    }

    @Override
    public void resize(int width, int height) {
        this.x2 = x + width;
        this.y2 = y + height;
    }

    @Override
    public boolean contains(int x, int y) {
        double distance = Line2D.ptSegDist(this.x, this.y, x2, y2, x, y);
        return distance <= 5.0;
    }

    @Override
    public void setPosition(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;
        this.x = x;
        this.y = y;
        this.x2 += dx;
        this.y2 += dy;
    }
}
