package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum PrimaryWeaponCategory {
    ASSAULT_RIFLE("Fucile d'Assalto"),
    MARKSMAN_RIFLE("Fucile di Precisione"),
    SNIPER_RIFLE("Fucile da Cecchino"),
    SUBMACHINE_GUN("Pistola Mitragliatrice"),
    SHOTGUN("Fucile"),
    EXPLOSIVE("Esplosiva"),
    ENERGY_BASED("A Energia"),
    SPECIAL("Speciale");

    // Attributes
    @NotNull private final String name;

    // Constructors
    PrimaryWeaponCategory(@NotNull final String name) {
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
