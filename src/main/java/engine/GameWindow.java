package engine;

import engine.util.ImageLoader;
import engine.util.ImageProcessor;
import engine.util.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GameWindow extends JPanel {

    private GameEngine gameEngine;
    private final String map1path = "src/main/java/engine/res/map1test.json";
    private final String pathToRes = "src/main/java/engine/res/";
    private JFrame frame;
    private int currentFrame = 1;
    private final String windowTitle = "Ascension Protocol v0.0.1";

    public GameWindow(GameEngine gameEngine) {


        this.gameEngine = gameEngine;
        this.setPreferredSize(new Dimension(1280, 720));

        createAndShowWindow();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }


    private void render(Graphics g) {

        ImageProcessor imageProcessor = new ImageProcessor();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        int[][] groundLayer = gameEngine.getMapData(map1path).getGroundLayer();

        for(int i = 0; i < gameEngine.getMapData(map1path).getGroundLayer().length; i++) {
            for (int j = 0; j < groundLayer[i].length; j++) {

                int camX = gameEngine.getCameraX();
                int camY = gameEngine.getCameraY();
                int tileValue = groundLayer[i][j];
                g.drawImage(imageProcessor.toBufferedImage(intToImage(tileValue)), (j*64)-camX, (i*64)-camY, null);
            }


        }

        for(int i = 0; i<gameEngine.getUnits().size(); i++){
            int camX = gameEngine.getCameraX();
            int camY = gameEngine.getCameraY();
            Unit unit = gameEngine.getUnit(i);
            Image img = unit.getImage();
            int width = img.getWidth(null);
            if(width != 0) {
                g.drawImage(imageProcessor.rotateImage(imageProcessor.toBufferedImage(imageProcessor.getSubImage(img, currentFrame % (width / 32) * 32, 0, 32, 32)), 30), unit.getX() - camX, unit.getY() - camY, null);
            }
        }

        if(gameEngine.controls.getMousePressed() && (gameEngine.controls.getTotalDragX() != 0 || gameEngine.controls.getTotalDragY() != 0)) {


            int startX = gameEngine.controls.getMouseX();
            int startY = gameEngine.controls.getMouseY();
            int currentX = gameEngine.controls.getTotalDragX();
            int currentY = gameEngine.controls.getTotalDragY();

            int x = Math.min(startX, currentX);
            int y = Math.min(startY, currentY);
            int width = Math.abs(currentX - startX);
            int height = Math.abs(currentY - startY);

            gameEngine.setSelectingArea(x, y, width, height);
            gameEngine.setSelecting(true);
            g.setColor(new Color(10, 255, 10, 255));
            g.drawRect(x, y, width, height);
            g.setColor(new Color(10, 255, 10, 60));
            g.fillRect(x + 1, y + 1, width - 1, height - 1);

        }

        currentFrame++;
    }


    public void createAndShowWindow() {

        frame = new JFrame(windowTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(this);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private Image intToImage(int tileType) {

        ImageLoader imageLoader = new ImageLoader();
        Image img1 = imageLoader.getImage("cyber_mix_top_right.png");
        Image img2 = imageLoader.getImage("cyber_mix_top.png");
        Image img3 = imageLoader.getImage("cyber_mix_top_left.png");
        Image img4 = imageLoader.getImage("cyber_mix_left.png");
        Image img5 = imageLoader.getImage("cyber_mix_bottom_left.png");
        Image img6 = imageLoader.getImage("cyber_mix_bottom.png");
        Image img7 = imageLoader.getImage("cyber_mix_bottom_right.png");
        Image img8 = imageLoader.getImage("cyber_mix_right.png");
        Image img9 = imageLoader.getImage("cyber_full_tile.png");
        Image img0 = imageLoader.getImage("cyber_ground_full_1.png");

        Map<Integer, Image> imgMap = Map.of(
                1, img1,
                2, img2,
                3, img3,
                4, img4,
                5, img5,
                6, img6,
                7, img7,
                8, img8,
                9, img9,
                0, img0
        );
        return imgMap.get(tileType);
    }

    public JFrame getFrame() {return frame;}
}

