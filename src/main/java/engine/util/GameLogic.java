package engine.util;

import engine.GameEngine;

public class GameLogic {

    private GameEngine engine;
    private Controls controls;

    public GameLogic(GameEngine engine) {
        this.engine = engine;
        controls = engine.controls;
    }

    public void update() {
        moveCamera(controls.getW(), controls.getS(), controls.getD(), controls.getA());
    }

    public void moveCamera(boolean up, boolean down, boolean right, boolean left) {
        System.out.printf("camX: " + engine.getCameraX() + ", CamY:" + engine.getCameraY());
        if(up) {engine.changeCameraY(-10);}
        if(down) {engine.changeCameraY(10);}
        if(right) {engine.changeCameraX(10);}
        if(left) {engine.changeCameraX(-10);}
    }

    public void moveUnits() {
        engine.setSelectedUnits();
        for (int i = 0; i < engine.getSelectedUnits().size(); i++) {
            System.out.println(engine.getSelectedUnits().get(i).getType());
        }
        System.out.println("Units Moved");
        System.out.println("Mouse X, Y: " + engine.controls.getMouseX() + ", " + engine.controls.getMouseY());
        engine.unitHandle.moveUnits(engine.getSelectedUnits(), engine.controls.getMouseX() - engine.getCameraX(), engine.controls.getMouseY()- engine.getCameraY(), engine.getMapData("src/main/java/engine/res/map1test.json"));
    }

}
