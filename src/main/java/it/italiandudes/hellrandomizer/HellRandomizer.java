package it.italiandudes.hellrandomizer;

import it.italiandudes.hellrandomizer.javafx.Client;
import it.italiandudes.hellrandomizer.utils.Defs;
import it.italiandudes.hellrandomizer.data.HelldiverData;
import it.italiandudes.hellrandomizer.utils.HelldiversDataManager;
import it.italiandudes.hellrandomizer.utils.Randomizer;
import it.italiandudes.hellrandomizer.data.enums.Booster;
import it.italiandudes.hellrandomizer.data.enums.Enemy;
import it.italiandudes.hellrandomizer.data.enums.Stratagem;
import it.italiandudes.idl.common.InfoFlags;
import it.italiandudes.idl.common.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings({"DataFlowIssue", "ConstantValue"})
public final class HellRandomizer {

    // Attributes
    private static final HashSet<@NotNull Booster> RANDOMIZED_BOOSTERS = new HashSet<>();

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

        // Load Helldivers Data
        HelldiversDataManager.loadHelldiversDataFile();
        if (HelldiversDataManager.getHelldiversData() == null) {
            Logger.log("Si e' verificato un errore durante il caricamento dei dati", new InfoFlags(true, true));
            System.exit(0);
        }

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

        // Add a flag to all Super Earth Enabled Stratagems and Boosters
        flagAllSuperEarthEnabled();

        // Remove Super Earth Enabled Boosters From Players Data
        removeSuperEarthEnabledBoosters();

        // Sorted Player Data
        HelldiverData @NotNull[] boosterSortedHelldiverData = HelldiversDataManager.getHelldiversData().stream().sorted(Comparator.comparingInt(o -> o.getBoosters().size())).toArray(HelldiverData[]::new);

        // Difficulty and Enemy Randomizer
        final boolean randomizeEnemy = Defs.RANDOMIZE_ENEMY && Defs.ENEMY_BLACKLIST.length < Enemy.values().length;
        if (randomizeEnemy && Defs.RANDOMIZE_DIFFICULTY) {
            System.out.print("\n\n");
            printDecorativeLine();
            System.out.println("Fazione: " + Randomizer.randomizeEnemy());
            System.out.println("Difficoltà: " + Randomizer.randomizeDifficulty());
            printDecorativeLine();
        } else if (randomizeEnemy && ! Defs.RANDOMIZE_DIFFICULTY) {
            System.out.print("\n\n");
            printDecorativeLine();
            System.out.println("Fazione: " + Randomizer.randomizeEnemy());
            printDecorativeLine();
        } else if (!randomizeEnemy && Defs.RANDOMIZE_DIFFICULTY) {
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
            if (Defs.ARMOR_RANDOMIZATION_PROCEDURE > -1) randomizeArmor(helldiverData);
            randomizeWeaponAndThrowableCategories(helldiverData);
            if (Defs.STRATAGEMS_RANDOMIZATION_PROCEDURE > -1) randomizeStratagems(helldiverData);
            if (Defs.RANDOMIZE_BOOSTERS) randomizeBooster(helldiverData);
            printDecorativeLine();
            System.out.print("\n\n\n");
        }
    }

    // Randomization Phases
    private static void randomizeArmor(@NotNull final HelldiverData helldiverData) {
        switch (Defs.ARMOR_RANDOMIZATION_PROCEDURE) {
            case 0:
                if (Randomizer.randomFromZeroTo(100) % 2 == 0) { // Category
                    System.out.println("\t-Categoria Armatura: " + Randomizer.randomizeArmorCategory(helldiverData));
                } else { // Buff
                    System.out.println("\t-Buff Armatura: " + Randomizer.randomizeArmorBuff(helldiverData));
                }
                break;

            case 1:
                System.out.println("\t-Categoria Armatura: " + Randomizer.randomizeArmorCategory(helldiverData));
                break;

            case 2:
                System.out.println("\t-Buff Armatura: " + Randomizer.randomizeArmorBuff(helldiverData));
                break;

            default:
                System.err.println("ERRORE: Il valore di ARMOR_RANDOMIZATION_PROCEDURE non è nei limiti massimi [-1, 2].");
                System.exit(0);
        }
    }
    private static void randomizePrimaryWeapon(@NotNull final HelldiverData helldiverData) {
        switch (Defs.PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE) {
            case -1: break; // No Randomization

            case 0:
                System.out.println("\t-Categoria Arma Primaria: " + Randomizer.randomizePrimaryWeaponType(helldiverData));
                break;

            case 1:
                System.out.println("\t-Arma Primaria: " + Randomizer.randomizePrimaryWeapon(helldiverData));
                break;
        }
    }
    private static void randomizeSecondaryWeapon(@NotNull final HelldiverData helldiverData) {
        switch (Defs.SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE) {
            case -1: break; // No Randomization

            case 0:
                System.out.println("\t-Categoria Arma Secondaria: " + Randomizer.randomizeSecondaryWeaponType(helldiverData));
                break;

            case 1:
                System.out.println("\t-Arma Secondaria: " + Randomizer.randomizeSecondaryWeapon(helldiverData));
                break;
        }
    }
    private static void randomizeThrowableWeapon(@NotNull final HelldiverData helldiverData) {
        switch (Defs.THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE) {
            case -1: break; // No Randomization

            case 0:
                System.out.println("\t-Categoria Arma Lanciabile: " + Randomizer.randomizeThrowableWeaponType(helldiverData));
                break;

            case 1:
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
        ArrayList<Stratagem> randomizedStratagems = new ArrayList<>();
        int maxStratagems = Math.min(4, helldiverData.getStratagems().length);

        if (maxStratagems > 0) {
            switch (Defs.STRATAGEMS_RANDOMIZATION_PROCEDURE) {
                case 0:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(helldiverData);
                        if (!randomizedStratagems.contains(stratagem)) randomizedStratagems.add(stratagem);
                    }
                    break;

                case 1:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(helldiverData);
                        if (!randomizedStratagems.contains(stratagem)) {
                            if (randomizedStratagems.isEmpty()) {
                                if (stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            } else randomizedStratagems.add(stratagem);
                        }
                    }
                    break;

                case 2:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(helldiverData);
                        if (!randomizedStratagems.contains(stratagem)) {
                            if (randomizedStratagems.isEmpty()) {
                                if (stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            } else {
                                if (!stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            }
                        }
                    }
                    break;

                case 3:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(helldiverData);
                        if (!randomizedStratagems.contains(stratagem)) {
                            if (randomizedStratagems.isEmpty()) {
                                if (stratagem.hasBackpack()) randomizedStratagems.add(stratagem);
                            } else {
                                randomizedStratagems.add(stratagem);
                            }
                        }
                    }
                    break;

                case 4:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(helldiverData);
                        if (!randomizedStratagems.contains(stratagem)) {
                            if (randomizedStratagems.isEmpty()) {
                                if (stratagem.hasBackpack()) randomizedStratagems.add(stratagem);
                            } else {
                                if (!stratagem.hasBackpack()) randomizedStratagems.add(stratagem);
                            }
                        }
                    }
                    break;

                case 5:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(helldiverData);
                        if (!randomizedStratagems.contains(stratagem)) {
                            if (randomizedStratagems.isEmpty()) {
                                if (stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            } else if (randomizedStratagems.size() == 1) {
                                if (stratagem.hasBackpack() || randomizedStratagems.get(0).hasBackpack()) randomizedStratagems.add(stratagem);
                            } else randomizedStratagems.add(stratagem);
                        }
                    }
                    break;

                case 6:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(helldiverData);
                        if (!randomizedStratagems.contains(stratagem)) {
                            if (randomizedStratagems.isEmpty()) {
                                if (stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            } else if (randomizedStratagems.size() == 1) {
                                if (randomizedStratagems.get(0).hasBackpack() && !stratagem.hasBackpack() && !stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                                else if (!randomizedStratagems.get(0).hasBackpack() && stratagem.hasBackpack() && !stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            } else if (!stratagem.isSupportWeapon() && !stratagem.hasBackpack()) randomizedStratagems.add(stratagem);
                        }
                    }
                    break;

                case 7:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(helldiverData);
                        if (!randomizedStratagems.contains(stratagem)) {
                            if (randomizedStratagems.isEmpty()) {
                                if (stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            } else if (randomizedStratagems.size() == 1) {
                                if (randomizedStratagems.get(0).hasBackpack() && !stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                                else if (stratagem.hasBackpack() && !stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            } else if (!stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                        }
                    }
                    break;

                case 8:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(helldiverData);
                        if (!randomizedStratagems.contains(stratagem)) {
                            if (randomizedStratagems.isEmpty()) {
                                if (stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            } else if (randomizedStratagems.size() == 1) {
                                if (randomizedStratagems.get(0).hasBackpack() && !stratagem.hasBackpack()) randomizedStratagems.add(stratagem);
                                else if (!randomizedStratagems.get(0).hasBackpack() && stratagem.hasBackpack()) randomizedStratagems.add(stratagem);
                            } else if (!stratagem.hasBackpack()) randomizedStratagems.add(stratagem);
                        }
                    }
                    break;

                default:
                    System.err.println("ERRORE: Il valore di STRATAGEMS_RANDOMIZATION_PROCEDURE non è nei limiti massimi [-1, 8].");
                    System.exit(-1);
            }
            System.out.println("\t-Stratagemmi:");
            for (Stratagem stratagem : randomizedStratagems) {
                System.out.println("\t\t- " + stratagem);
            }
        }
    }
    private static void randomizeBooster(@NotNull final HelldiverData helldiverData) {
        if (helldiverData.getBoosters().isEmpty()) return;
        Booster booster;
        do {
            booster = Randomizer.randomizeBooster(helldiverData);
        } while (RANDOMIZED_BOOSTERS.contains(booster));
        RANDOMIZED_BOOSTERS.add(booster);
        System.out.println("\t-Potenziamento: " + booster);
    }

    // Utility Methods
    private static void printDecorativeLine() {
        System.out.println("***************************************************************************");
    }
    private static void removeSuperEarthEnabledBoosters() {
        for (HelldiverData helldiverData : HelldiversDataManager.getHelldiversData()) {
            Arrays.asList(Defs.BOOSTERS_ENABLED_BY_SUPER_EARTH).forEach(helldiverData.getBoosters()::remove);
        }
    }
    private static void flagAllSuperEarthEnabled() {
        for (Stratagem stratagem : Defs.STRATAGEMS_ENABLED_BY_SUPER_EARTH) {
            stratagem.setEnabledBySuperEarth(true);
        }
        for (Booster booster : Defs.BOOSTERS_ENABLED_BY_SUPER_EARTH) {
            booster.setEnabledBySuperEarth(true);
        }
    }
}
