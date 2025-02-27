package it.italiandudes.hellrandomizer;

import it.italiandudes.hellrandomizer.enums.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class Randomizer {

    // Public
    @NotNull public static ArmorCategory randomizeArmorCategory(@NotNull final PlayerData playerData) {
        return playerData.getArmorCategories()[randomFromZeroTo(playerData.getArmorCategories().length)];
    }
    @NotNull public static ArmorBuff randomizeArmorBuff(@NotNull final PlayerData playerData) {
        return playerData.getArmorBuffs()[randomFromZeroTo(playerData.getArmorBuffs().length)];
    }
    @NotNull public static PrimaryWeapon randomizePrimaryWeapon(@NotNull final PlayerData playerData) {
        return playerData.getPrimaryWeapons()[randomFromZeroTo(playerData.getPrimaryWeapons().length)];
    }
    @NotNull public static PrimaryWeaponType randomizePrimaryWeaponType(@NotNull final PlayerData playerData) {
        return playerData.getPrimaryWeaponTypes()[randomFromZeroTo(playerData.getPrimaryWeaponTypes().length)];
    }
    @NotNull public static SecondaryWeapon randomizeSecondaryWeapon(@NotNull final PlayerData playerData) {
        return playerData.getSecondaryWeapons()[randomFromZeroTo(playerData.getSecondaryWeapons().length)];
    }
    @NotNull public static SecondaryWeaponType randomizeSecondaryWeaponType(@NotNull final PlayerData playerData) {
        return playerData.getSecondaryWeaponTypes()[randomFromZeroTo(playerData.getSecondaryWeaponTypes().length)];
    }
    @NotNull public static ThrowableWeapon randomizeThrowableWeapon(@NotNull final PlayerData playerData) {
        return playerData.getThrowableWeapons()[randomFromZeroTo(playerData.getThrowableWeapons().length)];
    }
    @NotNull public static ThrowableWeaponType randomizeThrowableWeaponType(@NotNull final PlayerData playerData) {
        return playerData.getThrowableWeaponTypes()[randomFromZeroTo(playerData.getThrowableWeaponTypes().length)];
    }
    @NotNull public static Stratagem randomizeStratagem(@NotNull final PlayerData playerData) {
        Stratagem stratagem;
        do {
            stratagem = playerData.getStratagems()[randomFromZeroTo(playerData.getStratagems().length)];
        } while (stratagem.isEnabledBySuperEarth());
        return stratagem;
    }
    @NotNull public static Booster randomizeBooster(@NotNull final PlayerData playerData) {
        Booster booster;
        do {
            booster = (Booster) playerData.getBoosters().toArray()[randomFromZeroTo(playerData.getBoosters().size())];
        } while (booster.isEnabledBySuperEarth());
        return booster;
    }
    @NotNull public static Enemy randomizeEnemy() {
        @NotNull final List<@NotNull Enemy> allowedEnemies = Arrays.stream(Enemy.values()).filter(enemy -> !Arrays.asList(Defs.ENEMY_BLACKLIST).contains(enemy)).collect(Collectors.toList());
        return allowedEnemies.get(randomFromZeroTo(allowedEnemies.size()));
    }
    @NotNull public static Difficulty randomizeDifficulty() {
        if (Defs.MIN_DIFFICULTY < 1) {
            System.err.println("ERRORE: Il valore di MIN_DIFFICULTY deve essere maggiore o uguale a 1 e minore o uguale a MAX_DIFFICULTY.");
            System.exit(0);
        }
        if (Defs.MAX_DIFFICULTY > 10) {
            System.err.println("ERRORE: Il valore di MAX_DIFFICULTY deve essere minore o uguale a 10 e maggiore o uguale a MIN_DIFFICULTY.");
            System.exit(0);
        }
        //noinspection ConstantValue
        if (Defs.MIN_DIFFICULTY > Defs.MAX_DIFFICULTY) {
            System.err.println("ERRORE: Il valore di MIN_DIFFICULTY deve essere minore o uguale a MAX_DIFFICULTY.");
            System.exit(0);
        }
        return Difficulty.values()[randomBetween(Defs.MIN_DIFFICULTY-1, Defs.MAX_DIFFICULTY)];
    }

    // Attributes
    private static final Random RANDOMIZER = new Random();
    public static int randomFromZeroTo(int max) { // 0 Included to Max excluded
        return RANDOMIZER.nextInt(max);
    }
    public static int randomBetween(int min, int max) { // Min included, Max excluded
        return RANDOMIZER.nextInt(max - min) + min;
    }
}
