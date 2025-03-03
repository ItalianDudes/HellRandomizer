package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum SecondaryWeaponRandomizationProcedure {
    NO_SECONDARY_WEAPON_RANDOMIZATION("Non Randomizzare l'Arma Secondaria"),
    RANDOMIZE_ARMOR_CATEGORY("Randomizza il Tipo di Arma Secondaria"),
    RANDOMIZE_ARMOR_BUFF("Randomizza l'Arma Secondaria");

    // Attributes
    @NotNull private final String name;

    // Constructors
    SecondaryWeaponRandomizationProcedure(@NotNull final String name) {
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
