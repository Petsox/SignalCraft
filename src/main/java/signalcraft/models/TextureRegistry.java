package signalcraft.models;

import net.minecraft.util.ResourceLocation;
import signalcraft.SignalCraft;

public enum TextureRegistry {

    SPEED_SIGN(SignalCraft.MOD_ID + ":textures/models/signs/rychlostnik.jpg"),

    CONT_RECE_BASE(SignalCraft.MOD_ID + ":textures/models/controllers/controller_blank.png"),
    CONT_UNIVERSAL(SignalCraft.MOD_ID + ":textures/models/controllers/controller_universal.png"),
    RECE_UNIVERSAL(SignalCraft.MOD_ID + ":textures/models/controllers/receiver_universal.png"),
    CONT_CROSSING(SignalCraft.MOD_ID + ":textures/models/controllers/controller_crossing.png"),
    RECE_CROSSING(SignalCraft.MOD_ID + ":textures/models/controllers/receiver_crossing.png"),
    RED_CONT_LIGHT_SIGNALS(SignalCraft.MOD_ID + ":textures/models/controllers/controller_signals_redstone.png"),
    RED_RECE_LIGHT_SIGNALS(SignalCraft.MOD_ID + ":textures/models/controllers/receiver_signals_redstone.png"),
    RECE_LIGHT_SIGNALS(SignalCraft.MOD_ID + ":textures/models/controllers/receiver_signals.png"),

    SKRINKA(SignalCraft.MOD_ID + ":textures/nove/azd71/skrinka.jpg"),
    PODKLAD(SignalCraft.MOD_ID + ":textures/nove/azd71/podklad.jpg"),
    ZAKLAD(SignalCraft.MOD_ID + ":textures/nove/azd71/zaklad.jpg"),
    SKRIN_ZADEK(SignalCraft.MOD_ID + ":textures/nove/azd71/caseBack.jpg"),
    STUPACKA(SignalCraft.MOD_ID + ":textures/nove/azd71/stupacka.jpg"),
    STOZAR(SignalCraft.MOD_ID + ":textures/nove/azd71/stozar.jpg"),
    PRUHY(SignalCraft.MOD_ID + ":textures/nove/azd71/stozar_pruhy.jpg"),
    SVETLO_ZHAS_RED(SignalCraft.MOD_ID + ":textures/nove/azd71/redLight_off.jpg"),
    SVETLO_ZHAS_WHI(SignalCraft.MOD_ID + ":textures/nove/azd71/whiteLight_off.jpg"),
    POZOR_VLAK(SignalCraft.MOD_ID + ":textures/nove/azd71/pv_sign.jpg"),
    CERNA(SignalCraft.MOD_ID + ":textures/nove/azd71/black.jpg"),
    PREDEK(SignalCraft.MOD_ID + ":textures/nove/azd71/predek.jpg"),
    SVETLO_W(SignalCraft.MOD_ID + ":textures/nove/azd71/whiteLight_on.jpg"),
    SVETLO_R(SignalCraft.MOD_ID + ":textures/nove/azd71/redLight_on.jpg"),
    KRIZ_SK(SignalCraft.MOD_ID + ":textures/nove/azd71/kriz_sk.jpg"),
    KRIZ_SK_VIC(SignalCraft.MOD_ID + ":textures/nove/azd71/kriz_sk_xk.jpg"),
    KRIZ_CZ(SignalCraft.MOD_ID + ":textures/nove/azd71/kriz_cz_1k.jpg"),
    KRIZ_CZ_VIC(SignalCraft.MOD_ID + ":textures/nove/azd71/kriz_cz_xk.jpg"),
    KRIZ_CZ_REFL(SignalCraft.MOD_ID + ":textures/nove/azd71/kriz_cz_refl.jpg"),
    KRIZ_CZ_REFL_VELKY(SignalCraft.MOD_ID + ":textures/nove/azd71/kriz_cz_refl_velky.jpg"),

    SSSR_ZADEK(SignalCraft.MOD_ID + ":textures/nove/sssr/zadek.jpg"),
    SSSR_ZADEK_A(SignalCraft.MOD_ID + ":textures/nove/sssr/zadek_a.jpg"),
    SSSR_ZADEK_B(SignalCraft.MOD_ID + ":textures/nove/sssr/zadek_b.jpg"),
    SSSR_KSILT(SignalCraft.MOD_ID + ":textures/nove/sssr/cerna_ksilt.jpg"),
    SSSR_CERNA(SignalCraft.MOD_ID + ":textures/nove/sssr/cerna.jpg"),
    SSSR_SVETLO_ZHAS(SignalCraft.MOD_ID + ":textures/nove/sssr/korona_zhas.jpg"),
    SSSR_SVETLO_RED(SignalCraft.MOD_ID + ":textures/nove/sssr/korona.jpg"),
    SSSR_SVETLO_WHITE(SignalCraft.MOD_ID + ":textures/nove/sssr/korona_white.jpg"),

    AZD97_HLAVNI(SignalCraft.MOD_ID + ":textures/nove/azd97/main.jpg"),
    LED_SVETLO_WHITE(SignalCraft.MOD_ID + ":textures/nove/azd97/led_poz.jpg"),

    VUD(SignalCraft.MOD_ID + ":textures/vud/515BC0B4.png"),
    RED_LIGHT_VUD(SignalCraft.MOD_ID + ":textures/nove/vud/redLight_on.jpg"),

    COCKY(SignalCraft.MOD_ID + ":textures/nove/azd/textura_cocky.jpg"),
    COCKY_OFF(SignalCraft.MOD_ID + ":textures/nove/azd/textura_cocky_off.jpg"),
    AZD_KONEC(SignalCraft.MOD_ID + ":textures/nove/azd/NvstTab.jpg"),
    AZD_INDIKATORY(SignalCraft.MOD_ID + ":textures/nove/azd/NvstIndi.jpg"),

    AZD65_HLAVNI(SignalCraft.MOD_ID + ":textures/nove/azd/NvstR1.jpg"),

    AZD70_HLAVNI(SignalCraft.MOD_ID + ":textures/nove/azd/NvstAZD1.jpg"),
    AZD70_HLAVNI_T(SignalCraft.MOD_ID + ":textures/nove/azd/Nvsttrp1.jpg"),

    AZD99_MAIN("signalcraft:textures/nove/azd99/motor_barrier.jpg"),
    AZD99_PODKLAD("signalcraft:textures/nove/azd99/podklad.jpg"),
    AZD99_SKRINKA("signalcraft:textures/nove/azd99/skrinka.jpg"),
    AZD99_ZAKLAD("signalcraft:textures/nove/azd99/zaklad.jpg"),
    AZD99_BARRIER("signalcraft:textures/nove/azd99/barrier.jpg"),

    SSSR_MAIN(SignalCraft.MOD_ID + ":textures/nove/sssr_nav/sssr_main.jpg"),
    SSSR_MAIN_T(SignalCraft.MOD_ID + ":textures/nove/sssr_nav/sssr_main_t.jpg"),
    SSSR_SKRINKA(SignalCraft.MOD_ID + ":textures/nove/sssr_nav/sssr_skrinka.jpg"),
    SSSR_PRUHY(SignalCraft.MOD_ID + ":textures/nove/sssr_nav/sssr_pruhy.jpg"),
    SSSR_CISLA(SignalCraft.MOD_ID + ":textures/nove/sssr_nav/navestni_tab.jpg"),
    SSSR_IND_30(SignalCraft.MOD_ID + ":textures/nove/sssr_nav/rych_ind_30.jpg"),
    SSSR_IND_50(SignalCraft.MOD_ID + ":textures/nove/sssr_nav/rych_ind_50.jpg"),
    SSSR_AB_TERC(SignalCraft.MOD_ID + ":textures/nove/sssr_nav/ab_tab.jpg"),
    SSSR_AB(SignalCraft.MOD_ID + ":textures/nove/sssr_nav/sssr_ab.jpg"),

    GSAR_WN(SignalCraft.MOD_ID + ":textures/gsar/models/signals_WN.png"),
    GSAR_WN_INVERTED(SignalCraft.MOD_ID + ":textures/gsar/models/signals_WNg.png"),
    GSAR_WN_ZLUTA(SignalCraft.MOD_ID + ":textures/gsar/models/signals_WNo.png"),
    GSAR_SIGNALS(SignalCraft.MOD_ID + ":textures/gsar/models/light_signalHP.png"),
    GSAR_KORONY(SignalCraft.MOD_ID + ":textures/gsar/models/light_signal_HPL.png"),
    GSAR_LIGHT_OFF(SignalCraft.MOD_ID + ":textures/gsar/models/light_off.png"),
    GSAR_SEMI_SIGNALS(SignalCraft.MOD_ID + ":textures/gsar/models/semi_signals.png"),
    GSAR_SEMI_SIGNALS_VR(SignalCraft.MOD_ID + ":textures/gsar/models/semi_signals_vr.png"),
    GSAR_SEMI_STATIV(SignalCraft.MOD_ID + ":textures/gsar/models/signals_stativ_semi.png"),
    GSAR_BU_STATIV(SignalCraft.MOD_ID + ":textures/gsar/models/stativ_railcross.png"),
    GSAR_LEVER(SignalCraft.MOD_ID + ":textures/gsar/models/signals_lever.png"),
    GSAR_RAILCROSS_LIGHT(SignalCraft.MOD_ID + ":textures/gsar/models/railcross_light.png"),
    GSAR_RAILCROSS(SignalCraft.MOD_ID + ":textures/gsar/models/railcross.png"),
    GSAR_ROD(SignalCraft.MOD_ID + ":textures/gsar/models/metal_rod.png"),
    GSAR_BU2(SignalCraft.MOD_ID + ":textures/gsar/models/signal_bu2.png"),
    GSAR_BU3(SignalCraft.MOD_ID + ":textures/gsar/models/signal_bu3.png"),
    GSAR_BU4(SignalCraft.MOD_ID + ":textures/gsar/models/signal_bu4.png"),
    GSAR_BU5(SignalCraft.MOD_ID + ":textures/gsar/models/signal_bu5.png"),
    GSAR_LF1_LF6(SignalCraft.MOD_ID + ":textures/gsar/models/signal_lf1_lf6.png"),
    GSAR_LF2(SignalCraft.MOD_ID + ":textures/gsar/models/signal_lf2.png"),
    GSAR_LF3(SignalCraft.MOD_ID + ":textures/gsar/models/signal_lf3.png"),
    GSAR_LF7(SignalCraft.MOD_ID + ":textures/gsar/models/signal_lf7.png"),
    GSAR_NE1(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne1.png"),
    GSAR_NE2(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne2.png"),
    GSAR_NE3_1(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne3_1.png"),
    GSAR_NE3_2(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne3_2.png"),
    GSAR_NE3_3(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne3_3.png"),
    GSAR_NE4(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne4.png"),
    GSAR_NE5(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne5.png"),
    GSAR_NE6(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne6.png"),
    GSAR_NE7(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne7.png"),
    GSAR_NE12(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ne12.png"),
    GSAR_RA10A(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ra10a.png"),
    GSAR_RA10B(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ra10b.png"),
    GSAR_RA11A(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ra11a.png"),
    GSAR_RA11B(SignalCraft.MOD_ID + ":textures/gsar/models/signal_ra11b.png"),
    GSAR_SH2(SignalCraft.MOD_ID + ":textures/gsar/models/signal_sh2.png"),
    GSAR_HECTOSIGN(SignalCraft.MOD_ID + ":textures/gsar/models/hecto_sign.png"),
    GSAR_SIGNAL_LEVER(SignalCraft.MOD_ID + ":textures/gsar/models/signals_lever.png"),
    GSAR_ICON_CLEAR(SignalCraft.MOD_ID + ":textures/gsar/models/light_off.png"),
    GSAR_ICON_HP1(SignalCraft.MOD_ID + ":textures/gsar/models/semi_signal_hp1.png"),
    GSAR_ICON_HP2(SignalCraft.MOD_ID + ":textures/gsar/models/semi_signal_hp2.png"),
    GSAR_ICON_HL(SignalCraft.MOD_ID + ":textures/gsar/models/light_signal_sign.png"),
    GSAR_ICON_WN1(SignalCraft.MOD_ID + ":textures/gsar/models/wn_signal.png"),
    GSAR_ICON_RAILCROSS(SignalCraft.MOD_ID + ":textures/gsar/models/raicross_sign.png"),
    GSAR_STATION_SIGN(SignalCraft.MOD_ID + ":textures/gsar/models/station_Sign.png")
    ;
    private final ResourceLocation texture;

    TextureRegistry(String path) {
        texture = new ResourceLocation(path);
    }

    public ResourceLocation get() {
        return texture;
    }
}