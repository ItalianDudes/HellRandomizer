package it.italiandudes.hellrandomizer.utils;

import it.italiandudes.hellrandomizer.data.HelldiverData;
import it.italiandudes.hellrandomizer.data.enums.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class Randomizer {

    // Public
    @NotNull public static ArmorCategory randomizeArmorCategory(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getArmorCategories()[randomFromZeroTo(helldiverData.getArmorCategories().length)];
    }
    @NotNull public static ArmorBuff randomizeArmorBuff(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getArmorBuffs()[randomFromZeroTo(helldiverData.getArmorBuffs().length)];
    }
    @NotNull public static PrimaryWeapon randomizePrimaryWeapon(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getPrimaryWeapons()[randomFromZeroTo(helldiverData.getPrimaryWeapons().length)];
    }
    @NotNull public static PrimaryWeaponType randomizePrimaryWeaponType(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getPrimaryWeaponTypes()[randomFromZeroTo(helldiverData.getPrimaryWeaponTypes().length)];
    }
    @NotNull public static SecondaryWeapon randomizeSecondaryWeapon(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getSecondaryWeapons()[randomFromZeroTo(helldiverData.getSecondaryWeapons().length)];
    }
    @NotNull public static SecondaryWeaponType randomizeSecondaryWeaponType(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getSecondaryWeaponTypes()[randomFromZeroTo(helldiverData.getSecondaryWeaponTypes().length)];
    }
    @NotNull public static ThrowableWeapon randomizeThrowableWeapon(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getThrowableWeapons()[randomFromZeroTo(helldiverData.getThrowableWeapons().length)];
    }
    @NotNull public static ThrowableWeaponType randomizeThrowableWeaponType(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getThrowableWeaponTypes()[randomFromZeroTo(helldiverData.getThrowableWeaponTypes().length)];
    }
    @NotNull public static Stratagem randomizeStratagem(@NotNull final HelldiverData helldiverData) {
        Stratagem stratagem;
        do {
            stratagem = helldiverData.getStratagems()[randomFromZeroTo(helldiverData.getStratagems().length)];
        } while (stratagem.isEnabledBySuperEarth());
        return stratagem;
    }
    @NotNull public static Booster randomizeBooster(@NotNull final HelldiverData helldiverData) {
        Booster booster;
        do {
            booster = (Booster) helldiverData.getBoosters().toArray()[randomFromZeroTo(helldiverData.getBoosters().size())];
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
