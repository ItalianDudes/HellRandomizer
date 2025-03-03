package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum ArmorCategory {
    LIGHT("Leggera"),
    MEDIUM("Media"),
    HEAVY("Pesante");

    // Attributes
    @NotNull private final String name;

    // Constructors
    ArmorCategory(@NotNull final String name) {
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
