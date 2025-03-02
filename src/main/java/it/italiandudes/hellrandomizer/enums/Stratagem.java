package it.italiandudes.hellrandomizer.enums;

import org.jetbrains.annotations.NotNull;

public enum Stratagem {
    CODE_MG43_MACHINE_GUN("MG-43 Mitragliatrice", true),
    CODE_APW1_ANTI_MATERIEL_RIFLE("APW-1 Fucile Antimateriale", true),
    CODE_M105_STALWART("M-105 Vigoroso", true),
    CODE_EAT17_EXPENDABLE_ANTI_TANK("EAT-17 Anticarro Consumabile", true),
    CODE_GR8_RECOILLESS_TANK("GR-8 Fucile senza Rinculo", true, true),
    CODE_FLAM40_FLAMETHROWER("FLAM-40 Lanciafiamme", true),
    CODE_AC8_AUTOCANNON("AC-8 Cannone Automatico", true, true),
    CODE_MG206_HEAVY_MACHINE_GUN("MG-206 Mitragliatrice Pesante", true),
    CODE_RL77_AIRBUST_ROCKET_LAUNCHER("RL-77 Lanciarazzi a Esplosione Aerea", true, true),
    CODE_MLS4X_COMMANDO("MLS-4X Commando", true),
    CODE_RS422_RAILGUN("RS-422 Cannone a Rotaia", true),
    CODE_FAF14_SPEAR("FAF-14 Lancia", true, true),
    WASP_LAUNCHER("Lanciamissili V.E.S.P.A. StA-X3", true, true),
    ORBITAL_GATLING_BARRAGE("Sbarramento Orbitale Gatling"),
    ORBITAL_AIRBUST_STRIKE("Attacco Orbitale con Esplosione Aerea"),
    ORBITAL_120MM_HE_BARRAGE("Sbarramento Orbitale Esplosivo da 120 MM"),
    ORBITAL_380MM_HE_BARRAGE("Sbarramento Orbitale Esplosivo da 380 MM"),
    ORBITAL_WALKING_BARRAGE("Sbarramento Orbitale Mobile"),
    ORBITAL_LASER("Laser Orbitale"),
    ORBITAL_NAPALM_BARRAGE("Sbarramento Orbitale al Napalm"),
    ORBITAL_RAILCANNON_STRIKE("Cannone a Rotaia Orbitale"),
    EAGLE_STRAFING_RUN("Mitragliamento a Tappeto Eagle"),
    EAGLE_AIRSTRIKE("Attacco Aereo Eagle"),
    EAGLE_CLUSTER_BOMB("Bomba a Grappolo Eagle"),
    EAGLE_NAPALM_AIRSTRIKE("Attacco Aereo con Napalm Eagle"),
    CODE_LIFT850_JUMP_PACK("LIFT-850 Jetpack", false, true),
    EAGLE_SMOKE_STRIKE("Attacco Fumogeno Eagle"),
    EAGLE_110MM_ROCKET_PODS("Missili Eagle da 110 MM"),
    EAGLE_500KG_BOMB("Bomba Eagle da 500 KG"),
    CODE_M102_FAST_RECON_VEHICLE("M-102 Veicolo da Ricognizione Rapida"),
    ORBITAL_PRECISION_STRIKE("Attacco Orbitale di Precisione"),
    ORBITAL_GAS_STRIKE("Attacco Orbitale con Gas"),
    ORBITAL_EMS_STRIKE("Attacco Orbitale Elettromagnetico"),
    ORBITAL_SMOKE_STRIKE("Barriera Fumogena Orbitale"),
    CODE_EMG101_HMG_EMPLACEMENT("E/MG-101 Postazione per Mitragliatrice Pesante"),
    CODE_FX12_SHIELD_GENERATOR_RELAY("FX-12 Relay Generatore di Scudi"),
    CODE_AARC3_TESLA_TOWER("A/ARC-3 Torre Tesla"),
    CODE_MD6_ANTI_PERSONNEL_MINEFIELD("MD-6 Campo di Mine Antiuomo"),
    CODE_B1_SUPPLY_PACK("B-1 Pacco Rifornimenti", false, true),
    CODE_GL21_GRENADE_LAUNCHER("GL-21 Lanciagranate", true),
    CODE_LAS98_LASER_CANNON("LAS-98 Cannone Laser", true),
    CODE_MDI4_INCENDIARY_MINES("MD-I4 Mine Incendiarie"),
    CODE_AXLAS5_GUARD_DOG_ROVER("AX/LAS-5 Rover \"Cane da Guardia\"", false, true),
    CODE_SH20_BALLISTIC_SHIELD_BACKPACK("SH-20 Zaino Scudo Antiproiettile", false, true),
    CODE_ARC3_ARC_THROWER("ARC-3 Fulminatore", true),
    CODE_MD17_ANTI_TANK_MINES("MD-17 Mine Anticarro"),
    CODE_LAS99_QUASAR_CANNON("LAS-99 Cannone Quasar", true),
    CODE_SH32_SHIELD_GENERATOR_PACK("SH-32 Pacchetto Generatore di Scudi", false, true),
    CODE_MD8_GAS_MINES("MD-8 Mine a Gas"),
    CODE_AMG43_MACHINE_GUN_SENTRY("A/MG-43 Sentinella con Mitragliatrice"),
    CODE_AG16_GATLING_SENTRY("A/G-16 Sentinella con Gatling"),
    CODE_AM12_MORTAR_SENTRY("A/M-12 Sentinella con Mortaio"),
    CODE_AXAR23_GUARD_DOG("AX/AR-23 \"Cane da Guardia\"", false, true),
    CODE_AAC8_AUTOCANNON_SENTRY("A/AC-8 Sentinella con Cannone Automatico"),
    CODE_AMLS4X_ROCKET_SENTRY("A/MLS-4X Sentinella con Razzi"),
    CODE_AM23_EMS_MORTAR_SENTRY("A/M-23 Sentinella con Mortaio Elettromagnetico"),
    CODE_EXO45_PATRIOT_EXOSUIT("EXO-45 Esoscheletro Patriota"),
    CODE_EXO49_EMANCIPATOR_EXOSUIT("EXO-49 Esoscheletro Emancipatore"),
    CODE_TX41_STERILIZER("TX-41 Sterilizzatore", true),
    CODE_AXTX13_GUARD_DOG_DOG_BREATH("AX/TX-13 Alitosi Canina", false, true),
    CODE_SH51_DIRECTIONAL_SHIELD("SH-51 Scudo Direzionale", false, true),
    CODE_AFLAM40_FLAME_SENTRY("A/FLAM-40 Sentinella Fiammeggiante"),
    CODE_EAT12_ANTI_TANK_EMPLACEMENT("E/AT-12 Postazione Anticarro"),
    CODE_B100_PORTABLE_HELLBOMB("B-100 Hellbomb Portatile", false, true);

    // Attributes
    @NotNull private final String name;
    private boolean isEnabledBySuperEarth = false;
    private final boolean isSupportWeapon;
    private final boolean hasBackpack;

    // Constructors
    Stratagem(@NotNull final String name) {
        this(name, false, false);
    }
    Stratagem(@NotNull final String name, final boolean isSupportWeapon) {
        this(name, isSupportWeapon, false);
    }
    Stratagem(@NotNull final String name, final boolean isSupportWeapon, final boolean hasBackpack) {
        this.name = name;
        this.isSupportWeapon = isSupportWeapon;
        this.hasBackpack = hasBackpack;
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
    public boolean isSupportWeapon() {
        return isSupportWeapon;
    }
    public boolean hasBackpack() {
        return hasBackpack;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
