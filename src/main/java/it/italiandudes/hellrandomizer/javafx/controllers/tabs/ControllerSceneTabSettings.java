package it.italiandudes.hellrandomizer.javafx.controllers.tabs;

import it.italiandudes.hellrandomizer.data.enums.*;
import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.javafx.alerts.ErrorAlert;
import it.italiandudes.hellrandomizer.javafx.alerts.InformationAlert;
import it.italiandudes.hellrandomizer.javafx.controllers.ControllerSceneMain;
import it.italiandudes.hellrandomizer.javafx.utils.Settings;
import it.italiandudes.hellrandomizer.javafx.utils.UIElementConfigurator;
import it.italiandudes.hellrandomizer.utils.Defs;
import it.italiandudes.idl.common.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.IOException;

public final class ControllerSceneTabSettings {

    // Main Link
    private ControllerSceneMain mainMenuController = null;
    private volatile boolean configurationComplete;
    public void setMainMenuController(@NotNull final ControllerSceneMain controller) {
        mainMenuController = controller;
    }
    public void configurationComplete() {
        configurationComplete = true;
    }

    // Constants
    @NotNull private static final Image TICK = new Image(Defs.Resources.getAsStream(JFXDefs.Resources.Image.IMAGE_TICK));
    @NotNull private static final Image CROSS = new Image(Defs.Resources.getAsStream(JFXDefs.Resources.Image.IMAGE_CROSS));

    // Attributes
    private boolean initializationCompleted = false;
    private final ObservableList<Stratagem> stratagems = FXCollections.observableArrayList();
    private final ObservableList<Stratagem> stratagemsEnabledBySuperEarth = FXCollections.observableArrayList();
    private final ObservableList<Booster> boosters = FXCollections.observableArrayList();
    private final ObservableList<Booster> boostersEnabledBySuperEarth = FXCollections.observableArrayList();

    // Graphic Elements
    @FXML private GridPane gridPaneSettings;
    @FXML private ToggleButton toggleButtonRandomizeEnemies;
    @FXML private ImageView imageViewToggleRandomizeEnemies;
    @FXML private ToggleButton toggleButtonBlacklistTerminids;
    @FXML private ImageView imageViewBlacklistTerminids;
    @FXML private ToggleButton toggleButtonBlacklistAutomatons;
    @FXML private ImageView imageViewBlacklistAutomatons;
    @FXML private ToggleButton toggleButtonBlacklistIlluminates;
    @FXML private ImageView imageViewBlacklistIlluminates;
    @FXML private ToggleButton toggleButtonRandomizeDifficulty;
    @FXML private ImageView imageViewToggleRandomizeDifficulty;
    @FXML private Spinner<Integer> spinnerMinDifficulty;
    @FXML private Spinner<Integer> spinnerMaxDifficulty;
    @FXML private ComboBox<ArmorRandomizationProcedure> comboBoxArmorRandomizationProcedure;
    @FXML private ComboBox<PrimaryWeaponRandomizationProcedure> comboBoxPrimaryWeaponRandomizationProcedure;
    @FXML private ComboBox<SecondaryWeaponRandomizationProcedure> comboBoxSecondaryWeaponRandomizationProcedure;
    @FXML private ComboBox<ThrowableWeaponRandomizationProcedure> comboBoxThrowableWeaponRandomizationProcedure;
    @FXML private ComboBox<StratagemsRandomizationProcedure> comboBoxStratagemsRandomizationProcedure;
    @FXML private ToggleButton toggleButtonRandomizeBoosters;
    @FXML private ImageView imageViewToggleRandomizeBoosters;
    @FXML private Button buttonRevertChanges;
    @FXML private Button buttonSaveChanges;

    @FXML private ListView<Stratagem> listViewStratagemsEnabledBySuperEarth;
    @FXML private ListView<Stratagem> listViewStratagems;
    @FXML private TextField textFieldSearchFromStratagemsEnabledBySuperEarth;
    @FXML private TextField textFieldSearchFromStratagemsList;

    @FXML private ListView<Booster> listViewBoostersEnabledBySuperEarth;
    @FXML private ListView<Booster> listViewBoosters;
    @FXML private TextField textFieldSearchFromBoostersEnabledBySuperEarth;
    @FXML private TextField textFieldSearchFromBoostersList;

    // Initialize
    @FXML
    private void initialize() {
        initializationCompleted = false;
        JFXDefs.startServiceTask(() -> {
            //noinspection StatementWithEmptyBody
            while (!configurationComplete);
            Platform.runLater(() -> {
                toggleButtonRandomizeEnemies.selectedProperty().addListener((observable, oldValue, newValue) -> imageViewToggleRandomizeEnemies.setImage(newValue?TICK:CROSS));
                toggleButtonRandomizeDifficulty.selectedProperty().addListener((observable, oldValue, newValue) -> imageViewToggleRandomizeDifficulty.setImage(newValue?TICK:CROSS));
                toggleButtonBlacklistTerminids.selectedProperty().addListener((observable, oldValue, newValue) -> imageViewBlacklistTerminids.setVisible(newValue));
                toggleButtonBlacklistAutomatons.selectedProperty().addListener((observable, oldValue, newValue) -> imageViewBlacklistAutomatons.setVisible(newValue));
                toggleButtonBlacklistIlluminates.selectedProperty().addListener((observable, oldValue, newValue) -> imageViewBlacklistIlluminates.setVisible(newValue));
                toggleButtonRandomizeBoosters.selectedProperty().addListener((observable, oldValue, newValue) -> imageViewToggleRandomizeBoosters.setImage(newValue?TICK:CROSS));
                spinnerMinDifficulty.getEditor().setTextFormatter(UIElementConfigurator.configureNewIntegerTextFormatter());
                spinnerMinDifficulty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
                spinnerMaxDifficulty.getEditor().setTextFormatter(UIElementConfigurator.configureNewIntegerTextFormatter());
                spinnerMaxDifficulty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
                comboBoxArmorRandomizationProcedure.getItems().addAll(ArmorRandomizationProcedure.values());
                comboBoxPrimaryWeaponRandomizationProcedure.getItems().addAll(PrimaryWeaponRandomizationProcedure.values());
                comboBoxSecondaryWeaponRandomizationProcedure.getItems().addAll(SecondaryWeaponRandomizationProcedure.values());
                comboBoxThrowableWeaponRandomizationProcedure.getItems().addAll(ThrowableWeaponRandomizationProcedure.values());
                comboBoxStratagemsRandomizationProcedure.getItems().addAll(StratagemsRandomizationProcedure.values());
                FilteredList<Stratagem> stratagemsEnabledBySuperEarth = new FilteredList<>(
                        this.stratagemsEnabledBySuperEarth,
                        stratagem -> true
                );
                listViewStratagemsEnabledBySuperEarth.setItems(stratagemsEnabledBySuperEarth);
                FilteredList<Stratagem> stratagems = new FilteredList<>(
                        this.stratagems,
                        stratagem -> true
                );
                listViewStratagems.setItems(stratagems);
                FilteredList<Booster> boostersEnabledBySuperEarth = new FilteredList<>(
                        this.boostersEnabledBySuperEarth,
                        booster -> true
                );
                listViewBoostersEnabledBySuperEarth.setItems(boostersEnabledBySuperEarth);
                FilteredList<Booster> boosters = new FilteredList<>(
                        this.boosters,
                        booster -> true
                );
                listViewBoosters.setItems(boosters);
                textFieldSearchFromStratagemsEnabledBySuperEarth.textProperty().addListener((observable, oldValue, newValue) -> ((FilteredList<Stratagem>) listViewStratagemsEnabledBySuperEarth.getItems()).setPredicate(stratagem -> stratagem.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromStratagemsList.textProperty().addListener((observable, oldValue, newValue) -> ((FilteredList<Stratagem>) listViewStratagems.getItems()).setPredicate(stratagem -> stratagem.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromBoostersEnabledBySuperEarth.textProperty().addListener((observable, oldValue, newValue) -> ((FilteredList<Booster>) listViewBoostersEnabledBySuperEarth.getItems()).setPredicate(booster -> booster.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromBoostersList.textProperty().addListener((observable, oldValue, newValue) -> ((FilteredList<Booster>) listViewBoosters.getItems()).setPredicate(booster -> booster.toString().toLowerCase().contains(newValue.toLowerCase())));
                loadDataFromSettings();
                spinnerMinDifficulty.getEditor().textProperty().addListener(observable -> stageChanges());
                spinnerMaxDifficulty.getEditor().textProperty().addListener(observable -> stageChanges());
                gridPaneSettings.setDisable(false);
                initializationCompleted = true;
            });
        });
    }

    // Methods
    private void enableChangesButton() {
        buttonRevertChanges.setDisable(false);
        buttonSaveChanges.setDisable(false);
        mainMenuController.getTabSettings().setText("Impostazioni *");
    }
    private void disableChangesButton() {
        buttonRevertChanges.setDisable(true);
        buttonSaveChanges.setDisable(true);
        mainMenuController.getTabSettings().setText("Impostazioni");
    }
    private void loadDataFromSettings() {
        toggleButtonRandomizeEnemies.setSelected(Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_ENEMY));
        JSONArray enemyBlacklist = Settings.getSettings().getJSONArray(Defs.SettingsKeys.ENEMY_BLACKLIST);
        boolean blacklistTerminids = false;
        boolean blacklistAutomatons = false;
        boolean blacklistIlluminates = false;
        for (int i=0; i < enemyBlacklist.length(); i++) {
            switch (enemyBlacklist.getInt(i)) {
                case 0:
                    blacklistTerminids = true;
                    break;

                case 1:
                    blacklistAutomatons = true;
                    break;

                case 2:
                    blacklistIlluminates = true;
                    break;
            }
        }
        toggleButtonBlacklistTerminids.setSelected(blacklistTerminids);
        toggleButtonBlacklistAutomatons.setSelected(blacklistAutomatons);
        toggleButtonBlacklistIlluminates.setSelected(blacklistIlluminates);
        toggleButtonRandomizeDifficulty.setSelected(Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_DIFFICULTY));
        spinnerMinDifficulty.getValueFactory().setValue(Settings.getSettings().getInt(Defs.SettingsKeys.MINIMUM_DIFFICULTY));
        spinnerMinDifficulty.getEditor().setText(spinnerMinDifficulty.getValue().toString());
        spinnerMaxDifficulty.getValueFactory().setValue(Settings.getSettings().getInt(Defs.SettingsKeys.MAXIMUM_DIFFICULTY));
        spinnerMaxDifficulty.getEditor().setText(spinnerMaxDifficulty.getValue().toString());
        comboBoxArmorRandomizationProcedure.getSelectionModel().select(Settings.getSettings().getInt(Defs.SettingsKeys.ARMOR_RANDOMIZATION_PROCEDURE));
        comboBoxPrimaryWeaponRandomizationProcedure.getSelectionModel().select(Settings.getSettings().getInt(Defs.SettingsKeys.PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE));
        comboBoxSecondaryWeaponRandomizationProcedure.getSelectionModel().select(Settings.getSettings().getInt(Defs.SettingsKeys.SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE));
        comboBoxThrowableWeaponRandomizationProcedure.getSelectionModel().select(Settings.getSettings().getInt(Defs.SettingsKeys.THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE));
        comboBoxStratagemsRandomizationProcedure.getSelectionModel().select(Settings.getSettings().getInt(Defs.SettingsKeys.STRATAGEMS_RANDOMIZATION_PROCEDURE));
        toggleButtonRandomizeBoosters.setSelected(Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_BOOSTERS));

        this.stratagemsEnabledBySuperEarth.clear();
        this.stratagems.clear();
        JSONArray stratagemsEnabledBySuperEarth = Settings.getSettings().getJSONArray(Defs.SettingsKeys.STRATAGEMS_ENABLED_BY_SUPER_EARTH);
        for (int i=0; i < stratagemsEnabledBySuperEarth.length(); i++) {
            this.stratagemsEnabledBySuperEarth.add(Stratagem.values()[stratagemsEnabledBySuperEarth.getInt(i)]);
        }
        this.stratagems.addAll(Stratagem.values());
        this.stratagems.removeAll(this.stratagemsEnabledBySuperEarth);

        this.boostersEnabledBySuperEarth.clear();
        this.boosters.clear();
        JSONArray boostersEnabledBySuperEarth = Settings.getSettings().getJSONArray(Defs.SettingsKeys.BOOSTERS_ENABLED_BY_SUPER_EARTH);
        for (int i=0; i < boostersEnabledBySuperEarth.length(); i++) {
            this.boostersEnabledBySuperEarth.add(Booster.values()[boostersEnabledBySuperEarth.getInt(i)]);
        }
        this.boosters.addAll(Booster.values());
        this.boosters.removeAll(this.boostersEnabledBySuperEarth);
    }

    // EDT
    @FXML
    private void stageChanges() {
        if (initializationCompleted) enableChangesButton();
    }
    @FXML
    private void revertChanges() {
        loadDataFromSettings();
        disableChangesButton();
    }
    @FXML
    private void addToStratagemsEnabledBySuperEarth() {}
    @FXML
    private void removeFromStratagemsEnabledBySuperEarth() {}
    @FXML
    private void addToBoostersEnabledBySuperEarth() {}
    @FXML
    private void removeFromBoostersEnabledBySuperEarth() {}
    @FXML
    private void saveChanges() {
        disableChangesButton();
        Settings.getSettings().put(Defs.SettingsKeys.RANDOMIZE_ENEMY, toggleButtonRandomizeEnemies.isSelected());
        JSONArray enemyBlacklist = new JSONArray();
        if (toggleButtonBlacklistTerminids.isSelected()) enemyBlacklist.put(0);
        if (toggleButtonBlacklistAutomatons.isSelected()) enemyBlacklist.put(1);
        if (toggleButtonBlacklistIlluminates.isSelected()) enemyBlacklist.put(2);
        if (enemyBlacklist.length() >= Enemy.values().length) {
            new ErrorAlert("ERRORE", "Errore di Inserimento", "La blacklist non deve contenere tutte le fazioni.");
        } else {
            Settings.getSettings().put(Defs.SettingsKeys.ENEMY_BLACKLIST, enemyBlacklist);
        }

        Settings.getSettings().put(Defs.SettingsKeys.RANDOMIZE_DIFFICULTY, toggleButtonRandomizeDifficulty.isSelected());
        int minDifficulty = Settings.getSettings().getInt(Defs.SettingsKeys.MINIMUM_DIFFICULTY);
        try {
            minDifficulty = Integer.parseInt(spinnerMinDifficulty.getEditor().getText());
        } catch (NumberFormatException e) {
            new ErrorAlert("ERRORE", "Errore di Inserimento", "La difficolta' minima deve essere un numero intero compreso tra 1 e 10.");
        }
        int maxDifficulty = Settings.getSettings().getInt(Defs.SettingsKeys.MAXIMUM_DIFFICULTY);
        try {
            maxDifficulty = Integer.parseInt(spinnerMaxDifficulty.getEditor().getText());
        } catch (NumberFormatException e) {
            new ErrorAlert("ERRORE", "Errore di Inserimento", "La difficolta' massima deve essere un numero intero compreso tra 1 e 10.");
        }
        if (maxDifficulty < minDifficulty) {
            new ErrorAlert("ERRORE", "Errore Logico", "La difficolta' minima deve essere minore o uguale a quella massima.");
        } else {
            Settings.getSettings().put(Defs.SettingsKeys.MINIMUM_DIFFICULTY, minDifficulty);
            Settings.getSettings().put(Defs.SettingsKeys.MAXIMUM_DIFFICULTY, maxDifficulty);
        }
        Settings.getSettings().put(Defs.SettingsKeys.ARMOR_RANDOMIZATION_PROCEDURE, comboBoxArmorRandomizationProcedure.getSelectionModel().getSelectedItem().ordinal());
        Settings.getSettings().put(Defs.SettingsKeys.PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE, comboBoxPrimaryWeaponRandomizationProcedure.getSelectionModel().getSelectedItem().ordinal());
        Settings.getSettings().put(Defs.SettingsKeys.SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE, comboBoxSecondaryWeaponRandomizationProcedure.getSelectionModel().getSelectedItem().ordinal());
        Settings.getSettings().put(Defs.SettingsKeys.THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE, comboBoxThrowableWeaponRandomizationProcedure.getSelectionModel().getSelectedItem().ordinal());
        Settings.getSettings().put(Defs.SettingsKeys.STRATAGEMS_RANDOMIZATION_PROCEDURE, comboBoxStratagemsRandomizationProcedure.getSelectionModel().getSelectedItem().ordinal());
        Settings.getSettings().put(Defs.SettingsKeys.RANDOMIZE_BOOSTERS, toggleButtonRandomizeBoosters.isSelected());
        JFXDefs.startServiceTask(() -> {
            try {
                Settings.writeJSONSettings();
                Platform.runLater(() -> new InformationAlert("SUCCESSO", "Salvataggio Modifiche", "Modifiche salvate con successo!"));
            } catch (IOException e) {
                Logger.log(e);
                Platform.runLater(() -> new ErrorAlert("ERRORE", "Errore di I/O", "Si e' verificato un errore durante il salvataggio delle modifiche."));
            }
        });
    }
}
