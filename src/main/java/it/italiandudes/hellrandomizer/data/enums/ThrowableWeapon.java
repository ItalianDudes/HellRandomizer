package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum ThrowableWeapon {
    // ALWAYS ADD TO THE BOTTOM
    G6_FRAG("G-6 a Frammentazione", ThrowableWeaponCategory.STANDARD),
    G12_HIGH_EXPLOSIVE("G-12 Altamente Esplosiva", ThrowableWeaponCategory.STANDARD),
    G10_INCENDIARY("G-10 Incendiaria", ThrowableWeaponCategory.STANDARD),
    G16_IMPACT("G-16 Impatto", ThrowableWeaponCategory.SPECIAL),
    G13_INCENDIARY_IMPACT("G-13 Granate Incendiarie", ThrowableWeaponCategory.SPECIAL),
    G23_STUN("G-23 Stordente", ThrowableWeaponCategory.SPECIAL),
    G4_GAS("G-4 Gas", ThrowableWeaponCategory.SPECIAL),
    G50_SEEKER("G-50 Cercatore", ThrowableWeaponCategory.SPECIAL),
    G3_SMOKE("G-3 Fumogena", ThrowableWeaponCategory.SPECIAL),
    G123_THERMITE("G-123 Termite", ThrowableWeaponCategory.SPECIAL),
    K2_THROWING_KNIFE("K-2 Coltello da Lancio", ThrowableWeaponCategory.SPECIAL),
    TED63_DYNAMITE("TED-63 Dinamite", ThrowableWeaponCategory.SPECIAL);
    // ALWAYS ADD TO THE BOTTOM

    // Attributes
    @NotNull private final String name;
    @NotNull private final ThrowableWeaponCategory type;

    // Constructors
    ThrowableWeapon(@NotNull final String name, @NotNull final ThrowableWeaponCategory type) {
        this.name = name;
        this.type = type;
    }

    // Methods
    public @NotNull String getName() {
        return name;
    }
    public @NotNull ThrowableWeaponCategory getType() {
        return type;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
