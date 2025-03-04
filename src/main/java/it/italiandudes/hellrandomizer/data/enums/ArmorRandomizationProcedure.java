package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum ArmorRandomizationProcedure {
    NO_ARMOR_RANDOMIZATION("Non Randomizzare le Armature"),
    RANDOMIZE_ARMOR_CATEGORY("Randomizza la Categoria dell'Armatura"),
    RANDOMIZE_ARMOR_BUFF("Randomizza l'Effetto dell'Armatura"),
    RANDOMIZE_ARMOR_BUFF_OR_CATEGORY("Randomizza o la Categoria o l'Effetto dell'Armatura");

    // Attributes
    @NotNull private final String name;

    // Constructors
    ArmorRandomizationProcedure(@NotNull final String name) {
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
