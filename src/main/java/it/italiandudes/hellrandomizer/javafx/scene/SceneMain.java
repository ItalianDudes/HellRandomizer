package it.italiandudes.hellrandomizer.javafx.scene;

import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.javafx.components.SceneController;
import it.italiandudes.hellrandomizer.javafx.controllers.ControllerSceneMain;
import it.italiandudes.hellrandomizer.utils.Defs;
import it.italiandudes.idl.common.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class SceneMain {

    // Scene Generator
    @NotNull
    public static SceneController getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(Defs.Resources.get(JFXDefs.Resources.FXML.FXML_MAIN));
            Parent root = loader.load();
            ControllerSceneMain controller = loader.getController();
            return new SceneController(root, controller);
        } catch (IOException e) {
            Logger.log(e);
            System.exit(-1);
            return null;
        }
    }
}
