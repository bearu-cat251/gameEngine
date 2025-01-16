package engine.util;

import engine.GameEngine;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandle extends KeyAdapter {

    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean gPressed = false;

    public InputHandle(JFrame frame) {
        frame.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            wPressed = true;
            System.out.println("W key pressed");
        }
        if (keyCode == KeyEvent.VK_A) {
            aPressed = true;
            System.out.println("A key pressed");
        }
        if (keyCode == KeyEvent.VK_S) {
            sPressed = true;
            System.out.println("S key pressed");
        }
        if (keyCode == KeyEvent.VK_D) {
            dPressed = true;
            System.out.println("D key pressed");
        }
        if (keyCode == KeyEvent.VK_G) {
            gPressed = true;
            System.out.println("G key pressed");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            wPressed = false;
            System.out.println("W key released");
        }
        if (keyCode == KeyEvent.VK_A) {
            aPressed = false;
            System.out.println("A key released");
        }
        if (keyCode == KeyEvent.VK_S) {
            sPressed = false;
            System.out.println("S key released");
        }
        if (keyCode == KeyEvent.VK_D) {
            dPressed = false;
            System.out.println("D key released");
        }
        if (keyCode == KeyEvent.VK_G) {

            System.out.println("G key released");
        }
    }

    public boolean isWPressed() {
        return wPressed;
    }

    public boolean isAPressed() {
        return aPressed;
    }

    public boolean isSPressed() {
        return sPressed;
    }

    public boolean isDPressed() {
        return dPressed;
    }

    public boolean isGPressed() {
        return gPressed;
    }

}

