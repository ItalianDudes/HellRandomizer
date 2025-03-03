package it.italiandudes.hellrandomizer.javafx.scene.tabs;

import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.javafx.components.SceneController;
import it.italiandudes.hellrandomizer.javafx.controllers.ControllerSceneMain;
import it.italiandudes.hellrandomizer.javafx.controllers.tabs.ControllerSceneTabPlayersData;
import it.italiandudes.hellrandomizer.utils.Defs;
import it.italiandudes.idl.common.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class SceneTabPlayersData {

    // Scene Generator
    @NotNull
    public static SceneController getScene(@NotNull final ControllerSceneMain mainController) {
        try {
            FXMLLoader loader = new FXMLLoader(Defs.Resources.get(JFXDefs.Resources.FXML.Tabs.FXML_TAB_PLAYERS_DATA));
            Parent root = loader.load();
            ControllerSceneTabPlayersData controller = loader.getController();
            controller.setMainMenuController(mainController);
            controller.configurationComplete();
            return new SceneController(root, controller);
        } catch (IOException e) {
            Logger.log(e);
            System.exit(-1);
            return null;
        }
    }
}
