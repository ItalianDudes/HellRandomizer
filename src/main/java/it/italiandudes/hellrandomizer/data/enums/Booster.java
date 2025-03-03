package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum Booster {
    HELLPOD_SPACE_OPTIMIZATION("Ottimizzazione Spazio Hellpod"),
    VITALITY_ENHANCEMENT("Miglioramento della Vitalità"),
    UAV_RECON_BOOSTER("Potenziamento Ricognizione Via Drone"),
    STAMINA_ENHANCEMENT("Miglioramento della Resistenza"),
    MUSCLE_ENHANCEMENT("Miglioramento Muscolare"),
    INCREASED_REINFORCEMENT_BUDGET("Budget dei Rinforzi Aumentato"),
    FLEXIBLE_REINFORCEMENT_BUDGET("Budget dei Rinforzi Flessibile"),
    LOCALIZATION_CONFUSION("Localizzazione Confusa"),
    EXPERT_EXTRACTION_PILOT("Pilota Esperto di Estrazioni"),
    MOTIVATIONAL_SHOCKS("Shock Motivazionali"),
    EXPERIMENTAL_INFUSION("Infusione Sperimentale"),
    FIREBOMB_HELLPODS("Hellpod Incendiari"),
    DEAD_SPRINT("Sprint Mortale"),
    ARMED_RESUPPLY_PODS("Pod di Rifornimento Armati")
    ;

    // Attributes
    @NotNull private final String name;
    private boolean isEnabledBySuperEarth = false;

    // Constructors
    Booster(@NotNull final String name) {
        this.name = name;
    }

    // Methods
    public @NotNull String getName() {
        return name;
    }
    public boolean isEnabledBySuperEarth() {
        return isEnabledBySuperEarth;
    }
    public void setEnabledBySuperEarth(boolean enabledBySuperEarth) {
        isEnabledBySuperEarth = enabledBySuperEarth;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
