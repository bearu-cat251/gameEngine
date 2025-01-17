package engine.util;

import engine.GameEngine;

public class GameLogic {

    private GameEngine engine;

    public GameLogic(GameEngine engine) {
        this.engine = engine;
    }

    public void update() {
        //moveCamera();
    }

    public void moveCamera(int x, int y) {

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
