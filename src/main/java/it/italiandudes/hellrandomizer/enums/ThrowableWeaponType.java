package it.italiandudes.hellrandomizer.enums;

import org.jetbrains.annotations.NotNull;

public enum ThrowableWeaponType {
    STANDARD("Standard"),
    SPECIAL("Speciale");

    // Attributes
    @NotNull private final String name;

    // Constructors
    ThrowableWeaponType(@NotNull final String name) {
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
