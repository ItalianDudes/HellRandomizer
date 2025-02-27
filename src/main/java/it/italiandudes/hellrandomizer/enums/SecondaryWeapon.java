package it.italiandudes.hellrandomizer.enums;

import org.jetbrains.annotations.NotNull;

public enum SecondaryWeapon {
    P2_PEACEMAKER("P-2 Pacificatore", SecondaryWeaponType.PISTOL),
    P19_REDEEMER("P-19 Redentore", SecondaryWeaponType.PISTOL),
    P113_VERDICT("P-113 Verdetto", SecondaryWeaponType.PISTOL),
    P4_SENATOR("P-4 Senatore", SecondaryWeaponType.PISTOL),
    CQC19_STUN_LANCE("CQC-19 Lancia Stordente", SecondaryWeaponType.MELEE),
    CQC30_STUN_BATON("CQC-30 Bastone Stordente", SecondaryWeaponType.MELEE),
    CQC5_COMBAT_HATCHET("CQC-5 Ascia da Battaglia", SecondaryWeaponType.MELEE),
    P11_STIM_PISTOL("P-11 Pistola Stim", SecondaryWeaponType.SPECIAL),
    SG22_BUSHWHACKER("SG-22 Schiantatopi", SecondaryWeaponType.SPECIAL),
    P72_CRISPER("P-72 Crisper", SecondaryWeaponType.SPECIAL),
    GP31_GRENADE_PISTOL("GP-31 Pistola a Granate", SecondaryWeaponType.SPECIAL),
    LAS7_DAGGER("LAS-7 Pugnale", SecondaryWeaponType.SPECIAL),
    GP31_ULTIMATUM("GP-31 Ultimatum", SecondaryWeaponType.SPECIAL),
    PLAS15_LOYALIST("PLAS-15 Lealista", SecondaryWeaponType.SPECIAL);

    // Attributes
    @NotNull private final String name;
    @NotNull private final SecondaryWeaponType type;

    // Constructors
    SecondaryWeapon(@NotNull final String name, @NotNull final SecondaryWeaponType type) {
        this.name = name;
        this.type = type;
    }

    // Methods
    public @NotNull String getName() {
        return name;
    }
    public @NotNull SecondaryWeaponType getType() {
        return type;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
