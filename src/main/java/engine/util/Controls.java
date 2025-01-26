package engine.util;

import engine.GameEngine;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controls extends MouseAdapter {

    private int mouseX = 0;
    private int mouseY = 0;
    private int mouseDragX = 0;
    private int mouseDragY = 0;
    private int lastClickX, lastClickY;
    private boolean mousePressed;
    private GameEngine engine;
    private Camera camera;
    private boolean W = false, A = false, S = false, D = false;

    public Controls(JFrame frame, GameEngine engine) {
        this.engine = engine;
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
        camera = engine.getCamera();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseDragX = e.getX();
        mouseDragY = e.getY();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        lastClickX = e.getX();
        lastClickY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        camera.drag(camera.getX(), camera.getY());
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        mousePressed = false;
        camera.drag(camera.getX(), camera.getY());
        mouseDragX = 0;
        mouseDragY = 0;
    }

    public int getMouseX() {
        return camera.getMapX(mouseX);
    }

    public int getMouseY() {return camera.getMapY(mouseY);}

    public int getTotalDragX() {return camera.getMapX(mouseDragX + camera.getDragX());}

    public int getTotalDragY() {return camera.getMapY(mouseDragY + camera.getDragY());}
    public int getLastClickX() {return lastClickX;}
    public int getLastClickY() {return lastClickY;}
    public boolean getMousePressed() {return mousePressed;}


    public void setKeyPressed(int keyCode) {

        if(keyCode == KeyEvent.VK_W) {
            W = true;
        }
        if(keyCode == KeyEvent.VK_A) {
            A = true;
        }
        if(keyCode == KeyEvent.VK_S) {
            S = true;
        }
        if(keyCode == KeyEvent.VK_D) {
            D = true;
        }
        if(keyCode == KeyEvent.VK_G) {
            engine.gameLogic.moveUnits();
        }
    }

    public boolean getW() {return W;}
    public boolean getS() {return S;}
    public boolean getA() {return A;}
    public boolean getD() {return D;}
    public void setKeyReleased(int keyCode) {

        if(keyCode == KeyEvent.VK_W) {
            W = false;
        }
        if(keyCode == KeyEvent.VK_A) {
            A = false;
        }
        if(keyCode == KeyEvent.VK_S) {
            S = false;
        }
        if(keyCode == KeyEvent.VK_D) {
            D = false;
        }
    }
}

