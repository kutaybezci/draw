package com.kutaybezci.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author kutay.bezci
 */
public class Artist extends Thread {
    private final Canvas canvas;
    private final BufferedImage image;
    private final Random random;

    public Artist(Canvas canvas) {
        this.canvas=canvas;
        Session session=Session.getInstance();
        this.image = new BufferedImage(session.getWidth(), session.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.random = new Random(System.currentTimeMillis());
    }

    public GeneralPath generateArbitary() {
        Session session=Session.getInstance();
        int pointCount = random.nextInt(Session.getInstance().getMaxPointCount());
        int rule = random.nextInt(2) == 0 ? GeneralPath.WIND_EVEN_ODD : GeneralPath.WIND_NON_ZERO;
        GeneralPath polyline = new GeneralPath(rule, pointCount);
        polyline.moveTo(1 + random.nextInt(session.getWidth()), 1 + random.nextInt(session.getHeight()));
        for (int i = 1; i < pointCount; i++) {
            int lineType = random.nextInt(3);
            if (lineType == 0) {
                polyline.lineTo(1 + random.nextInt(1 + random.nextInt(session.getWidth())),//
                        1 + random.nextInt(session.getHeight()));
            } else if (lineType == 1) {
                polyline.quadTo(1 + random.nextInt(session.getWidth()),//
                        1 + random.nextInt(session.getHeight()),//
                        1 + random.nextInt(session.getWidth()),//
                        1 + random.nextInt(session.getHeight()));
            } else {
                polyline.curveTo(1 + random.nextInt(session.getWidth()),//
                        1 + random.nextInt(session.getHeight()),//
                        1 + random.nextInt(session.getWidth()),//
                        1 + random.nextInt(session.getHeight()),//
                        1 + random.nextInt(session.getWidth()), //
                        1 + random.nextInt(session.getHeight()));
            }
        }
        polyline.closePath();
        return polyline;
    }

    public Color generateColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Override
    public void run() {
        Session session=Session.getInstance();
        while (true) {
            try {
                if (session.isRunning()) {
                    Graphics2D graphics2D = (Graphics2D) image.getGraphics();
                    graphics2D.setColor(generateColor());
                    graphics2D.fill(generateArbitary());
                    canvas.setImage(image);
                    canvas.repaint();
                }
                sleep(Session.getInstance().getSleepMilis());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
