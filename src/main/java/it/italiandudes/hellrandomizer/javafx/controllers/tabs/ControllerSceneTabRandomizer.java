package it.italiandudes.hellrandomizer.javafx.controllers.tabs;

import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.javafx.controllers.ControllerSceneMain;
import javafx.fxml.FXML;
import org.jetbrains.annotations.NotNull;

public final class ControllerSceneTabRandomizer {

    // Main Link
    private ControllerSceneMain mainMenuController = null;
    private volatile boolean configurationComplete;
    public void setMainMenuController(@NotNull final ControllerSceneMain controller) {
        mainMenuController = controller;
    }
    public void configurationComplete() {
        configurationComplete = true;
    }

    // Graphic Elements

    // Initialize
    @FXML
    private void initialize() {
        JFXDefs.startServiceTask(() -> {
            //noinspection StatementWithEmptyBody
            while (!configurationComplete);
            postInitialize();
        });
    }
    private void postInitialize() { // Non-EDT Initialize
    }

    // Methods
    private void resetController() {
    }

    // EDT
}
