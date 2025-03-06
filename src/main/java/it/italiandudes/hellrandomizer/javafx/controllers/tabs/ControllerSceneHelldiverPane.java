package it.italiandudes.hellrandomizer.javafx.controllers.tabs;

import it.italiandudes.hellrandomizer.data.HelldiverData;
import it.italiandudes.hellrandomizer.data.enums.*;
import it.italiandudes.hellrandomizer.javafx.JFXDefs;
import it.italiandudes.hellrandomizer.utils.Defs;
import it.italiandudes.hellrandomizer.utils.Randomizer;
import it.italiandudes.hellrandomizer.utils.Settings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@SuppressWarnings("unused")
public final class ControllerSceneHelldiverPane {

    // Main Link
    private ControllerSceneTabRandomizer randomizerTabController = null;
    private HelldiverData helldiverData;
    private volatile boolean configurationComplete;
    public void setRandomizerTabController(@NotNull final ControllerSceneTabRandomizer controller) {
        randomizerTabController = controller;
    }
    public @NotNull ControllerSceneTabRandomizer getRandomizerTabController() {
        return randomizerTabController;
    }
    public void setHelldiverData(@NotNull final HelldiverData helldiverData) {
        this.helldiverData = helldiverData;
    }
    public @NotNull HelldiverData getHelldiverData() {
        return helldiverData;
    }
    public void configurationComplete() {
        configurationComplete = true;
    }

    // Attributes
    private Booster randomizedBooster = null;

    // Graphic Elements
    @FXML private Label labelArmor;
    @FXML private Label labelPrimaryWeapon;
    @FXML private Label labelSecondaryWeapon;
    @FXML private Label labelThrowableWeapon;
    @FXML private Label labelHelldiverName;
    @FXML private Label labelHelldiverArmor;
    @FXML private Label labelHelldiverPrimaryWeapon;
    @FXML private Label labelHelldiverSecondaryWeapon;
    @FXML private Label labelHelldiverThrowableWeapon;
    @FXML private ListView<Stratagem> listViewHelldiverStratagems;
    @FXML private Label labelHelldiverBooster;

    // Initialize
    @FXML
    private void initialize() {
        // resetFields();
        JFXDefs.startServiceTask(() -> {
            //noinspection StatementWithEmptyBody
            while (!configurationComplete);
            Platform.runLater(() -> setHelldiverName(helldiverData.getName()));
        });
    }

    // Methods
    public void setHelldiverName(@NotNull final String name) {
        labelHelldiverName.setText(name);
    }
    public void setHelldiverArmorCategory(@NotNull final ArmorCategory armorCategory) {
        labelArmor.setText("Categoria Armatura:");
        labelHelldiverArmor.setText(armorCategory.toString());
    }
    public void setHelldiverArmorBuff(@NotNull final ArmorBuff armorBuff) {
        labelArmor.setText("Effetto Armatura:");
        labelHelldiverArmor.setText(armorBuff.toString());
    }
    public void setPrimaryWeaponCategory(@NotNull final PrimaryWeaponCategory primaryWeaponCategory) {
        labelPrimaryWeapon.setText("Categoria Arma Primaria:");
        labelHelldiverPrimaryWeapon.setText(primaryWeaponCategory.toString());
    }
    public void setPrimaryWeapon(@Nullable final PrimaryWeapon primaryWeapon) {
        labelPrimaryWeapon.setText("Arma Primaria:");
        labelHelldiverPrimaryWeapon.setText(primaryWeapon != null ? primaryWeapon.toString() : "A Scelta");
    }
    public void setSecondaryWeaponCategory(@NotNull final SecondaryWeaponCategory secondaryWeaponCategory) {
        labelSecondaryWeapon.setText("Categoria Arma Secondaria:");
        labelHelldiverSecondaryWeapon.setText(secondaryWeaponCategory.toString());
    }
    public void setSecondaryWeapon(@Nullable final SecondaryWeapon secondaryWeapon) {
        labelSecondaryWeapon.setText("Arma Secondaria:");
        labelHelldiverSecondaryWeapon.setText(secondaryWeapon != null ? secondaryWeapon.toString() : "A Scelta");
    }
    public void setThrowableWeaponCategory(@NotNull final ThrowableWeaponCategory throwableWeaponCategory) {
        labelThrowableWeapon.setText("Categoria Arma Lanciabile:");
        labelHelldiverThrowableWeapon.setText(throwableWeaponCategory.toString());
    }
    public void setThrowableWeapon(@Nullable final ThrowableWeapon throwableWeapon) {
        labelThrowableWeapon.setText("Arma Lanciabile:");
        labelHelldiverThrowableWeapon.setText(throwableWeapon != null ? throwableWeapon.toString() : "A Scelta");
    }
    public void setStratagems(@NotNull final ArrayList<Stratagem> stratagems) {
        listViewHelldiverStratagems.getItems().clear();
        listViewHelldiverStratagems.getItems().addAll(stratagems);
    }
    public void setBooster(@Nullable final Booster booster) {
        if (booster != null) {
            randomizedBooster = booster;
            labelHelldiverBooster.setText(booster.toString());
        } else labelHelldiverBooster.setText("Vuoto");
        labelHelldiverBooster.setText(booster != null ? booster.toString() : "Vuoto");
    }

    // EDT
    @FXML
    private void randomizeArmor() {
        switch (Settings.getSettings().getInt(Defs.SettingsKeys.ARMOR_RANDOMIZATION_PROCEDURE)) {
            case 0: // No Randomization
                labelArmor.setText("Armatura:");
                labelHelldiverArmor.setText("A Scelta");
                break;

            case 1: // Category
                setHelldiverArmorCategory(Randomizer.randomizeArmorCategory(helldiverData));
                break;

            case 2: // Buff
                setHelldiverArmorBuff(Randomizer.randomizeArmorBuff(helldiverData));
                break;

            case 3: // Category or Buff
                if (Randomizer.randomBetween(1, 100) % 2 == 0) setHelldiverArmorCategory(Randomizer.randomizeArmorCategory(helldiverData));
                else setHelldiverArmorBuff(Randomizer.randomizeArmorBuff(helldiverData));
                break;
        }
    }
    @FXML
    private void randomizePrimary() {
        switch (Settings.getSettings().getInt(Defs.SettingsKeys.PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE)) {
            case 0: // No Randomization
                setPrimaryWeapon(null);
                break;

            case 1: // Category
                setPrimaryWeaponCategory(Randomizer.randomizePrimaryWeaponCategory(helldiverData));
                break;

            case 2: // Weapon
                setPrimaryWeapon(Randomizer.randomizePrimaryWeapon(helldiverData));
                break;
        }
    }
    @FXML
    private void randomizeSecondary() {
        switch (Settings.getSettings().getInt(Defs.SettingsKeys.SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE)) {
            case 0: // No Randomization
                setSecondaryWeapon(null);
                break;

            case 1: // Category
                setSecondaryWeaponCategory(Randomizer.randomizeSecondaryWeaponCategory(helldiverData));
                break;

            case 2: // Weapon
                setSecondaryWeapon(Randomizer.randomizeSecondaryWeapon(helldiverData));
                break;
        }
    }
    @FXML
    private void randomizeThrowable() {
        switch (Settings.getSettings().getInt(Defs.SettingsKeys.THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE)) {
            case 0: // No Randomization
                setThrowableWeapon(null);
                break;

            case 1: // Category
                setThrowableWeaponCategory(Randomizer.randomizeThrowableWeaponCategory(helldiverData));
                break;

            case 2: // Weapon
                setThrowableWeapon(Randomizer.randomizeThrowableWeapon(helldiverData));
                break;
        }
    }
    @FXML
    private void randomizeStratagems() {
        setStratagems(Randomizer.randomizeStratagems(helldiverData));
    }
    @FXML
    private void randomizeBooster() {
        if (randomizedBooster != null) {
            Randomizer.removeBoosterFromBoostersHistory(randomizedBooster);
            randomizedBooster = null;
        }
        setBooster(Randomizer.randomizeBooster(helldiverData));
    }
    @FXML
    public void randomizeAll() {
        System.out.println(helldiverData.getName());
        randomizeArmor();
        randomizePrimary();
        randomizeSecondary();
        randomizeThrowable();
        randomizeStratagems();
        randomizeBooster();
    }
}
