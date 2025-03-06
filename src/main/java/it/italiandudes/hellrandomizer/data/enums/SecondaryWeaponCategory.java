package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum SecondaryWeaponCategory {
    PISTOL("Pistola"),
    MELEE("Corpo a Corpo"),
    SPECIAL("Speciale");

    // Attributes
    @NotNull private final String name;

    // Constructors
    SecondaryWeaponCategory(@NotNull final String name) {
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
