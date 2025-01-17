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

    public Controls(JFrame frame, GameEngine engine) {
        this.engine = engine;
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
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
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        mousePressed = false;
        mouseDragX = 0;
        mouseDragY = 0;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMouseDragX() {
        return mouseDragX;
    }

    public int getMouseDragY() {
        return mouseDragY;
    }
    public int getLastClickX() {return lastClickX;}
    public int getLastClickY() {return lastClickY;}
    public boolean getMousePressed() {return mousePressed;}

    public void setKeyPressed(int keyCode) {

        if(keyCode == KeyEvent.VK_G) {
            engine.gameLogic.moveUnits();
        }
    }
    public void setKeyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_G) {

        }
    }
}
