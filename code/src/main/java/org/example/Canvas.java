package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private static Canvas instance;
    private List<Shape> shapes = new ArrayList<>();
    private Shape selectedShape = null;

    private Canvas() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedShape = null;
                for (Shape shape : shapes) {
                    if (shape.contains(e.getX(), e.getY())) {
                        selectedShape = shape;
                        break;
                    }
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedShape = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedShape != null) {
                    selectedShape.setPosition(e.getX(), e.getY());
                    repaint();
                }
            }
        });
    }

    public static Canvas getInstance() {
        if (instance == null) {
            instance = new Canvas();
        }
        return instance;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
        repaint();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
        repaint();
    }

    public void clear() {
        shapes.clear();
        repaint();
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void copySelectedShape() {
        if (selectedShape != null) {
            Shape copiedShape = selectedShape.clone();
            copiedShape.setPosition(selectedShape.x + 20, selectedShape.y + 20); // 偏移位置以示区分
            shapes.add(copiedShape);
            repaint();
        }
    }

    public void groupSelectedShapes(List<Shape> selectedShapes) {
        CompositeShape compositeShape = new CompositeShape(0, 0, Color.BLACK);
        for (Shape shape : selectedShapes) {
            compositeShape.addShape(shape);
        }
        shapes.removeAll(selectedShapes);
        shapes.add(compositeShape);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (selectedShape != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.RED);
            g2.drawRect(selectedShape.x - 5, selectedShape.y - 5, 10, 10);
        }
    }
}
