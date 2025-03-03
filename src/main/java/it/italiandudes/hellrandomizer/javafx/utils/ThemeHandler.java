package it.italiandudes.hellrandomizer.javafx.utils;

import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.utils.Defs;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

public final class ThemeHandler {

    // Theme Instance
    private static final String THEME = Defs.Resources.get(JFXDefs.Resources.CSS.THEME).toExternalForm();

    // Theme Loader
    public static void loadTheme(@NotNull final Scene scene) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(THEME);
    }
}
