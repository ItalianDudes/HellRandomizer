package it.italiandudes.hellrandomizer.utils;

import it.italiandudes.hellrandomizer.data.HelldiverData;
import it.italiandudes.hellrandomizer.data.enums.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.ArrayList;
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
        return helldiverData.getArmorBuffs().toArray(new ArmorBuff[0])[randomFromZeroTo(helldiverData.getArmorBuffs().size())];
    }
    @NotNull public static PrimaryWeapon randomizePrimaryWeapon(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getPrimaryWeapons().toArray(new PrimaryWeapon[0])[randomFromZeroTo(helldiverData.getPrimaryWeapons().size())];
    }
    @NotNull public static PrimaryWeaponType randomizePrimaryWeaponType(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getPrimaryWeaponTypes().toArray(new PrimaryWeaponType[0])[randomFromZeroTo(helldiverData.getPrimaryWeaponTypes().size())];
    }
    @NotNull public static SecondaryWeapon randomizeSecondaryWeapon(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getSecondaryWeapons().toArray(new SecondaryWeapon[0])[randomFromZeroTo(helldiverData.getSecondaryWeapons().size())];
    }
    @NotNull public static SecondaryWeaponType randomizeSecondaryWeaponType(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getSecondaryWeaponTypes().toArray(new SecondaryWeaponType[0])[randomFromZeroTo(helldiverData.getSecondaryWeaponTypes().size())];
    }
    @NotNull public static ThrowableWeapon randomizeThrowableWeapon(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getThrowableWeapons().toArray(new ThrowableWeapon[0])[randomFromZeroTo(helldiverData.getThrowableWeapons().size())];
    }
    @NotNull public static ThrowableWeaponType randomizeThrowableWeaponType(@NotNull final HelldiverData helldiverData) {
        return helldiverData.getThrowableWeaponTypes().toArray(new ThrowableWeaponType[0])[randomFromZeroTo(helldiverData.getThrowableWeaponTypes().size())];
    }
    @NotNull public static Stratagem randomizeStratagem(@NotNull final HelldiverData helldiverData) {
        Stratagem stratagem;
        do {
            stratagem = helldiverData.getStratagems().toArray(new Stratagem[0])[randomFromZeroTo(helldiverData.getStratagems().size())];
        } while (stratagem.isEnabledBySuperEarth());
        return stratagem;
    }
    @NotNull public static Booster randomizeBooster(@NotNull final HelldiverData helldiverData) {
        Booster booster;
        do {
            booster = helldiverData.getBoosters().toArray(new Booster[0])[randomFromZeroTo(helldiverData.getBoosters().size())];
        } while (booster.isEnabledBySuperEarth());
        return booster;
    }
    @NotNull public static Enemy randomizeEnemy() {
        JSONArray enemyBlacklist = Settings.getSettings().getJSONArray(Defs.SettingsKeys.ENEMY_BLACKLIST);
        ArrayList<Enemy> blacklist = new ArrayList<>();
        for (int i=0; i < enemyBlacklist.length(); i++) {
            blacklist.add(Enemy.values()[enemyBlacklist.getInt(i)]);
        }
        @NotNull final List<@NotNull Enemy> allowedEnemies = Arrays.stream(Enemy.values()).filter(enemy -> !blacklist.contains(enemy)).collect(Collectors.toList());
        return allowedEnemies.get(randomFromZeroTo(allowedEnemies.size()));
    }
    @NotNull public static Difficulty randomizeDifficulty() {
        if (Settings.getSettings().getInt(Defs.SettingsKeys.MINIMUM_DIFFICULTY) < 1) {
            System.err.println("ERRORE: Il valore di MIN_DIFFICULTY deve essere maggiore o uguale a 1 e minore o uguale a MAX_DIFFICULTY.");
            System.exit(0);
        }
        if (Settings.getSettings().getInt(Defs.SettingsKeys.MAXIMUM_DIFFICULTY) > 10) {
            System.err.println("ERRORE: Il valore di MAX_DIFFICULTY deve essere minore o uguale a 10 e maggiore o uguale a MIN_DIFFICULTY.");
            System.exit(0);
        }
        if (Settings.getSettings().getInt(Defs.SettingsKeys.MINIMUM_DIFFICULTY) > Settings.getSettings().getInt(Defs.SettingsKeys.MAXIMUM_DIFFICULTY)) {
            System.err.println("ERRORE: Il valore di MIN_DIFFICULTY deve essere minore o uguale a MAX_DIFFICULTY.");
            System.exit(0);
        }
        return Difficulty.values()[randomBetween(Settings.getSettings().getInt(Defs.SettingsKeys.MINIMUM_DIFFICULTY)-1, Settings.getSettings().getInt(Defs.SettingsKeys.MAXIMUM_DIFFICULTY))];
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
