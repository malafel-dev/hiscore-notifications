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
    MAD_ANGEL("Mad Angel", 54),
    MIMIC("Mimic",55),
    MAGGOT_KING("Maggot King", 56),
    NEX("Nex",57),
    NIGHTMARE("Nightmare",58),
    PHOSANIS_NIGHTMARE("Phosani's Nightmare",59),
    OBOR("Obor",60),
    PHANTOM_MUSPAH("Phantom Muspah",61),
    SARACHNIS("Sarachnis",62),
    SCORPIA("Scorpia",63),
    SCURRIUS("Scurrius",64),
    SHELLBANE_GRYPHON("Shellbane Gryphon",65),
    SKOTIZO("Skotizo",66),
    SOL_HEREDIT("Sol Heredit",67),
    SPINDEL("Spindel",68),
    TEMPOROSS("Tempoross",69),
    THE_GAUNTLET("Gauntlet",70),
    THE_CORRUPTED_GAUNTLET("Corrupted Gauntlet",71),
    THE_HUEYCOATL("Hueycoatl",72),
    THE_LEVIATHAN("Leviathan",73),
    THE_ROYAL_TITANS("The Royal Titans",74),
    THE_WHISPERER("Whisperer",75),
    THEATRE_OF_BLOOD("Theatre of Blood",76),
    THEATRE_OF_BLOOD_HARD_MODE("Theatre of Blood Hard Mode",77),
    THERMONUCLEAR_SMOKE_DEVIL("Thermonuclear Smoke Devil",78),
    TOMBS_OF_AMASCUT("Tombs of Amascut",79),
    TOMBS_OF_AMASCUT_EXPERT("Tombs of Amascut Expert Mode",80),
    TZKAL_ZUK("TzKal-Zuk",81),
    TZTOK_JAD("TzTok-Jad",82),
    VARDORVIS("Vardorvis",83),
    VENENATIS("Venenatis",84),
    VETION("Vet'ion",85),
    VORKATH("Vorkath",86),
    WINTERTODT("Wintertodt",87),
    YAMA("Yama",88),
    ZALCANO("Zalcano",89),
    ZULRAH("Zulrah",90);

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
