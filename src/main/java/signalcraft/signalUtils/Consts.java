package signalcraft.signalUtils;

import net.minecraftforge.common.util.EnumHelper;

public class Consts {
    //Other
    public static final int lightPropArrLenght = 11;
    public static final int mechPropArrLenght = 9;
    public static final int signPropArrLenght = 17;
    public static final int switchPropArrLenght = 8;
    public static final int crossPropArrLenght = 28;

    //GUI IDs ---------------------------------
    public enum GuiIDs {
        NOGUI,
        SPEED_SIGN,
        PR_UPOZ,
        AZD71,
        AZD97,
        AZD71_HEAD,
        AZD97_HEAD,
        AZD99,
        SSSR,
        SSSR_SINGLE,
        SSSR_HEAD,
        SSSR_SINGLE_HEAD,
        VUD,
        AZD_2_LIGHTS,
        AZD_2_LIGHTS_T,
        AZD_3_LIGHTS,
        AZD_3_LIGHTS_T,
        AZD_4_LIGHTS,
        AZD_5_LIGHTS,
        AZD_6_LIGHTS,
        AZD_AB3,
        AZD_AB4,
        AZD_DIST,
        AZD_RE_DIST,
        AZD_SHUNT,
        AZD_INSERTED,
        SSSR_5_LIGHTS,
        SSSR_4_LIGHTS,
        SSSR_3_LIGHTS,
        SSSR_2_LIGHTS,
        SSSR_DISTANT,
        SSSR_DISTANT_REPEATING,
        SSSR_SHUNT,
        SSSR_AB3,
        SSSR_AB4,
        SSSR_INSERTED,
        REDSTONE_CONTROLLER,
        REDSTONE_RECEIVER,

        //GSAR GUIs
        STATIV_HP,
        SEMI_STATIV_HP,
        SWITCH_MANUAL,
        SWITCH_ELECTRIC,
        MODERN_BARRIERS,
        SIGN_LF1,
        SIGN_LF6,
        SIGN_LF7,
        SIGNAL_SH2,
        HECTOMETER_SIGN,
        SIGNAL_LEVER,
        STATION_SIGN,

        ;

        public int getId() {
            return ordinal();
        }

        public static GuiIDs fromId(int id) {
            GuiIDs[] all = values();
            return (id >= 0 && id < all.length) ? all[id] : null;
        }

        /**
         * Registers a new GUI ID for addon mods, appending a genuine new enum
         * constant at runtime via Forge's {@link EnumHelper}, e.g.
         * {@code public static final Consts.GuiIDs MY_GUI = Consts.GuiIDs.register("MY_GUI");}
         */
        public static synchronized GuiIDs register(String name) {
            for (GuiIDs existing : values()) {
                if (existing.name().equals(name)) {
                    throw new IllegalArgumentException("A GuiID named '" + name + "' is already registered.");
                }
            }
            return EnumHelper.addEnum(GuiIDs.class, name, new Class<?>[0], new Object[0]);
        }
    }


    //Position Strings ---------------------------------
    public enum Position {
        MIDDLE("Stred"),
        LEFT("Vlevo"),
        RIGHT("Vpravo");

        public final String Pos;

        Position(String Pos) {
            this.Pos = Pos;
        }

        public String toString() {
            return Pos;
        }

        public static Position fromString(String text) {
            for (Position p : Position.values()) {
                if (p.Pos.equals(text)) {
                    return p;
                }
            }
            throw new IllegalArgumentException("No enum constant for text: " + text);
        }
    }

    //Boolean Strings ---------------------------------
    public enum BooleanSTR {
        YES("Ano", true),
        NO("Ne", false);

        public final String BooTxT;
        public final Boolean Boo;

        BooleanSTR(String BooTxT, Boolean boo) {
            this.BooTxT = BooTxT;
            this.Boo = boo;
        }

        public String toString() {
            return BooTxT;
        }

        public Boolean toBoolean() {
            return Boo;
        }

        public static BooleanSTR fromString(String text) {
            for (BooleanSTR B : BooleanSTR.values()) {
                if (B.BooTxT.equals(text)) {
                    return B;
                }
            }
            throw new IllegalArgumentException("No enum constant for text: " + text);
        }

        public static BooleanSTR fromBoolean(Boolean boo) {
            for (BooleanSTR B : BooleanSTR.values()) {
                if (B.Boo.equals(boo)) {
                    return B;
                }
            }
            throw new IllegalArgumentException("No enum constant for boolean: " + boo);
        }
    }

    //Type Strings ----------------------------------------
    public enum Types {
        TYPE_1("typ1"),
        TYPE_2("typ2"),
        TYPE_3("typ3");
        public final String Type;

        Types(String Type) {
            this.Type = Type;
        }

        public String toString() {
            return Type;
        }

        public static Types fromString(String text) {
            for (Types txt : Types.values()) {
                if (txt.Type.equals(text)) {
                    return txt;
                }
            }
            throw new IllegalArgumentException("No enum constant for text: " + text);
        }
    }

    //SpeedSignText (Light Signals) Strings ---------------------------------
    public enum SpeedSignText {
        SIGN_30("30"),
        SIGN_50("50"),
        NO_SIGN("nic"),
        LIGHT_30("30S");
        public final String SpeedSignTxt;

        SpeedSignText(String SpeedSignText) {
            this.SpeedSignTxt = SpeedSignText;
        }

        public String toString() {
            return SpeedSignTxt;
        }

        public static SpeedSignText fromString(String text) {
            for (SpeedSignText txt : SpeedSignText.values()) {
                if (txt.SpeedSignTxt.equals(text)) {
                    return txt;
                }
            }
            throw new IllegalArgumentException("No enum constant for text: " + text);
        }
    }

    //Crossing Sound Types ---------------------------------
    public enum SoundType {
        NO_SOUND("", "", "",  false, 0, 0, 20),
        cinkP3597("signalcraft:cinkP3597", "gui.sound.bellClassic", "P3597", true, 14, 28, 40),
        cinkP8240("signalcraft:cinkP8240", "gui.sound.bellClassic", "P8240",true, 9, 18, 40),
        cinkP3599("signalcraft:cinkP3599", "gui.sound.bellClassic", "P3599",true, 22, 41, 40),
        cinkP7063("signalcraft:cinkP7063", "gui.sound.bellClassic", "P7063",true, 11, 22, 40),
        AZD71S3("signalcraft:docink", "gui.sound.azd71s3.text", "", false, 0, 0, 40),
        cinkVUDP5263("signalcraft:cinkVUDP5263", "gui.sound.vud.text", "P5263", true, 11, 22, 40),
        cinkVUDP7041("signalcraft:cinkVUDP7041", "gui.sound.vud.text", "P7041", true, 10, 20, 40),
        hornP5343("signalcraft:hornP5343", "gui.sound.horn.text", "P5343",true, 11, 22, 40),
        hornSP1318("signalcraft:hornSP1318", "gui.sound.horn.text", "SP1318",true, 20, 40, 40),
        OTRADOVICE("signalcraft:otradovice1", "gui.sound.otradovice.text", "P2778", false, 15, 30, 40),
        OTRADOVICE2("signalcraft:otradovice2", "gui.sound.otradovice2.text", "", false, 15, 30, 40),
        OTRADOVICE3("signalcraft:otradovice3", "gui.sound.otradovice3.text", "", false, 15, 30, 40),
        AZD97("signalcraft:azd97zv1", "gui.sound.azd97zv1.text", "", true, 11, 22, 50),
        AZD97ZV2("signalcraft:azd97zv2", "gui.sound.azd97zv2.text", "", true, 10, 20, 50);
        public final String SoundLocation;
        public final String GUIString;
        public final String Comment;
        public final boolean isValidForCycle;
        public final int soundTimer;
        public final int blinkTimer;
        /** Full on+off cycle length (in ticks) for the crossing's Poz (supplementary) light, independent of soundTimer/blinkTimer. */
        public final int pozitBlinkTimer;

        SoundType(String SoundLocation, String GUIString, String Comment, boolean isValidForCycle, int soundTimer, int blinkTimer, int pozitBlinkTimer) {
            this.SoundLocation = SoundLocation;
            this.GUIString = GUIString;
            this.Comment = Comment;
            this.isValidForCycle = isValidForCycle;
            this.soundTimer = soundTimer;
            this.blinkTimer = blinkTimer;
            this.pozitBlinkTimer = pozitBlinkTimer;
        }

        public String toString() {
            return SoundLocation;
        }

        public SoundType nextValid() {
            SoundType[] all = values();
            int i = this.ordinal();

            // loop through until we find the next valid one
            for (int offset = 1; offset <= all.length; offset++) {
                SoundType candidate = all[(i + offset) % all.length];
                if (candidate.isValidForCycle) {
                    return candidate;
                }
            }
            // fallback (should never happen)
            return this;
        }
        public static SoundType fromString(String text) {
            for (SoundType loc : SoundType.values()) {
                if (loc.SoundLocation.equals(text)) {
                    return loc;
                }
            }
            throw new IllegalArgumentException("No enum constant for text: " + text);
        }
    }

    //Crossing Distance from Pole ---------------------------------
    public enum DistFromPole {
        DIST_00("00"),
        DIST_30("30"),
        DIST_50("50"),
        DIST_100("100");
        public final String Dist;

        DistFromPole(String Dist) {
            this.Dist = Dist;
        }

        public String toString() {
            return Dist;
        }

        public static DistFromPole fromString(String text) {
            for (DistFromPole d : DistFromPole.values()) {
                if (d.Dist.equals(text)) {
                    return d;
                }
            }
            throw new IllegalArgumentException("No enum constant for text: " + text);
        }
    }

    //Cedule (PV Sign) State ---------------------------------
    public enum CeduleState {
        UP("Up"),
        DOWN("Down"),
        NONE("None");

        public final String State;

        CeduleState(String State) {
            this.State = State;
        }

        public String toString() {
            return State;
        }

        public static CeduleState fromString(String text) {
            for (CeduleState c : CeduleState.values()) {
                if (c.State.equals(text)) {
                    return c;
                }
            }
            throw new IllegalArgumentException("No enum constant for text: " + text);
        }
    }

    // Enum for Colors ---------------------------------
        public enum GameColor {
            WHITE(0xF0F0F0, "color.white"),
            ORANGE(0xF2B233, "color.orange"),
            MAGENTA(0xE57FD8, "color.magenta"),
            LIGHT_BLUE(0x99B2F2, "color.light_blue"),
            YELLOW(0xDEDE6C, "color.yellow"),
            LIME(0x7FCC19, "color.lime"),
            PINK(0xF2B2CC, "color.pink"),
            GRAY(0x4C4C4C, "color.gray"),
            LIGHT_GRAY(0x999999, "color.light_gray"),
            CYAN(0x4C99B2, "color.cyan"),
            PURPLE(0xB266E5, "color.purple"),
            BLUE(0x3366CC, "color.blue"),
            BROWN(0x7F664C, "color.brown"),
            GREEN(0x57A64E, "color.green"),
            RED(0xCC4C4C, "color.red"),
            BLACK(0x222222, "color.black");

            private final int hexValue;
            private final String langKey;

            GameColor(int hexValue, String langKey) {
                this.hexValue = hexValue;
                this.langKey = langKey;
            }

            public int getHexValue() {
                return hexValue;
            }

            public String getLangKey() {
                return langKey;
            }

        }
    // Enum for Sides ---------------------------------

    public enum Side {
        SOUTH("side.south.1"),
        NORTH("side.north.2"),
        EAST("side.east.3"),
        WEST("side.west.4");

        private final String langKey;

        Side( String langKey) {
            this.langKey = langKey;
        }

        public String getLangKey() {
            return langKey;
        }

    }
}
