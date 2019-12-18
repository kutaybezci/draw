package com.kutaybezci.draw;

/**
 *
 * @author kutay.bezci
 */
public class Session {
    private volatile long sleepMilis=500;
    private volatile int maxPointCount=50;
    private volatile boolean running=false;
    private volatile int width=500;
    private volatile int height=500;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getMaxPointCount() {
        return maxPointCount;
    }

    public void setMaxPointCount(int maxPointCount) {
        this.maxPointCount = maxPointCount;
    }

    public long getSleepMilis() {
        return sleepMilis;
    }

    public void setSleepMilis(long sleepMilis) {
        this.sleepMilis = sleepMilis;
    }
    
    private Session() {
        
    }
    
    public static Session getInstance() {
        return SessionHolder.INSTANCE;
    }
    
    private static class SessionHolder {

        private static final Session INSTANCE = new Session();
    }
}
