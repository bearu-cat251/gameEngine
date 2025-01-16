package engine;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        GameWindow gameWindow = new GameWindow(gameEngine);
        gameEngine.setGameWindow(gameWindow);
        gameEngine.run();
    }
}
