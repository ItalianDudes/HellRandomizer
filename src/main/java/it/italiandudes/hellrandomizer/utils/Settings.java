package it.italiandudes.hellrandomizer.utils;

import it.italiandudes.idl.common.JSONManager;
import it.italiandudes.idl.common.JarHandler;
import it.italiandudes.idl.common.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public final class Settings {

    // Settings
    private static JSONObject SETTINGS = null;

    // Settings Loader
    public static void loadSettingsFile() {
        File settingsFile = new File(new File(Defs.JAR_POSITION).getParent() + File.separator + Defs.Resources.JSON.CLIENT_SETTINGS);
        if (!settingsFile.exists() || !settingsFile.isFile()) {
            try {
                JarHandler.copyFileFromJar(new File(Defs.JAR_POSITION), Defs.Resources.JSON.DEFAULT_JSON_SETTINGS, settingsFile, true);
            } catch (IOException e) {
                Logger.log(e, Defs.LOGGER_CONTEXT);
                return;
            }
        }
        try {
            SETTINGS = JSONManager.readJSON(settingsFile);
            fixJSONSettings();
        } catch (IOException | JSONException e) {
            Logger.log(e, Defs.LOGGER_CONTEXT);
        }
    }

    // Settings Checker
    private static void fixJSONSettings() throws JSONException, IOException {
        try {
            SETTINGS.getBoolean(Defs.SettingsKeys.RANDOMIZE_ENEMY);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.RANDOMIZE_ENEMY);
            SETTINGS.put(Defs.SettingsKeys.RANDOMIZE_ENEMY, true);
        }
        try {
            SETTINGS.getJSONArray(Defs.SettingsKeys.ENEMY_BLACKLIST);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.ENEMY_BLACKLIST);
            SETTINGS.put(Defs.SettingsKeys.ENEMY_BLACKLIST, new JSONArray());
        }
        try {
            SETTINGS.getBoolean(Defs.SettingsKeys.RANDOMIZE_DIFFICULTY);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.RANDOMIZE_DIFFICULTY);
            SETTINGS.put(Defs.SettingsKeys.RANDOMIZE_DIFFICULTY, true);
        }
        try {
            SETTINGS.getInt(Defs.SettingsKeys.MINIMUM_DIFFICULTY);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.MINIMUM_DIFFICULTY);
            SETTINGS.put(Defs.SettingsKeys.MINIMUM_DIFFICULTY, 1);
        }
        try {
            SETTINGS.getInt(Defs.SettingsKeys.MAXIMUM_DIFFICULTY);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.MAXIMUM_DIFFICULTY);
            SETTINGS.put(Defs.SettingsKeys.MAXIMUM_DIFFICULTY, 10);
        }
        try {
            SETTINGS.getInt(Defs.SettingsKeys.ARMOR_RANDOMIZATION_PROCEDURE);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.ARMOR_RANDOMIZATION_PROCEDURE);
            SETTINGS.put(Defs.SettingsKeys.ARMOR_RANDOMIZATION_PROCEDURE, 2);
        }
        try {
            SETTINGS.getInt(Defs.SettingsKeys.PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE);
            SETTINGS.put(Defs.SettingsKeys.PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE, 1);
        }
        try {
            SETTINGS.getInt(Defs.SettingsKeys.SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE);
            SETTINGS.put(Defs.SettingsKeys.SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE, 1);
        }
        try {
            SETTINGS.getInt(Defs.SettingsKeys.THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE);
            SETTINGS.put(Defs.SettingsKeys.THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE, 1);
        }
        try {
            SETTINGS.getInt(Defs.SettingsKeys.STRATAGEMS_RANDOMIZATION_PROCEDURE);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.STRATAGEMS_RANDOMIZATION_PROCEDURE);
            SETTINGS.put(Defs.SettingsKeys.STRATAGEMS_RANDOMIZATION_PROCEDURE, 6);
        }
        try {
            SETTINGS.getJSONArray(Defs.SettingsKeys.STRATAGEMS_ENABLED_BY_SUPER_EARTH);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.STRATAGEMS_ENABLED_BY_SUPER_EARTH);
            SETTINGS.put(Defs.SettingsKeys.STRATAGEMS_ENABLED_BY_SUPER_EARTH, new JSONArray());
        }
        try {
            SETTINGS.getBoolean(Defs.SettingsKeys.RANDOMIZE_BOOSTERS);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.RANDOMIZE_BOOSTERS);
            SETTINGS.put(Defs.SettingsKeys.RANDOMIZE_BOOSTERS, true);
        }
        try {
            SETTINGS.getJSONArray(Defs.SettingsKeys.BOOSTERS_ENABLED_BY_SUPER_EARTH);
        } catch (JSONException e) {
            SETTINGS.remove(Defs.SettingsKeys.BOOSTERS_ENABLED_BY_SUPER_EARTH);
            SETTINGS.put(Defs.SettingsKeys.BOOSTERS_ENABLED_BY_SUPER_EARTH, new JSONArray());
        }
        writeJSONSettings();
    }

    // Settings Writer
    public static void writeJSONSettings() throws IOException {
        JSONManager.writeJSON(SETTINGS, new File(new File(Defs.JAR_POSITION).getParent() + File.separator + Defs.Resources.JSON.CLIENT_SETTINGS));
    }

    // Settings Getter
    @NotNull
    public static JSONObject getSettings() {
        return SETTINGS;
    }
}
