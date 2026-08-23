package signalcraft.entities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.controllers.TileContReceBase;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.crossings.TileCrossingController;
import signalcraft.entities.controllers.crossings.TileCrossingReceiver;
import signalcraft.entities.controllers.signals.lightSignals.TileReceiverLightSignals;
import signalcraft.entities.controllers.signals.lightSignals.TileRedControllerLightSignals;
import signalcraft.entities.controllers.signals.TileRedReceiverSignals;
import signalcraft.entities.controllers.universal.TileControllerUniversal;
import signalcraft.entities.controllers.universal.TileReceiverUniversal;
import signalcraft.entities.gsar.blocks.*;
import signalcraft.entities.gsar.signalsBU.*;
import signalcraft.entities.gsar.signalsHP.*;
import signalcraft.entities.gsar.signalsLF.*;
import signalcraft.entities.gsar.signalsNE.*;
import signalcraft.entities.gsar.signalsRA.*;
import signalcraft.entities.gsar.signalsSH.*;
import signalcraft.entities.gsar.signalsSO.TileGSARHectometer;
import signalcraft.entities.gsar.signalsSO.TileGSARSignalLever;
import signalcraft.entities.gsar.signalsSO.TileGSARStationSign;
import signalcraft.entities.gsar.signalsWN.TileSwitchElectricGSAR;
import signalcraft.entities.gsar.signalsWN.TileSwitchManualGSAR;
import signalcraft.entities.levelCrossings.azd.*;
import signalcraft.entities.levelCrossings.sssr.TileSSSR;
import signalcraft.entities.levelCrossings.sssr.TileSSSRHead;
import signalcraft.entities.levelCrossings.sssr.TileSSSRSingle;
import signalcraft.entities.levelCrossings.sssr.TileSSSRSingleHead;
import signalcraft.entities.levelCrossings.vud.TileVUD;
import signalcraft.entities.signals.lightSignals.azd70.*;
import signalcraft.entities.signals.lightSignals.sssr.*;
import signalcraft.entities.signals.signSignals.*;
import signalcraft.models.gsar.signalsBU.ModelGSARBarriers;
import signalcraft.models.gsar.signalsBU.ModelGSARFullBarriers;
import signalcraft.models.gsar.signalsBU.ModelGSARLightSignalsBU;
import signalcraft.models.gsar.signalsBU.ModelGSARRailCross;
import signalcraft.models.gsar.signalsHP.ModelGSARLightSignals;
import signalcraft.models.gsar.signalsHP.ModelGSARSemiSignals;
import signalcraft.models.gsar.signalsHP.ModelGSARSemiSignalsVR;
import signalcraft.models.gsar.signalsHP.ModelGSARStativSemiSignals;
import signalcraft.models.gsar.signalsNE.ModelGSARSignalNE13;
import signalcraft.models.gsar.signalsRA.ModelGSARSignalRA11;
import signalcraft.models.gsar.signalsSH.ModelGSARSignalSH2;
import signalcraft.models.gsar.signalsSH.ModelGSARSignalSHF;
import signalcraft.models.gsar.signalsSH.ModelGSARSignalSHL;
import signalcraft.models.gsar.signalsSO.ModelSignalsLeverGSAR;
import signalcraft.models.gsar.signalsSO.ModelStationSignGSAR;
import signalcraft.models.gsar.signalsWN.ModelSwitchesGSAR;
import signalcraft.models.levelCrossing.azd.*;
import signalcraft.models.levelCrossing.sssr.ModelCrossSSSR;
import signalcraft.models.levelCrossing.sssr.ModelCrossSSSRHead;
import signalcraft.models.levelCrossing.sssr.ModelCrossSSSRSingle;
import signalcraft.models.levelCrossing.sssr.ModelCrossSSSRSingleHead;
import signalcraft.models.levelCrossing.vud.ModelVUD;
import signalcraft.models.lightSignals.azd70.*;
import signalcraft.models.lightSignals.sssr.*;
import signalcraft.renderers.entities.GenericTileRenderer;
import signalcraft.renderers.entities.TileSpeedSignRenderer;
import signalcraft.renderers.entities.controllers.TileControllerRenderer;
import signalcraft.renderers.entities.gsar.*;
import signalcraft.renderers.entities.levelCrossings.azd.TileAZD99Renderer;
import signalcraft.renderers.entities.levelCrossings.TileCrossingsRenderer;
import signalcraft.renderers.entities.lightSignals.TileLightSignalsRenderer;
import signalcraft.renderers.entities.signSignals.TilePrUpozRenderer;

import java.util.function.Supplier;

public enum SCTEntities {
    SPEED_SIGN(TileSpeedSign.class, "SpeedSignal"),
    PR_UPOZ_SIGN(TilePrUpoz.class, "PrUpozSignal"),
    AZD_1LIGHT(TileAZD1Light.class, "AZD1Light"),
    AZD_2LIGHTS(TileAZD2Lights.class, "AZD2Lights"),
    AZD_2LIGHTS_T(TileAZD2LightsT.class, "AZD2LightsT"),
    AZD_3LIGHTS(TileAZD3Lights.class, "AZD3Lights"),
    AZD_3LIGHTS_T(TileAZD3LightsT.class, "AZD3LightsT"),
    AZD_4LIGHTS(TileAZD4Lights.class, "AZD4Lights"),
    AZD_4LIGHTS_T(TileAZD4LightsT.class, "AZD4LightsT"),
    AZD_5LIGHTS(TileAZD5Lights.class, "AZD5Lights"),
    AZD_5LIGHTS_T(TileAZD5LightsT.class, "AZD5LightsT"),
    AZD_6LIGHTS(TileAZD6Lights.class, "AZD6Lights"),
    AZD_AB3(TileAZDAB3.class, "AZDAB3"),
    AZD_AB4(TileAZDAB4.class, "AZDAB4"),
    AZD_DIST(TileAZDPr.class, "AZDPr"),
    AZD_RE_DIST(TileAZDOPr.class, "AZDOPr"),
    AZD_SHUNT(TileAZDPosun.class, "AZDPosun"),
    AZD_SHUNT_T(TileAZDPosunT.class, "AZDPosunT"),
    AZD_INSERTED(TileAZDVloz.class, "AZDVloz"),
    AZD_71(TileAZD71.class, "AZD71"),
    AZD_97(TileAZD97.class, "AZD97"),
    AZD_71_HEAD(TileAZD71Head.class, "AZD71Head"),
    AZD_97_HEAD(TileAZD97Head.class, "AZD97Head"),
    VUD(TileVUD.class, "VUD"),
    AZD_99(TileAZD99.class, "AZD99"),
    SSSR(TileSSSR.class, "SSSR"),
    SSSR_SINGLE(TileSSSRSingle.class, "SSSRSingle"),
    SSSR_HEAD(TileSSSRHead.class, "SSSRHead"),
    SSSR_SINGLE_HEAD(TileSSSRSingleHead.class, "SSSRSingleHead"),
    SSSR_5LIGHTS(TileSSSR5Lights.class, "SSSR5Lights"),
    SSSR_5LIGHTS_T(TileSSSR5LightsT.class, "SSSR5LightsT"),
    SSSR_4LIGHTS(TileSSSR4Lights.class, "SSSR4Lights"),
    SSSR_4LIGHTS_T(TileSSSR4LightsT.class, "SSSR4LightsT"),
    SSSR_3LIGHTS(TileSSSR3Lights.class, "SSSR3Lights"),
    SSSR_3LIGHTS_T(TileSSSR3LightsT.class, "SSSR3LightsT"),
    SSSR_3LIGHTS_MECH_T(TileSSSR3LightsMechT.class, "SSSR3LightsMechT"),
    SSSR_2LIGHTS(TileSSSR2Lights.class, "SSSR2Lights"),
    SSSR_2LIGHTS_T(TileSSSR2LightsT.class, "SSSR2LightsT"),
    SSSR_2LIGHTS_MECH_T(TileSSSR2LightsMechT.class, "SSSR2LightsMechT"),
    SSSR_SHUNT(TileSSSRPosun.class, "SSSRPosun"),
    SSSR_SHUNT_T(TileSSSRPosunT.class, "SSSRPosunT"),
    SSSR_DISTANT(TileSSSRPr.class, "SSSRPr"),
    SSSR_DISTANT_REPEATING(TileSSSROPr.class, "SSSROPr"),
    SSSR_INSERTED(TileSSSRVloz.class, "SSSRVloz"),
    SSSR_AB3(TileSSSRAB3.class, "SSSRAB3"),
    SSSR_AB4(TileSSSRAB4.class, "SSSRAB4"),

    //AZD65_2LIGHTS(TileAZD652Lights.class, "AZD65_2Lights", new TileLightSignalsRenderer(new ModelAZD652Lights())),

    //Controllers and Receivers --------------------------------------------

    CONT_RECE_BASE(TileContReceBase.class, "ContReceBase"),
    CONT_CROSSINGS(TileCrossingController.class, "CrossCont"),
    RECE_CROSSINGS(TileCrossingReceiver.class, "CrossRece"),
    RECE_LIGHT_SIGNALS(TileReceiverLightSignals.class, "LightRece"),
    CONT_REDSTONE_LIGHT_SIGNALS(TileRedControllerLightSignals.class, "LightRedCont"),
    RECE_REDSTONE_SIGNALS(TileRedReceiverSignals.class, "RedRece"),
    RECE_UNIVERSAL(TileReceiverUniversal.class, "UniversalRece"),
    CONT_UNIVERSAL(TileControllerUniversal.class, "UniversalCont"),

    //GSAR Stuff --------------------------------------------

    SIGNAL_HP3(TileGSARLightSignalHPx3.class, "SignalHPx3"),
    SIGNAL_HP5(TileGSARLightSignalHPx5.class, "SignalHPx5"),
    SIGNAL_VR3(TileGSARLightSignalVRx3.class, "SignalVRx3"),
    SIGNAL_VR5(TileGSARLightSignalVRx5.class, "SignalVRx5"),
    SIGN_NE1(TileGSARSignalNE1.class, "SignNE1"),
    SIGN_NE2(TileGSARSignalNE2.class, "SignNE2"),
    SIGN_NE3_1(TileGSARSignalNE3_1.class, "SignNE3_1"),
    SIGN_NE3_2(TileGSARSignalNE3_2.class, "SignNE3_2"),
    SIGN_NE3_3(TileGSARSignalNE3_3.class, "SignNE3_3"),
    SIGN_NE4(TileGSARSignalNE4.class, "SignNE4"),
    SIGN_NE5(TileGSARSignalNE5.class, "SignNE5"),
    SIGN_NE6(TileGSARSignalNE6.class, "SignNE6"),
    SIGN_NE7(TileGSARSignalNE7.class, "SignNE7"),
    SIGN_NE12(TileGSARSignalNE12.class, "SignNE12"),
    SIGNAL_NE13(TileGSARSignalNE13.class, "SignalNE13"),

    SIGN_LF1(TileGSARSignalLF1.class, "SignLF1"),
    SIGN_LF2(TileGSARSignalLF2.class, "SignLF2"),
    SIGN_LF3(TileGSARSignalLF3.class, "SignLF3"),
    SIGN_LF6(TileGSARSignalLF6.class, "SignLF6"),
    SIGN_LF7(TileGSARSignalLF7.class, "SignLF7"),

    SIGNAL_SHL(TileGSARLightSignalSHL.class, "SignalSHL"),
    SIGNAL_SHL_SINGLE(TileGSARLightSignalSHLSingle.class, "SignalSHLSingle"),
    SIGNAL_SHF(TileGSARSemiSignalSHF.class, "SignalSHF"),
    SIGNAL_SHF_SINGLE(TileGSARSemiSignalSHFSingle.class, "SignalSHFSingle"),
    SIGN_SH2(TileGSARSignSignalSH2.class, "SignSH2"),

    SIGN_HECTO(TileGSARHectometer.class, "SignHectometer"),
    SIGNAL_LEVER(TileGSARSignalLever.class, "SignalLever"),
    STATION_SIGN(TileGSARStationSign.class, "StationSign"),

    SIGN_RA10A(TileGSARSignalRA10a.class, "SignRA10a"),
    SIGN_RA10B(TileGSARSignalRA10b.class, "SignRA10b"),
    SIGN_RA11A(TileGSARSignalRA11a.class, "SignRA11a"),
    SIGN_RA11B(TileGSARSignalRA11b.class, "SignRA11b"),
    SIGN_RA11W(TileGSARSignalRA11W.class, "SignRA11W"),
    SIGN_RA11Y(TileGSARSignalRA11Y.class, "SignRA11Y"),
    SIGNAL_RA11WL(TileGSARSignalRA11WL.class, "SignalRA11WL"),
    SIGNAL_RA11YL(TileGSARSignalRA11YL.class, "SignalRA11YL"),
    STATIV_RA11(TileGSARStativRA11.class, "StativRA11"),

    SIGN_BU2(TileGSARSignalBU2.class, "SignBU2"),
    SIGN_BU3(TileGSARSignalBU3.class, "SignBU3"),
    SIGN_BU4(TileGSARSignalBU4.class, "SignBU4"),
    SIGN_BU4Z(TileGSARSignalBU4Z.class, "SignBU4Z"),
    SIGN_BU5(TileGSARSignalBU5.class, "SignBU5"),
    SIGN_BU5Z(TileGSARSignalBU5Z.class, "SignBU5Z"),
    SIGN_PF2(TileGSARSignalPF2.class, "SignPF2"),
    SIGN_PF2Z(TileGSARSignalPF2Z.class, "SignPF2Z"),
    SIGN_CROSS(TileGSARRailCross.class, "SignCross"),
    SIGN_CROSS_FENCE(TileGSARRailCrossFence.class, "SignCrossFence"),
    SIGN_CROSS_FLASH(TileGSARRailCrossFlash.class, "SignCrossFlash"),
    STATIV_CROSS(TileGSARRailCrossStativ.class, "RailCrossStativ"),
    BARRIER_STOP(TileGSARBarrierStop.class, "BarrierStop"),
    BARRIER_FULL4L(TileGSARFullBarriersx4L.class, "FullBarrier4L"),
    BARRIER_FULL4R(TileGSARFullBarriersx4R.class, "FullBarrier4R"),
    BARRIER_FULL10L(TileGSARFullBarriersx10L.class, "FullBarrier10L"),
    BARRIER_FULL10R(TileGSARFullBarriersx10R.class, "FullBarrier10R"),
    CROSS_LIGHT(TileGSARRailCrossLight.class, "CrossLight"),
    CROSS_LIGHT_S(TileGSARRailCrossLightS.class, "CrossLightS"),
    CROSS_MODERN(TileGSARRailCrossModern.class, "CrossModern"),
    SIGNAL_BU0x3(TileGSARLightSignalBU0x3.class, "LightSignalBU0x3"),
    SIGNAL_BU0x5(TileGSARLightSignalBU0x5.class, "LightSignalBU0x5"),
    BARRIER_HALF_L(TileGSARHalfBarrierL.class, "HalfBarrierL"),
    BARRIER_HALF_R(TileGSARHalfBarrierR.class, "HalfBarrierR"),
    BARRIER_MODERN_L(TileGSARModernBarrierL.class, "ModernBarrierL"),
    BARRIER_MODERN_R(TileGSARModernBarrierR.class, "ModernBarrierR"),

    STATIV_LIGHT_HP(TileGSARStativLightSignals.class, "StativLightHP"),
    STATIV_LIGHT_VR(TileGSARStativLightSignalsVR.class, "StativLightVR"),
    SEMI_SIGNAL_1W_HP3(TileGSARSemiSignal1Wingsx3.class, "SemiSignal1WHPx3"),
    SEMI_SIGNAL_1W_HP5(TileGSARSemiSignal1Wingsx5.class, "SemiSignal1WHPx5"),
    SEMI_SIGNAL_2W_HP3(TileGSARSemiSignal2Wingsx3.class, "SemiSignal2WHPx3"),
    SEMI_SIGNAL_2W_HP5(TileGSARSemiSignal2Wingsx5.class, "SemiSignal2WHPx5"),
    SEMI_SIGNAL_VRx3(TileGSARSemiSignalVRx3.class, "SemiSignalVRx3"),
    STATIV_SEMI_HP(TileGSARStativSemiSignals.class, "StativSemiHP"),
    STATIV_SEMI_VR(TileGSARStativSemiSignalsVR.class, "StativSemiVR"),
    SWITCH_MANUAL(TileSwitchManualGSAR.class, "SwitchManualGSAR"),
    SWITCH_ELECTRIC(TileSwitchElectricGSAR.class, "SwitchElectricGSAR"),

    METAL_ROD(TileMetalRod.class, "MetalRod"),
    BRIDGE_BEAMS(TileBridgeBeams.class, "BridgeBeams"),
    BRIDGE_BEAMS_CORNER(TileBridgeBeamsCorner.class, "BridgeBeamsCorner"),
    BRIDGE_BEAMS_CROSS(TileBridgeBeamsCross.class, "BridgeBeamsCross"),
    BRIDGE_BEAMS_TRIPLE(TileBridgeBeamsTriple.class, "BridgeBeamsTriple"),
    BRIDGE_GROUND(TileBridgeGround.class, "BridgeGround"),
    BRIDGE_GROUND_BEAMS(TileBridgeGroundBeams.class, "BridgeGroundBeams"),
    LADDER(TileLadder.class, "Ladder"),
    RAILING(TileRailing.class, "Railing"),
    RAILING_2(TileRailing2.class, "Railing2"),
    RAILING_RODS(TileRailingRods.class, "RailingRods"),

    ;
    public final Class<? extends TileEntity> tileEntityClass;
    public final String Id;

    SCTEntities(Class<? extends TileEntity> tileEntityClass, String Id) {
        this.tileEntityClass = tileEntityClass;
        this.Id = Id;
    }

    public String getIdByTile(TileEntity tile) {
        for (SCTEntities entity : SCTEntities.values()) {
            if (entity.tileEntityClass.isInstance(tile)) return entity.Id;
        }
        return "TileNotRegistered";
    }

    // Kept in a separate nested class so the server, which touches SCTEntities directly, never has to resolve TileEntitySpecialRenderer.
    public static final class Renderers {
        public static final java.util.EnumMap<SCTEntities, Supplier<TileEntitySpecialRenderer>> MAP = new java.util.EnumMap<>(SCTEntities.class);

        static {
            MAP.put(SPEED_SIGN, TileSpeedSignRenderer::new);
            MAP.put(PR_UPOZ_SIGN, TilePrUpozRenderer::new);
            MAP.put(AZD_1LIGHT, () -> new TileLightSignalsRenderer(new ModelAZD1Light()));
            MAP.put(AZD_2LIGHTS, () -> new TileLightSignalsRenderer(new ModelAZD2Lights()));
            MAP.put(AZD_2LIGHTS_T, () -> new TileLightSignalsRenderer(new ModelAZD2LightsT()));
            MAP.put(AZD_3LIGHTS, () -> new TileLightSignalsRenderer(new ModelAZD3Lights()));
            MAP.put(AZD_3LIGHTS_T, () -> new TileLightSignalsRenderer(new ModelAZD3LightsT()));
            MAP.put(AZD_4LIGHTS, () -> new TileLightSignalsRenderer(new ModelAZD4Lights()));
            MAP.put(AZD_4LIGHTS_T, () -> new TileLightSignalsRenderer(new ModelAZD4LightsT()));
            MAP.put(AZD_5LIGHTS, () -> new TileLightSignalsRenderer(new ModelAZD5Lights()));
            MAP.put(AZD_5LIGHTS_T, () -> new TileLightSignalsRenderer(new ModelAZD5LightsT()));
            MAP.put(AZD_6LIGHTS, () -> new TileLightSignalsRenderer(new ModelAZD6Lights()));
            MAP.put(AZD_AB3, () -> new TileLightSignalsRenderer(new ModelAZDAB3()));
            MAP.put(AZD_AB4, () -> new TileLightSignalsRenderer(new ModelAZDAB4()));
            MAP.put(AZD_DIST, () -> new TileLightSignalsRenderer(new ModelAZDPr()));
            MAP.put(AZD_RE_DIST, () -> new TileLightSignalsRenderer(new ModelAZDOPr()));
            MAP.put(AZD_SHUNT, () -> new TileLightSignalsRenderer(new ModelAZDPosun()));
            MAP.put(AZD_SHUNT_T, () -> new TileLightSignalsRenderer(new ModelAZDPosunT()));
            MAP.put(AZD_INSERTED, () -> new TileLightSignalsRenderer(new ModelAZDVloz()));
            MAP.put(AZD_71, () -> new TileCrossingsRenderer(new ModelAZD71()));
            MAP.put(AZD_97, () -> new TileCrossingsRenderer(new ModelAZD97()));
            MAP.put(AZD_71_HEAD, () -> new TileCrossingsRenderer(new ModelAZD71Head()));
            MAP.put(AZD_97_HEAD, () -> new TileCrossingsRenderer(new ModelAZD97Head()));
            MAP.put(VUD, () -> new TileCrossingsRenderer(new ModelVUD()));
            MAP.put(AZD_99, () -> new TileAZD99Renderer(new ModelAZD99()));
            MAP.put(SSSR, () -> new TileCrossingsRenderer(new ModelCrossSSSR()));
            MAP.put(SSSR_SINGLE, () -> new TileCrossingsRenderer(new ModelCrossSSSRSingle()));
            MAP.put(SSSR_HEAD, () -> new TileCrossingsRenderer(new ModelCrossSSSRHead()));
            MAP.put(SSSR_SINGLE_HEAD, () -> new TileCrossingsRenderer(new ModelCrossSSSRSingleHead()));
            MAP.put(SSSR_5LIGHTS, () -> new TileLightSignalsRenderer(new ModelSSSR5Lights()));
            MAP.put(SSSR_5LIGHTS_T, () -> new TileLightSignalsRenderer(new ModelSSSR5LightsT()));
            MAP.put(SSSR_4LIGHTS, () -> new TileLightSignalsRenderer(new ModelSSSR4Lights()));
            MAP.put(SSSR_4LIGHTS_T, () -> new TileLightSignalsRenderer(new ModelSSSR4LightsT()));
            MAP.put(SSSR_3LIGHTS, () -> new TileLightSignalsRenderer(new ModelSSSR3Lights()));
            MAP.put(SSSR_3LIGHTS_T, () -> new TileLightSignalsRenderer(new ModelSSSR3LightsT()));
            MAP.put(SSSR_3LIGHTS_MECH_T, () -> new TileLightSignalsRenderer(new ModelSSSR3LightsMechT()));
            MAP.put(SSSR_2LIGHTS, () -> new TileLightSignalsRenderer(new ModelSSSR2Lights()));
            MAP.put(SSSR_2LIGHTS_T, () -> new TileLightSignalsRenderer(new ModelSSSR2LightsT()));
            MAP.put(SSSR_2LIGHTS_MECH_T, () -> new TileLightSignalsRenderer(new ModelSSSR2LightsMechT()));
            MAP.put(SSSR_SHUNT, () -> new TileLightSignalsRenderer(new ModelSSSRPosun()));
            MAP.put(SSSR_SHUNT_T, () -> new TileLightSignalsRenderer(new ModelSSSRPosunT()));
            MAP.put(SSSR_DISTANT, () -> new TileLightSignalsRenderer(new ModelSSSRPr()));
            MAP.put(SSSR_DISTANT_REPEATING, () -> new TileLightSignalsRenderer(new ModelSSSROPr()));
            MAP.put(SSSR_INSERTED, () -> new TileLightSignalsRenderer(new ModelSSSRVloz()));
            MAP.put(SSSR_AB3, () -> new TileLightSignalsRenderer(new ModelSSSRAB3()));
            MAP.put(SSSR_AB4, () -> new TileLightSignalsRenderer(new ModelSSSRAB4()));
            MAP.put(CONT_RECE_BASE, () -> new TileControllerRenderer(new TileContReceBase()));
            MAP.put(CONT_CROSSINGS, () -> new TileControllerRenderer(new TileCrossingController()));
            MAP.put(RECE_CROSSINGS, () -> new TileControllerRenderer(new TileCrossingReceiver()));
            MAP.put(RECE_LIGHT_SIGNALS, () -> new TileControllerRenderer(new TileReceiverLightSignals()));
            MAP.put(CONT_REDSTONE_LIGHT_SIGNALS, () -> new TileControllerRenderer(new TileRedControllerLightSignals()));
            MAP.put(RECE_REDSTONE_SIGNALS, () -> new TileControllerRenderer(new TileRedReceiverSignals()));
            MAP.put(RECE_UNIVERSAL, () -> new TileControllerRenderer(new TileReceiverUniversal()));
            MAP.put(CONT_UNIVERSAL, () -> new TileControllerRenderer(new TileControllerUniversal()));
            MAP.put(SIGNAL_HP3, () -> new TileLightSignalsRendererGSAR(new ModelGSARLightSignals()));
            MAP.put(SIGNAL_HP5, () -> new TileLightSignalsRendererGSAR(new ModelGSARLightSignals()));
            MAP.put(SIGNAL_VR3, () -> new TileLightSignalsRendererGSAR(new ModelGSARLightSignals()));
            MAP.put(SIGNAL_VR5, () -> new TileLightSignalsRendererGSAR(new ModelGSARLightSignals()));
            MAP.put(SIGN_NE1, () -> new GenericTileRenderer(new TileGSARSignalNE1()));
            MAP.put(SIGN_NE2, () -> new GenericTileRenderer(new TileGSARSignalNE2()));
            MAP.put(SIGN_NE3_1, () -> new GenericTileRenderer(new TileGSARSignalNE3_1()));
            MAP.put(SIGN_NE3_2, () -> new GenericTileRenderer(new TileGSARSignalNE3_2()));
            MAP.put(SIGN_NE3_3, () -> new GenericTileRenderer(new TileGSARSignalNE3_3()));
            MAP.put(SIGN_NE4, () -> new GenericTileRenderer(new TileGSARSignalNE4()));
            MAP.put(SIGN_NE5, () -> new GenericTileRenderer(new TileGSARSignalNE5()));
            MAP.put(SIGN_NE6, () -> new GenericTileRenderer(new TileGSARSignalNE6()));
            MAP.put(SIGN_NE7, () -> new GenericTileRenderer(new TileGSARSignalNE7()));
            MAP.put(SIGN_NE12, () -> new GenericTileRenderer(new TileGSARSignalNE12()));
            MAP.put(SIGNAL_NE13, () -> new TileLightSignalsRendererGSAR(new ModelGSARSignalNE13()));
            MAP.put(SIGN_LF1, () -> new GenericTileRenderer(new TileGSARSignalLF1()));
            MAP.put(SIGN_LF2, () -> new GenericTileRenderer(new TileGSARSignalLF2()));
            MAP.put(SIGN_LF3, () -> new GenericTileRenderer(new TileGSARSignalLF3()));
            MAP.put(SIGN_LF6, () -> new GenericTileRenderer(new TileGSARSignalLF6()));
            MAP.put(SIGN_LF7, () -> new GenericTileRenderer(new TileGSARSignalLF7()));
            MAP.put(SIGNAL_SHL, () -> new TileLightSignalsRendererGSAR(new ModelGSARSignalSHL()));
            MAP.put(SIGNAL_SHL_SINGLE, () -> new TileLightSignalsRendererGSAR(new ModelGSARSignalSHL()));
            MAP.put(SIGNAL_SHF, () -> new TileSemiSignalsRendererGSAR(new ModelGSARSignalSHF()));
            MAP.put(SIGNAL_SHF_SINGLE, () -> new TileSemiSignalsRendererGSAR(new ModelGSARSignalSHF()));
            MAP.put(SIGN_SH2, () -> new TileSignalSH2RendererGSAR(new ModelGSARSignalSH2()));
            MAP.put(SIGN_HECTO, () -> new GenericTileRenderer(new TileGSARHectometer()));
            MAP.put(SIGNAL_LEVER, () -> new TileSignalLeverRendererGSAR(new ModelSignalsLeverGSAR()));
            MAP.put(STATION_SIGN, () -> new TileStationSignRendererGSAR(new ModelStationSignGSAR()));
            MAP.put(SIGN_RA10A, () -> new GenericTileRenderer(new TileGSARSignalRA10a()));
            MAP.put(SIGN_RA10B, () -> new GenericTileRenderer(new TileGSARSignalRA10b()));
            MAP.put(SIGN_RA11A, () -> new GenericTileRenderer(new TileGSARSignalRA11a()));
            MAP.put(SIGN_RA11B, () -> new GenericTileRenderer(new TileGSARSignalRA11b()));
            MAP.put(SIGN_RA11W, () -> new TileLightSignalsRendererGSAR(new ModelGSARSignalRA11()));
            MAP.put(SIGN_RA11Y, () -> new TileLightSignalsRendererGSAR(new ModelGSARSignalRA11()));
            MAP.put(SIGNAL_RA11WL, () -> new TileLightSignalsRendererGSAR(new ModelGSARSignalRA11()));
            MAP.put(SIGNAL_RA11YL, () -> new TileLightSignalsRendererGSAR(new ModelGSARSignalRA11()));
            MAP.put(STATIV_RA11, () -> new GenericTileRenderer(new TileGSARStativRA11()));
            MAP.put(SIGN_BU2, () -> new GenericTileRenderer(new TileGSARSignalBU2()));
            MAP.put(SIGN_BU3, () -> new GenericTileRenderer(new TileGSARSignalBU3()));
            MAP.put(SIGN_BU4, () -> new GenericTileRenderer(new TileGSARSignalBU4()));
            MAP.put(SIGN_BU4Z, () -> new GenericTileRenderer(new TileGSARSignalBU4Z()));
            MAP.put(SIGN_BU5, () -> new GenericTileRenderer(new TileGSARSignalBU5()));
            MAP.put(SIGN_BU5Z, () -> new GenericTileRenderer(new TileGSARSignalBU5Z()));
            MAP.put(SIGN_PF2, () -> new GenericTileRenderer(new TileGSARSignalPF2()));
            MAP.put(SIGN_PF2Z, () -> new GenericTileRenderer(new TileGSARSignalPF2Z()));
            MAP.put(SIGN_CROSS, () -> new GenericTileRenderer(new TileGSARRailCross()));
            MAP.put(SIGN_CROSS_FENCE, () -> new GenericTileRenderer(new TileGSARRailCrossFence()));
            MAP.put(SIGN_CROSS_FLASH, () -> new GenericTileRenderer(new TileGSARRailCrossFlash()));
            MAP.put(STATIV_CROSS, () -> new GenericTileRenderer(new TileGSARRailCrossStativ()));
            MAP.put(BARRIER_STOP, () -> new GenericTileRenderer(new TileGSARBarrierStop()));
            MAP.put(BARRIER_FULL4L, () -> new TileBarrierRendererGSAR(new ModelGSARFullBarriers()));
            MAP.put(BARRIER_FULL4R, () -> new TileBarrierRendererGSAR(new ModelGSARFullBarriers()));
            MAP.put(BARRIER_FULL10L, () -> new TileBarrierRendererGSAR(new ModelGSARFullBarriers()));
            MAP.put(BARRIER_FULL10R, () -> new TileBarrierRendererGSAR(new ModelGSARFullBarriers()));
            MAP.put(CROSS_LIGHT, () -> new TileRailCrossRendererGSAR(new ModelGSARRailCross()));
            MAP.put(CROSS_LIGHT_S, () -> new TileRailCrossRendererGSAR(new ModelGSARRailCross()));
            MAP.put(CROSS_MODERN, () -> new TileRailCrossRendererGSAR(new ModelGSARRailCross()));
            MAP.put(SIGNAL_BU0x3, () -> new TileLightSignalsRendererGSAR(new ModelGSARLightSignalsBU()));
            MAP.put(SIGNAL_BU0x5, () -> new TileLightSignalsRendererGSAR(new ModelGSARLightSignalsBU()));
            MAP.put(BARRIER_HALF_L, () -> new TileBarrierRendererGSAR(new ModelGSARBarriers()));
            MAP.put(BARRIER_HALF_R, () -> new TileBarrierRendererGSAR(new ModelGSARBarriers()));
            MAP.put(BARRIER_MODERN_L, () -> new TileBarrierRendererGSAR(new ModelGSARBarriers()));
            MAP.put(BARRIER_MODERN_R, () -> new TileBarrierRendererGSAR(new ModelGSARBarriers()));
            MAP.put(STATIV_LIGHT_HP, () -> new TileStativRendererGSAR(new ModelGSARLightSignals()));
            MAP.put(STATIV_LIGHT_VR, () -> new TileStativRendererGSAR(new ModelGSARLightSignals()));
            MAP.put(SEMI_SIGNAL_1W_HP3, () -> new TileSemiSignalsRendererGSAR(new ModelGSARSemiSignals()));
            MAP.put(SEMI_SIGNAL_1W_HP5, () -> new TileSemiSignalsRendererGSAR(new ModelGSARSemiSignals()));
            MAP.put(SEMI_SIGNAL_2W_HP3, () -> new TileSemiSignalsRendererGSAR(new ModelGSARSemiSignals()));
            MAP.put(SEMI_SIGNAL_2W_HP5, () -> new TileSemiSignalsRendererGSAR(new ModelGSARSemiSignals()));
            MAP.put(SEMI_SIGNAL_VRx3, () -> new TileSemiSignalsRendererGSAR(new ModelGSARSemiSignalsVR()));
            MAP.put(STATIV_SEMI_HP, () -> new TileStativRendererGSAR(new ModelGSARStativSemiSignals()));
            MAP.put(STATIV_SEMI_VR, () -> new TileStativRendererGSAR(new ModelGSARStativSemiSignals()));
            MAP.put(SWITCH_MANUAL, () -> new TileSwitchRendererGSAR(new ModelSwitchesGSAR()));
            MAP.put(SWITCH_ELECTRIC, () -> new TileSwitchRendererGSAR(new ModelSwitchesGSAR()));
            MAP.put(METAL_ROD, () -> new GenericTileRenderer(new TileMetalRod()));
            MAP.put(BRIDGE_BEAMS, () -> new GenericTileRenderer(new TileBridgeBeams()));
            MAP.put(BRIDGE_BEAMS_CORNER, () -> new GenericTileRenderer(new TileBridgeBeamsCorner()));
            MAP.put(BRIDGE_BEAMS_CROSS, () -> new GenericTileRenderer(new TileBridgeBeamsCross()));
            MAP.put(BRIDGE_BEAMS_TRIPLE, () -> new GenericTileRenderer(new TileBridgeBeamsTriple()));
            MAP.put(BRIDGE_GROUND, () -> new GenericTileRenderer(new TileBridgeGround()));
            MAP.put(BRIDGE_GROUND_BEAMS, () -> new GenericTileRenderer(new TileBridgeGroundBeams()));
            MAP.put(LADDER, () -> new GenericTileRenderer(new TileLadder()));
            MAP.put(RAILING, () -> new GenericTileRenderer(new TileRailing()));
            MAP.put(RAILING_2, () -> new GenericTileRenderer(new TileRailing2()));
            MAP.put(RAILING_RODS, () -> new GenericTileRenderer(new TileRailingRods()));
        }

        private Renderers() {}
    }
}