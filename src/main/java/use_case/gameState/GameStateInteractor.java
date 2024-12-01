package use_case.gameState;

import entity.*;
import kotlin.jvm.Throws;
import use_case.game.GameDataAccessInterface;
import use_case.game.GameOutputBoundary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Individualized logic for game interactions.
 */
public class GameStateInteractor implements GameStateInputBoundary {
    private final GameOutputBoundary gameOutputBoundary;
    private ArrayList<QuestionAnswer> questionsAnswers;
    private AbstractGame game;

    public GameStateInteractor(GameOutputBoundary gameOutputBoundary
    ) {
        this.gameOutputBoundary = gameOutputBoundary;
        this.game = null;
    }

    public GameStateInteractor(GameOutputBoundary gameOutputBoundary,
                               ArrayList<QuestionAnswer> questionsAnswers
                               ) {
        this.gameOutputBoundary = gameOutputBoundary;
        this.game = null;
        this.questionsAnswers = questionsAnswers;
    }

    /**
     * Returns the Game instance.
     *
     * @return The Game object.
     */
    @Override
    public AbstractGame getGame() {
        return game;
    }

    /**
     * Prepares for starting the game by getting the difficulty.
     */
    public void gatherDifficultyForGame() {
        gameOutputBoundary.prepareDifficultyView();
    }

    /**
     * Starts the game by preparing the first question view.
     */
    @Override
    public void startGame(int difficulty) {
        // Make the game based on what difficulty the game is
        switch (difficulty) {
            case 0:
                this.game = new EasyGame(questionsAnswers);
                break;
            case 1:
                this.game = new MediumGame(questionsAnswers);
                break;
            case 2:
                this.game = new HardGame(questionsAnswers);
                break;
            default:
                this.game = null;
                System.out.println("INVALID DIFFICULTY");
                break;
        }

        if (game != null) {
            this.updateGameStateWithNewDisplayAnimals();

            final QuestionAnswer firstQuestion = game.getCurrentQuestion();
            final Integer answerTime = game.getCurrentQuestionAnswerTime();
            gameOutputBoundary.prepareQuestionView(firstQuestion, answerTime);
        }
        else {
            gameOutputBoundary.prepareFailView("The game is not active");
        }
    }

    /**
     * Processes the user's answer and proceeds to the next question or ends the game.
     *
     * @param userAnswer The user's submitted answer.
     */
    @Override
    public void executeAnswerSubmit(String userAnswer) {
        if (game == null) {
            gameOutputBoundary.prepareFailView("The game is not active");
        }

        // Execute Answer Submit Logic
        final QuestionAnswer currentQuestionAnswer = game.getCurrentQuestion();
        currentQuestionAnswer.setUserAnswer(userAnswer);
        if (currentQuestionAnswer == null) {
            gameOutputBoundary.prepareEndGameView();
        }
        else {
            if (currentQuestionAnswer.validateAnswer()) {
                gameOutputBoundary.prepareAnswerResultView(currentQuestionAnswer);
                System.out.println("Updates Animal");
                game.updateQuestionAnswersCorrect(true);
                if (!(game instanceof EasyGame)) {
                    game.updateQuestionAnswerTimes(currentQuestionAnswer.getTimer().getTimeLimit()
                            - currentQuestionAnswer.getTimer().getRemainingTime());
                }
            }
            else {
                gameOutputBoundary.prepareAnswerResultView(currentQuestionAnswer);
                System.out.println("Calls updateQuestion False");
                game.updateQuestionAnswersCorrect(false);
                if (!(game instanceof EasyGame)) {
                    game.updateQuestionAnswerTimes(currentQuestionAnswer.getTimer().getTimeLimit()
                            - currentQuestionAnswer.getTimer().getRemainingTime());
                }
            }

        }
    }

    /**
     * either move to the next question from the reward page or go to the reward page
     * @param justSubmitted indicates the current page was the submit button, therefore we should test for reward page
     */
    public void moveAnswerToNextQuestion(boolean justSubmitted) {
        if (game == null) {
            gameOutputBoundary.prepareFailView("The game is not active");
        }

        // Execute Answer Submit Logic
        QuestionAnswer currentQuestionAnswer = game.getCurrentQuestion();

        // Move to rewards page
        if (currentQuestionAnswer.isCorrect() && justSubmitted) {
            this.updateGameStateWithNewDisplayAnimals();
            gameOutputBoundary.prepareAnimalRewardView();
        }
        // The call from Rewards page to go to the next question
        else if (!justSubmitted) {
            game.moveToNextQuestion();
            // check if game ended
            if (game.isGameFinished()) {
                gameOutputBoundary.prepareEndGameView();
            }
            else {
                currentQuestionAnswer = game.getCurrentQuestion();
                gameOutputBoundary.prepareQuestionView(currentQuestionAnswer, game.getCurrentQuestionAnswerTime());
            }
        }
        else if (!currentQuestionAnswer.isCorrect() && justSubmitted) {
            this.updateGameStateWithNewDisplayAnimals();

            // Incorrect so we need to go to the next question for easy and medium
            if (game instanceof EasyGame || game instanceof MediumGame) {
                game.moveToNextQuestion();

                // Check if game ended
                if (game.isGameFinished()) {
                    gameOutputBoundary.prepareEndGameView();
                }
                else {
                    // if not, go to the next question
                    currentQuestionAnswer = game.getCurrentQuestion();
                    gameOutputBoundary.prepareQuestionView(currentQuestionAnswer, game.getCurrentQuestionAnswerTime());
                }
            }
            // Incorrect for hard game ends everything
            else {
                gameOutputBoundary.prepareEndGameView();
            }
        }
    }

    /**
     * Function to increase animals currently in farm.
     */
    private void increaseAnimal() {
        game.updateQuestionAnswersCorrect(true);
    }

    /**
     * Function to decrease animals currently in farm.
     */
    private void decreaseAnimal() {
        game.updateQuestionAnswersCorrect(false);
    }

    private void updateGameStateWithNewDisplayAnimals() {
        gameOutputBoundary.setDisplayAnimalsToGameState(this.getCurrentListAnimalsToDisplay());
    }

    /**
     * Function to return current list of animals that we should display for each view in the background.
     * @return animalsShouldDisplay - the list of animals we should display on each view in the background.
     */
    private List<Animal> getCurrentListAnimalsToDisplay() {
        final List<Animal> currentAnimals = game.getAnimalFarm().getCurrentAnimals();
        return currentAnimals;
    }

    @Override
    public void setQuestionsAnswers(ArrayList<QuestionAnswer> questionsAnswers) {
        this.questionsAnswers = questionsAnswers;
    }

    /**
     * Gets the number of correctly answered questions.
     * @return the number of correct answers.
     */
    public int getScore() {
        return this.game.getScore();
    }

    /**
     * Gets the number of correctly answered questions.
     * @return the number of correct answers.
     */
    public int getAvgTime() {
        return this.game.getAvgTime();
    }

    /**
     * Gets the number of correctly answered questions.
     * @return the number of correct answers.
     */
    public int getTotalTime() {
        return this.game.getTotalTime();
    }
}
