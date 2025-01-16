package engine;

import engine.util.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private GameWindow gameWindow;

    private static final int TARGET_FPS = 60;
    private static final int TARGET_UPS = 60;
    private static final long NANOSECONDS_IN_SECOND = 1_000_000_000L;

    private static long lastUpdateTime = System.nanoTime();
    private static long lastRenderTime = System.nanoTime();
    private static int fps = 0;
    private static int ups = 0;

    private final int camSpeed = 6;
    private int loops;

    private List<Unit> selectedUnits;

    private static long timePerUpdate = NANOSECONDS_IN_SECOND / TARGET_UPS;
    private static long timePerFrame = NANOSECONDS_IN_SECOND / TARGET_FPS;

    private int cameraX = 0,cameraY = 0;
    private UnitHandle unitHandle;
    private UnitAttributeHandle unitAttributeHandle;

    private InputHandle inputHandle;
    private boolean mousePressed = false;
    private int startMouseX, startMouseY, currentMouseX, currentMouseY;
    private int slcX = 0, slcY = 0, slcWidth = 0, slcHeight = 0;
    private boolean selecting = false;
    private int mouseX = 0, mouseY = 0;

    private ImageLoader imageLoader;

    public GameEngine() {

        imageLoader = new ImageLoader();

        getMapData("src/main/java/engine/res/map1test.json");
        Image image = imageLoader.getImage("unit_medium_generic.png");

        unitAttributeHandle = new UnitAttributeHandle();
        unitHandle = new UnitHandle();
        selectedUnits = new ArrayList<>();
        unitHandle.addUnit(new Unit("Infantry", 100, 50, 50, image));
        unitHandle.addUnit(new Unit("Infantry", 100, 100, 50, image));
        unitHandle.addUnit(new Unit("Infantry", 100, 150, 50, image));
        unitHandle.addUnit(new Unit("Infantry", 100, 500, 400, image));

    }

    public void setGameWindow(GameWindow gameWindow) {

        this.gameWindow = gameWindow;
        inputHandle = new InputHandle(gameWindow.getFrame());
    }

    public void run() {
        while (true) {
            long now = System.nanoTime();
            long updateDelta = now - lastUpdateTime;
            long renderDelta = now - lastRenderTime;


            if (updateDelta >= timePerUpdate) {
                update();
                lastUpdateTime = now;
                ups++;
            }


            if (renderDelta >= timePerFrame) {
                render();
                lastRenderTime = now;
                fps++;
            }


            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (now - lastUpdateTime >= NANOSECONDS_IN_SECOND) {
                fps = 0;
                ups = 0;
                lastUpdateTime = now;
            }
            loops++;
        }
    }

    public void update() {
        moveCamera();
        moveUnits(mouseX, mouseY);
     }


    public void render() {gameWindow.repaint();}

    public List getUnits() {return unitHandle.getUnits();}
    public Unit getUnit(int index) {return unitHandle.getUnit(index);}

    public MapData getMapData(String path) {
        ReadMap readMap = new ReadMap();
        MapData mapData = readMap.readMap(path);
        return mapData;
    }

    public int getCameraX() {return cameraX;}
    public int getCameraY() {return cameraY;}

    public void moveCamera() {
        if(inputHandle.isAPressed()) {cameraX = cameraX-camSpeed;}
        if(inputHandle.isDPressed()) {cameraX = cameraX+camSpeed;}
        if(inputHandle.isSPressed()) {cameraY = cameraY+camSpeed;}
        if(inputHandle.isWPressed()) {cameraY = cameraY-camSpeed;}
    }

    public void setMousePressed(boolean pressed) {this.mousePressed = pressed;}



    public boolean isMousePressed() {return mousePressed;}

    public void setStartMousePosition(int x, int y) {
        this.startMouseX = x;
        this.startMouseY = y;
        this.currentMouseX = x;
        this.currentMouseY = y;
    }

    public void updateMouseDrag(int x, int y) {
        this.currentMouseX = x;
        this.currentMouseY = y;
    }

    public void setSelectingArea(int x, int y, int width, int height) {
        slcX = x;
        slcY = y;
        slcWidth = width;
        slcHeight = height;
    }
    public void setSelecting(boolean selecting) {this.selecting = selecting;}
    public void setSelectedUnits() {
        for(int i = 0; i < unitHandle.getUnits().size(); i++) {
            int unitX = unitHandle.getUnitX(i);
            int unitY = unitHandle .getUnitY(i);
            if(unitX > slcX && unitY > slcY && unitX < slcX + slcWidth && unitY < slcY + slcHeight) {
                selectedUnits.add(unitHandle.getUnit(i));
                System.out.println(unitHandle.getUnit(i).getType());
            } else {if(!selectedUnits.isEmpty()) {selectedUnits.remove(unitHandle.getUnit(i));}}
        }

    }

    public boolean checkMouseDrag() {return mousePressed;}
    public int getMouseX() {return startMouseX;}
    public int getMouseY() {return startMouseY;}
    public int getMouseDragX() {return currentMouseX - startMouseX;}
    public int getMouseDragY() {return currentMouseY - startMouseY;}
    public void setMouseLocation(int x, int y) {
        mouseX = x;
        mouseY = y;
    }
    public void moveUnits(int goalX, int goalY) {
        if (inputHandle.isGPressed()) {
            for (int i = 0; i < selectedUnits.size(); i++) {
                int unitX = selectedUnits.get(i).getX();
                int unitY = selectedUnits.get(i).getY();
                int[] local = new int[9];
                int[][] groundLayer = getMapData("src/main/java/engine/res/map1test.json").getGroundLayer();
                int index = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        int neighborX = unitX + dx;
                        int neighborY = unitY + dy;

                        if (neighborX >= 0 && neighborX < groundLayer.length && neighborY >= 0 && neighborY < groundLayer[0].length) {
                            local[index] = groundLayer[neighborY][neighborX];
                        } else {local[index] = -1;}
                        index++;
                    }
                }
                for(int tile : local) {
                    System.out.println(tile + " ");
                }
                System.out.println(selectedUnits.get(i).getType() + " moved");
                selectedUnits.get(i).moveTo(goalX, goalY, local);
            }
        }
    }
}
