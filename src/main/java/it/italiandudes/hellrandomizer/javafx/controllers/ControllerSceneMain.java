package it.italiandudes.hellrandomizer.javafx.controllers;

import it.italiandudes.hellrandomizer.javafx.components.SceneController;
import it.italiandudes.hellrandomizer.javafx.controllers.tabs.ControllerSceneTabPlayersData;
import it.italiandudes.hellrandomizer.javafx.controllers.tabs.ControllerSceneTabRandomizer;
import it.italiandudes.hellrandomizer.javafx.controllers.tabs.ControllerSceneTabSettings;
import it.italiandudes.hellrandomizer.javafx.scene.tabs.SceneTabPlayersData;
import it.italiandudes.hellrandomizer.javafx.scene.tabs.SceneTabRandomizer;
import it.italiandudes.hellrandomizer.javafx.scene.tabs.SceneTabSettings;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import org.jetbrains.annotations.NotNull;

public final class ControllerSceneMain {

    // Graphic Elements
    @FXML private Tab tabRandomizer;
    @FXML private Tab tabPlayersData;
    @FXML private Tab tabSettings;

    // Scene Controllers
    private SceneController sceneControllerTabRandomizer;
    private SceneController sceneControllerTabPlayersData;
    private SceneController sceneControllerTabSettings;

    // Initialize
    @FXML
    private void initialize() {
        sceneControllerTabRandomizer = SceneTabRandomizer.getScene(this);
        tabRandomizer.setContent(sceneControllerTabRandomizer.getParent());
        sceneControllerTabPlayersData = SceneTabPlayersData.getScene(this);
        tabPlayersData.setContent(sceneControllerTabPlayersData.getParent());
        sceneControllerTabSettings = SceneTabSettings.getScene(this);
        tabSettings.setContent(sceneControllerTabSettings.getParent());
    }

    // Methods
    @NotNull
    public Tab getTabRandomizer() {
        return tabRandomizer;
    }
    @NotNull
    public Tab getTabPlayersData() {
        return tabPlayersData;
    }
    @NotNull
    public Tab getTabSettings() {
        return tabSettings;
    }
    @NotNull
    public ControllerSceneTabRandomizer getTabRandomizerController() {
        return (ControllerSceneTabRandomizer) sceneControllerTabRandomizer.getController();
    }
    @NotNull
    public ControllerSceneTabPlayersData getTabPlayersDataController() {
        return (ControllerSceneTabPlayersData) sceneControllerTabPlayersData.getController();
    }
    @NotNull
    public ControllerSceneTabSettings getTabSettingsController() {
        return (ControllerSceneTabSettings) sceneControllerTabSettings.getController();
    }
}
