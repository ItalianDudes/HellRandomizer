package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum StratagemsRandomizationProcedure {
    NO_STRATAGEMS_RANDOMIZATION("Non Randomizzare gli Stratagemmi"),
    RANDOMIZE_STRATAGEMS("Randomizza gli Stratagemmi senza Garanzie"),
    AT_LEAST_ONE_SUPPORT_WEAPON("Garantisci almeno un'Arma di Supporto"),
    ONLY_ONE_SUPPORT_WEAPON("Garantisci una sola Arma di Supporto"),
    AT_LEAST_ONE_BACKPACK("Garantisci almeno uno Zaino"),
    ONLY_ONE_BACKPACK("Garantisci un solo Zaino"),
    AT_LEAST_ONE_SUPPORT_WEAPON_AT_LEAST_ONE_BACKPACK("Garantisci almeno un'Arma di Supporto e almeno uno Zaino"),
    ONLY_ONE_SUPPORT_WEAPON_ONLY_ONE_BACKPACK("Garantisci una sola Arma di Supporto e un solo Zaino"),
    ONLY_ONE_SUPPORT_WEAPON_AT_LEAST_ONE_BACKPACK("Garantisci una sola Arma di Supporto e almeno uno Zaino"),
    AT_LEAST_ONE_SUPPORT_WEAPON_ONLY_ONE_BACKPACK("Garantisci almeno un'Arma di Supporto e un solo Zaino");

    // Attributes
    @NotNull private final String name;

    // Constructors
    StratagemsRandomizationProcedure(@NotNull final String name) {
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
