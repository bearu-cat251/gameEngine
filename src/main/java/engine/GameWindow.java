package engine;

import engine.util.ImageLoader;
import engine.util.ImageProcessor;
import engine.util.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameEngine.setMousePressed(true);
                gameEngine.setStartMousePosition(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                gameEngine.setSelectedUnits();
                gameEngine.setMousePressed(false);
                gameEngine.setStartMousePosition(e.getX(), e.getY());


                int deltaX = Math.abs(gameEngine.getMouseDragX());
                int deltaY = Math.abs(gameEngine.getMouseDragY());
                if (deltaX < 5 && deltaY < 5) {
                    System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());

                } else {
                    System.out.println("Mouse drag ended.");
                    gameEngine.setSelecting(false);

                }
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if (gameEngine.isMousePressed()) {
                    gameEngine.updateMouseDrag(e.getX(), e.getY());

                }
            }
        });


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
                g.drawImage((intToImage(tileValue)), (j*64)-camX, (i*64)-camY, null);
            }


        }

        for(int i = 0; i<gameEngine.getUnits().size(); i++){
            int camX = gameEngine.getCameraX();
            int camY = gameEngine.getCameraY();
            Unit unit = gameEngine.getUnit(i);
            Image img = unit.getImage();
            int width = img.getWidth(null);
            g.drawImage(imageProcessor.getSubImage(img, currentFrame%(width/32)*32, 0, 32, 32), unit.getX()-camX, unit.getY()-camY, null);

        }
        if (gameEngine.checkMouseDrag()) {

            int startX = gameEngine.getMouseX();
            int startY = gameEngine.getMouseY();
            int currentX = gameEngine.getMouseX() + gameEngine.getMouseDragX();
            int currentY = gameEngine.getMouseY() + gameEngine.getMouseDragY();


            int x = Math.min(startX, currentX);
            int y = Math.min(startY, currentY);
            int width = Math.abs(currentX - startX);
            int height = Math.abs(currentY - startY);

            gameEngine.setSelectingArea(x, y, width, height);
            gameEngine.setSelecting(true);
            g.setColor(new Color(10, 255, 10, 255));
            g.drawRect(x, y, width, height);
            g.setColor(new Color(10, 255, 10, 60));
            g.fillRect(x+1, y+1, width-1, height-1);
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

