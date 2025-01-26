package editor;

import javax.swing.*;

public class Editor {
    public static void main(String[] args) {
        Saver saver = new Saver();
        EditorWindow editorWindow = new EditorWindow();
        JFrame editorFrame = new JFrame();
        editorFrame.setVisible(true);
        editorFrame.add(editorWindow);
        editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorFrame.setResizable(false);
        editorFrame.pack();
    }
}
