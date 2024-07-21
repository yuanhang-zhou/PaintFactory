package org.example;
import java.awt.*;

public abstract class Shape implements Cloneable {
    protected int x, y;
    protected Color color;
    protected String description = "";

    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract void draw(Graphics g);
    public abstract void resize(int width, int height);
    public abstract boolean contains(int x, int y);
    public abstract void setPosition(int x, int y);

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported for shape", e);
        }
    }
}
