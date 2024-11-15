package interface_adapter.game;

import java.io.File;

/**
 * The State for a File.
 * <p>For this example, ...</p>
 */
public class GameState {
    private File file;
    private String error;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
