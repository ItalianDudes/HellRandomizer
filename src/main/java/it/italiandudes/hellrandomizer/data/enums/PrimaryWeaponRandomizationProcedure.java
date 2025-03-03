package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum PrimaryWeaponRandomizationProcedure {
    NO_PRIMARY_WEAPON_RANDOMIZATION("Non Randomizzare l'Arma Primaria"),
    RANDOMIZE_ARMOR_CATEGORY("Randomizza il Tipo di Arma Primaria"),
    RANDOMIZE_ARMOR_BUFF("Randomizza l'Arma Primaria");

    // Attributes
    @NotNull private final String name;

    // Constructors
    PrimaryWeaponRandomizationProcedure(@NotNull final String name) {
        this.name = name;
    }

    // Methods
    public @NotNull String getName() {
        return name;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
