package it.italiandudes.hellrandomizer.data.enums;

import org.jetbrains.annotations.NotNull;

public enum PrimaryWeapon {
    // ALWAYS ADD TO THE BOTTOM
    AR23_LIBERATOR("AR-23 Liberatore", PrimaryWeaponCategory.ASSAULT_RIFLE),
    AR23P_LIBERATOR_PENETRATOR("AR-23P Liberatore Penetrante", PrimaryWeaponCategory.ASSAULT_RIFLE),
    AR23C_LIBERATOR_PERCUSSIVE("AR-23C Liberatore a Percussione", PrimaryWeaponCategory.ASSAULT_RIFLE),
    STA52_ASSALT_RIFLE("Fucile d'Assalto StA-52", PrimaryWeaponCategory.ASSAULT_RIFLE),
    AR23A_LIBERATOR_CARBINE("AR-23A Carabina Liberatore", PrimaryWeaponCategory.ASSAULT_RIFLE),
    AR61_TENDERIZER("AR-61 Batticarne", PrimaryWeaponCategory.ASSAULT_RIFLE),
    BR14_ADJUDICATOR("BR-14 Giudicatore", PrimaryWeaponCategory.ASSAULT_RIFLE),
    R2124_CONSTITUTION("R-2124 Costituzione", PrimaryWeaponCategory.MARKSMAN_RIFLE),
    R63_DILIGENCE("R-63 Zelo", PrimaryWeaponCategory.MARKSMAN_RIFLE),
    R63CS_DILIGENCE_COUNTER_SNIPER("R-63CS Fucile Zelo da Cecchino", PrimaryWeaponCategory.MARKSMAN_RIFLE),
    PLAS39_ACCELERATOR_RIFLE("Fucile Acceleratore PLAS-39", PrimaryWeaponCategory.SNIPER_RIFLE),
    MP98_KNIGHT("MP-98 Cavaliere", PrimaryWeaponCategory.SUBMACHINE_GUN),
    STA11_SMG("Mitra StA-11", PrimaryWeaponCategory.SUBMACHINE_GUN),
    SMG32_REPRIMAND("SMG-32 Reprimenda", PrimaryWeaponCategory.SUBMACHINE_GUN),
    SMG37_DEFENDER("SMG-37 Difensore", PrimaryWeaponCategory.SUBMACHINE_GUN),
    SMG72_PUMMELER("SMG-72 Pestello", PrimaryWeaponCategory.SUBMACHINE_GUN),
    SG8_PUNISHER("SG-8 Punitore", PrimaryWeaponCategory.SHOTGUN),
    SG8S_SLUGGER("SG-8S Fucile Slug", PrimaryWeaponCategory.SHOTGUN),
    SG20_HALT("SG-20 Arresto", PrimaryWeaponCategory.SHOTGUN),
    SG451_COOKOUT("SG-451 Barbecue", PrimaryWeaponCategory.SHOTGUN),
    SG225_BREAKER("SG-225 Spezzaossa", PrimaryWeaponCategory.SHOTGUN),
    SG225SP_BREAKER_SPRAY_AND_PRAY("SG-225SP Spezzaossa Spruzza e Prega", PrimaryWeaponCategory.SHOTGUN),
    SG225IE_BREAKER_INCENDIARY("SG-225IE Spezzaossa Incendiario", PrimaryWeaponCategory.SHOTGUN),
    CB9_EXPLODING_CROSSBOW("CB-9 Balestra Esplosiva", PrimaryWeaponCategory.EXPLOSIVE),
    R36_ERUPTOR("R-36 Eruptor", PrimaryWeaponCategory.EXPLOSIVE),
    SG8P_PUNISHER_PLASMA("SG-8P Plasma Punitore", PrimaryWeaponCategory.ENERGY_BASED),
    ARC12_BLITZER("ARC-12 Blitzer", PrimaryWeaponCategory.ENERGY_BASED),
    LAS5_SCYTHE("LAS-5 Falce", PrimaryWeaponCategory.ENERGY_BASED),
    LAS16_SICKLE("LAS-16 Falcetto", PrimaryWeaponCategory.ENERGY_BASED),
    LAS17_DOUBLE_EDGE_SICKLE("LAS-17 Falce a Doppio Taglio", PrimaryWeaponCategory.ENERGY_BASED),
    PLAS1_SCORCHER("PLAS-1 Spellatore", PrimaryWeaponCategory.ENERGY_BASED),
    PLAS101_PURIFIER("PLAS-101 Purificatore", PrimaryWeaponCategory.ENERGY_BASED),
    FLAM66_TORCHER("FLAM-66 Piromane", PrimaryWeaponCategory.SPECIAL),
    JAR5_DOMINATOR("JAR-5 Dominatore", PrimaryWeaponCategory.SPECIAL),
    R6_DEADEYE("R-6 Occhio di Falco", PrimaryWeaponCategory.MARKSMAN_RIFLE);
    // ALWAYS ADD TO THE BOTTOM

    // Attributes
    @NotNull private final String name;
    @NotNull private final PrimaryWeaponCategory type;

    // Constructors
    PrimaryWeapon(@NotNull final String name, @NotNull final PrimaryWeaponCategory type) {
        this.name = name;
        this.type = type;
    }

    // Methods
    public @NotNull String getName() {
        return name;
    }
    public @NotNull PrimaryWeaponCategory getType() {
        return type;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
