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
    NEX("Nex",55),
    NIGHTMARE("Nightmare",56),
    PHOSANIS_NIGHTMARE("Phosani's Nightmare",57),
    OBOR("Obor",58),
    PHANTOM_MUSPAH("Phantom Muspah",59),
    SARACHNIS("Sarachnis",60),
    SCORPIA("Scorpia",61),
    SCURRIUS("Scurrius",62),
    SHELLBANE_GRYPHON("Shellbane Gryphon",63),
    SKOTIZO("Skotizo",64),
    SOL_HEREDIT("Sol Heredit",65),
    SPINDEL("Spindel",66),
    TEMPOROSS("Tempoross",67),
    THE_GAUNTLET("Gauntlet",68),
    THE_CORRUPTED_GAUNTLET("Corrupted Gauntlet",69),
    THE_HUEYCOATL("Hueycoatl",70),
    THE_LEVIATHAN("Leviathan",71),
    THE_ROYAL_TITANS("The Royal Titans",72),
    THE_WHISPERER("Whisperer",73),
    THEATRE_OF_BLOOD("Theatre of Blood",74),
    THEATRE_OF_BLOOD_HARD_MODE("Theatre of Blood Hard Mode",75),
    THERMONUCLEAR_SMOKE_DEVIL("Thermonuclear Smoke Devil",76),
    TOMBS_OF_AMASCUT("Tombs of Amascut",77),
    TOMBS_OF_AMASCUT_EXPERT("Tombs of Amascut Expert Mode",78),
    TZKAL_ZUK("TzKal-Zuk",79),
    TZTOK_JAD("TzTok-Jad",80),
    VARDORVIS("Vardorvis",81),
    VENENATIS("Venenatis",82),
    VETION("Vet'ion",83),
    VORKATH("Vorkath",84),
    WINTERTODT("Wintertodt",85),
    YAMA("Yama",86),
    ZALCANO("Zalcano",87),
    ZULRAH("Zulrah",88);

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
