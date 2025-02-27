package it.italiandudes.hellrandomizer.enums;

import org.jetbrains.annotations.NotNull;

public enum PrimaryWeapon {
    AR23_LIBERATOR("AR-23 Liberatore", PrimaryWeaponType.ASSAULT_RIFLE),
    AR23P_LIBERATOR_PENETRATOR("AR-23P Liberatore Penetrante", PrimaryWeaponType.ASSAULT_RIFLE),
    AR23C_LIBERATOR_PERCUSSIVE("AR-23C Liberatore a Percussione", PrimaryWeaponType.ASSAULT_RIFLE),
    STA52_ASSALT_RIFLE("Fucile d'Assalto StA-52", PrimaryWeaponType.ASSAULT_RIFLE),
    AR23A_LIBERATOR_CARBINE("AR-23A Carabina Liberatore", PrimaryWeaponType.ASSAULT_RIFLE),
    AR61_TENDERIZER("AR-61 Batticarne", PrimaryWeaponType.ASSAULT_RIFLE),
    BR14_ADJUDICATOR("BR-14 Giudicatore", PrimaryWeaponType.ASSAULT_RIFLE),
    R2124_CONSTITUTION("R-2124 Costituzione", PrimaryWeaponType.MARKSMAN_RIFLE),
    R63_DILIGENCE("R-63 Zelo", PrimaryWeaponType.MARKSMAN_RIFLE),
    R63CS_DILIGENCE_COUNTER_SNIPER("R-63CS Fucile Zelo da Cecchino", PrimaryWeaponType.MARKSMAN_RIFLE),
    PLAS39_ACCELERATOR_RIFLE("Fucile Acceleratore PLAS-39", PrimaryWeaponType.SNIPER_RIFLE),
    MP98_KNIGHT("MP-98 Cavaliere", PrimaryWeaponType.SUBMACHINE_GUN),
    STA11_SMG("Mitra StA-11", PrimaryWeaponType.SUBMACHINE_GUN),
    SMG32_REPRIMAND("SMG-32 Reprimenda", PrimaryWeaponType.SUBMACHINE_GUN),
    SMG37_DEFENDER("SMG-37 Difensore", PrimaryWeaponType.SUBMACHINE_GUN),
    SMG72_PUMMELER("SMG-72 Pestello", PrimaryWeaponType.SUBMACHINE_GUN),
    SG8_PUNISHER("SG-8 Punitore", PrimaryWeaponType.SHOTGUN),
    SG8S_SLUGGER("SG-8S Fucile Slug", PrimaryWeaponType.SHOTGUN),
    SG20_HALT("SG-20 Arresto", PrimaryWeaponType.SHOTGUN),
    SG451_COOKOUT("SG-451 Barbecue", PrimaryWeaponType.SHOTGUN),
    SG225_BREAKER("SG-225 Spezzaossa", PrimaryWeaponType.SHOTGUN),
    SG225SP_BREAKER_SPRAY_AND_PRAY("SG-225SP Spezzaossa Spruzza e Prega", PrimaryWeaponType.SHOTGUN),
    SG225IE_BREAKER_INCENDIARY("SG-225IE Spezzaossa Incendiario", PrimaryWeaponType.SHOTGUN),
    CB9_EXPLODING_CROSSBOW("CB-9 Balestra Esplosiva", PrimaryWeaponType.EXPLOSIVE),
    R36_ERUPTOR("R-36 Eruptor", PrimaryWeaponType.EXPLOSIVE),
    SG8P_PUNISHER_PLASMA("SG-8P Plasma Punitore", PrimaryWeaponType.ENERGY_BASED),
    ARC12_BLITZER("ARC-12 Blitzer", PrimaryWeaponType.ENERGY_BASED),
    LAS5_SCYTHE("LAS-5 Falce", PrimaryWeaponType.ENERGY_BASED),
    LAS16_SICKLE("LAS-16 Falcetto", PrimaryWeaponType.ENERGY_BASED),
    LAS17_DOUBLE_EDGE_SICKLE("LAS-17 Falce a Doppio Taglio", PrimaryWeaponType.ENERGY_BASED),
    PLAS1_SCORCHER("PLAS-1 Spellatore", PrimaryWeaponType.ENERGY_BASED),
    PLAS101_PURIFIER("PLAS-101 Purificatore", PrimaryWeaponType.ENERGY_BASED),
    FLAM66_TORCHER("FLAM-66 Piromane", PrimaryWeaponType.SPECIAL),
    JAR5_DOMINATOR("JAR-5 Dominatore", PrimaryWeaponType.SPECIAL);

    // Attributes
    @NotNull private final String name;
    @NotNull private final PrimaryWeaponType type;

    // Constructors
    PrimaryWeapon(@NotNull final String name, @NotNull final PrimaryWeaponType type) {
        this.name = name;
        this.type = type;
    }

    // Methods
    public @NotNull String getName() {
        return name;
    }
    public @NotNull PrimaryWeaponType getType() {
        return type;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
