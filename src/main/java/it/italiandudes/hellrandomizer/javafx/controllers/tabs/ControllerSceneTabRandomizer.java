package it.italiandudes.hellrandomizer.javafx.controllers.tabs;

import it.italiandudes.hellrandomizer.data.HelldiverData;
import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.javafx.components.SceneController;
import it.italiandudes.hellrandomizer.javafx.controllers.ControllerSceneMain;
import it.italiandudes.hellrandomizer.javafx.scene.tabs.SceneHelldiverPane;
import it.italiandudes.hellrandomizer.utils.Defs;
import it.italiandudes.hellrandomizer.utils.HelldiversDataManager;
import it.italiandudes.hellrandomizer.utils.Randomizer;
import it.italiandudes.hellrandomizer.utils.Settings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class ControllerSceneTabRandomizer {

    // Main Link
    private ControllerSceneMain mainMenuController = null;
    private volatile boolean configurationComplete;
    public void setMainMenuController(@NotNull final ControllerSceneMain controller) {
        mainMenuController = controller;
    }
    public @NotNull ControllerSceneMain getMainMenuController() {
        return mainMenuController;
    }
    public void configurationComplete() {
        configurationComplete = true;
    }

    // Constants
    @NotNull private static final Image PLAY = new Image(JFXDefs.Resources.Image.IMAGE_PLAY);
    @NotNull private static final Image STOP = new Image(JFXDefs.Resources.Image.IMAGE_STOP);

    // Graphic Elements
    @FXML private VBox vBoxHelldiversContainer;
    @FXML private ToggleButton toggleButtonRandomizeAll;
    @FXML private ImageView imageViewToggleRandomizeAll;
    @FXML private Label labelEnemy;
    @FXML private Label labelDifficulty;

    // Attributes
    @NotNull private final ArrayList<@NotNull SceneController> helldiversSceneControllers = new ArrayList<>();
    private List<@NotNull SceneController> boostersSortedHelldiversSceneControllers = null;

    // Initialize
    @FXML
    private void initialize() {
        toggleButtonRandomizeAll.selectedProperty().addListener((observable, oldValue, newValue) -> imageViewToggleRandomizeAll.setImage(newValue?STOP:PLAY));
        JFXDefs.startServiceTask(() -> {
            //noinspection StatementWithEmptyBody
            while (!configurationComplete);
            Platform.runLater(this::loadHelldiverScenes);
        });
    }

    // Methods
    public void loadHelldiverScenes() {
        vBoxHelldiversContainer.getChildren().removeIf(node -> node instanceof AnchorPane);
        helldiversSceneControllers.clear();
        for (HelldiverData helldiverData : getHelldiversEnrolledForRandomizer()) {
            SceneController newHelldiver = SceneHelldiverPane.getScene(this, helldiverData);
            helldiversSceneControllers.add(newHelldiver);
            vBoxHelldiversContainer.getChildren().add(newHelldiver.getParent());
        }
        boostersSortedHelldiversSceneControllers = helldiversSceneControllers.stream().sorted((o1, o2) -> {
            ControllerSceneHelldiverPane p1 = (ControllerSceneHelldiverPane) o1.getController();
            ControllerSceneHelldiverPane p2 = (ControllerSceneHelldiverPane) o2.getController();
            return Integer.compare(p2.getHelldiverData().getBoosters().size(), p1.getHelldiverData().getBoosters().size()) * -1;
        }).collect(Collectors.toList());
    }
    @NotNull
    public List<@NotNull HelldiverData> getHelldiversEnrolledForRandomizer() {
        return HelldiversDataManager.getHelldiversData().stream()
                .filter(HelldiverData::isEnrolledForRandomizer)
                .sorted(Comparator.comparing(HelldiverData::getName))
                .collect(Collectors.toList());
    }
    private void randomizeAll() {
        randomizeEnemy();
        randomizeDifficulty();
        for (SceneController sceneHelldiverPane : boostersSortedHelldiversSceneControllers) {
            ((ControllerSceneHelldiverPane) sceneHelldiverPane.getController()).randomizeAll();
        }
    }

    // EDT
    @FXML
    private void toggleRandomizeAll() {
        if (!toggleButtonRandomizeAll.isSelected()) return;
        randomizeAll();
        if (toggleButtonRandomizeAll.isSelected()) Platform.runLater(this::toggleRandomizeAll);
    }
    @FXML
    private void randomizeEnemy() {
        if (Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_ENEMY)) {
            labelEnemy.setText(Randomizer.randomizeEnemy().toString());
        } else labelEnemy.setText("A Scelta");
    }
    @FXML
    private void randomizeDifficulty() {
        if (Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_DIFFICULTY)) {
            labelDifficulty.setText(Randomizer.randomizeDifficulty().toString());
        } else labelDifficulty.setText("A Scelta");
    }
}
