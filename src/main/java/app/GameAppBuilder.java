package app;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import interface_adapter.game.GameController;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameViewModel;
import use_case.game.GameDataAccessInterface;
import use_case.game.GameInteractor;
import use_case.game.GameOutputBoundary;
import view.GameView;

/**
 * Builder for the Game.
 */
public class GameAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
    private GameDataAccessInterface gameDao;
    private GameViewModel gameViewModel = new GameViewModel();
    private GameView gameView;
    private GameInteractor gameInteractor;
    private File file;

    /**
     * Sets the FileDAO to be used in this application.
     * @param gameDataAccess the DAO to use
     * @return this builder
     */
    public GameAppBuilder addGameDao(GameDataAccessInterface gameDataAccess) {
        gameDao = gameDataAccess;
        return this;
    }

    /**
     * Creates the objects for the File Use Case and connects the FileView to its
     * controller.
     * <p>This method must be called after addFileView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addFileView
     */
    public GameAppBuilder addGameUseCase() {
        final GameOutputBoundary gameOutputBoundary = new GamePresenter(gameViewModel);
        gameInteractor = new GameInteractor(
                gameDao, gameOutputBoundary);

        final GameController controller = new GameController(gameInteractor);
        if (gameView == null) {
            throw new RuntimeException("addFileView must be called before addFileUseCase");
        }
        gameView.setGameController(controller);
        return this;
    }

    /**
     * Creates the FileView and underlying FileViewModel.
     * @return this builder
     */
    public GameAppBuilder addGameView() {
        gameViewModel = new GameViewModel();
        gameView = new GameView(gameViewModel);
        return this;
    }

    /**
     * Builds the application.
     * @return the JFrame for the application
     */
    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("File Application");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(gameView);
        return frame;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
