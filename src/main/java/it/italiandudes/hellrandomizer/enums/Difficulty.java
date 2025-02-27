package it.italiandudes.hellrandomizer.enums;

import org.jetbrains.annotations.NotNull;

public enum Difficulty {
    TRIVIAL("1 - Facilissimo"),
    EASY("2 - Facile"),
    MEDIUM("3 - Medio"),
    CHALLENGING("4 - Impegnativo"),
    HARD("5 - Difficile"),
    EXTREME("6 - Estremo"),
    SUICIDE_MISSION("7 - Missione Suicida"),
    IMPOSSIBLE("8 - Impossibile"),
    HELLDIVE("9 - Helldive"),
    SUPER_HELLDIVE("10 - Super Helldive");

    // Attributes
    @NotNull private final String name;

    // Constructors
    Difficulty(@NotNull final String name) {
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
