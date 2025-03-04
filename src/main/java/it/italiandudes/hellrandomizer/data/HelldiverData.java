package it.italiandudes.hellrandomizer.data;

import it.italiandudes.hellrandomizer.data.enums.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class HelldiverData {

    // Attributes
    @NotNull private final String name;
    private boolean enrolledForRandomizer;
    private final ArmorCategory @NotNull[] armorCategories;
    @NotNull private final Set<@NotNull ArmorBuff> armorBuffs;
    @NotNull private final Set<@NotNull PrimaryWeaponType> primaryWeaponTypes;
    @NotNull private final Set<@NotNull PrimaryWeapon> primaryWeapons;
    @NotNull private final Set<@NotNull SecondaryWeaponType> secondaryWeaponTypes;
    @NotNull private final Set<@NotNull SecondaryWeapon> secondaryWeapons;
    @NotNull private final Set<@NotNull ThrowableWeaponType> throwableWeaponTypes;
    @NotNull private final Set<@NotNull ThrowableWeapon> throwableWeapons;
    @NotNull private final Set<@NotNull Stratagem> stratagems;
    @NotNull private final Set<@NotNull Booster> boosters;

    // Constructors
    public HelldiverData(
            @NotNull final String name, final boolean enrolledForRandomizer, final ArmorCategory @NotNull [] armorCategories,
            final boolean armorBuffsAsBlacklist, final ArmorBuff @Nullable [] armorBuffs,
            final boolean primaryWeaponsAsBlacklist, final PrimaryWeapon @Nullable [] primaryWeapons,
            final boolean secondaryWeaponsAsBlacklist, final SecondaryWeapon @Nullable [] secondaryWeapons,
            final boolean throwableWeaponsBlacklist, final ThrowableWeapon @Nullable [] throwableWeapons,
            final boolean stratagemsAsBlacklist, final Stratagem @Nullable [] stratagems,
            final boolean boostersAsBlacklist, final Booster @Nullable [] boosters) {
        this.name = name;
        this.enrolledForRandomizer = enrolledForRandomizer;
        this.armorCategories = armorCategories;

        if (armorBuffsAsBlacklist) {
            this.armorBuffs = armorBuffs != null ? Arrays.stream(ArmorBuff.values()).filter(element -> !Arrays.asList(armorBuffs).contains(element)).collect(Collectors.toSet()) : Arrays.stream(ArmorBuff.values()).collect(Collectors.toSet());
        } else this.armorBuffs = armorBuffs != null ? Arrays.stream(armorBuffs).collect(Collectors.toSet()) : new HashSet<>();

        if (primaryWeaponsAsBlacklist) {
            this.primaryWeapons = primaryWeapons != null ? Arrays.stream(PrimaryWeapon.values()).filter(weapon -> !Arrays.asList(primaryWeapons).contains(weapon)).collect(Collectors.toSet()) : Arrays.stream(PrimaryWeapon.values()).collect(Collectors.toSet());
        } else this.primaryWeapons = primaryWeapons != null ? Arrays.stream(primaryWeapons).collect(Collectors.toSet()) : new HashSet<>();
        this.primaryWeaponTypes = this.primaryWeapons.stream().map(PrimaryWeapon::getType).collect(Collectors.toSet());

        if (secondaryWeaponsAsBlacklist) {
            this.secondaryWeapons = secondaryWeapons != null ? Arrays.stream(SecondaryWeapon.values()).filter(weapon -> !Arrays.asList(secondaryWeapons).contains(weapon)).collect(Collectors.toSet()) : Arrays.stream(SecondaryWeapon.values()).collect(Collectors.toSet());
        } else this.secondaryWeapons = secondaryWeapons != null ? Arrays.stream(secondaryWeapons).collect(Collectors.toSet()) : new HashSet<>();
        this.secondaryWeaponTypes = this.secondaryWeapons.stream().map(SecondaryWeapon::getType).collect(Collectors.toSet());

        if (throwableWeaponsBlacklist) {
            this.throwableWeapons = throwableWeapons != null ? Arrays.stream(ThrowableWeapon.values()).filter(weapon -> !Arrays.asList(throwableWeapons).contains(weapon)).collect(Collectors.toSet()) : Arrays.stream(ThrowableWeapon.values()).collect(Collectors.toSet());
        } else this.throwableWeapons = throwableWeapons != null ? Arrays.stream(throwableWeapons).collect(Collectors.toSet()) : new HashSet<>();
        this.throwableWeaponTypes = this.throwableWeapons.stream().map(ThrowableWeapon::getType).collect(Collectors.toSet());

        if (stratagemsAsBlacklist) {
            this.stratagems = stratagems != null ? Arrays.stream(Stratagem.values()).filter(element -> !Arrays.asList(stratagems).contains(element)).collect(Collectors.toSet()) : Arrays.stream(Stratagem.values()).collect(Collectors.toSet());
        } else this.stratagems = stratagems != null ? Arrays.stream(stratagems).collect(Collectors.toSet()) : new HashSet<>();

        if (boostersAsBlacklist) {
            this.boosters = boosters != null ? Arrays.stream(Booster.values()).filter(element -> !Arrays.asList(boosters).contains(element)).collect(Collectors.toSet()) : Arrays.stream(Booster.values()).collect(Collectors.toSet());
        } else this.boosters = boosters != null ? Arrays.stream(boosters).collect(Collectors.toSet()) : new HashSet<>();
    }
    public HelldiverData(@NotNull final JSONObject json) throws JSONException {
        this.name = json.getString("name");
        this.enrolledForRandomizer = json.getBoolean("enrolledForRandomizer");
        JSONArray armorCategories = json.getJSONArray("armorCategories");
        this.armorCategories = new ArmorCategory[armorCategories.length()];
        for (int i = 0; i < armorCategories.length(); i++) {
            this.armorCategories[i] = ArmorCategory.values()[armorCategories.getInt(i)];
        }
        JSONArray armorBuffs = json.getJSONArray("armorBuffs");
        this.armorBuffs = new HashSet<>();
        for (int i = 0; i < armorBuffs.length(); i++) {
            this.armorBuffs.add(ArmorBuff.values()[armorBuffs.getInt(i)]);
        }
        JSONArray primaryWeapons = json.getJSONArray("primaryWeapons");
        this.primaryWeapons = new HashSet<>();
        for (int i = 0; i < primaryWeapons.length(); i++) {
            this.primaryWeapons.add(PrimaryWeapon.values()[primaryWeapons.getInt(i)]);
        }
        this.primaryWeaponTypes = this.primaryWeapons.stream().map(PrimaryWeapon::getType).collect(Collectors.toSet());
        JSONArray secondaryWeapons = json.getJSONArray("secondaryWeapons");
        this.secondaryWeapons = new HashSet<>();
        for (int i = 0; i < secondaryWeapons.length(); i++) {
            this.secondaryWeapons.add(SecondaryWeapon.values()[secondaryWeapons.getInt(i)]);
        }
        this.secondaryWeaponTypes = this.secondaryWeapons.stream().map(SecondaryWeapon::getType).collect(Collectors.toSet());
        JSONArray throwableWeapons = json.getJSONArray("throwableWeapons");
        this.throwableWeapons = new HashSet<>();
        for (int i = 0; i < throwableWeapons.length(); i++) {
            this.throwableWeapons.add(ThrowableWeapon.values()[throwableWeapons.getInt(i)]);
        }
        this.throwableWeaponTypes = this.throwableWeapons.stream().map(ThrowableWeapon::getType).collect(Collectors.toSet());
        JSONArray stratagems = json.getJSONArray("stratagems");
        this.stratagems = new HashSet<>();
        for (int i = 0; i < stratagems.length(); i++) {
            this.stratagems.add(Stratagem.values()[stratagems.getInt(i)]);
        }
        JSONArray boosters = json.getJSONArray("boosters");
        this.boosters = new HashSet<>();
        for (int i = 0; i < boosters.length(); i++) {
            this.boosters.add(Booster.values()[boosters.getInt(i)]);
        }
    }

    // Methods
    public void updatePrimaryWeaponTypes() {
        this.primaryWeaponTypes.removeIf(primaryWeaponType -> true);
        this.primaryWeaponTypes.addAll(this.primaryWeapons.stream().map(PrimaryWeapon::getType).collect(Collectors.toSet()));
    }
    public void updateSecondaryWeaponTypes() {
        this.secondaryWeaponTypes.removeIf(secondaryWeaponType -> true);
        this.secondaryWeaponTypes.addAll(this.secondaryWeapons.stream().map(SecondaryWeapon::getType).collect(Collectors.toSet()));
    }
    public void updateThrowableWeaponTypes() {
        this.throwableWeaponTypes.removeIf(throwableWeaponType -> true);
        this.throwableWeaponTypes.addAll(this.throwableWeapons.stream().map(ThrowableWeapon::getType).collect(Collectors.toSet()));
    }
    public @NotNull String getName() {
        return name;
    }
    public boolean isEnrolledForRandomizer() {
        return enrolledForRandomizer;
    }
    public void setEnrolledForRandomizer(final boolean enrolledForRandomizer) {
        this.enrolledForRandomizer = enrolledForRandomizer;
    }
    public ArmorCategory @NotNull [] getArmorCategories() {
        return armorCategories;
    }
    public @NotNull Set<@NotNull ArmorBuff> getArmorBuffs() {
        return armorBuffs;
    }
    public @NotNull Set<@NotNull PrimaryWeaponType> getPrimaryWeaponTypes() {
        return primaryWeaponTypes;
    }
    public @NotNull Set<@NotNull PrimaryWeapon> getPrimaryWeapons() {
        return primaryWeapons;
    }
    public @NotNull Set<@NotNull SecondaryWeaponType> getSecondaryWeaponTypes() {
        return secondaryWeaponTypes;
    }
    public @NotNull Set<@NotNull SecondaryWeapon> getSecondaryWeapons() {
        return secondaryWeapons;
    }
    public @NotNull Set<@NotNull ThrowableWeaponType> getThrowableWeaponTypes() {
        return throwableWeaponTypes;
    }
    public @NotNull Set<@NotNull ThrowableWeapon> getThrowableWeapons() {
        return throwableWeapons;
    }
    public @NotNull Set<@NotNull Stratagem> getStratagems() {
        return stratagems;
    }
    public @NotNull Set<@NotNull Booster> getBoosters() {
        return boosters;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HelldiverData)) return false;

        HelldiverData that = (HelldiverData) o;
        return isEnrolledForRandomizer() == that.isEnrolledForRandomizer() && getName().equals(that.getName()) && Arrays.equals(getArmorCategories(), that.getArmorCategories()) && getArmorBuffs().equals(that.getArmorBuffs()) && getPrimaryWeaponTypes().equals(that.getPrimaryWeaponTypes()) && getPrimaryWeapons().equals(that.getPrimaryWeapons()) && getSecondaryWeaponTypes().equals(that.getSecondaryWeaponTypes()) && getSecondaryWeapons().equals(that.getSecondaryWeapons()) && getThrowableWeaponTypes().equals(that.getThrowableWeaponTypes()) && getThrowableWeapons().equals(that.getThrowableWeapons()) && getStratagems().equals(that.getStratagems()) && getBoosters().equals(that.getBoosters());
    }
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + Boolean.hashCode(isEnrolledForRandomizer());
        result = 31 * result + Arrays.hashCode(getArmorCategories());
        result = 31 * result + getArmorBuffs().hashCode();
        result = 31 * result + getPrimaryWeaponTypes().hashCode();
        result = 31 * result + getPrimaryWeapons().hashCode();
        result = 31 * result + getSecondaryWeaponTypes().hashCode();
        result = 31 * result + getSecondaryWeapons().hashCode();
        result = 31 * result + getThrowableWeaponTypes().hashCode();
        result = 31 * result + getThrowableWeapons().hashCode();
        result = 31 * result + getStratagems().hashCode();
        result = 31 * result + getBoosters().hashCode();
        return result;
    }
    @NotNull
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("enrolledForRandomizer", enrolledForRandomizer);
        JSONArray armorCategories = new JSONArray();
        for (ArmorCategory armorCategory : this.armorCategories) {
            armorCategories.put(armorCategory.ordinal());
        }
        json.put("armorCategories", armorCategories);
        JSONArray armorBuffs = new JSONArray();
        for (ArmorBuff armorBuff : this.armorBuffs) {
            armorBuffs.put(armorBuff.ordinal());
        }
        json.put("armorBuffs", armorBuffs);
        JSONArray primaryWeapons = new JSONArray();
        for (PrimaryWeapon primaryWeapon : this.primaryWeapons) {
            primaryWeapons.put(primaryWeapon.ordinal());
        }
        json.put("primaryWeapons", primaryWeapons);
        JSONArray secondaryWeapons = new JSONArray();
        for (SecondaryWeapon secondaryWeapon : this.secondaryWeapons) {
            secondaryWeapons.put(secondaryWeapon.ordinal());
        }
        json.put("secondaryWeapons", secondaryWeapons);
        JSONArray throwableWeapons = new JSONArray();
        for (ThrowableWeapon throwableWeapon : this.throwableWeapons) {
            throwableWeapons.put(throwableWeapon.ordinal());
        }
        json.put("throwableWeapons", throwableWeapons);
        JSONArray stratagems = new JSONArray();
        for (Stratagem stratagem : this.stratagems) {
            stratagems.put(stratagem.ordinal());
        }
        json.put("stratagems", stratagems);
        JSONArray boosters = new JSONArray();
        for (Booster booster : this.boosters) {
            boosters.put(booster.ordinal());
        }
        json.put("boosters", boosters);
        return json;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
