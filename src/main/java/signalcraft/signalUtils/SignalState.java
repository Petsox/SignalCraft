package signalcraft.signalUtils;
import signalcraft.entities.signals.ISignal;

import java.util.ArrayList;
import java.util.Locale;

public enum SignalState {
    ZHAS("Zhas", new String[]{"nic"}, null, null, false,false),
    ALL("All", new String[]{}, null, null, false, false),
    STUJ("Stuj", new String[]{"cervena"}, null, null, false, false),
    VOLNO("Volno", new String[]{"zelena"}, null, null, false, false),
    VYSTRAHA("Vystraha", new String[]{"zluta"}, null, null, false, false),
    PN("PN", new String[]{"bila", "cervena"}, "bila", null, false, false),
    R40VYSTRAHA("R40Vystraha", new String[]{"zluta", "40"}, null, null, false, false),
    R30VYSTRAHA("R30Vystraha", new String[]{"zluta", "40", "30"}, null, null, false, false),
    R60VYSTRAHA("R60Vystraha", new String[]{"zluta", "40", "pruh_spodni"}, null, null, true, false),
    R80VYSTRAHA("R80Vystraha", new String[]{"zluta", "40", "pruh_horni"}, null, null, true, false),
    R100VYSTRAHA("R100Vystraha", new String[]{"zluta", "40", "pruh_horni", "pruh_100"}, null, null, false, true),
    R40VOLNO("R40Volno", new String[]{"zelena", "40"}, null, null, false,false),
    R30VOLNO("R30Volno", new String[]{"zelena", "40", "30"}, null, null, false,false),
    R60VOLNO("R60Volno", new String[]{"zelena", "40", "pruh_spodni"}, null, null, true, false),
    R80VOLNO("R80Volno", new String[]{"zelena", "40", "pruh_horni"}, null, null, true, false),
    R100VOLNO("R100Volno", new String[]{"zelena", "40", "pruh_horni", "pruh_100"}, null, null, false, true),
    R40OCEK40("R40Ocek40", new String[]{"zluta", "40"}, "zluta", null, false, false),
    R30OCEK40("R30Ocek40", new String[]{"zluta", "40", "30"}, "zluta", null, false, false),
    R40OCEK60("R40Ocek60", new String[]{"zluta", "40"}, null, "zluta", false, false),
    R30OCEK60("R30Ocek60", new String[]{"zluta", "40", "30"}, null, "zluta", false, false),
    R40OCEK80("R40Ocek80", new String[]{"zelena", "40"}, "zelena", null, false, false),
    R30OCEK80("R30Ocek80", new String[]{"zelena", "40", "30"}, "zelena", null, false, false),
    R40OCEK100("R40Ocek100", new String[]{"zelena", "40"}, null, "zelena", false, false),
    R30OCEK100("R30Ocek100", new String[]{"zelena", "40", "30"}, null, "zelena", false, false),
    R60OCEK40("R60Ocek40", new String[]{"zluta", "40", "pruh_spodni"}, "zluta", null, true, false),
    R60OCEK60("R60Ocek60", new String[]{"zluta", "40", "pruh_spodni"}, null, "zluta", true, false),
    R60OCEK80("R60Ocek80", new String[]{"zelena", "40", "pruh_spodni"}, "zelena", null, true, false),
    R60OCEK100("R60Ocek100", new String[]{"zelena", "40", "pruh_spodni"}, null, "zelena", true, false),
    R80OCEK40("R80Ocek40", new String[]{"zluta", "40", "pruh_horni"}, "zluta", null, true, false),
    R80OCEK60("R80Ocek60", new String[]{"zluta", "40", "pruh_horni"}, null, "zluta", true, false),
    R80OCEK80("R80Ocek80", new String[]{"zelena", "40", "pruh_horni"}, "zelena", null, true, false),
    R80OCEK100("R80Ocek100", new String[]{"zelena", "40", "pruh_horni"}, null, "zelena", true, false),
    R100OCEK40("R100Ocek40", new String[]{"zluta", "40", "pruh_horni", "pruh_100"}, "zluta", null, false, true),
    R100OCEK60("R100Ocek60", new String[]{"zluta", "40", "pruh_horni", "pruh_100"}, null, "zluta", false, true),
    R100OCEK80("R100Ocek80", new String[]{"zelena", "40", "pruh_horni", "pruh_100"}, "zelena", null, false, true),
    R100OCEK100("R100Ocek100", new String[]{"zelena", "40", "pruh_horni", "pruh_100"}, null, "zelena", false, true),
    OCEK40("Ocek40", new String[]{"zluta"}, "zluta", null, false, false),
    OCEK60("Ocek60", new String[]{"zluta"}, null, "zluta", false, false),
    OCEK80("Ocek80", new String[]{"zelena"}, "zelena", null, false, false),
    OCEK100("Ocek100", new String[]{"zelena"}, null, "zelena", false, false),
    OPAKVOLNO("OpakVolno", new String[]{"zelena", "bila"}, null, null, false, false),
    OPAKVYSTRAHA("OpakVystraha", new String[]{"zluta", "bila"}, null, null, false, false),
    OPAKOCEK40("OpakOcek40", new String[]{"zluta", "bila"}, "zluta", null, false, false),
    OPAKOCEK60("OpakOcek60", new String[]{"zluta", "bila"}, null, "zluta", false, false),
    OPAKOCEK80("OpakOcek80", new String[]{"zelena", "bila"}, "zelena", null, false, false),
    OPAKOCEK100("OpakOcek100", new String[]{"zelena", "bila"}, null, "zelena", false, false),
    POSUNDOV("PosunDov", new String[]{"bila"}, null, null, false, false),
    POSUNZAK("PosunZak", new String[]{"modra"}, null, null, false, false),
    ODNAVDOVJIZDU("OdNavDovJizdu", new String[]{"bila"}, "bila", null, false, false),
    STUJPOSUNZAK("StujPosunZak", new String[]{"cervena", "modra"}, null, null, false, false),
    STUJPOSUNDOV("StujPosunDov", new String[]{"cervena", "bila"}, null, null, false, false),
    R40OPAKOCEK100("R40OpakOcek100", new String[]{"zelena", "bila", "40"}, null, "zelena", false, false),
    R40OPAKOCEK80("R40OpakOcek80", new String[]{"zelena", "bila", "40"}, "zelena", null, false, false),
    R40OPAKOCEK60("R40OpakOcek60", new String[]{"zluta", "bila", "40"}, null, "zluta", false, false),
    R40OPAKOCEK40("R40OpakOcek40", new String[]{"zluta", "bila", "40"}, "zluta", null, false, false),
    R40OPAKVYSTRAHA("R40OpakVystraha", new String[]{"zluta", "bila", "40"}, null, null, false, false),
    R40OPAKVOLNO("R40OpakVolno", new String[]{"zelena", "bila", "40"}, null, null, false, false),
    R30OPAKVYSTRAHA("R30OpakVystraha", new String[]{"zluta", "bila", "40", "30"}, null, null, false, false),
    R30OPAKVOLNO("R30OpakVolno", new String[]{"zelena", "bila", "40"}, null, null, false, false),

    //Special state for signals that are active but have no state to display (e.g. signals with only one state)
    ACTIVATE("Activate", null, null, null, false, false),
    ;
    private final String signalState;
    public static final SignalState[] VALUES = values();
    public final String[] signals;
    public final String blinkSlow;
    public final String blinkFast;
    public final boolean pruhy;
    public final boolean pruhy3;

    SignalState(String SignalString, String[] signals, String blinkSlow, String blinkFast, Boolean pruhy, Boolean pruhy3) {
        this.signalState = SignalString;
        this.signals = signals;
        this.blinkSlow = blinkSlow;
        this.blinkFast = blinkFast;
        this.pruhy = pruhy;
        this.pruhy3 = pruhy3;
    }


    public static SignalState fromOrdinal(String ordinal) {
        for (SignalState value : VALUES) {
            if (value.signalState.equalsIgnoreCase(ordinal)) {
                return value;
            }
        }
        return STUJ;
    }

    public static SignalState fromInteger(int ordinal) {
        for (SignalState value : VALUES) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return STUJ;
    }

    public static SignalState fromString(String ordinal) {
        for (SignalState value : VALUES) {
            if (value.signalState.equalsIgnoreCase(ordinal)) {
                return value;
            }
        }
        return STUJ;
    }

    public static int toInteger(SignalState state) {
        return state.ordinal();
    }

    public static String[] getSignals(String ordinal) {
        for (SignalState value : VALUES) {
            if (value.signalState.equals(ordinal)) {
                return value.signals;
            }
        }
        return STUJ.signals;
    }

    public static String[] getPossibleColorsFromStates(ISignal tileE) {
        ArrayList<String> result = new ArrayList<>();
        SignalState[] States = tileE.getValidStatesForTile();
        for (SignalState state : States) {
            for (int j = 0; j < getSignals(state.signalState).length; j++) {
                if (result.contains(getSignals(state.signalState)[j])) continue;
                result.add(getSignals(state.signalState)[j]);
            }
        }
        return result.toArray(new String[0]);
    }

    public static boolean getIsStateBlink(String state, SignalState signal) {
        if (signal.blinkSlow != null && signal.blinkSlow.equals(state)) {
            return true;
        } else return signal.blinkFast != null && signal.blinkFast.equals(state);
    }

    public static boolean contains(String test) {
        for (SignalState value : VALUES) {
            if (value.signalState.equalsIgnoreCase(test)) return true;
        }
        return false;
    }

    public String StateToString() {
        return signalState;
    }

    public static boolean getBlinkSpeed(String test) {
        for (SignalState value : VALUES) {
            if (value.signalState.equals(test)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String[] sa = name().split("_");
        String out = "";
        for (String s : sa) {
            out = out + s.charAt(0) + s.substring(1).toLowerCase(Locale.ENGLISH) + " ";
        }
        out = out.trim();
        return out;
    }
}
