package it.italiandudes.hellrandomizer.javafx.controllers.tabs;

import it.italiandudes.hellrandomizer.data.HelldiverData;
import it.italiandudes.hellrandomizer.data.enums.*;
import it.italiandudes.hellrandomizer.javafx.Client;
import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.javafx.alerts.ErrorAlert;
import it.italiandudes.hellrandomizer.javafx.alerts.InformationAlert;
import it.italiandudes.hellrandomizer.javafx.controllers.ControllerSceneMain;
import it.italiandudes.hellrandomizer.utils.HelldiversDataManager;
import it.italiandudes.idl.common.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public final class ControllerSceneTabPlayersData {

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
    @NotNull private static final Image TICK = JFXDefs.Resources.Image.IMAGE_TICK;
    @NotNull private static final Image CROSS = JFXDefs.Resources.Image.IMAGE_CROSS;

    // Attributes
    private boolean initializationCompleted = false;
    @Nullable private String newHelldiver = null;

    @NotNull private final ObservableList<ArmorBuff> armorBuffs = FXCollections.observableArrayList();
    @NotNull private final FilteredList<ArmorBuff> filteredArmorBuffs =new FilteredList<>(
            this.armorBuffs,
            armorBuff -> true
    );

    @NotNull private final ObservableList<ArmorBuff> helldiverArmorBuffs = FXCollections.observableArrayList();
    @NotNull private final FilteredList<ArmorBuff> filteredHelldiverArmorBuffs =new FilteredList<>(
            this.helldiverArmorBuffs,
            armorBuff -> true
    );

    @NotNull private final ObservableList<PrimaryWeapon> primaryWeapons = FXCollections.observableArrayList();
    @NotNull private final FilteredList<PrimaryWeapon> filteredPrimaryWeapons =new FilteredList<>(
            this.primaryWeapons,
            primaryWeapon -> true
    );

    @NotNull private final ObservableList<PrimaryWeapon> helldiverPrimaryWeapons = FXCollections.observableArrayList();
    @NotNull private final FilteredList<PrimaryWeapon> filteredHelldiverPrimaryWeapons =new FilteredList<>(
            this.helldiverPrimaryWeapons,
            primaryWeapon -> true
    );

    @NotNull private final ObservableList<SecondaryWeapon> secondaryWeapons = FXCollections.observableArrayList();
    @NotNull private final FilteredList<SecondaryWeapon> filteredSecondaryWeapons =new FilteredList<>(
            this.secondaryWeapons,
            secondaryWeapon -> true
    );

    @NotNull private final ObservableList<SecondaryWeapon> helldiverSecondaryWeapons = FXCollections.observableArrayList();
    @NotNull private final FilteredList<SecondaryWeapon> filteredHelldiverSecondaryWeapons =new FilteredList<>(
            this.helldiverSecondaryWeapons,
            secondaryWeapon -> true
    );

    @NotNull private final ObservableList<ThrowableWeapon> throwableWeapons = FXCollections.observableArrayList();
    @NotNull private final FilteredList<ThrowableWeapon> filteredThrowableWeapons =new FilteredList<>(
            this.throwableWeapons,
            throwableWeapon -> true
    );

    @NotNull private final ObservableList<ThrowableWeapon> helldiverThrowableWeapons = FXCollections.observableArrayList();
    @NotNull private final FilteredList<ThrowableWeapon> filteredHelldiverThrowableWeapons =new FilteredList<>(
            this.helldiverThrowableWeapons,
            throwableWeapon -> true
    );

    @NotNull private final ObservableList<Stratagem> stratagems = FXCollections.observableArrayList();
    @NotNull private final FilteredList<Stratagem> filteredStratagems =new FilteredList<>(
            this.stratagems,
            stratagem -> true
    );
    @NotNull private final ObservableList<Stratagem> helldiverStratagems = FXCollections.observableArrayList();
    @NotNull private final FilteredList<Stratagem> filteredHelldiverStratagems = new FilteredList<>(
            this.helldiverStratagems,
            stratagem -> true
    );
    @NotNull private final ObservableList<Booster> boosters = FXCollections.observableArrayList();
    @NotNull private final FilteredList<Booster> filteredBoosters = new FilteredList<>(
            this.boosters,
            booster -> true
    );
    @NotNull private final ObservableList<Booster> helldiverBoosters = FXCollections.observableArrayList();
    @NotNull private final FilteredList<Booster> filteredHelldiverBoosters = new FilteredList<>(
            this.helldiverBoosters,
            booster -> true
    );

    // Graphic Elements
    @FXML private ListView<HelldiverData> listViewHelldiversData;

    @FXML private GridPane gridPaneHelldiversDataContainer;
    @FXML private GridPane gridPaneHelldiverData;

    @FXML private ToggleButton toggleButtonRandomizePlayer;
    @FXML private ImageView imageViewToggleRandomizePlayer;

    @FXML private TextField textFieldSearchFromHelldiverArmorBuffs;
    @FXML private TextField textFieldSearchFromArmorBuffsList;
    @FXML private ListView<ArmorBuff> listViewHelldiverArmorBuffs;
    @FXML private ListView<ArmorBuff> listViewArmorBuffs;

    @FXML private TextField textFieldSearchFromHelldiverPrimaryWeapons;
    @FXML private TextField textFieldSearchFromPrimaryWeaponsList;
    @FXML private ListView<PrimaryWeapon> listViewHelldiverPrimaryWeapons;
    @FXML private ListView<PrimaryWeapon> listViewPrimaryWeapons;

    @FXML private TextField textFieldSearchFromHelldiverSecondaryWeapons;
    @FXML private TextField textFieldSearchFromSecondaryWeaponsList;
    @FXML private ListView<SecondaryWeapon> listViewHelldiverSecondaryWeapons;
    @FXML private ListView<SecondaryWeapon> listViewSecondaryWeapons;

    @FXML private TextField textFieldSearchFromHelldiverThrowableWeapons;
    @FXML private TextField textFieldSearchFromThrowableWeaponsList;
    @FXML private ListView<ThrowableWeapon> listViewHelldiverThrowableWeapons;
    @FXML private ListView<ThrowableWeapon> listViewThrowableWeapons;

    @FXML private TextField textFieldSearchFromHelldiverStratagems;
    @FXML private TextField textFieldSearchFromStratagemsList;
    @FXML private ListView<Stratagem> listViewHelldiverStratagems;
    @FXML private ListView<Stratagem> listViewStratagems;

    @FXML private TextField textFieldSearchFromHelldiverBoosters;
    @FXML private TextField textFieldSearchFromBoostersList;
    @FXML private ListView<Booster> listViewHelldiverBoosters;
    @FXML private ListView<Booster> listViewBoosters;

    @FXML private Button buttonRevertChanges;
    @FXML private Button buttonSaveChanges;

    // Initialize
    @FXML
    private void initialize() {
        initializationCompleted = false;
        JFXDefs.startServiceTask(() -> {
            //noinspection StatementWithEmptyBody
            while (!configurationComplete);
            Platform.runLater(() -> {
                listViewHelldiversData.getItems().addAll(HelldiversDataManager.getHelldiversData());
                listViewHelldiversData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    revertChanges();
                    if (newValue != null) {
                        loadHelldiverData(newValue);
                        gridPaneHelldiversDataContainer.setVisible(true);
                    }
                    else gridPaneHelldiversDataContainer.setVisible(false);
                });

                toggleButtonRandomizePlayer.selectedProperty().addListener((observable, oldValue, newValue) -> imageViewToggleRandomizePlayer.setImage(newValue?TICK:CROSS));
                toggleButtonRandomizePlayer.setSelected(true);

                listViewHelldiverArmorBuffs.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewHelldiverArmorBuffs.setItems(filteredHelldiverArmorBuffs);
                listViewArmorBuffs.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewArmorBuffs.setItems(filteredArmorBuffs);

                listViewHelldiverPrimaryWeapons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewHelldiverPrimaryWeapons.setItems(filteredHelldiverPrimaryWeapons);
                listViewPrimaryWeapons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewPrimaryWeapons.setItems(filteredPrimaryWeapons);

                listViewHelldiverSecondaryWeapons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewHelldiverSecondaryWeapons.setItems(filteredHelldiverSecondaryWeapons);
                listViewSecondaryWeapons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewSecondaryWeapons.setItems(filteredSecondaryWeapons);

                listViewHelldiverThrowableWeapons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewHelldiverThrowableWeapons.setItems(filteredHelldiverThrowableWeapons);
                listViewThrowableWeapons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewThrowableWeapons.setItems(filteredThrowableWeapons);

                listViewHelldiverStratagems.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewHelldiverStratagems.setItems(filteredHelldiverStratagems);
                listViewStratagems.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewStratagems.setItems(filteredStratagems);

                listViewHelldiverBoosters.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewHelldiverBoosters.setItems(filteredHelldiverBoosters);
                listViewBoosters.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewBoosters.setItems(filteredBoosters);

                textFieldSearchFromHelldiverArmorBuffs.textProperty().addListener((observable, oldValue, newValue) -> filteredHelldiverArmorBuffs.setPredicate(armorBuff -> armorBuff.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromHelldiverPrimaryWeapons.textProperty().addListener((observable, oldValue, newValue) -> filteredHelldiverPrimaryWeapons.setPredicate(primaryWeapon -> primaryWeapon.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromHelldiverSecondaryWeapons.textProperty().addListener((observable, oldValue, newValue) -> filteredHelldiverSecondaryWeapons.setPredicate(secondaryWeapon -> secondaryWeapon.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromHelldiverThrowableWeapons.textProperty().addListener((observable, oldValue, newValue) -> filteredHelldiverThrowableWeapons.setPredicate(throwableWeapon -> throwableWeapon.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromHelldiverStratagems.textProperty().addListener((observable, oldValue, newValue) -> filteredHelldiverStratagems.setPredicate(stratagem -> stratagem.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromHelldiverBoosters.textProperty().addListener((observable, oldValue, newValue) -> filteredHelldiverBoosters.setPredicate(booster -> booster.toString().toLowerCase().contains(newValue.toLowerCase())));

                textFieldSearchFromArmorBuffsList.textProperty().addListener((observable, oldValue, newValue) -> filteredArmorBuffs.setPredicate(armorBuff -> armorBuff.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromPrimaryWeaponsList.textProperty().addListener((observable, oldValue, newValue) -> filteredPrimaryWeapons.setPredicate(primaryWeapon -> primaryWeapon.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromSecondaryWeaponsList.textProperty().addListener((observable, oldValue, newValue) -> filteredSecondaryWeapons.setPredicate(secondaryWeapon -> secondaryWeapon.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromThrowableWeaponsList.textProperty().addListener((observable, oldValue, newValue) -> filteredThrowableWeapons.setPredicate(throwableWeapon -> throwableWeapon.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromStratagemsList.textProperty().addListener((observable, oldValue, newValue) -> filteredStratagems.setPredicate(stratagem -> stratagem.toString().toLowerCase().contains(newValue.toLowerCase())));
                textFieldSearchFromBoostersList.textProperty().addListener((observable, oldValue, newValue) -> filteredBoosters.setPredicate(booster -> booster.toString().toLowerCase().contains(newValue.toLowerCase())));

                gridPaneHelldiverData.setDisable(false);
                initializationCompleted = true;
            });
        });
    }

    // Methods
    private void enableChangesButton() {
        buttonRevertChanges.setDisable(false);
        buttonSaveChanges.setDisable(false);
        mainMenuController.getTabPlayersData().setText("Dati Helldivers *");
    }
    private void disableChangesButton() {
        buttonRevertChanges.setDisable(true);
        buttonSaveChanges.setDisable(true);
        mainMenuController.getTabPlayersData().setText("Dati Helldivers");
    }
    private void createNewHelldiver(@NotNull final String name) {
        newHelldiver = name;
        gridPaneHelldiversDataContainer.setVisible(true);
        gridPaneHelldiverData.setDisable(true);
        toggleButtonRandomizePlayer.setSelected(false);

        this.helldiverArmorBuffs.clear();
        this.armorBuffs.setAll(ArmorBuff.values());

        this.helldiverPrimaryWeapons.clear();
        this.primaryWeapons.setAll(PrimaryWeapon.values());

        this.helldiverSecondaryWeapons.clear();
        this.secondaryWeapons.clear();
        this.secondaryWeapons.setAll(SecondaryWeapon.values());

        this.helldiverThrowableWeapons.clear();
        this.throwableWeapons.clear();
        this.throwableWeapons.setAll(ThrowableWeapon.values());

        this.helldiverStratagems.clear();
        this.stratagems.clear();
        this.stratagems.setAll(Stratagem.values());

        this.helldiverBoosters.clear();
        this.boosters.clear();
        this.boosters.setAll(Booster.values());
        gridPaneHelldiverData.setDisable(false);
        stageChanges();
    }
    private void loadHelldiverData(@NotNull final HelldiverData helldiverData) {
        gridPaneHelldiverData.setDisable(true);
        toggleButtonRandomizePlayer.setSelected(helldiverData.isEnrolledForRandomizer());

        this.helldiverArmorBuffs.setAll(helldiverData.getArmorBuffs());
        this.armorBuffs.setAll(ArmorBuff.values());
        this.armorBuffs.removeAll(this.helldiverArmorBuffs);

        this.helldiverPrimaryWeapons.setAll(helldiverData.getPrimaryWeapons());
        this.primaryWeapons.setAll(PrimaryWeapon.values());
        this.primaryWeapons.removeAll(this.helldiverPrimaryWeapons);

        this.helldiverSecondaryWeapons.setAll(helldiverData.getSecondaryWeapons());
        this.secondaryWeapons.setAll(SecondaryWeapon.values());
        this.secondaryWeapons.removeAll(this.helldiverSecondaryWeapons);

        this.helldiverThrowableWeapons.setAll(helldiverData.getThrowableWeapons());
        this.throwableWeapons.setAll(ThrowableWeapon.values());
        this.throwableWeapons.removeAll(this.helldiverThrowableWeapons);

        this.helldiverStratagems.setAll(helldiverData.getStratagems());
        this.stratagems.setAll(Stratagem.values());
        this.stratagems.removeAll(this.helldiverStratagems);

        this.helldiverBoosters.setAll(helldiverData.getBoosters());
        this.boosters.setAll(Booster.values());
        this.boosters.removeAll(this.helldiverBoosters);

        gridPaneHelldiverData.setDisable(false);
    }

    // EDT
    @FXML
    private void showHelldiversContextMenu(@NotNull final ContextMenuEvent event) {
        ContextMenu contextMenu = new ContextMenu();
        Menu addHelldiver = new Menu("Aggiungi Helldiver");
        MenuItem add = new MenuItem();
        TextField name = new TextField();
        name.setPromptText("Nome Helldiver");
        add.setGraphic(name);
        addHelldiver.getItems().add(add);
        contextMenu.getItems().add(addHelldiver);
        add.setOnAction(event1 -> {
            if (name.getText() == null || name.getText().replace("\t", "").replace(" ", "").isEmpty()) return;
            createNewHelldiver(name.getText());
        });

        HelldiverData data = listViewHelldiversData.getSelectionModel().getSelectedItem();
        if (data != null) {
            Menu renameHelldiver = new Menu("Rinomina Helldiver");
            MenuItem rename = new MenuItem();
            renameHelldiver.getItems().add(rename);
            TextField field = new TextField();
            field.setPromptText("Nuovo Nome");
            rename.setGraphic(field);
            rename.setOnAction(event1 -> {
                if (field.getText() == null || field.getText().replace("\t", "").replace(" ", "").isEmpty()) return;
                data.setName(field.getText());
                listViewHelldiversData.refresh();
                JFXDefs.startServiceTask(() -> {
                    try {
                        HelldiversDataManager.writeHelldiversDataFile();
                        Platform.runLater(() -> new InformationAlert("SUCCESSO", "Rinominazione Helldiver", "Rinominazione avvenuta con successo!"));
                    } catch (IOException e) {
                        Logger.log(e);
                        Platform.runLater(() -> new ErrorAlert("ERRORE", "Errore di I/O", "Si e' verificato un errore durante la scrittura dei dati."));
                    }
                });
            });
            contextMenu.getItems().add(renameHelldiver);

            MenuItem delete = new MenuItem("Elimina Helldiver");
            delete.setOnAction(event1 -> {
                HelldiversDataManager.getHelldiversData().remove(data);
                JFXDefs.startServiceTask(() -> {
                    try {
                        HelldiversDataManager.writeHelldiversDataFile();
                    } catch (IOException e) {
                        Logger.log(e);
                    }
                });
                listViewHelldiversData.getItems().remove(data);
            });
            contextMenu.getItems().add(delete);
        }

        contextMenu.setAutoHide(true);
        contextMenu.show(Client.getStage(), event.getScreenX(), event.getScreenY());
    }

    // EDT: Helldiver Lists
    @FXML
    private void addToHelldiverArmorBuffs() {
        ObservableList<ArmorBuff> selection = listViewArmorBuffs.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        helldiverArmorBuffs.addAll(selection);
        armorBuffs.removeAll(selection);
        stageChanges();
    }
    @FXML
    private void removeFromHelldiverArmorBuffs() {
        ObservableList<ArmorBuff> selection = listViewHelldiverArmorBuffs.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        armorBuffs.addAll(selection);
        helldiverArmorBuffs.removeAll(selection);
        stageChanges();
    }
    @FXML
    private void addToHelldiverPrimaryWeapons() {
        ObservableList<PrimaryWeapon> selection = listViewPrimaryWeapons.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        helldiverPrimaryWeapons.addAll(selection);
        primaryWeapons.removeAll(selection);
        stageChanges();
    }
    @FXML
    private void removeFromHelldiverPrimaryWeapons() {
        ObservableList<PrimaryWeapon> selection = listViewHelldiverPrimaryWeapons.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        primaryWeapons.addAll(selection);
        helldiverPrimaryWeapons.removeAll(selection);
        stageChanges();
    }

    @FXML
    private void addToHelldiverSecondaryWeapons() {
        ObservableList<SecondaryWeapon> selection = listViewSecondaryWeapons.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        helldiverSecondaryWeapons.addAll(selection);
        secondaryWeapons.removeAll(selection);
        stageChanges();
    }
    @FXML
    private void removeFromHelldiverSecondaryWeapons() {
        ObservableList<SecondaryWeapon> selection = listViewHelldiverSecondaryWeapons.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        secondaryWeapons.addAll(selection);
        helldiverSecondaryWeapons.removeAll(selection);
        stageChanges();
    }

    @FXML
    private void addToHelldiverThrowableWeapons() {
        ObservableList<ThrowableWeapon> selection = listViewThrowableWeapons.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        helldiverThrowableWeapons.addAll(selection);
        throwableWeapons.removeAll(selection);
        stageChanges();
    }
    @FXML
    private void removeFromHelldiverThrowableWeapons() {
        ObservableList<ThrowableWeapon> selection = listViewHelldiverThrowableWeapons.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        throwableWeapons.addAll(selection);
        helldiverThrowableWeapons.removeAll(selection);
        stageChanges();
    }

    @FXML
    private void addToHelldiverStratagems() {
        ObservableList<Stratagem> selection = listViewStratagems.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        helldiverStratagems.addAll(selection);
        stratagems.removeAll(selection);
        stageChanges();
    }
    @FXML
    private void removeFromHelldiverStratagems() {
        ObservableList<Stratagem> selection = listViewHelldiverStratagems.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        stratagems.addAll(selection);
        helldiverStratagems.removeAll(selection);
        stageChanges();
    }

    @FXML
    private void addToHelldiverBoosters() {
        ObservableList<Booster> selection = listViewBoosters.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        helldiverBoosters.addAll(selection);
        boosters.removeAll(selection);
        stageChanges();
    }
    @FXML
    private void removeFromHelldiverBoosters() {
        ObservableList<Booster> selection = listViewHelldiverBoosters.getSelectionModel().getSelectedItems();
        if (selection == null) return;
        boosters.addAll(selection);
        helldiverBoosters.removeAll(selection);
        stageChanges();
    }

    // EDT: Change
    @FXML
    private void stageChanges() {
        if (initializationCompleted) enableChangesButton();
    }
    @FXML
    private void revertChanges() {
        if (newHelldiver != null) createNewHelldiver(newHelldiver);
        else {
            HelldiverData data = listViewHelldiversData.getSelectionModel().getSelectedItem();
            if (data != null) loadHelldiverData(listViewHelldiversData.getSelectionModel().getSelectedItem());
            else gridPaneHelldiversDataContainer.setVisible(false);
            disableChangesButton();
        }
    }
    @FXML
    private void saveChanges() {
        disableChangesButton();
        if (newHelldiver != null) {
            HelldiverData newData = new HelldiverData(
                    newHelldiver, toggleButtonRandomizePlayer.isSelected(), ArmorCategory.values(),
                    false, helldiverArmorBuffs.toArray(new ArmorBuff[0]),
                    false, helldiverPrimaryWeapons.toArray(new PrimaryWeapon[0]),
                    false, helldiverSecondaryWeapons.toArray(new SecondaryWeapon[0]),
                    false, helldiverThrowableWeapons.toArray(new ThrowableWeapon[0]),
                    false, helldiverStratagems.toArray(new Stratagem[0]),
                    false, helldiverBoosters.toArray(new Booster[0])
            );
            HelldiversDataManager.getHelldiversData().add(newData);
            listViewHelldiversData.getItems().add(newData);
            listViewHelldiversData.getSelectionModel().select(newData);
            disableChangesButton();
        } else {
            HelldiverData helldiverData = listViewHelldiversData.getSelectionModel().getSelectedItem();
            if (helldiverData == null) {
                new ErrorAlert("ERRORE", "Errore di Salvataggio", "E' stato premuto il tasto di salvataggio senza però avere un helldiver selezionato.");
                newHelldiver = null;
                return;
            }
            helldiverData.setEnrolledForRandomizer(toggleButtonRandomizePlayer.isSelected());

            helldiverData.getArmorBuffs().removeIf(any -> true);
            helldiverData.getArmorBuffs().addAll(helldiverArmorBuffs);

            helldiverData.getPrimaryWeapons().removeIf(any -> true);
            helldiverData.getPrimaryWeapons().addAll(helldiverPrimaryWeapons);
            helldiverData.updatePrimaryWeaponTypes();

            helldiverData.getSecondaryWeapons().removeIf(any -> true);
            helldiverData.getSecondaryWeapons().addAll(helldiverSecondaryWeapons);
            helldiverData.updateSecondaryWeaponTypes();

            helldiverData.getThrowableWeapons().removeIf(any -> true);
            helldiverData.getThrowableWeapons().addAll(helldiverThrowableWeapons);
            helldiverData.updateThrowableWeaponTypes();

            helldiverData.getStratagems().removeIf(any -> true);
            helldiverData.getStratagems().addAll(helldiverStratagems);

            helldiverData.getBoosters().removeIf(any -> true);
            helldiverData.getBoosters().addAll(helldiverBoosters);
        }
        newHelldiver = null;
        JFXDefs.startServiceTask(() -> {
            try {
                HelldiversDataManager.writeHelldiversDataFile();
                Platform.runLater(() -> new InformationAlert("SUCCESSO", "Salvataggio Modifiche", "Modifiche salvate con successo!"));
            } catch (IOException e) {
                Platform.runLater(() -> new ErrorAlert("ERRORE", "Errore di I/O", "Si e' verificato un errore durante il salvataggio dei dati, chiusura applicazione..."));
                Logger.log(e);
                Client.exit(-1);
            }
        });
    }
}