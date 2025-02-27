package it.italiandudes.hellrandomizer.enums;

import org.jetbrains.annotations.NotNull;

public enum ThrowableWeapon {
    G6_FRAG("G-6 a Frammentazione", ThrowableWeaponType.STANDARD),
    G12_HIGH_EXPLOSIVE("G-12 Altamente Esplosiva", ThrowableWeaponType.STANDARD),
    G10_INCENDIARY("G-10 Incendiaria", ThrowableWeaponType.STANDARD),
    G16_IMPACT("G-16 Impatto", ThrowableWeaponType.SPECIAL),
    G13_INCENDIARY_IMPACT("G-13 Granate Incendiarie", ThrowableWeaponType.SPECIAL),
    G23_STUN("G-23 Stordente", ThrowableWeaponType.SPECIAL),
    G4_GAS("G-4 Gas", ThrowableWeaponType.SPECIAL),
    G50_SEEKER("G-50 Cercatore", ThrowableWeaponType.SPECIAL),
    G3_SMOKE("G-3 Fumogena", ThrowableWeaponType.SPECIAL),
    G123_THERMITE("G-123 Termite", ThrowableWeaponType.SPECIAL),
    K2_THROWING_KNIFE("K-2 Coltello da Lancio", ThrowableWeaponType.SPECIAL);

    // Attributes
    @NotNull private final String name;
    @NotNull private final ThrowableWeaponType type;

    // Constructors
    ThrowableWeapon(@NotNull final String name, @NotNull final ThrowableWeaponType type) {
        this.name = name;
        this.type = type;
    }

    // Methods
    public @NotNull String getName() {
        return name;
    }
    public @NotNull ThrowableWeaponType getType() {
        return type;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
