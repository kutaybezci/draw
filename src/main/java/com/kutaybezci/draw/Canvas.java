package com.kutaybezci.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kutay.bezci
 */
public class Canvas extends JComponent {

    private BufferedImage image;

    public Canvas(){
        Session session=Session.getInstance();
        this.image = new BufferedImage(session.getWidth(), session.getHeight(), BufferedImage.TYPE_INT_RGB);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(this.image, 0, 0, this);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Canvas");
        Dimension dimension = new Dimension(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        Artist a = new Artist(canvas);
        a.start();
        frame.getContentPane().add(canvas);
        frame.pack();
        frame.setSize(dimension);
        canvas.setBounds(frame.getBounds());
        frame.setVisible(true);
    }
}
