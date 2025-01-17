package engine.util;

import engine.GameEngine;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandle extends KeyAdapter {
    private GameEngine engine;
    public InputHandle(JFrame frame, GameEngine engine) {
        frame.addKeyListener(this);
        this.engine = engine;
    }

    @Override
    public void keyPressed(KeyEvent e) {engine.controls.setKeyPressed(e.getKeyCode());}
    @Override
    public void keyReleased(KeyEvent e) {engine.controls.setKeyReleased(e.getKeyCode());}


}

