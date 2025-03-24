package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum ArmorBuff {
    ADVANCED_FILTRATION("Filtro Avanzato"),
    INFLAMMABLE("Ignifugo"),
    PEAK_PHYSIQUE("Fisico Perfetto"),
    UNFLINCHING("Impassibile"),
    SIEGE_READY("A Prova di Assedio"),
    ACCLIMATED("Acclimatato"),
    SCOUT("Esploratore"),
    FORTIFIED("Fortificato"),
    MEDKIT("Kit Medico"),
    ENGINEERING_KIT("Kit da Geniere"),
    EXTRA_PADDING("Imbottitura Extra"),
    ELECTRICAL_CONDUIT("Guaina Elettrica"),
    DEMOCRACY_PROTECTS("La Democrazia Protegge"),
    SERVO_ASSISTED("Servoassistito"),
    INTEGRATED_EXPLOSIVES("Esplosivo Integrato"),
    GUNSLINGER("Pistolero");

    // Attributes
    @NotNull
    private final String name;

    // Constructors
    ArmorBuff(@NotNull final String name) {
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
