package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("图形绘制软件");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            Canvas canvas = Canvas.getInstance();
            frame.add(canvas, BorderLayout.CENTER);

            JPanel controlPanel = new JPanel();
            JButton addCircleButton = new JButton("添加圆形");
            JButton addTriangleButton = new JButton("添加三角形");
            JButton addRectangleButton = new JButton("添加方框");
            JButton addEllipseButton = new JButton("添加椭圆");
            JButton addLineButton = new JButton("添加连接线");
            JButton copyButton = new JButton("复制选中图形");
            JButton groupButton = new JButton("组合图形");
            
            controlPanel.add(addCircleButton);
            controlPanel.add(addTriangleButton);
            controlPanel.add(addRectangleButton);
            controlPanel.add(addEllipseButton);
            controlPanel.add(addLineButton);
            controlPanel.add(copyButton);
            controlPanel.add(groupButton);
            frame.add(controlPanel, BorderLayout.NORTH);

            addCircleButton.addActionListener(e -> {
                Shape circle = ShapeFactory.createShape("circle", 100, 100, Color.BLACK);
                Command addCommand = new AddShapeCommand(canvas, circle);
                CommandManager.getInstance().executeCommand(addCommand);
            });

            addTriangleButton.addActionListener(e -> {
                Shape triangle = ShapeFactory.createShape("triangle", 100, 100, Color.BLACK);
                Command addCommand = new AddShapeCommand(canvas, triangle);
                CommandManager.getInstance().executeCommand(addCommand);
            });

            addRectangleButton.addActionListener(e -> {
                Shape rectangle = ShapeFactory.createShape("rectangle", 100, 100, Color.BLACK);
                Command addCommand = new AddShapeCommand(canvas, rectangle);
                CommandManager.getInstance().executeCommand(addCommand);
            });

            addEllipseButton.addActionListener(e -> {
                Shape ellipse = ShapeFactory.createShape("ellipse", 100, 100, Color.BLACK);
                Command addCommand = new AddShapeCommand(canvas, ellipse);
                CommandManager.getInstance().executeCommand(addCommand);
            });

            addLineButton.addActionListener(e -> {
                Shape line = ShapeFactory.createShape("line", 100, 100, Color.BLACK);
                Command addCommand = new AddShapeCommand(canvas, line);
                CommandManager.getInstance().executeCommand(addCommand);
            });

            copyButton.addActionListener(e -> canvas.copySelectedShape());
            
            groupButton.addActionListener(e -> {
                List<Shape> selectedShapes = new ArrayList<>();
                Shape selectedShape = canvas.getSelectedShape();
                if (selectedShape != null) {
                    for (Shape shape : canvas.getShapes()) {
                        if (shape.contains(selectedShape.x, selectedShape.y)) {
                            selectedShapes.add(shape);
                        }
                    }
                }
                if (!selectedShapes.isEmpty()) {
                    canvas.groupSelectedShapes(selectedShapes);
                }
            });

            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("文件");
            JMenuItem saveMenuItem = new JMenuItem("保存");
            JMenuItem loadMenuItem = new JMenuItem("加载");
            JMenu editMenu = new JMenu("编辑");
            JMenuItem undoMenuItem = new JMenuItem("撤销");
            JMenuItem redoMenuItem = new JMenuItem("重做");

            saveMenuItem.addActionListener(e -> {
                String filename = JOptionPane.showInputDialog("输入文件名:");
                if (filename != null && !filename.trim().isEmpty()) {
                    ShapeFileManager.saveShapes(canvas.getShapes(), filename);
                }
            });

            loadMenuItem.addActionListener(e -> {
                String filename = JOptionPane.showInputDialog("输入文件名:");
                if (filename != null && !filename.trim().isEmpty()) {
                    List<Shape> shapes = ShapeFileManager.loadShapes(filename);
                    if (shapes != null) {
                        canvas.setShapes(shapes);
                    }
                }
            });

            undoMenuItem.addActionListener(e -> CommandManager.getInstance().undo());
            redoMenuItem.addActionListener(e -> CommandManager.getInstance().redo());

            fileMenu.add(saveMenuItem);
            fileMenu.add(loadMenuItem);
            editMenu.add(undoMenuItem);
            editMenu.add(redoMenuItem);
            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            frame.setJMenuBar(menuBar);

            canvas.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        for (Shape shape : canvas.getShapes()) {
                            if (shape.contains(e.getX(), e.getY())) {
                                JPopupMenu shapeEditor = new ShapeEditor(shape, canvas);
                                shapeEditor.show(canvas, e.getX(), e.getY());
                                break;
                            }
                        }
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}
