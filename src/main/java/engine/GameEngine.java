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
    private int lastCamX = cameraX;
    private int lastCamY = cameraY;
    public UnitHandle unitHandle;
    private UnitAttributeHandle unitAttributeHandle;

    private InputHandle inputHandle;
    private boolean mousePressed = false;
    private int slcX = 0, slcY = 0, slcWidth = 0, slcHeight = 0;
    private boolean selecting = false;
    private int mouseX = 0, mouseY = 0;
    public Controls controls;
    public GameLogic gameLogic;

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
        inputHandle = new InputHandle(gameWindow.getFrame(), this);
        controls = new Controls(gameWindow.getFrame(), this);
        gameLogic = new GameLogic(this);

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
        gameLogic.update();
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
    public void setCameraX(int x) {lastCamX = cameraX; cameraX = x;}
    public void setCameraY(int y) {lastCamY = cameraY; cameraY = y;}
    public void changeCameraX(int speed) {cameraX = cameraX + speed;}
    public void changeCameraY(int speed) {cameraY = cameraY + speed;}

    public Controls getControls() {return controls;}

    public void setSelectingArea(int x, int y, int width, int height) {
        slcX = x - cameraX;
        slcY = y - cameraY;
        slcWidth = width;
        slcHeight = height;
    }

    public List<Unit> getSelectedUnits() {return selectedUnits;}
    public void setSelecting(boolean selecting) {this.selecting = selecting;}
    public void setSelectedUnits() {
        if(selecting) {
            for (int i = 0; i < unitHandle.getUnits().size(); i++) {
                int unitX = unitHandle.getUnitX(i) - cameraX;
                int unitY = unitHandle.getUnitY(i) - cameraY;
                if (unitX > slcX && unitY > slcY && unitX < slcX + slcWidth && unitY < slcY + slcHeight) {
                    selectedUnits.add(unitHandle.getUnit(i));
                    System.out.println(unitHandle.getUnit(i).getType());
                } else {
                    if (!selectedUnits.isEmpty()) {
                        selectedUnits.remove(unitHandle.getUnit(i));
                    }

                }

            }

        }

    }

}
