package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeEditor extends JPopupMenu {
    private Shape shape;
    private Canvas canvas;

    public ShapeEditor(Shape shape, Canvas canvas) {
        this.shape = shape;
        this.canvas = canvas;

        JMenuItem editDescription = new JMenuItem("编辑描述");
        editDescription.addActionListener(e -> {
            String description = JOptionPane.showInputDialog("输入描述:", shape.getDescription());
            if (description != null) {
                shape.setDescription(description);
                canvas.repaint();
            }
        });
        add(editDescription);

        JMenuItem editSize = new JMenuItem("调整尺寸");
        editSize.addActionListener(e -> {
            String widthStr = JOptionPane.showInputDialog("输入宽度:", "50");
            String heightStr = JOptionPane.showInputDialog("输入高度:", "50");
            if (widthStr != null && heightStr != null) {
                try {
                    int width = Integer.parseInt(widthStr);
                    int height = Integer.parseInt(heightStr);
                    shape.resize(width, height);
                    canvas.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "无效的尺寸输入");
                }
            }
        });
        add(editSize);
    }
}
