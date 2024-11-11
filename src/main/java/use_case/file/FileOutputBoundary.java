package use_case.file;

import java.io.File;

/**
 * The output boundary for the Login Use Case.
 */
public interface FileOutputBoundary {
    /**
     * Prepares the success view for the File related Use Cases.
     * @param message the output data
     */
    void prepareSuccessView(File message);

    /**
     * Prepares the failure view for the File related Use Cases.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
