package it.italiandudes.hellrandomizer.utils;

import it.italiandudes.hellrandomizer.HellRandomizer;
import it.italiandudes.hellrandomizer.data.PlayerData;
import it.italiandudes.hellrandomizer.data.enums.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public final class Defs {

    // App File Name
    public static final String APP_FILE_NAME = "HellRandomizer";

    // Start Args
    public static final String START_ARG_NOGUI = "-nogui";

    // Logger Context
    public static final String LOGGER_CONTEXT = "HellRandomizer";

    // Jar App Position
    public static final String JAR_POSITION;
    static {
        try {
            JAR_POSITION = new File(HellRandomizer.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // JSON Settings
    public static final class SettingsKeys {
        public static final String RANDOMIZE_ENEMY = "randomizeEnemy";
        public static final String ENEMY_BLACKLIST = "enemyBlacklist";
        public static final String RANDOMIZE_DIFFICULTY = "randomizeDifficulty";
        public static final String MINIMUM_DIFFICULTY = "minimumDifficulty";
        public static final String MAXIMUM_DIFFICULTY = "maximumDifficulty";
        public static final String ARMOR_RANDOMIZATION_PROCEDURE = "armorRandomizationProcedure";
        public static final String PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE = "primaryWeaponRandomizationProcedure";
        public static final String SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE = "secondaryWeaponRandomizationProcedure";
        public static final String THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE = "throwableWeaponRandomizationProcedure";
        public static final String STRATAGEMS_RANDOMIZATION_PROCEDURE = "stratagemsRandomizationProcedure";
        public static final String STRATAGEMS_ENABLED_BY_SUPER_EARTH = "stratagemsEnabledBySuperEarth";
        public static final String RANDOMIZE_BOOSTERS = "randomizeBoosters";
        public static final String BOOSTERS_ENABLED_BY_SUPER_EARTH = "boostersEnabledBySuperEarth";
    }

    // Resources Location
    public static final class Resources {

        // Project Resources Root
        public static final String PROJECT_RESOURCES_ROOT = "/it/italiandudes/hellrandomizer/resources/";

        //Resource Getters
        public static URL get(@NotNull final String resourceConst) {
            return Objects.requireNonNull(HellRandomizer.class.getResource(resourceConst));
        }
        public static InputStream getAsStream(@NotNull final String resourceConst) {
            return Objects.requireNonNull(HellRandomizer.class.getResourceAsStream(resourceConst));
        }
        // JSON
        public static final class JSON {
            public static final String CLIENT_SETTINGS = "settings.json";
            public static final String DEFAULT_JSON_SETTINGS = PROJECT_RESOURCES_ROOT + "json/" + CLIENT_SETTINGS;
        }
    }

    /*
    * Qui puoi modificare i settaggi dell'Helldivers 2 Randomizer.
    * Ogni settaggio è spiegato attraverso un commento.
    * Assicurati di modificare i valori seguendo le linee guida dei commenti.
    * Valori non validi potrebbero non essere gestiti correttamente e causare un crash del randomizer.
    * */

    // Dati Giocatori
    /*
    * Decisamente l'unico settaggio tedioso da fare.
    * Contiene una mappa di tutti i giocatori e dell'equipaggiamento che possiedono.
    * Questo settaggio difatti indica per ogni giocatore presente:
    * - Categorie di armature possedute (Leggero, Medio, Pesante)
    * - Buff armature possedute
    * - Categorie di armi primarie possedute
    * - Categorie di armi secondarie possedute
    * - Categorie di lanciabili possedute
    * - Stratagemmi posseduti
    * - Booster posseduti
    * Questa lunga lista serve tutte le informazioni sui giocatori presenti.
    * Escluse le categorie di armature, è possibile dire per ogni singola lista se va trattata come una whitelist (lista di posseduti) o come una blacklist (lista di NON posseduti).
    * Il criterio di scelta è lasciato all'utente per scrivere meno.
    * Di seguito un esempio di cosa scrivere:
    * new HashSet<>(Arrays.asList(
    *         new PlayerData(
    *                 "Esempio",
    *                 new ArmorCategory[] {
    *                         ArmorCategory.LIGHT,
    *                         ArmorCategory.HEAVY
    *                 },
    *                 true,
    *                 new ArmorBuff[] {
    *                         ArmorBuff.ADVANCED_FILTRATION,
    *                         ArmorBuff.UNFLINCHING
    *                 },
    *                 false, new PrimaryWeaponType[] {PrimaryWeaponType.ASSAULT_RIFLE}, // Significa che il giocatore possiede solo fucili d'assalto
    *                 true, new SecondaryWeaponType[] {SecondaryWeaponType.MELEE}, // Significa che il giocatore non possiede armi corpo a corpo secondarie
    *                 true, null, // Significa che il giocatore in questione possiede tutte le categorie di armi speciali
    *                 true,
    *                 new Stratagem[] {
    *                         Stratagem.STERILIZER,
    *                         Stratagem.GUARD_DOG_DOG_BREATH,
    *                         Stratagem.PORTABLE_HELLBOMB
    *                 },
    *                 false,
    *                 new Booster[] {
    *                         Booster.MOTIVATIONAL_SHOCKS
    *                 })
    * ));
    * */
    @NotNull
    public static final HashSet<PlayerData> PLAYERS_DATA = new HashSet<>(Arrays.asList(
            new PlayerData(
                    "Alessio", true,
                    ArmorCategory.values(),
                    true,
                    new ArmorBuff[] {
                            ArmorBuff.ADVANCED_FILTRATION,
                            ArmorBuff.PEAK_PHYSIQUE,
                            ArmorBuff.UNFLINCHING
                    },
                    true,
                    new PrimaryWeapon[] {
                            PrimaryWeapon.STA52_ASSALT_RIFLE,
                            PrimaryWeapon.AR23A_LIBERATOR_CARBINE,
                            PrimaryWeapon.AR61_TENDERIZER,
                            PrimaryWeapon.SMG32_REPRIMAND,
                            PrimaryWeapon.SMG72_PUMMELER,
                            PrimaryWeapon.SG20_HALT,
                            PrimaryWeapon.PLAS101_PURIFIER
                    },
                    true,
                    new SecondaryWeapon[] {
                            SecondaryWeapon.P113_VERDICT,
                            SecondaryWeapon.CQC30_STUN_BATON,
                            SecondaryWeapon.CQC5_COMBAT_HATCHET,
                            SecondaryWeapon.P11_STIM_PISTOL,
                            SecondaryWeapon.SG22_BUSHWHACKER,
                            SecondaryWeapon.PLAS15_LOYALIST
                    },
                    true,
                    new ThrowableWeapon[] {
                            ThrowableWeapon.G13_INCENDIARY_IMPACT,
                            ThrowableWeapon.G4_GAS,
                            ThrowableWeapon.K2_THROWING_KNIFE
                    },
                    true,
                    new Stratagem[] {
                            Stratagem.CODE_TX41_STERILIZER,
                            Stratagem.CODE_AXTX13_GUARD_DOG_DOG_BREATH
                    },
                    true,
                    new Booster[] {
                            Booster.MOTIVATIONAL_SHOCKS,
                            Booster.DEAD_SPRINT,
                            Booster.EXPERIMENTAL_INFUSION
                    }
            ),
            new PlayerData(
                    "Jennifer", false,
                    ArmorCategory.values(),
                    false,
                    new ArmorBuff[] {
                            ArmorBuff.SCOUT,
                            ArmorBuff.ACCLIMATED,
                            ArmorBuff.DEMOCRACY_PROTECTS,
                            ArmorBuff.ENGINEERING_KIT,
                            ArmorBuff.MEDKIT,
                            ArmorBuff.EXTRA_PADDING,
                            ArmorBuff.FORTIFIED
                    },
                    true,
                    new PrimaryWeapon[] {
                            PrimaryWeapon.AR23C_LIBERATOR_PERCUSSIVE,
                            PrimaryWeapon.STA52_ASSALT_RIFLE,
                            PrimaryWeapon.AR23A_LIBERATOR_CARBINE,
                            PrimaryWeapon.AR61_TENDERIZER,
                            PrimaryWeapon.SMG32_REPRIMAND,
                            PrimaryWeapon.SMG72_PUMMELER,
                            PrimaryWeapon.SG20_HALT,
                            PrimaryWeapon.SG451_COOKOUT,
                            PrimaryWeapon.SG225IE_BREAKER_INCENDIARY,
                            PrimaryWeapon.CB9_EXPLODING_CROSSBOW,
                            PrimaryWeapon.R36_ERUPTOR,
                            PrimaryWeapon.SG8P_PUNISHER_PLASMA,
                            PrimaryWeapon.ARC12_BLITZER,
                            PrimaryWeapon.LAS16_SICKLE,
                            PrimaryWeapon.LAS17_DOUBLE_EDGE_SICKLE,
                            PrimaryWeapon.PLAS101_PURIFIER,
                            PrimaryWeapon.FLAM66_TORCHER,
                            PrimaryWeapon.JAR5_DOMINATOR
                    },
                    false,
                    new SecondaryWeapon[] {
                            SecondaryWeapon.P2_PEACEMAKER,
                            SecondaryWeapon.P19_REDEEMER
                    },
                    true,
                    new ThrowableWeapon[] {
                            ThrowableWeapon.G10_INCENDIARY,
                            ThrowableWeapon.G13_INCENDIARY_IMPACT,
                            ThrowableWeapon.G23_STUN,
                            ThrowableWeapon.G4_GAS,
                            ThrowableWeapon.G50_SEEKER,
                            ThrowableWeapon.K2_THROWING_KNIFE
                    },
                    true,
                    new Stratagem[] {
                            Stratagem.CODE_RL77_AIRBUST_ROCKET_LAUNCHER,
                            Stratagem.ORBITAL_NAPALM_BARRAGE,
                            Stratagem.EAGLE_SMOKE_STRIKE,
                            Stratagem.EAGLE_110MM_ROCKET_PODS,
                            Stratagem.ORBITAL_GAS_STRIKE,
                            Stratagem.ORBITAL_EMS_STRIKE,
                            Stratagem.ORBITAL_SMOKE_STRIKE,
                            Stratagem.CODE_EMG101_HMG_EMPLACEMENT,
                            Stratagem.CODE_FX12_SHIELD_GENERATOR_RELAY,
                            Stratagem.CODE_AARC3_TESLA_TOWER,
                            Stratagem.CODE_LAS98_LASER_CANNON,
                            Stratagem.CODE_SH20_BALLISTIC_SHIELD_BACKPACK,
                            Stratagem.CODE_ARC3_ARC_THROWER,
                            Stratagem.CODE_MD17_ANTI_TANK_MINES,
                            Stratagem.CODE_SH32_SHIELD_GENERATOR_PACK,
                            Stratagem.CODE_EXO45_PATRIOT_EXOSUIT,
                            Stratagem.CODE_TX41_STERILIZER,
                            Stratagem.CODE_AXTX13_GUARD_DOG_DOG_BREATH,
                            Stratagem.CODE_SH51_DIRECTIONAL_SHIELD,
                            Stratagem.CODE_AFLAM40_FLAME_SENTRY,
                            Stratagem.CODE_B100_PORTABLE_HELLBOMB,
                            Stratagem.CODE_MD8_GAS_MINES

                    },
                    false,
                    new Booster[] {
                            Booster.VITALITY_ENHANCEMENT,
                            Booster.INCREASED_REINFORCEMENT_BUDGET,
                            Booster.HELLPOD_SPACE_OPTIMIZATION,
                            Booster.UAV_RECON_BOOSTER,
                            Booster.MUSCLE_ENHANCEMENT,
                            Booster.STAMINA_ENHANCEMENT,
                            Booster.EXPERT_EXTRACTION_PILOT
                    }
            ),
            new PlayerData(
                    "Manta", true,
                    ArmorCategory.values(),
                    true,
                    new ArmorBuff[] {
                            ArmorBuff.PEAK_PHYSIQUE
                    },
                    true,
                    new PrimaryWeapon[] {
                            PrimaryWeapon.STA52_ASSALT_RIFLE,
                            PrimaryWeapon.AR23A_LIBERATOR_CARBINE,
                            PrimaryWeapon.CB9_EXPLODING_CROSSBOW,
                            PrimaryWeapon.ARC12_BLITZER
                    },
                    true,
                    new SecondaryWeapon[] {
                            SecondaryWeapon.CQC30_STUN_BATON,
                            SecondaryWeapon.CQC5_COMBAT_HATCHET,
                            SecondaryWeapon.SG22_BUSHWHACKER,
                            SecondaryWeapon.P72_CRISPER,
                            SecondaryWeapon.LAS7_DAGGER
                    },
                    true,
                    new ThrowableWeapon[] {
                            ThrowableWeapon.K2_THROWING_KNIFE
                    },
                    true,
                    new Stratagem[] {
                            Stratagem.CODE_AFLAM40_FLAME_SENTRY
                    },
                    true,
                    new Booster[] {
                            Booster.EXPERIMENTAL_INFUSION
                    }
            ),
            new PlayerData(
                    "Tonno", false,
                    ArmorCategory.values(),
                    true,
                    new ArmorBuff[] {
                            ArmorBuff.ADVANCED_FILTRATION,
                            ArmorBuff.PEAK_PHYSIQUE,
                            ArmorBuff.UNFLINCHING,
                            ArmorBuff.SERVO_ASSISTED
                    },
                    true,
                    new PrimaryWeapon[] {
                            PrimaryWeapon.STA52_ASSALT_RIFLE,
                            PrimaryWeapon.AR23A_LIBERATOR_CARBINE,
                            PrimaryWeapon.AR61_TENDERIZER,
                            PrimaryWeapon.MP98_KNIGHT,
                            PrimaryWeapon.SMG32_REPRIMAND,
                            PrimaryWeapon.SMG72_PUMMELER,
                            PrimaryWeapon.SG20_HALT,
                            PrimaryWeapon.SG225IE_BREAKER_INCENDIARY,
                            PrimaryWeapon.LAS17_DOUBLE_EDGE_SICKLE,
                            PrimaryWeapon.PLAS101_PURIFIER,
                            PrimaryWeapon.JAR5_DOMINATOR
                    },
                    true,
                    new SecondaryWeapon[] {
                            SecondaryWeapon.P113_VERDICT,
                            SecondaryWeapon.CQC30_STUN_BATON,
                            SecondaryWeapon.CQC5_COMBAT_HATCHET,
                            SecondaryWeapon.P11_STIM_PISTOL,
                            SecondaryWeapon.SG22_BUSHWHACKER,
                            SecondaryWeapon.GP31_ULTIMATUM,
                            SecondaryWeapon.PLAS15_LOYALIST
                    },
                    true,
                    new ThrowableWeapon[] {
                            ThrowableWeapon.G10_INCENDIARY,
                            ThrowableWeapon.G13_INCENDIARY_IMPACT,
                            ThrowableWeapon.G4_GAS,
                            ThrowableWeapon.G50_SEEKER,
                            ThrowableWeapon.K2_THROWING_KNIFE
                    },
                    true,
                    new Stratagem[] {
                            Stratagem.CODE_TX41_STERILIZER,
                            Stratagem.CODE_AXTX13_GUARD_DOG_DOG_BREATH,
                            Stratagem.CODE_B100_PORTABLE_HELLBOMB
                    },
                    true,
                    new Booster[] {
                            Booster.FLEXIBLE_REINFORCEMENT_BUDGET,
                            Booster.MOTIVATIONAL_SHOCKS,
                            Booster.EXPERIMENTAL_INFUSION,
                            Booster.FIREBOMB_HELLPODS,
                            Booster.DEAD_SPRINT
                    }
            ),
            new PlayerData(
                    "Andrea", true,
                    ArmorCategory.values(),
                    true,
                    new ArmorBuff[] {
                            ArmorBuff.ADVANCED_FILTRATION,
                            ArmorBuff.INFLAMMABLE,
                            ArmorBuff.PEAK_PHYSIQUE,
                            ArmorBuff.ELECTRICAL_CONDUIT
                    },
                    true,
                    new PrimaryWeapon[] {
                            PrimaryWeapon.STA52_ASSALT_RIFLE,
                            PrimaryWeapon.AR23A_LIBERATOR_CARBINE,
                            PrimaryWeapon.AR61_TENDERIZER,
                            PrimaryWeapon.BR14_ADJUDICATOR,
                            PrimaryWeapon.SMG72_PUMMELER,
                            PrimaryWeapon.SG451_COOKOUT,
                            PrimaryWeapon.CB9_EXPLODING_CROSSBOW,
                            PrimaryWeapon.R36_ERUPTOR,
                            PrimaryWeapon.SG8P_PUNISHER_PLASMA,
                            PrimaryWeapon.ARC12_BLITZER,
                            PrimaryWeapon.LAS16_SICKLE,
                            PrimaryWeapon.PLAS101_PURIFIER,
                            PrimaryWeapon.FLAM66_TORCHER
                    },
                    true,
                    new SecondaryWeapon[] {
                            SecondaryWeapon.P113_VERDICT,
                            SecondaryWeapon.CQC19_STUN_LANCE,
                            SecondaryWeapon.P11_STIM_PISTOL,
                            SecondaryWeapon.SG22_BUSHWHACKER,
                            SecondaryWeapon.P72_CRISPER,
                            SecondaryWeapon.GP31_GRENADE_PISTOL,
                            SecondaryWeapon.LAS7_DAGGER
                    },
                    true,
                    new ThrowableWeapon[] {
                            ThrowableWeapon.G13_INCENDIARY_IMPACT,
                            ThrowableWeapon.G23_STUN,
                            ThrowableWeapon.G4_GAS,
                            ThrowableWeapon.G123_THERMITE,
                            ThrowableWeapon.K2_THROWING_KNIFE
                    },
                    true,
                    new Stratagem[] {
                            Stratagem.CODE_TX41_STERILIZER,
                            Stratagem.CODE_AXTX13_GUARD_DOG_DOG_BREATH,
                            Stratagem.CODE_SH51_DIRECTIONAL_SHIELD,
                            Stratagem.CODE_AFLAM40_FLAME_SENTRY,
                            Stratagem.CODE_EAT12_ANTI_TANK_EMPLACEMENT,
                            Stratagem.CODE_B100_PORTABLE_HELLBOMB
                    },
                    true,
                    new Booster[] {
                            Booster.LOCALIZATION_CONFUSION,
                            Booster.EXPERT_EXTRACTION_PILOT,
                            Booster.MOTIVATIONAL_SHOCKS,
                            Booster.EXPERIMENTAL_INFUSION,
                            Booster.FIREBOMB_HELLPODS,
                            Booster.ARMED_RESUPPLY_PODS
                    }
            ),
            new PlayerData(
                    "Edoardo", false,
                    ArmorCategory.values(),
                    false,
                    new ArmorBuff[] {
                            ArmorBuff.SCOUT,
                            ArmorBuff.DEMOCRACY_PROTECTS,
                            ArmorBuff.ENGINEERING_KIT,
                            ArmorBuff.MEDKIT,
                            ArmorBuff.EXTRA_PADDING,
                            ArmorBuff.FORTIFIED
                    },
                    false,
                    new PrimaryWeapon[] {
                            PrimaryWeapon.AR23_LIBERATOR,
                            PrimaryWeapon.R2124_CONSTITUTION,
                            PrimaryWeapon.R63_DILIGENCE,
                            PrimaryWeapon.SMG37_DEFENDER,
                            PrimaryWeapon.SG8_PUNISHER,
                            PrimaryWeapon.LAS5_SCYTHE
                    },
                    false,
                    new SecondaryWeapon[] {
                            SecondaryWeapon.P2_PEACEMAKER,
                            SecondaryWeapon.P19_REDEEMER
                    },
                    false,
                    new ThrowableWeapon[] {
                            ThrowableWeapon.G6_FRAG,
                            ThrowableWeapon.G12_HIGH_EXPLOSIVE
                    },
                    false,
                    new Stratagem[] {
                            Stratagem.CODE_MG43_MACHINE_GUN,
                            Stratagem.ORBITAL_380MM_HE_BARRAGE,
                            Stratagem.EAGLE_STRAFING_RUN,
                            Stratagem.EAGLE_AIRSTRIKE,
                            Stratagem.EAGLE_NAPALM_AIRSTRIKE,
                            Stratagem.ORBITAL_PRECISION_STRIKE,
                            Stratagem.CODE_AM23_EMS_MORTAR_SENTRY,
                            Stratagem.CODE_AM12_MORTAR_SENTRY,
                            Stratagem.CODE_AXAR23_GUARD_DOG
                    },
                    false,
                    new Booster[] {
                            Booster.HELLPOD_SPACE_OPTIMIZATION,
                            Booster.VITALITY_ENHANCEMENT
                    }
            )
    ));

    // Fazione
    /*
     * Indica se randomizzare o no quale nemico combattere.
     * Scelte: true | false
     * DEFAULT: true
     * */
    public static final boolean RANDOMIZE_ENEMY = false;

    /*
     * Permette di rimuovere delle fazioni di nemici dal Randomizer.
     * Inserire tutte le fazioni nella lista disattiverà il randomizer della fazione.
     *
     * DEFAULT: {}
     * */
    public static final Enemy @NotNull [] ENEMY_BLACKLIST = {};

    // Difficoltà
    /*
     * Indica se randomizzare o no la difficoltà del gioco.
     * Scelte: true | false
     * DEFAULT: true
     * */
    public static final boolean RANDOMIZE_DIFFICULTY = false;

    /*
    * Permette di impostare il range di difficoltà che il Randomizer può randomizzare.
    * MIN_DIFFICULTY rappresenta la difficoltà minima che può uscire. Minimo: 1
    * MAX_DIFFICULTY rappresenta la difficoltà massima che può uscire. Massimo: 10
    * MIN_DIFFICULTY deve essere sempre minore o uguale a MAX_DIFFICULTY.
    * */
    public static final int MIN_DIFFICULTY = 6;
    public static final int MAX_DIFFICULTY = 7;

    // Armature
    /*
    * Indica quale procedura utilizzare per randomizzare le armature:
    * -1: Non randomizzare l'armatura.
    * 0: Per ogni giocatore verrà randomizzato o la categoria armatura da usare oppure il perk da usare.
    * 1: Per ogni giocatore verrà randomizzata la categoria armatura da usare, lasciando al giocatore libertà sul perk.
    * 2: Per ogni giocatore verrà randomizzato il perk da avere sull'armatura, lasciando al giocatore libertà sulla categoria armatura.
    *
    * DEFAULT: 0
    * */
    public static final int ARMOR_RANDOMIZATION_PROCEDURE = 2;


    // Weapons
    /*
    * Indica quale procedura utilizzare per randomizzare l'arma primaria:
    * -1: Non randomizzare l'arma primaria.
    * 0: Randomizza la categoria dell'arma primaria da usare.
    * 1: Randomizza l'arma primaria dalla lista di armi primarie del giocatore.
    *
    * DEFAULT: 1
    * */
    public static final int PRIMARY_WEAPON_RANDOMIZATION_PROCEDURE = 1;

    /*
     * Indica quale procedura utilizzare per randomizzare l'arma secondaria:
     * -1: Non randomizzare l'arma secondaria.
     * 0: Randomizza la categoria dell'arma secondaria da usare.
     * 1: Randomizza l'arma secondaria dalla lista di armi secondarie del giocatore.
     *
     * DEFAULT: 1
     * */
    public static final int SECONDARY_WEAPON_RANDOMIZATION_PROCEDURE = 1;

    /*
     * Indica quale procedura utilizzare per randomizzare l'arma lanciabile:
     * -1: Non randomizzare l'arma lanciabile.
     * 0: Randomizza la categoria dell'arma lanciabile da usare.
     * 1: Randomizza l'arma lanciabile dalla lista di armi lanciabili del giocatore.
     *
     * DEFAULT: 1
     * */
    public static final int THROWABLE_WEAPON_RANDOMIZATION_PROCEDURE = 1;

    // Stratagems
    /*
     * Indica quale procedura utilizzare per randomizzare gli stratagemmi.
     * Ogni criterio escluso il -1 previene la randomizzazione di doppioni e/o di stratagemmi attivati dalla Super Terra.
     * -1: Non randomizzare gli stratagemmi.
     * 0: Per ogni giocatore verranno randomizzati gli stratagemmi senza fornire ALCUNA garanzia di cosa venga randomizzato.
     * 1: Per ogni giocatore verranno randomizzati gli stratagemmi fornendo la garanzia di ALMENO un'arma di supporto.
     * 2: Per ogni giocatore verranno randomizzati gli stratagemmi fornendo la garanzia di UNA SOLA arma di supporto.
     * 3: Per ogni giocatore verranno randomizzati gli stratagemmi fornendo la garanzia di ALMENO uno zaino.
     * 4: Per ogni giocatore verranno randomizzati gli stratagemmi fornendo la garanzia di UN SOLO zaino.
     * 5: Per ogni giocatore verranno randomizzati gli stratagemmi fornendo la garanzia di ALMENO un'arma di supporto e ALMENO uno zaino.
     * 6: Per ogni giocatore verranno randomizzati gli stratagemmi fornendo la garanzia di UNA SOLA arma di supporto e UN SOLO zaino.
     * 7: Per ogni giocatore verranno randomizzati gli stratagemmi fornendo la garanzia di UNA SOLA arma di supporto e ALMENO uno zaino.
     * 8: Per ogni giocatore verranno randomizzati gli stratagemmi fornendo la garanzia di ALMENO un'arma di supporto e UN SOLO zaino.
     *
     * DEFAULT: 0
     * */
    public static final int STRATAGEMS_RANDOMIZATION_PROCEDURE = 6;

    /*
    * Indica quali stratagemmi sono stati attivati dalla Super Terra.
    * Serve per evitare che a un giocatore venga randomizzato uno stratagemma che viene già fornito dalla Super Terra.
    * Per aggiungere stratagemmi alla lista di quelli attivati dalla super terra fai come segue:
    * {
    *       Stratagem.AIRBUST_ROCKET_LAUNCHER,
    *       Stratagem.PORTABLE_HELLBOMB
    * }
    *
    * DEFAULT: {}
    * */
    public static final Stratagem @NotNull [] STRATAGEMS_ENABLED_BY_SUPER_EARTH = {};

    // Boosters
    /*
     * Indica se randomizzare o no il booster che il giocatore deve usare.
     * Scelte: true | false
     * DEFAULT: true
     * */
    public static final boolean RANDOMIZE_BOOSTERS = true;

    /*
     * Indica quali potenziamenti sono stati attivati dalla Super Terra.
     * Serve per evitare che a un giocatore venga randomizzato un potenziamento che viene già fornito dalla Super Terra.
     * Per aggiungere booster alla lista di quelli attivati dalla super terra fai come segue:
     * {
     *      Booster.HELLPOD_SPACE_OPTIMIZATION,
     *      Booster.EXPERT_EXTRACTION_PILOT
     * }
     *
     * DEFAULT: {}
     * */
    public static final Booster @NotNull [] BOOSTERS_ENABLED_BY_SUPER_EARTH = {};
}
