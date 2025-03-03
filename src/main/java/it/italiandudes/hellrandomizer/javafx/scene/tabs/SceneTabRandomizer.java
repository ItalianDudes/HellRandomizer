package it.italiandudes.hellrandomizer.javafx.scene.tabs;

import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.javafx.components.SceneController;
import it.italiandudes.hellrandomizer.javafx.controllers.ControllerSceneMain;
import it.italiandudes.hellrandomizer.javafx.controllers.tabs.ControllerSceneTabRandomizer;
import it.italiandudes.hellrandomizer.utils.Defs;
import it.italiandudes.idl.common.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class SceneTabRandomizer {

    // Scene Generator
    @NotNull
    public static SceneController getScene(@NotNull final ControllerSceneMain mainMenuController) {
        try {
            FXMLLoader loader = new FXMLLoader(Defs.Resources.get(JFXDefs.Resources.FXML.Tabs.FXML_TAB_RANDOMIZER));
            Parent root = loader.load();
            ControllerSceneTabRandomizer controller = loader.getController();
            controller.setMainMenuController(mainMenuController);
            controller.configurationComplete();
            return new SceneController(root, controller);
        } catch (IOException e) {
            Logger.log(e);
            System.exit(-1);
            return null;
        }
    }
}
