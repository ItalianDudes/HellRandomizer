package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum SecondaryWeapon {
    P2_PEACEMAKER("P-2 Pacificatore", SecondaryWeaponCategory.PISTOL),
    P19_REDEEMER("P-19 Redentore", SecondaryWeaponCategory.PISTOL),
    P113_VERDICT("P-113 Verdetto", SecondaryWeaponCategory.PISTOL),
    P4_SENATOR("P-4 Senatore", SecondaryWeaponCategory.PISTOL),
    CQC19_STUN_LANCE("CQC-19 Lancia Stordente", SecondaryWeaponCategory.MELEE),
    CQC30_STUN_BATON("CQC-30 Bastone Stordente", SecondaryWeaponCategory.MELEE),
    CQC5_COMBAT_HATCHET("CQC-5 Ascia da Battaglia", SecondaryWeaponCategory.MELEE),
    P11_STIM_PISTOL("P-11 Pistola Stim", SecondaryWeaponCategory.SPECIAL),
    SG22_BUSHWHACKER("SG-22 Schiantatopi", SecondaryWeaponCategory.SPECIAL),
    P72_CRISPER("P-72 Crisper", SecondaryWeaponCategory.SPECIAL),
    GP31_GRENADE_PISTOL("GP-31 Pistola a Granate", SecondaryWeaponCategory.SPECIAL),
    LAS7_DAGGER("LAS-7 Pugnale", SecondaryWeaponCategory.SPECIAL),
    GP31_ULTIMATUM("GP-31 Ultimatum", SecondaryWeaponCategory.SPECIAL),
    PLAS15_LOYALIST("PLAS-15 Lealista", SecondaryWeaponCategory.SPECIAL),
    LAS58_TALON("LAS-58 Artiglio", SecondaryWeaponCategory.SPECIAL);

    // Attributes
    @NotNull private final String name;
    @NotNull private final SecondaryWeaponCategory type;

    // Constructors
    SecondaryWeapon(@NotNull final String name, @NotNull final SecondaryWeaponCategory type) {
        this.name = name;
        this.type = type;
    }

    // Methods
    public @NotNull String getName() {
        return name;
    }
    public @NotNull SecondaryWeaponCategory getType() {
        return type;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
