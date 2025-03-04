package it.italiandudes.hellrandomizer.utils;

import it.italiandudes.hellrandomizer.data.HelldiverData;
import it.italiandudes.idl.common.JSONManager;
import it.italiandudes.idl.common.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public final class HelldiversDataManager {

    // Attributes
    private static HashSet<HelldiverData> HELLDIVERS_DATA = null;
    private static final File HELLDIVERS_DATA_FILE = new File(new File(Defs.JAR_POSITION).getParent() + File.separator + Defs.Resources.JSON.HELLDIVERS_DATA);
    private static final int VERSION = 1;

    // Methods
    public static void loadHelldiversDataFile() {
        if (HELLDIVERS_DATA != null) return;
        if (!HELLDIVERS_DATA_FILE.exists() || !HELLDIVERS_DATA_FILE.isFile()) {
            try {
                JSONObject object = new JSONObject();
                object.put("version", VERSION);
                object.put("data", new JSONArray());
                JSONManager.writeJSON(object, HELLDIVERS_DATA_FILE);
            } catch (IOException e) {
                Logger.log(e, Defs.LOGGER_CONTEXT);
                return;
            }
        }
        try {
            JSONObject helldiversData = JSONManager.readJSON(HELLDIVERS_DATA_FILE);
            if (helldiversData.getInt("version") != VERSION) {
                Logger.log("Version mismatch: expected " + VERSION + ", file version " + helldiversData.getInt("version"));
                return;
            }
            JSONArray data = helldiversData.getJSONArray("data");
            HELLDIVERS_DATA = new HashSet<>();
            for (int i=0; i < data.length(); i++) {
                HELLDIVERS_DATA.add(new HelldiverData(data.getJSONObject(i)));
            }
        } catch (IOException | JSONException e) {
            Logger.log(e, Defs.LOGGER_CONTEXT);
            HELLDIVERS_DATA = null;
        }
    }
    public static void writeHelldiversDataFile() throws IOException {
        if (HELLDIVERS_DATA == null) return;
        JSONObject object = new JSONObject();
        object.put("version", VERSION);
        JSONArray data = new JSONArray();
        for (HelldiverData helldiverData : HELLDIVERS_DATA) {
            data.put(helldiverData.toJSONObject());
        }
        object.put("data", data);
        JSONManager.writeJSON(object, HELLDIVERS_DATA_FILE);
    }
    public static HashSet<HelldiverData> getHelldiversData() {
        return HELLDIVERS_DATA;
    }
}
