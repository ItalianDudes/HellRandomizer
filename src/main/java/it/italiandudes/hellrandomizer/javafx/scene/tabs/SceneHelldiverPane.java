package it.italiandudes.hellrandomizer.javafx.scene.tabs;

import it.italiandudes.hellrandomizer.data.HelldiverData;
import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.javafx.components.SceneController;
import it.italiandudes.hellrandomizer.javafx.controllers.tabs.ControllerSceneHelldiverPane;
import it.italiandudes.hellrandomizer.javafx.controllers.tabs.ControllerSceneTabRandomizer;
import it.italiandudes.hellrandomizer.utils.Defs;
import it.italiandudes.idl.common.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class SceneHelldiverPane {

    // Scene Generator
    @NotNull
    public static SceneController getScene(@NotNull final ControllerSceneTabRandomizer tabRandomizerController, @NotNull final HelldiverData helldiverData) {
        try {
            FXMLLoader loader = new FXMLLoader(Defs.Resources.get(JFXDefs.Resources.FXML.Tabs.FXML_TAB_HELLDIVER_PANE));
            Parent root = loader.load();
            ControllerSceneHelldiverPane controller = loader.getController();
            controller.setRandomizerTabController(tabRandomizerController);
            controller.setHelldiverData(helldiverData);
            controller.configurationComplete();
            return new SceneController(root, controller);
        } catch (IOException e) {
            Logger.log(e);
            System.exit(-1);
            return null;
        }
    }
}
