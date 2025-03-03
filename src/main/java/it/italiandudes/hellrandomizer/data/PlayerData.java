package it.italiandudes.hellrandomizer.data;

import it.italiandudes.hellrandomizer.data.enums.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class PlayerData {

    // Attributes
    @NotNull private final String name;
    private final boolean enrolledForRandomizer;
    private final ArmorCategory @NotNull[] armorCategories;
    private final ArmorBuff @NotNull[] armorBuffs;
    private final PrimaryWeaponType @NotNull[] primaryWeaponTypes;
    private final PrimaryWeapon @NotNull[] primaryWeapons;
    private final SecondaryWeaponType @NotNull[] secondaryWeaponTypes;
    private final SecondaryWeapon @NotNull[] secondaryWeapons;
    private final ThrowableWeaponType @NotNull[] throwableWeaponTypes;
    private final ThrowableWeapon @NotNull[] throwableWeapons;
    private final Stratagem @NotNull[] stratagems;
    @NotNull private final HashSet<@NotNull Booster> boosters;

    // Constructors
    public PlayerData(
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
            this.armorBuffs = armorBuffs != null ? Arrays.stream(ArmorBuff.values()).filter(element -> !Arrays.asList(armorBuffs).contains(element)).toArray(ArmorBuff[]::new) : ArmorBuff.values();
        } else this.armorBuffs = armorBuffs != null ? armorBuffs : new ArmorBuff[] {};

        if (primaryWeaponsAsBlacklist) {
            this.primaryWeapons = primaryWeapons != null ? Arrays.stream(PrimaryWeapon.values()).filter(weapon -> !Arrays.asList(primaryWeapons).contains(weapon)).toArray(PrimaryWeapon[]::new) : PrimaryWeapon.values();
        } else this.primaryWeapons = primaryWeapons != null ? primaryWeapons : new PrimaryWeapon[] {};
        this.primaryWeaponTypes = Arrays.stream(this.primaryWeapons).map(PrimaryWeapon::getType).distinct().toArray(PrimaryWeaponType[]::new);

        if (secondaryWeaponsAsBlacklist) {
            this.secondaryWeapons = secondaryWeapons != null ? Arrays.stream(SecondaryWeapon.values()).filter(weapon -> !Arrays.asList(secondaryWeapons).contains(weapon)).toArray(SecondaryWeapon[]::new) : SecondaryWeapon.values();
        } else this.secondaryWeapons = secondaryWeapons != null ? secondaryWeapons : new SecondaryWeapon[] {};
        this.secondaryWeaponTypes = Arrays.stream(this.secondaryWeapons).map(SecondaryWeapon::getType).distinct().toArray(SecondaryWeaponType[]::new);

        if (throwableWeaponsBlacklist) {
            this.throwableWeapons = throwableWeapons != null ? Arrays.stream(ThrowableWeapon.values()).filter(weapon -> !Arrays.asList(throwableWeapons).contains(weapon)).toArray(ThrowableWeapon[]::new) : ThrowableWeapon.values();
        } else this.throwableWeapons = throwableWeapons != null ? throwableWeapons : new ThrowableWeapon[] {};
        this.throwableWeaponTypes = Arrays.stream(this.throwableWeapons).map(ThrowableWeapon::getType).distinct().toArray(ThrowableWeaponType[]::new);

        if (stratagemsAsBlacklist) {
            this.stratagems = stratagems != null ? Arrays.stream(Stratagem.values()).filter(element -> !Arrays.asList(stratagems).contains(element)).toArray(Stratagem[]::new) : Stratagem.values();
        } else this.stratagems = stratagems != null ? stratagems : new Stratagem[] {};

        if (boostersAsBlacklist) {
            this.boosters = boosters != null ? new HashSet<>(Arrays.stream(Booster.values()).filter(element -> !Arrays.asList(boosters).contains(element)).collect(Collectors.toList())) : new HashSet<>(Arrays.asList(Booster.values()));
        } else this.boosters = boosters != null ? new HashSet<>(Arrays.asList(boosters)) : new HashSet<>();
    }

    // Methods
    public @NotNull String getName() {
        return name;
    }
    public boolean isEnrolledForRandomizer() {
        return enrolledForRandomizer;
    }
    public ArmorCategory @NotNull [] getArmorCategories() {
        return armorCategories;
    }
    public ArmorBuff @NotNull [] getArmorBuffs() {
        return armorBuffs;
    }
    public PrimaryWeaponType @NotNull [] getPrimaryWeaponTypes() {
        return primaryWeaponTypes;
    }
    public PrimaryWeapon @NotNull [] getPrimaryWeapons() {
        return primaryWeapons;
    }
    public SecondaryWeaponType @NotNull [] getSecondaryWeaponTypes() {
        return secondaryWeaponTypes;
    }
    public SecondaryWeapon @NotNull [] getSecondaryWeapons() {
        return secondaryWeapons;
    }
    public ThrowableWeaponType @NotNull [] getThrowableWeaponTypes() {
        return throwableWeaponTypes;
    }
    public ThrowableWeapon @NotNull [] getThrowableWeapons() {
        return throwableWeapons;
    }
    public Stratagem @NotNull [] getStratagems() {
        return stratagems;
    }
    public @NotNull HashSet<@NotNull Booster> getBoosters() {
        return boosters;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlayerData)) return false;

        PlayerData that = (PlayerData) o;
        return isEnrolledForRandomizer() == that.isEnrolledForRandomizer() && getName().equals(that.getName()) && Arrays.equals(getArmorCategories(), that.getArmorCategories()) && Arrays.equals(getArmorBuffs(), that.getArmorBuffs()) && Arrays.equals(getPrimaryWeaponTypes(), that.getPrimaryWeaponTypes()) && Arrays.equals(getPrimaryWeapons(), that.getPrimaryWeapons()) && Arrays.equals(getSecondaryWeaponTypes(), that.getSecondaryWeaponTypes()) && Arrays.equals(getSecondaryWeapons(), that.getSecondaryWeapons()) && Arrays.equals(getThrowableWeaponTypes(), that.getThrowableWeaponTypes()) && Arrays.equals(getThrowableWeapons(), that.getThrowableWeapons()) && Arrays.equals(getStratagems(), that.getStratagems()) && getBoosters().equals(that.getBoosters());
    }
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + Boolean.hashCode(isEnrolledForRandomizer());
        result = 31 * result + Arrays.hashCode(getArmorCategories());
        result = 31 * result + Arrays.hashCode(getArmorBuffs());
        result = 31 * result + Arrays.hashCode(getPrimaryWeaponTypes());
        result = 31 * result + Arrays.hashCode(getPrimaryWeapons());
        result = 31 * result + Arrays.hashCode(getSecondaryWeaponTypes());
        result = 31 * result + Arrays.hashCode(getSecondaryWeapons());
        result = 31 * result + Arrays.hashCode(getThrowableWeaponTypes());
        result = 31 * result + Arrays.hashCode(getThrowableWeapons());
        result = 31 * result + Arrays.hashCode(getStratagems());
        result = 31 * result + getBoosters().hashCode();
        return result;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
