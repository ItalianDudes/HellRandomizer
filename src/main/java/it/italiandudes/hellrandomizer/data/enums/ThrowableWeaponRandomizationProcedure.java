package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum ThrowableWeaponRandomizationProcedure {
    NO_THROWABLE_WEAPON_RANDOMIZATION("Non Randomizzare l'Arma Lanciabile"),
    RANDOMIZE_ARMOR_CATEGORY("Randomizza il Tipo di Arma Lanciabile"),
    RANDOMIZE_ARMOR_BUFF("Randomizza l'Arma Lanciabile");

    // Attributes
    @NotNull private final String name;

    // Constructors
    ThrowableWeaponRandomizationProcedure(@NotNull final String name) {
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
