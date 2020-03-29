package com.Alex;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class Window extends JFrame implements KeyListener {
    private ArrayList<CoordinatesData> rectangles = new ArrayList<>();
    private CoordinatesData circle = new CoordinatesData();

    private int windowWidth = 400;
    private int windowHeight = 400;
    private JLabel label;
    private int count=0;

    public Window(int nrThreads) {
        addKeyListener(this);

        setContentPane(new DrawPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(windowWidth, windowHeight);
        setVisible(true);
        init(nrThreads);
        setResizable(false);

        label = new JLabel();
        getContentPane().add(label, BorderLayout.EAST);

        circle.X = this.getSize().height - 150;
        circle.Y = this.getSize().width - 50;
        circle.setR(20);;

        label.setText("Score:" + count);
        label.setForeground(Color.CYAN);
        setTitle("2D Game");


    }

    public void keyReleased(KeyEvent e) {

    }

    // Create a component that you can actually draw on.
    class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
            g.fillRect(0,0,windowWidth, windowHeight);
            g.setColor(Color.BLACK);
            for (CoordinatesData rectangle : rectangles) {
                g.fillRect(rectangle.X, rectangle.Y, rectangle.getW(), rectangle.getW());
                g.setColor(Color.CYAN);

            }

            g.fillOval(circle.X, circle.Y, circle.getR(), circle.getR());
            g.setColor(Color.CYAN);
        }
    }

    private void init(int noOfThreads) {

        for (int i = 0; i < noOfThreads; i++) {
            CoordinatesData rectangle = new CoordinatesData();

            Random random = new Random();

            rectangle.setY(0);
            rectangle.setW(25);
            rectangle.setH(25);
            rectangle.setX( i + 5 + random.nextInt(6) * (rectangle.getW() + random.nextInt(70)));
            this.rectangles.add(rectangle);
        }
        this.repaint();
    }

    public void DropObject(int id, int val) {
        rectangles.get(id).Y += val;
        this.repaint();
    }

    public void RestartObject(int id) {
        rectangles.get(id).Y = 0;
        this.repaint();
        count++;
        label.setText("Score:" + count);

    }

    public boolean IsShapeOut(int id) {
        return (rectangles.get(id).Y > this.getHeight() - 10);
    }

    public boolean IsCrash(int id) {
        int a1 = Math.abs(rectangles.get(id).getX() - circle.getX());
        int a2 = Math.abs(rectangles.get(id).Y - circle.Y);

        return (a1 <= 20 && a2 < 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            circle.X += 10;

        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            circle.X -= 10;

        if(circle.X < 0)
            circle.X = 0;
        else if(circle.X > 370)
            circle.X = 370;

    }
}
