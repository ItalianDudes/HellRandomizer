package it.italiandudes.hellrandomizer;

import it.italiandudes.hellrandomizer.javafx.Client;
import it.italiandudes.hellrandomizer.utils.Defs;
import it.italiandudes.hellrandomizer.data.HelldiverData;
import it.italiandudes.hellrandomizer.utils.HelldiversDataManager;
import it.italiandudes.hellrandomizer.utils.Randomizer;
import it.italiandudes.hellrandomizer.data.enums.Booster;
import it.italiandudes.hellrandomizer.data.enums.Enemy;
import it.italiandudes.hellrandomizer.data.enums.Stratagem;
import it.italiandudes.hellrandomizer.utils.Settings;
import it.italiandudes.idl.common.InfoFlags;
import it.italiandudes.idl.common.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

public final class HellRandomizer {

    // Main Method
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (IOException e) {
            System.err.println("Impossibile impostare la console su Charset UTF-8.");
        }

        // Initializing the logger
        try {
            Logger.init();
            Logger.log("Logger initialized!", Defs.LOGGER_CONTEXT);
        } catch (IOException e) {
            Logger.log("An error has occurred during Logger initialization, exit...", Defs.LOGGER_CONTEXT);
            return;
        }

        // Configure the shutdown hooks
        Logger.log("Configuring Shutdown Hooks...", Defs.LOGGER_CONTEXT);
        Runtime.getRuntime().addShutdownHook(new Thread(Logger::close));
        Logger.log("Shutdown Hooks configured!", Defs.LOGGER_CONTEXT);

        // Load Settings
        Settings.loadSettingsFile();

        // Load Helldivers Data
        HelldiversDataManager.loadHelldiversDataFile();
        if (HelldiversDataManager.getHelldiversData() == null) {
            Logger.log("Si e' verificato un errore durante il caricamento dei dati", new InfoFlags(true, true));
            System.exit(0);
        }

        // Add a flag to all Super Earth Enabled Stratagems and Boosters
        flagAllSuperEarthEnabled();

        // Start the client
        if (Arrays.stream(args).noneMatch(Predicate.isEqual(Defs.START_ARG_NOGUI))) {
            try {
                Logger.log("Starting UI...", Defs.LOGGER_CONTEXT);
                Client.start(args);
            } catch (NoClassDefFoundError e) {
                Logger.log("ERROR: TO RUN THIS JAR YOU NEED JAVA 8 WITH BUILT-IN JAVAFX!", new InfoFlags(true, true, true, true), Defs.LOGGER_CONTEXT);
                Logger.log(e, Defs.LOGGER_CONTEXT);
                Logger.close();
                System.exit(-1);
            }
            return;
        }

        // Remove Super Earth Enabled Boosters From Players Data
        removeSuperEarthEnabledBoosters();

        // Sorted Player Data
        HelldiverData @NotNull[] boosterSortedHelldiverData = HelldiversDataManager.getHelldiversData().stream().sorted(Comparator.comparingInt(o -> o.getBoosters().size())).toArray(HelldiverData[]::new);

        // Difficulty and Enemy Randomizer
        final boolean randomizeEnemy = Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_ENEMY) && Settings.getSettings().getJSONArray(Defs.SettingsKeys.ENEMY_BLACKLIST).length() < Enemy.values().length;
        if (randomizeEnemy && Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_DIFFICULTY)) {
            System.out.print("\n\n");
            printDecorativeLine();
            System.out.println("Fazione: " + Randomizer.randomizeEnemy());
            System.out.println("Difficoltà: " + Randomizer.randomizeDifficulty());
            printDecorativeLine();
        } else if (randomizeEnemy && ! Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_DIFFICULTY)) {
            System.out.print("\n\n");
            printDecorativeLine();
            System.out.println("Fazione: " + Randomizer.randomizeEnemy());
            printDecorativeLine();
        } else if (!randomizeEnemy && Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_DIFFICULTY)) {
            System.out.print("\n\n");
            printDecorativeLine();
            System.out.println("Difficoltà: " + Randomizer.randomizeDifficulty());
            printDecorativeLine();
        }


        // Player Data Randomizer
        System.out.println("\n");
        for (HelldiverData helldiverData : boosterSortedHelldiverData) {
            if (!helldiverData.isEnrolledForRandomizer()) continue;
            System.out.print("\n");
            printDecorativeLine();
            System.out.println("Giocatore: " + helldiverData.getName());
            if (Settings.getSettings().getInt(Defs.SettingsKeys.ARMOR_RANDOMIZATION_PROCEDURE) > 0) randomizeArmor(helldiverData);
            randomizeWeaponAndThrowableCategories(helldiverData);
            if (Settings.getSettings().getInt(Defs.SettingsKeys.STRATAGEMS_RANDOMIZATION_PROCEDURE) > 0) randomizeStratagems(helldiverData);
            if (Settings.getSettings().getBoolean(Defs.SettingsKeys.RANDOMIZE_BOOSTERS)) randomizeBooster(helldiverData);
            printDecorativeLine();
            System.out.print("\n\n\n");
        }

        // Close Logger
        Logger.close();
    }

    // Randomization Phases
    private static void randomizeArmor(@NotNull final HelldiverData helldiverData) {
        switch (Settings.getSettings().getInt(Defs.SettingsKeys.ARMOR_RANDOMIZATION_PROCEDURE)) {
            case 1:
                System.out.println("\t-Categoria Armatura: " + Randomizer.randomizeArmorCategory(helldiverData));

                break;

            case 2:
                System.out.println("\t-Buff Armatura: " + Randomizer.randomizeArmorBuff(helldiverData));
                break;

            case 3:
                if (Randomizer.randomFromZeroTo(100) % 2 == 0) { // Category
                    System.out.println("\t-Categoria Armatura: " + Randomizer.randomizeArmorCategory(helldiverData));
                } else { // Buff
                    System.out.println("\t-Buff Armatura: " + Randomizer.randomizeArmorBuff(helldiverData));
                }
                break;

            default:
                System.err.println("ERRORE: Il valore di ARMOR_RANDOMIZATION_PROCEDURE non è nei limiti massimi [0, 3].");
                System.exit(0);
        }
    }
    private static void randomizePrimaryWeapon(@NotNull final HelldiverData helldiverData) {
        switch (Settings.getSettings().getInt(Defs.SettingsKeys.PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE)) {
            case 0: break; // No Randomization

            case 1:
                System.out.println("\t-Categoria Arma Primaria: " + Randomizer.randomizePrimaryWeaponCategory(helldiverData));
                break;

            case 2:
                System.out.println("\t-Arma Primaria: " + Randomizer.randomizePrimaryWeapon(helldiverData));
                break;
        }
    }
    private static void randomizeSecondaryWeapon(@NotNull final HelldiverData helldiverData) {
        switch (Settings.getSettings().getInt(Defs.SettingsKeys.SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE)) {
            case 0: break; // No Randomization

            case 1:
                System.out.println("\t-Categoria Arma Secondaria: " + Randomizer.randomizeSecondaryWeaponCategory(helldiverData));
                break;

            case 2:
                System.out.println("\t-Arma Secondaria: " + Randomizer.randomizeSecondaryWeapon(helldiverData));
                break;
        }
    }
    private static void randomizeThrowableWeapon(@NotNull final HelldiverData helldiverData) {
        switch (Settings.getSettings().getInt(Defs.SettingsKeys.THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE)) {
            case 0: break; // No Randomization

            case 1:
                System.out.println("\t-Categoria Arma Lanciabile: " + Randomizer.randomizeThrowableWeaponCategory(helldiverData));
                break;

            case 2:
                System.out.println("\t-Arma Lanciabile: " + Randomizer.randomizeThrowableWeapon(helldiverData));
                break;
        }
    }
    private static void randomizeWeaponAndThrowableCategories(@NotNull final HelldiverData helldiverData) {
        randomizePrimaryWeapon(helldiverData);
        randomizeSecondaryWeapon(helldiverData);
        randomizeThrowableWeapon(helldiverData);
    }
    private static void randomizeStratagems(@NotNull final HelldiverData helldiverData) {
        ArrayList<Stratagem> randomizedStratagems = Randomizer.randomizeStratagems(helldiverData);
        System.out.println("\t-Stratagemmi:");
        for (Stratagem stratagem : randomizedStratagems) {
            System.out.println("\t\t- " + stratagem);
        }
    }
    private static void randomizeBooster(@NotNull final HelldiverData helldiverData) {
        Booster booster = Randomizer.randomizeBooster(helldiverData);
        System.out.println("\t-Potenziamento: " + (booster != null ? booster : "Nessuno"));
    }

    // Utility Methods
    private static void printDecorativeLine() {
        System.out.println("***************************************************************************");
    }
    private static void removeSuperEarthEnabledBoosters() {
        JSONArray boostersEnabledBySuperEarth = Settings.getSettings().getJSONArray(Defs.SettingsKeys.BOOSTERS_ENABLED_BY_SUPER_EARTH);
        List<Booster> superEarthEnabledBoosters = new ArrayList<>();
        for (int i=0; i < boostersEnabledBySuperEarth.length(); i++) {
            superEarthEnabledBoosters.add(Booster.values()[boostersEnabledBySuperEarth.getInt(i)]);
        }
        for (HelldiverData helldiverData : HelldiversDataManager.getHelldiversData()) {
            superEarthEnabledBoosters.forEach(helldiverData.getBoosters()::remove);
        }
    }
    private static void flagAllSuperEarthEnabled() {
        JSONArray stratagemsEnabledBySuperEarth = Settings.getSettings().getJSONArray(Defs.SettingsKeys.STRATAGEMS_ENABLED_BY_SUPER_EARTH);
        for (int i=0; i < stratagemsEnabledBySuperEarth.length(); i++) {
            Stratagem.values()[stratagemsEnabledBySuperEarth.getInt(i)].setEnabledBySuperEarth(true);
        }
        JSONArray boostersEnabledBySuperEarth = Settings.getSettings().getJSONArray(Defs.SettingsKeys.BOOSTERS_ENABLED_BY_SUPER_EARTH);
        for (int i=0; i < boostersEnabledBySuperEarth.length(); i++) {
            Booster.values()[boostersEnabledBySuperEarth.getInt(i)].setEnabledBySuperEarth(true);
        }
    }
}
