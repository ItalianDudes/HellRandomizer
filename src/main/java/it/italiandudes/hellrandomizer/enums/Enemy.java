package it.italiandudes.hellrandomizer.enums;

import org.jetbrains.annotations.NotNull;

public enum Enemy {
    TERMINIDS("Terminidi"),
    AUTOMATONS("Automaton"),
    ILLUMINATES("Illuminati");

    // Attributes
    @NotNull
    private final String name;

    // Constructors
    Enemy(@NotNull final String name) {
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
