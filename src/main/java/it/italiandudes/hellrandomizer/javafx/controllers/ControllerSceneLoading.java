package it.italiandudes.hellrandomizer.javafx.controllers;

import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public final class ControllerSceneLoading {

    // Graphic Elements
    @FXML private ImageView imageViewLoading;

    //Initialize
    @FXML
    private void initialize() {
        imageViewLoading.setImage(JFXDefs.Resources.GIF.GIF_LOADING);
    }
}