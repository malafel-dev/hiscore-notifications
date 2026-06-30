package com.malafel.hiscore.leaderboard;

public enum BossInfo {
    INVALID("Invalid Boss",-1),
    ABYSSAL_SIRE("Abyssal Sire",20),
    ALCHEMICAL_HYDRA("Alchemical Hydra",21),
    AMOXLIATL("Amoxliatl",22),
    ARAXXOR("Araxxor",23),
    ARTIO("Artio",24),
    BARROWS_CHESTS("Barrows Chests",25),
    BRUTUS("Brutus",26),
    BRYOPHYTA("Bryophyta",27),
    CALLISTO("Callisto",28),
    CALVARION("Calvar'ion",29),
    CERBERUS("Cerberus",30),
    CHAMBERS_OF_XERIC("Chambers of Xeric",31),
    CHAMBERS_OF_XERIC_CHALLENGE_MODE("Chambers of Xeric Challenge Mode",32),
    CHAOS_ELEMENTAL("Chaos Elemental",33),
    CHAOS_FANATIC("Chaos Fanatic",34),
    COMMANDER_ZILYANA("Commander Zilyana",35),
    CORPOREAL_BEAST("Corporeal Beast",36),
    CRAZY_ARCHAEOLOGIST("Crazy Archaeologist",37),
    DAGANNOTH_PRIME("Dagannoth Prime",38),
    DAGANNOTH_REX("Dagannoth Rex",39),
    DAGANNOTH_SUPREME("Dagannoth Supreme",40),
    DERANGED_ARCHAEOLOGIST("Deranged Archaeologist",41),
    DOOM_OF_MOKHAIOTL("Doom of Mokhaiotl",42),
    DUKE_SUCELLUS("Duke Sucellus",43),
    GENERAL_GRAARDOR("General Graardor",44),
    GIANT_MOLE("Giant Mole",45),
    GROTESQUE_GUARDIANS("Grotesque Guardians",46),
    HESPORI("Hespori",47),
    KALPHITE_QUEEN("Kalphite Queen",48),
    KING_BLACK_DRAGON("King Black Dragon",49),
    KRAKEN("Kraken",50),
    KREEARRA("Kree'Arra",51),
    KRIL_TSUTSAROTH("K'ril Tsutsaroth",52),
    LUNAR_CHESTS("Lunar Chest",53),
    MIMIC("Mimic",54),
    MAGGOT_KING("Maggot King", 55),
    NEX("Nex",56),
    NIGHTMARE("Nightmare",57),
    PHOSANIS_NIGHTMARE("Phosani's Nightmare",58),
    OBOR("Obor",59),
    PHANTOM_MUSPAH("Phantom Muspah",60),
    SARACHNIS("Sarachnis",61),
    SCORPIA("Scorpia",62),
    SCURRIUS("Scurrius",63),
    SHELLBANE_GRYPHON("Shellbane Gryphon",64),
    SKOTIZO("Skotizo",65),
    SOL_HEREDIT("Sol Heredit",66),
    SPINDEL("Spindel",67),
    TEMPOROSS("Tempoross",68),
    THE_GAUNTLET("Gauntlet",69),
    THE_CORRUPTED_GAUNTLET("Corrupted Gauntlet",70),
    THE_HUEYCOATL("Hueycoatl",71),
    THE_LEVIATHAN("Leviathan",72),
    THE_ROYAL_TITANS("The Royal Titans",73),
    THE_WHISPERER("Whisperer",74),
    THEATRE_OF_BLOOD("Theatre of Blood",75),
    THEATRE_OF_BLOOD_HARD_MODE("Theatre of Blood Hard Mode",76),
    THERMONUCLEAR_SMOKE_DEVIL("Thermonuclear Smoke Devil",77),
    TOMBS_OF_AMASCUT("Tombs of Amascut",78),
    TOMBS_OF_AMASCUT_EXPERT("Tombs of Amascut Expert Mode",79),
    TZKAL_ZUK("TzKal-Zuk",80),
    TZTOK_JAD("TzTok-Jad",81),
    VARDORVIS("Vardorvis",82),
    VENENATIS("Venenatis",83),
    VETION("Vet'ion",84),
    VORKATH("Vorkath",85),
    WINTERTODT("Wintertodt",86),
    YAMA("Yama",87),
    ZALCANO("Zalcano",88),
    ZULRAH("Zulrah",89);

    public final String chatCommandsLongName;
    public final int tableNumber;

    BossInfo(String chatCommandsLongName, int tableNumber) {
        this.chatCommandsLongName = chatCommandsLongName;
        this.tableNumber = tableNumber;
    }

    public static BossInfo fromName(String name) {
        for (BossInfo i: BossInfo.values()) {
            if (i.chatCommandsLongName.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return INVALID;
    }
}
