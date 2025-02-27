package it.italiandudes.hellrandomizer;

import it.italiandudes.hellrandomizer.enums.Booster;
import it.italiandudes.hellrandomizer.enums.Enemy;
import it.italiandudes.hellrandomizer.enums.Stratagem;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

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

        // Add a flag to all Super Earth Enabled Stratagems and Boosters
        flagAllSuperEarthEnabled();

        // Remove Super Earth Enabled Boosters From Players Data
        removeSuperEarthEnabledBoosters();

        // Sorted Player Data
        PlayerData @NotNull[] boosterSortedPlayerData = Defs.PLAYERS_DATA.stream().sorted(Comparator.comparingInt(o -> o.getBoosters().size())).toArray(PlayerData[]::new);

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
        for (PlayerData playerData : boosterSortedPlayerData) {
            if (!playerData.isEnrolledForRandomizer()) continue;
            System.out.print("\n");
            printDecorativeLine();
            System.out.println("Giocatore: " + playerData.getName());
            if (Defs.ARMOR_RANDOMIZATION_PROCEDURE > -1) randomizeArmor(playerData);
            randomizeWeaponAndThrowableCategories(playerData);
            if (Defs.STRATAGEMS_RANDOMIZATION_PROCEDURE > -1) randomizeStratagems(playerData);
            if (Defs.RANDOMIZE_BOOSTERS) randomizeBooster(playerData);
            printDecorativeLine();
            System.out.print("\n\n\n");
        }
    }

    // Randomization Phases
    private static void randomizeArmor(@NotNull final PlayerData playerData) {
        switch (Defs.ARMOR_RANDOMIZATION_PROCEDURE) {
            case 0:
                if (Randomizer.randomFromZeroTo(100) % 2 == 0) { // Category
                    System.out.println("\t-Categoria Armatura: " + Randomizer.randomizeArmorCategory(playerData));
                } else { // Buff
                    System.out.println("\t-Buff Armatura: " + Randomizer.randomizeArmorBuff(playerData));
                }
                break;

            case 1:
                System.out.println("\t-Categoria Armatura: " + Randomizer.randomizeArmorCategory(playerData));
                break;

            case 2:
                System.out.println("\t-Buff Armatura: " + Randomizer.randomizeArmorBuff(playerData));
                break;

            default:
                System.err.println("ERRORE: Il valore di ARMOR_RANDOMIZATION_PROCEDURE non è nei limiti massimi [-1, 2].");
                System.exit(0);
        }
    }
    private static void randomizePrimaryWeapon(@NotNull final PlayerData playerData) {
        switch (Defs.PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE) {
            case -1: break; // No Randomization

            case 0:
                System.out.println("\t-Categoria Arma Primaria: " + Randomizer.randomizePrimaryWeaponType(playerData));
                break;

            case 1:
                System.out.println("\t-Arma Primaria: " + Randomizer.randomizePrimaryWeapon(playerData));
                break;
        }
    }
    private static void randomizeSecondaryWeapon(@NotNull final PlayerData playerData) {
        switch (Defs.SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE) {
            case -1: break; // No Randomization

            case 0:
                System.out.println("\t-Categoria Arma Secondaria: " + Randomizer.randomizeSecondaryWeaponType(playerData));
                break;

            case 1:
                System.out.println("\t-Arma Secondaria: " + Randomizer.randomizeSecondaryWeapon(playerData));
                break;
        }
    }
    private static void randomizeThrowableWeapon(@NotNull final PlayerData playerData) {
        switch (Defs.THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE) {
            case -1: break; // No Randomization

            case 0:
                System.out.println("\t-Categoria Arma Lanciabile: " + Randomizer.randomizeThrowableWeaponType(playerData));
                break;

            case 1:
                System.out.println("\t-Arma Lanciabile: " + Randomizer.randomizeThrowableWeapon(playerData));
                break;
        }
    }
    private static void randomizeWeaponAndThrowableCategories(@NotNull final PlayerData playerData) {
        randomizePrimaryWeapon(playerData);
        randomizeSecondaryWeapon(playerData);
        randomizeThrowableWeapon(playerData);
    }
    private static void randomizeStratagems(@NotNull final PlayerData playerData) {
        ArrayList<Stratagem> randomizedStratagems = new ArrayList<>();
        int maxStratagems = Math.min(4, playerData.getStratagems().length);

        if (maxStratagems > 0) {
            switch (Defs.STRATAGEMS_RANDOMIZATION_PROCEDURE) {
                case 0:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(playerData);
                        if (!randomizedStratagems.contains(stratagem)) randomizedStratagems.add(stratagem);
                    }
                    break;

                case 1:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(playerData);
                        if (!randomizedStratagems.contains(stratagem)) {
                            if (randomizedStratagems.isEmpty()) {
                                if (stratagem.isSupportWeapon()) randomizedStratagems.add(stratagem);
                            } else randomizedStratagems.add(stratagem);
                        }
                    }
                    break;

                case 2:
                    while (randomizedStratagems.size() < maxStratagems) {
                        Stratagem stratagem = Randomizer.randomizeStratagem(playerData);
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
                        Stratagem stratagem = Randomizer.randomizeStratagem(playerData);
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
                        Stratagem stratagem = Randomizer.randomizeStratagem(playerData);
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
                        Stratagem stratagem = Randomizer.randomizeStratagem(playerData);
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
                        Stratagem stratagem = Randomizer.randomizeStratagem(playerData);
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
                        Stratagem stratagem = Randomizer.randomizeStratagem(playerData);
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
                        Stratagem stratagem = Randomizer.randomizeStratagem(playerData);
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
    private static void randomizeBooster(@NotNull final PlayerData playerData) {
        if (playerData.getBoosters().isEmpty()) return;
        Booster booster;
        do {
            booster = Randomizer.randomizeBooster(playerData);
        } while (RANDOMIZED_BOOSTERS.contains(booster));
        RANDOMIZED_BOOSTERS.add(booster);
        System.out.println("\t-Potenziamento: " + booster);
    }

    // Utility Methods
    private static void printDecorativeLine() {
        System.out.println("***************************************************************************");
    }
    private static void removeSuperEarthEnabledBoosters() {
        for (PlayerData playerData : Defs.PLAYERS_DATA) {
            Arrays.asList(Defs.BOOSTERS_ENABLED_BY_SUPER_EARTH).forEach(playerData.getBoosters()::remove);
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
