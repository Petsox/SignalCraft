package signalcraft.ItemBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.IItemRenderer;
import signalcraft.ItemBlocks.controllers.*;
import signalcraft.ItemBlocks.gsar.blocks.*;
import signalcraft.ItemBlocks.gsar.signalsBU.*;
import signalcraft.ItemBlocks.gsar.signalsHP.*;
import signalcraft.ItemBlocks.gsar.signalsLF.*;
import signalcraft.ItemBlocks.gsar.signalsNE.*;
import signalcraft.ItemBlocks.gsar.signalsRA.*;
import signalcraft.ItemBlocks.gsar.signalsSH.*;
import signalcraft.ItemBlocks.gsar.signalsSO.ItemBlockGSARHectometer;
import signalcraft.ItemBlocks.gsar.signalsSO.ItemBlockGSARSignalLever;
import signalcraft.ItemBlocks.gsar.signalsSO.ItemBlockGSARStationSign;
import signalcraft.ItemBlocks.gsar.signalsWN.ItemBlockGSARSwitchElectric;
import signalcraft.ItemBlocks.gsar.signalsWN.ItemBlockGSARSwitchManual;
import signalcraft.ItemBlocks.levelCrossings.azd.*;
import signalcraft.ItemBlocks.levelCrossings.sssr.ItemBlockCrossSSSR;
import signalcraft.ItemBlocks.levelCrossings.sssr.ItemBlockCrossSSSRHead;
import signalcraft.ItemBlocks.levelCrossings.sssr.ItemBlockCrossSSSRSingle;
import signalcraft.ItemBlocks.levelCrossings.sssr.ItemBlockCrossSSSRSingleHead;
import signalcraft.ItemBlocks.levelCrossings.vud.ItemBlockVUD;
import signalcraft.ItemBlocks.lightSignals.azd70.*;
import signalcraft.ItemBlocks.lightSignals.sssr.*;
import signalcraft.ItemBlocks.signSignals.ItemBlockPrUpoz;
import signalcraft.ItemBlocks.signSignals.ItemBlockSpeedSign;
import signalcraft.blocks.controllers.BlockContReceBase;
import signalcraft.blocks.controllers.crossings.BlockCrossingController;
import signalcraft.blocks.controllers.crossings.BlockCrossingReceiver;
import signalcraft.blocks.controllers.signals.BlockReceiverLightSignals;
import signalcraft.blocks.controllers.signals.BlockRedControllerLightSignals;
import signalcraft.blocks.controllers.signals.BlockRedReceiverSignals;
import signalcraft.blocks.controllers.universal.BlockUniversalController;
import signalcraft.blocks.controllers.universal.BlockUniversalReceiver;
import signalcraft.blocks.gsar.blocks.*;
import signalcraft.blocks.gsar.signalsBU.*;
import signalcraft.blocks.gsar.signalsHP.*;
import signalcraft.blocks.gsar.signalsLF.*;
import signalcraft.blocks.gsar.signalsNE.*;
import signalcraft.blocks.gsar.signalsRA.*;
import signalcraft.blocks.gsar.signalsSH.*;
import signalcraft.blocks.gsar.signalsSO.BlockGSARHectometer;
import signalcraft.blocks.gsar.signalsSO.BlockGSARSignalLever;
import signalcraft.blocks.gsar.signalsSO.BlockGSARStationSign;
import signalcraft.blocks.gsar.signalsWN.BlockGSARSwitchManual;
import signalcraft.blocks.gsar.signalsWN.BlockGSARSwitchMechanic;
import signalcraft.blocks.levelCrossings.azd.*;
import signalcraft.blocks.levelCrossings.sssr.BlockCrossSSSR;
import signalcraft.blocks.levelCrossings.sssr.BlockCrossSSSRHead;
import signalcraft.blocks.levelCrossings.sssr.BlockCrossSSSRSingle;
import signalcraft.blocks.levelCrossings.sssr.BlockCrossSSSRSingleHead;
import signalcraft.blocks.levelCrossings.vud.BlockVUD;
import signalcraft.entities.controllers.TileContReceBase;
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
import signalcraft.entities.gsar.signalsWN.TileSwitchElectricGSAR;
import signalcraft.entities.gsar.signalsWN.TileSwitchManualGSAR;
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
import signalcraft.renderers.items.GenericItemRenderer;
import signalcraft.renderers.items.SpeedSignItemRenderer;
import signalcraft.blocks.signals.lightSignals.azd70.*;
import signalcraft.blocks.signals.lightSignals.sssr.*;
import signalcraft.blocks.signals.signSignals.*;
import signalcraft.renderers.items.controllers.ControllerItemRenderer;
import signalcraft.renderers.items.gsar.*;
import signalcraft.renderers.items.levelCrossings.AZD99ItemRenderer;
import signalcraft.renderers.items.levelCrossings.CrossingItemRenderer;
import signalcraft.renderers.items.lightSignals.LightSignalItemRenderer;
import signalcraft.renderers.items.signSignals.PrUpozItemRenderer;

import java.util.function.Supplier;

public enum SCItemBlocks {
    SPEED_SIGN(new BlockSpeedSign("SpeedSign"), ItemBlockSpeedSign.class),
    PR_UPOZ_SIGN(new BlockPrUpoz("PrUpoz"), ItemBlockPrUpoz.class),
    AZD_1_LIGHT(new BlockAZD1Light("AZD1Light"), ItemBlockAZD1Light.class),
    AZD_2_LIGHTS(new BlockAZD2Lights("AZD2Lights"), ItemBlockAZD2Lights.class),
    AZD_2_LIGHTS_T(new BlockAZD2LightsT("AZD2LightsT"), ItemBlockAZD2LightsT.class),
    AZD_3_LIGHTS(new BlockAZD3Lights("AZD3Lights"), ItemBlockAZD3Lights.class),
    AZD_3_LIGHTS_T(new BlockAZD3LightsT("AZD3LightsT"), ItemBlockAZD3LightsT.class),
    AZD_4_LIGHTS(new BlockAZD4Lights("AZD4Lights"), ItemBlockAZD4Lights.class),
    AZD_4_LIGHTS_T(new BlockAZD4LightsT("AZD4LightsT"), ItemBlockAZD4LightsT.class),
    AZD_5_LIGHTS(new BlockAZD5Lights("AZD5Lights"), ItemBlockAZD5Lights.class),
    AZD_5_LIGHTS_T(new BlockAZD5LightsT("AZD5LightsT"), ItemBlockAZD5LightsT.class),
    AZD_6_LIGHTS(new BlockAZD6Lights("AZD6Lights"), ItemBlockAZD6Lights.class),
    AZD_AB3(new BlockAZDAB3("AZDAB3"), ItemBlockAZDAB3.class),
    AZD_AB4(new BlockAZDAB4("AZDAB4"), ItemBlockAZDAB4.class),
    AZD_DIST(new BlockAZDPr("AZDPr"), ItemBlockAZDPr.class),
    AZD_RE_DIST(new BlockAZDOPr("AZDOPr"), ItemBlockAZDOPr.class),
    AZD_SHUNT(new BlockAZDPosun("AZDPosun"), ItemBlockAZDPosun.class),
    AZD_SHUNT_T(new BlockAZDPosunT("AZDPosunT"), ItemBlockAZDPosunT.class),
    AZD_INSERTED(new BlockAZDVloz("AZDVloz"), ItemBlockAZDVloz.class),
    AZD_71(new BlockAZD71("CrossAZD71"), ItemBlockAZD71.class),
    AZD_97(new BlockAZD97("CrossAZD97"), ItemBlockAZD97.class),
    AZD_71_HEAD(new BlockAZD71Head("CrossAZD71Head"), ItemBlockAZD71Head.class),
    AZD_97_HEAD(new BlockAZD97Head("CrossAZD97Head"), ItemBlockAZD97Head.class),
    VUD(new BlockVUD("CrossVUD"), ItemBlockVUD.class),
    AZD_99(new BlockAZD99("CrossAZD99"), ItemBlockAZD99.class),
    SSSR(new BlockCrossSSSR("CrossSSSR"), ItemBlockCrossSSSR.class),
    SSSR_SINGLE(new BlockCrossSSSRSingle("CrossSSSRSingle"), ItemBlockCrossSSSRSingle.class),
    SSSR_HEAD(new BlockCrossSSSRHead("CrossSSSRHead"), ItemBlockCrossSSSRHead.class),
    SSSR_SINGLE_HEAD(new BlockCrossSSSRSingleHead("CrossSSSRSingleHead"), ItemBlockCrossSSSRSingleHead.class),
    SSSR_5_LIGHTS(new BlockSSSR5Lights("SSSR5Lights"), ItemBlockSSSR5Lights.class),
    SSSR_5_LIGHTS_T(new BlockSSSR5LightsT("SSSR5LightsT"), ItemBlockSSSR5LightsT.class),
    SSSR_4_LIGHTS(new BlockSSSR4Lights("SSSR4Lights"), ItemBlockSSSR4Lights.class),
    SSSR_4_LIGHTS_T(new BlockSSSR4LightsT("SSSR4LightsT"), ItemBlockSSSR4LightsT.class),
    SSSR_3_LIGHTS(new BlockSSSR3Lights("SSSR3Lights"), ItemBlockSSSR3Lights.class),
    SSSR_3_LIGHTS_T(new BlockSSSR3LightsT("SSSR3LightsT"), ItemBlockSSSR3LightsT.class),
    SSSR_3_LIGHTS_MECH_T(new BlockSSSR3LightsMechT("SSSR3LightsMechT"), ItemBlockSSSR3LightsMechT.class),
    SSSR_2_LIGHTS(new BlockSSSR2Lights("SSSR2Lights"), ItemBlockSSSR2Lights.class),
    SSSR_2_LIGHTS_T(new BlockSSSR2LightsT("SSSR2LightsT"), ItemBlockSSSR2LightsT.class),
    SSSR_2_LIGHTS_MECH_T(new BlockSSSR2LightsMechT("SSSR2LightsMechT"), ItemBlockSSSR2LightsMechT.class),
    SSSR_SHUNT(new BlockSSSRPosun("SSSRPosun"), ItemBlockSSSRPosun.class),
    SSSR_SHUNT_T(new BlockSSSRPosunT("SSSRPosunT"), ItemBlockSSSRPosunT.class),
    SSSR_DISTANT(new BlockSSSRPr("SSSRPr"), ItemBlockSSSRPr.class),
    SSSR_DISTANT_REPEATING(new BlockSSSROPr("SSSROPr"), ItemBlockSSSROPr.class),
    SSSR_INSERTED(new BlockSSSRVloz("SSSRVloz"), ItemBlockSSSRVloz.class),
    SSSR_AB3(new BlockSSSRAB3("SSSRAB3"), ItemBlockSSSRAB3.class),
    SSSR_AB4(new BlockSSSRAB4("SSSRAB4"), ItemBlockSSSRAB4.class),

    //AZD65_2LIGHTS(new BlockAZD652Lights("AZD65_2Lights"), new LightSignalItemRenderer(new ModelAZD652Lights()), ItemBlockAZD652Lights.class),

    //Controllers and Receivers--------------------------------------------

    CONT_RECE_BASE(new BlockContReceBase("ContReceBase"), ItemBlockContReceBase.class),
    CONT_CROSSINGS(new BlockCrossingController("CrossCont"), ItemBlockCrossingController.class),
    RECE_CROSSINGS(new BlockCrossingReceiver("CrossRece"), ItemBlockCrossingReceiver.class),
    RECE_LIGHT_SIGNALS(new BlockReceiverLightSignals("LightRece"), ItemBlockLightSignalsReceiver.class),
    CONT_REDSTONE_LIGHT_SIGNALS(new BlockRedControllerLightSignals("LightRedCont"), ItemBlockLightSignalsRedController.class),
    RECE_REDSTONE_SIGNALS(new BlockRedReceiverSignals("RedRece"), ItemBlockRedReceiverSignals.class),
    RECE_UNIVERSAL(new BlockUniversalReceiver("UniversalRece"), ItemBlockUniversalReceiver.class),
    CONT_UNIVERSAL(new BlockUniversalController("UniversalCont"), ItemBlockUniversalController.class),

    //GSAR Stuff --------------------------------------------

    SIGNAL_HP3(new BlockGSARLightSignalHPx3("SignalHPx3"), ItemBlockGSARSignalHPx3.class),
    SIGNAL_HP5(new BlockGSARLightSignalHPx5("SignalHPx5"), ItemBlockGSARSignalHPx5.class),
    SIGNAL_VR3(new BlockGSARLightSignalVRx3("SignalVRx5"), ItemBlockGSARSignalVRx3.class),
    SIGNAL_VR5(new BlockGSARLightSignalVRx5("SignalVRx3"), ItemBlockGSARSignalVRx5.class),
    SIGN_NE1(new BlockGSARSignalNE1("SignNE1"), ItemBlockGSARSignalNE1.class),
    SIGN_NE2(new BlockGSARSignalNE2("SignNE2"), ItemBlockGSARSignalNE2.class),
    SIGN_NE3_1(new BlockGSARSignalNE3_1("SignNE3_1"), ItemBlockGSARSignalNE3_1.class),
    SIGN_NE3_2(new BlockGSARSignalNE3_2("SignNE3_2"), ItemBlockGSARSignalNE3_2.class),
    SIGN_NE3_3(new BlockGSARSignalNE3_3("SignNE3_3"), ItemBlockGSARSignalNE3_3.class),
    SIGN_NE4(new BlockGSARSignalNE4("SignNE4"), ItemBlockGSARSignalNE4.class),
    SIGN_NE5(new BlockGSARSignalNE5("SignNE5"), ItemBlockGSARSignalNE5.class),
    SIGN_NE6(new BlockGSARSignalNE6("SignNE6"), ItemBlockGSARSignalNE6.class),
    SIGN_NE7(new BlockGSARSignalNE7("SignNE7"), ItemBlockGSARSignalNE7.class),
    SIGN_NE12(new BlockGSARSignalNE12("SignNE12"), ItemBlockGSARSignalNE12.class),
    SIGNAL_NE13(new BlockGSARSignalNE13("SignalNE13"), ItemBlockGSARSignalNE13.class),

    SIGN_LF1(new BlockGSARSignalLF1("SignLF1"), ItemBlockGSARSignalLF1.class),
    SIGN_LF2(new BlockGSARSignalLF2("SignLF2"), ItemBlockGSARSignalLF2.class),
    SIGN_LF3(new BlockGSARSignalLF3("SignLF3"), ItemBlockGSARSignalLF3.class),
    SIGN_LF6(new BlockGSARSignalLF6("SignLF6"), ItemBlockGSARSignalLF6.class),
    SIGN_LF7(new BlockGSARSignalLF7("SignLF7"), ItemBlockGSARSignalLF7.class),

    SIGNAL_SHL(new BlockGSARSignalSHL("SignalSHL"), ItemBlockGSARSignalSHL.class),
    SIGNAL_SHL_SINGLE(new BlockGSARSignalSHLSingle("SignalSHLSingle"), ItemBlockGSARSignalSHLSingle.class),
    SIGNAL_SHF(new BlockGSARSignalSHF("SignalSHF"), ItemBlockGSARSignalSHF.class),
    SIGNAL_SHF_SINGLE(new BlockGSARSignalSHFSingle("SignalSHFSingle"), ItemBlockGSARSignalSHFSingle.class),
    SIGNAL_SH2(new BlockGSARSignalSH2("SignalSH2"), ItemBlockGSARSignalSH2.class),

    SIGN_HECTO(new BlockGSARHectometer("SignHectometer"), ItemBlockGSARHectometer.class),
    SIGNAL_LEVER(new BlockGSARSignalLever("SignalLever"), ItemBlockGSARSignalLever.class),
    STATION_SIGN(new BlockGSARStationSign("StationSign", false), ItemBlockGSARStationSign.class),

    //I Hate this more than anything else in this mod, but I am so done with the GSAR bullshit code that I just want to get it over with.
    //Now I have to register this block twice, because GSAR spaghetti code is so bad that it doesn't work otherwise. I am so fucking done with this, I just want to get it over with and move on to better things.
    GSAR_STATION_SIGN_WALL(new BlockGSARStationSign("StationSignWall", true), ItemBlockGSARStationSign.class),

    SIGN_RA10A(new BlockGSARSignalRA10a("SignRA10a"), ItemBlockGSARSignalRA10a.class),
    SIGN_RA10B(new BlockGSARSignalRA10b("SignRA10b"), ItemBlockGSARSignalRA10b.class),
    SIGN_RA11A(new BlockGSARSignalRA11a("SignRA11a"), ItemBlockGSARSignalRA11a.class),
    SIGN_RA11B(new BlockGSARSignalRA11b("SignRA11b"), ItemBlockGSARSignalRA11b.class),
    SIGN_RA11W(new BlockGSARSignalRA11W("SignRA11W"), ItemBlockGSARSignalRA11W.class),
    SIGN_RA11Y(new BlockGSARSignalRA11Y("SignRA11Y"), ItemBlockGSARSignalRA11Y.class),
    SIGNAL_RA11WL(new BlockGSARSignalRA11WL("SignalRA11WL"), ItemBlockGSARSignalRA11WL.class),
    SIGNAL_RA11YL(new BlockGSARSignalRA11YL("SignalRA11YL"), ItemBlockGSARSignalRA11YL.class),
    STATIV_RA11(new BlockGSARStativRA11("StativRA11"), ItemBlockGSARStativRA11.class),

    SIGN_BU2(new BlockGSARSignalBU2("SignBU2"), ItemBlockGSARSignalBU2.class),
    SIGN_BU3(new BlockGSARSignalBU3("SignBU3"), ItemBlockGSARSignalBU3.class),
    SIGN_BU4(new BlockGSARSignalBU4("SignBU4"), ItemBlockGSARSignalBU4.class),
    SIGN_BU4Z(new BlockGSARSignalBU4Z("SignBU4Z"), ItemBlockGSARSignalBU4Z.class),
    SIGN_BU5(new BlockGSARSignalBU5("SignBU5"), ItemBlockGSARSignalBU5.class),
    SIGN_BU5Z(new BlockGSARSignalBU5Z("SignBU5Z"), ItemBlockGSARSignalBU5Z.class),
    SIGN_PF2(new BlockGSARSignalPF2("SignPF2"), ItemBlockGSARSignalPF2.class),
    SIGN_PF2Z(new BlockGSARSignalPF2Z("SignPF2Z"), ItemBlockGSARSignalPF2Z.class),
    SIGN_CROSS(new BlockGSARRailCross("RailCross"), ItemBlockGSARRailCross.class),
    SIGN_CROSS_FLASH(new BlockGSARRailCrossFlash("RailCrossFlash"), ItemBlockGSARRailCrossFlash.class),
    SIGN_CROSS_FENCE(new BlockGSARRailCrossFence("RailCrossFence"), ItemBlockGSARRailCrossFence.class),
    STATIV_CROSS(new BlockGSARRailCrossStativ("RailCrossStativ"), ItemBlockGSARRailCrossStativ.class),
    BARRIER_STOP(new BlockGSARBarrierStop("BarrierStop"), ItemBlockGSARBarrierStop.class),
    BARRIER_FULL4L(new BlockGSARBarrierFull4L("BarrierFull4L"), ItemBlockGSARBarrierFull4L.class),
    BARRIER_FULL4R(new BlockGSARBarrierFull4R("BarrierFull4R"), ItemBlockGSARBarrierFull4R.class),
    BARRIER_FULL10L(new BlockGSARBarrierFull10L("BarrierFull10L"), ItemBlockGSARBarrierFull10L.class),
    BARRIER_FULL10R(new BlockGSARBarrierFull10R("BarrierFull10R"), ItemBlockGSARBarrierFull10R.class),
    CROSS_LIGHT(new BlockGSARRailCrossLight("CrossLight"), ItemBlockGSARRailCrossLight.class),
    CROSS_LIGHT_S(new BlockGSARRailCrossLightS("CrossLightS"), ItemBlockGSARRailCrossLightS.class),
    CROSS_MODERN(new BlockGSARRailCrossModern("CrossModern"), ItemBlockGSARRailCrossModern.class),
    SIGNAL_BU0x3(new BlockGSARLightSignalBU0x3("SignalBU0x3"), ItemBlockGSARSignalBU0x3.class),
    SIGNAL_BU0x5(new BlockGSARLightSignalBU0x5("SignalBU0x5"), ItemBlockGSARSignalBU0x5.class),
    BARRIER_HALF_L(new BlockGSARHalfBarrierL("HalfBarrierL"), ItemBlockGSARHalfBarrierL.class),
    BARRIER_HALF_R(new BlockGSARHalfBarrierR("HalfBarrierR"), ItemBlockGSARHalfBarrierR.class),
    BARRIER_MODERN_L(new BlockGSARModernBarrierL("ModernBarrierL"), ItemBlockGSARModernBarrierL.class),
    BARRIER_MODERN_R(new BlockGSARModernBarrierR("ModernBarrierR"), ItemBlockGSARModernBarrierR.class),

    STATIV_LIGHT_HP(new BlockGSARStativLightSignals("StativLightHP"), ItemBlockGSARStativHP.class),
    STATIV_LIGHT_VR(new BlockGSARStativLightSignalsVR("StativLightVR"), ItemBlockGSARStativVR.class),
    SEMI_SIGNAL_1W_HPx3(new BlockGSARSemiSignal1Wingsx3("SemiSignal1WHPx3"), ItemBlockGSARSemiSignal1Wingsx3.class),
    SEMI_SIGNAL_1W_HPx5(new BlockGSARSemiSignal1Wingsx5("SemiSignal1WHPx5"), ItemBlockGSARSemiSignal1Wingsx5.class),
    SEMI_SIGNAL_2W_HPx3(new BlockGSARSemiSignal2Wingsx3("SemiSignal2WHPx3"), ItemBlockGSARSemiSignal2Wingsx3.class),
    SEMI_SIGNAL_2W_HPx5(new BlockGSARSemiSignal2Wingsx5("SemiSignal2WHPx5"), ItemBlockGSARSemiSignal2Wingsx5.class),
    SEMI_SIGNAL_VRx3(new BlockGSARSemiSignalVRx3("SemiSignalVRx3"), ItemBlockGSARSemiSignalVRx3.class),
    STATIV_SEMI_HP(new BlockGSARStativSemiSignals("StativSemiHP"), ItemBlockGSARSemiStativHP.class),
    STATIV_SEMI_VR(new BlockGSARStativSemiSignalsVR("StativSemiVR"), ItemBlockGSARSemiStativVR.class),
    SWITCH_MANUAL(new BlockGSARSwitchManual("SwitchManualGSAR"), ItemBlockGSARSwitchManual.class),
    SWITCH_ELECTRIC(new BlockGSARSwitchMechanic("SwitchElectricGSAR"), ItemBlockGSARSwitchElectric.class),

    METAL_ROD(new BlockMetalRod("MetalRod"), ItemBlockMetalRod.class),
    BRIDGE_BEAMS(new BlockBridgeBeams("BridgeBeams"), ItemBlockBridgeBeams.class),
    BRIDGE_BEAMS_CORNER(new BlockBridgeBeamsCorner("BridgeBeamsCorner"), ItemBlockBridgeBeamsCorner.class),
    BRIDGE_BEAMS_CROSS(new BlockBridgeBeamsCross("BridgeBeamsCross"), ItemBlockBridgeBeamsCross.class),
    BRIDGE_BEAMS_TRIPLE(new BlockBridgeBeamsTriple("BlockBridgeBeamsTriple"), ItemBlockBridgeBeamsTriple.class),
    BRIDGE_GROUND(new BlockBridgeGround("BlockBridgeGround"), ItemBlockBridgeGround.class),
    BRIDGE_GROUND_BEAMS(new BlockBridgeGroundBeams("BlockBridgeGroundBeams"), ItemBlockBridgeGroundBeams.class),
    LADDER(new BlockLadder("BlockLadder"), ItemBlockLadder.class),
    RAILING(new BlockRailing("BlockRailing"), ItemBlockRailing.class),
    RAILING_2(new BlockRailing2("BlockRailing2"), ItemBlockRailing2.class),
    RAILING_RODS(new BlockRailingRods("BlockRailingRods"), ItemBlockRailingRods.class),

    ;

    public final Block block;
    public final Class<? extends ItemBlock> itemBlockClass;

    SCItemBlocks(Block block, Class<? extends ItemBlock> itemBlockClass) {
        this.block = block;
        this.itemBlockClass = itemBlockClass;
    }

    // Kept in a separate nested class so the server, which touches SCItemBlocks directly, never has to resolve IItemRenderer.
    public static final class Renderers {
        public static final java.util.EnumMap<SCItemBlocks, Supplier<IItemRenderer>> MAP = new java.util.EnumMap<>(SCItemBlocks.class);

        static {
            MAP.put(SPEED_SIGN, () -> new SpeedSignItemRenderer());
            MAP.put(PR_UPOZ_SIGN, () -> new PrUpozItemRenderer());
            MAP.put(AZD_1_LIGHT, () -> new LightSignalItemRenderer(new ModelAZD1Light()));
            MAP.put(AZD_2_LIGHTS, () -> new LightSignalItemRenderer(new ModelAZD2Lights()));
            MAP.put(AZD_2_LIGHTS_T, () -> new LightSignalItemRenderer(new ModelAZD2LightsT()));
            MAP.put(AZD_3_LIGHTS, () -> new LightSignalItemRenderer(new ModelAZD3Lights()));
            MAP.put(AZD_3_LIGHTS_T, () -> new LightSignalItemRenderer(new ModelAZD3LightsT()));
            MAP.put(AZD_4_LIGHTS, () -> new LightSignalItemRenderer(new ModelAZD4Lights()));
            MAP.put(AZD_4_LIGHTS_T, () -> new LightSignalItemRenderer(new ModelAZD4LightsT()));
            MAP.put(AZD_5_LIGHTS, () -> new LightSignalItemRenderer(new ModelAZD5Lights()));
            MAP.put(AZD_5_LIGHTS_T, () -> new LightSignalItemRenderer(new ModelAZD5LightsT()));
            MAP.put(AZD_6_LIGHTS, () -> new LightSignalItemRenderer(new ModelAZD6Lights()));
            MAP.put(AZD_AB3, () -> new LightSignalItemRenderer(new ModelAZDAB3()));
            MAP.put(AZD_AB4, () -> new LightSignalItemRenderer(new ModelAZDAB4()));
            MAP.put(AZD_DIST, () -> new LightSignalItemRenderer(new ModelAZDPr()));
            MAP.put(AZD_RE_DIST, () -> new LightSignalItemRenderer(new ModelAZDOPr()));
            MAP.put(AZD_SHUNT, () -> new LightSignalItemRenderer(new ModelAZDPosun()));
            MAP.put(AZD_SHUNT_T, () -> new LightSignalItemRenderer(new ModelAZDPosunT()));
            MAP.put(AZD_INSERTED, () -> new LightSignalItemRenderer(new ModelAZDVloz()));
            MAP.put(AZD_71, () -> new CrossingItemRenderer(new ModelAZD71()));
            MAP.put(AZD_97, () -> new CrossingItemRenderer(new ModelAZD97()));
            MAP.put(AZD_71_HEAD, () -> new CrossingItemRenderer(new ModelAZD71Head()));
            MAP.put(AZD_97_HEAD, () -> new CrossingItemRenderer(new ModelAZD97Head()));
            MAP.put(VUD, () -> new CrossingItemRenderer(new ModelVUD()));
            MAP.put(AZD_99, () -> new AZD99ItemRenderer(new ModelAZD99()));
            MAP.put(SSSR, () -> new CrossingItemRenderer(new ModelCrossSSSR()));
            MAP.put(SSSR_SINGLE, () -> new CrossingItemRenderer(new ModelCrossSSSRSingle()));
            MAP.put(SSSR_HEAD, () -> new CrossingItemRenderer(new ModelCrossSSSRHead()));
            MAP.put(SSSR_SINGLE_HEAD, () -> new CrossingItemRenderer(new ModelCrossSSSRSingleHead()));
            MAP.put(SSSR_5_LIGHTS, () -> new LightSignalItemRenderer(new ModelSSSR5Lights()));
            MAP.put(SSSR_5_LIGHTS_T, () -> new LightSignalItemRenderer(new ModelSSSR5LightsT()));
            MAP.put(SSSR_4_LIGHTS, () -> new LightSignalItemRenderer(new ModelSSSR4Lights()));
            MAP.put(SSSR_4_LIGHTS_T, () -> new LightSignalItemRenderer(new ModelSSSR4LightsT()));
            MAP.put(SSSR_3_LIGHTS, () -> new LightSignalItemRenderer(new ModelSSSR3Lights()));
            MAP.put(SSSR_3_LIGHTS_T, () -> new LightSignalItemRenderer(new ModelSSSR3LightsT()));
            MAP.put(SSSR_3_LIGHTS_MECH_T, () -> new LightSignalItemRenderer(new ModelSSSR3LightsMechT()));
            MAP.put(SSSR_2_LIGHTS, () -> new LightSignalItemRenderer(new ModelSSSR2Lights()));
            MAP.put(SSSR_2_LIGHTS_T, () -> new LightSignalItemRenderer(new ModelSSSR2LightsT()));
            MAP.put(SSSR_2_LIGHTS_MECH_T, () -> new LightSignalItemRenderer(new ModelSSSR2LightsMechT()));
            MAP.put(SSSR_SHUNT, () -> new LightSignalItemRenderer(new ModelSSSRPosun()));
            MAP.put(SSSR_SHUNT_T, () -> new LightSignalItemRenderer(new ModelSSSRPosunT()));
            MAP.put(SSSR_DISTANT, () -> new LightSignalItemRenderer(new ModelSSSRPr()));
            MAP.put(SSSR_DISTANT_REPEATING, () -> new LightSignalItemRenderer(new ModelSSSROPr()));
            MAP.put(SSSR_INSERTED, () -> new LightSignalItemRenderer(new ModelSSSRVloz()));
            MAP.put(SSSR_AB3, () -> new LightSignalItemRenderer(new ModelSSSRAB3()));
            MAP.put(SSSR_AB4, () -> new LightSignalItemRenderer(new ModelSSSRAB4()));
            MAP.put(CONT_RECE_BASE, () -> new ControllerItemRenderer(new TileContReceBase()));
            MAP.put(CONT_CROSSINGS, () -> new ControllerItemRenderer(new TileCrossingController()));
            MAP.put(RECE_CROSSINGS, () -> new ControllerItemRenderer(new TileCrossingReceiver()));
            MAP.put(RECE_LIGHT_SIGNALS, () -> new ControllerItemRenderer(new TileReceiverLightSignals()));
            MAP.put(CONT_REDSTONE_LIGHT_SIGNALS, () -> new ControllerItemRenderer(new TileRedControllerLightSignals()));
            MAP.put(RECE_REDSTONE_SIGNALS, () -> new ControllerItemRenderer(new TileRedReceiverSignals()));
            MAP.put(RECE_UNIVERSAL, () -> new ControllerItemRenderer(new TileReceiverUniversal()));
            MAP.put(CONT_UNIVERSAL, () -> new ControllerItemRenderer(new TileControllerUniversal()));
            MAP.put(SIGNAL_HP3, () -> new ItemLightRendererGSAR(new ModelGSARLightSignals(), new TileGSARLightSignalHPx3()));
            MAP.put(SIGNAL_HP5, () -> new ItemLightRendererGSAR(new ModelGSARLightSignals(), new TileGSARLightSignalHPx5()));
            MAP.put(SIGNAL_VR3, () -> new ItemLightRendererGSAR(new ModelGSARLightSignals(), new TileGSARLightSignalVRx3()));
            MAP.put(SIGNAL_VR5, () -> new ItemLightRendererGSAR(new ModelGSARLightSignals(), new TileGSARLightSignalVRx5()));
            MAP.put(SIGN_NE1, () -> new GenericItemRenderer(new TileGSARSignalNE1()));
            MAP.put(SIGN_NE2, () -> new GenericItemRenderer(new TileGSARSignalNE2()));
            MAP.put(SIGN_NE3_1, () -> new GenericItemRenderer(new TileGSARSignalNE3_1()));
            MAP.put(SIGN_NE3_2, () -> new GenericItemRenderer(new TileGSARSignalNE3_2()));
            MAP.put(SIGN_NE3_3, () -> new GenericItemRenderer(new TileGSARSignalNE3_3()));
            MAP.put(SIGN_NE4, () -> new GenericItemRenderer(new TileGSARSignalNE4()));
            MAP.put(SIGN_NE5, () -> new GenericItemRenderer(new TileGSARSignalNE5()));
            MAP.put(SIGN_NE6, () -> new GenericItemRenderer(new TileGSARSignalNE6()));
            MAP.put(SIGN_NE7, () -> new GenericItemRenderer(new TileGSARSignalNE7()));
            MAP.put(SIGN_NE12, () -> new GenericItemRenderer(new TileGSARSignalNE12()));
            MAP.put(SIGNAL_NE13, () -> new ItemLightRendererGSAR(new ModelGSARSignalNE13(), new TileGSARSignalNE13()));
            MAP.put(SIGN_LF1, () -> new GenericItemRenderer(new TileGSARSignalLF1()));
            MAP.put(SIGN_LF2, () -> new GenericItemRenderer(new TileGSARSignalLF2()));
            MAP.put(SIGN_LF3, () -> new GenericItemRenderer(new TileGSARSignalLF3()));
            MAP.put(SIGN_LF6, () -> new GenericItemRenderer(new TileGSARSignalLF6()));
            MAP.put(SIGN_LF7, () -> new GenericItemRenderer(new TileGSARSignalLF7()));
            MAP.put(SIGNAL_SHL, () -> new ItemLightRendererGSAR(new ModelGSARSignalSHL(), new TileGSARLightSignalSHL()));
            MAP.put(SIGNAL_SHL_SINGLE, () -> new ItemLightRendererGSAR(new ModelGSARSignalSHL(), new TileGSARLightSignalSHLSingle()));
            MAP.put(SIGNAL_SHF, () -> new ItemSemiSignalsRendererGSAR(new ModelGSARSignalSHF(), new TileGSARSemiSignalSHF()));
            MAP.put(SIGNAL_SHF_SINGLE, () -> new ItemSemiSignalsRendererGSAR(new ModelGSARSignalSHF(), new TileGSARSemiSignalSHFSingle()));
            MAP.put(SIGNAL_SH2, () -> new ItemSignalSH2RendererGSAR(new ModelGSARSignalSH2(), new TileGSARSignSignalSH2()));
            MAP.put(SIGN_HECTO, () -> new GenericItemRenderer(new TileGSARHectometer()));
            MAP.put(SIGNAL_LEVER, () -> new SignalLeverItemRendererGSAR(new ModelSignalsLeverGSAR()));
            MAP.put(STATION_SIGN, () -> new StationSignItemRendererGSAR(new ModelStationSignGSAR()));
            MAP.put(GSAR_STATION_SIGN_WALL, () -> new StationSignItemRendererGSAR(new ModelStationSignGSAR()));
            MAP.put(SIGN_RA10A, () -> new GenericItemRenderer(new TileGSARSignalRA10a()));
            MAP.put(SIGN_RA10B, () -> new GenericItemRenderer(new TileGSARSignalRA10b()));
            MAP.put(SIGN_RA11A, () -> new GenericItemRenderer(new TileGSARSignalRA11a()));
            MAP.put(SIGN_RA11B, () -> new GenericItemRenderer(new TileGSARSignalRA11b()));
            MAP.put(SIGN_RA11W, () -> new ItemLightRendererGSAR(new ModelGSARSignalRA11(), new TileGSARSignalRA11W()));
            MAP.put(SIGN_RA11Y, () -> new ItemLightRendererGSAR(new ModelGSARSignalRA11(), new TileGSARSignalRA11Y()));
            MAP.put(SIGNAL_RA11WL, () -> new ItemLightRendererGSAR(new ModelGSARSignalRA11(), new TileGSARSignalRA11WL()));
            MAP.put(SIGNAL_RA11YL, () -> new ItemLightRendererGSAR(new ModelGSARSignalRA11(), new TileGSARSignalRA11YL()));
            MAP.put(STATIV_RA11, () -> new GenericItemRenderer(new TileGSARStativRA11()));
            MAP.put(SIGN_BU2, () -> new GenericItemRenderer(new TileGSARSignalBU2()));
            MAP.put(SIGN_BU3, () -> new GenericItemRenderer(new TileGSARSignalBU3()));
            MAP.put(SIGN_BU4, () -> new GenericItemRenderer(new TileGSARSignalBU4()));
            MAP.put(SIGN_BU4Z, () -> new GenericItemRenderer(new TileGSARSignalBU4Z()));
            MAP.put(SIGN_BU5, () -> new GenericItemRenderer(new TileGSARSignalBU5()));
            MAP.put(SIGN_BU5Z, () -> new GenericItemRenderer(new TileGSARSignalBU5Z()));
            MAP.put(SIGN_PF2, () -> new GenericItemRenderer(new TileGSARSignalPF2()));
            MAP.put(SIGN_PF2Z, () -> new GenericItemRenderer(new TileGSARSignalPF2Z()));
            MAP.put(SIGN_CROSS, () -> new GenericItemRenderer(new TileGSARRailCross()));
            MAP.put(SIGN_CROSS_FLASH, () -> new GenericItemRenderer(new TileGSARRailCrossFlash()));
            MAP.put(SIGN_CROSS_FENCE, () -> new GenericItemRenderer(new TileGSARRailCrossFence()));
            MAP.put(STATIV_CROSS, () -> new GenericItemRenderer(new TileGSARRailCrossStativ()));
            MAP.put(BARRIER_STOP, () -> new GenericItemRenderer(new TileGSARBarrierStop()));
            MAP.put(BARRIER_FULL4L, () -> new ItemFullBarrierRendererGSAR(new ModelGSARFullBarriers(), new TileGSARFullBarriersx4L()));
            MAP.put(BARRIER_FULL4R, () -> new ItemFullBarrierRendererGSAR(new ModelGSARFullBarriers(), new TileGSARFullBarriersx4R()));
            MAP.put(BARRIER_FULL10L, () -> new ItemFullBarrierRendererGSAR(new ModelGSARFullBarriers(), new TileGSARFullBarriersx10L()));
            MAP.put(BARRIER_FULL10R, () -> new ItemFullBarrierRendererGSAR(new ModelGSARFullBarriers(), new TileGSARFullBarriersx10R()));
            MAP.put(CROSS_LIGHT, () -> new ItemRailCrossRendererGSAR(new ModelGSARRailCross(), new TileGSARRailCrossLight()));
            MAP.put(CROSS_LIGHT_S, () -> new ItemRailCrossRendererGSAR(new ModelGSARRailCross(), new TileGSARRailCrossLightS()));
            MAP.put(CROSS_MODERN, () -> new ItemRailCrossRendererGSAR(new ModelGSARRailCross(), new TileGSARRailCrossModern()));
            MAP.put(SIGNAL_BU0x3, () -> new ItemLightRendererGSAR(new ModelGSARLightSignalsBU(), new TileGSARLightSignalBU0x3()));
            MAP.put(SIGNAL_BU0x5, () -> new ItemLightRendererGSAR(new ModelGSARLightSignalsBU(), new TileGSARLightSignalBU0x5()));
            MAP.put(BARRIER_HALF_L, () -> new ItemBarriersRendererGSAR(new ModelGSARBarriers(), new TileGSARHalfBarrierL()));
            MAP.put(BARRIER_HALF_R, () -> new ItemBarriersRendererGSAR(new ModelGSARBarriers(), new TileGSARHalfBarrierR()));
            MAP.put(BARRIER_MODERN_L, () -> new ItemBarriersRendererGSAR(new ModelGSARBarriers(), new TileGSARModernBarrierL()));
            MAP.put(BARRIER_MODERN_R, () -> new ItemBarriersRendererGSAR(new ModelGSARBarriers(), new TileGSARModernBarrierR()));
            MAP.put(STATIV_LIGHT_HP, () -> new ItemStativSignalsRendererGSAR(new ModelGSARLightSignals(), new TileGSARStativLightSignals()));
            MAP.put(STATIV_LIGHT_VR, () -> new ItemStativSignalsRendererGSAR(new ModelGSARLightSignals(), new TileGSARStativLightSignalsVR()));
            MAP.put(SEMI_SIGNAL_1W_HPx3, () -> new ItemSemiSignalsRendererGSAR(new ModelGSARSemiSignals(), new TileGSARSemiSignal1Wingsx3()));
            MAP.put(SEMI_SIGNAL_1W_HPx5, () -> new ItemSemiSignalsRendererGSAR(new ModelGSARSemiSignals(), new TileGSARSemiSignal1Wingsx5()));
            MAP.put(SEMI_SIGNAL_2W_HPx3, () -> new ItemSemiSignalsRendererGSAR(new ModelGSARSemiSignals(), new TileGSARSemiSignal2Wingsx3()));
            MAP.put(SEMI_SIGNAL_2W_HPx5, () -> new ItemSemiSignalsRendererGSAR(new ModelGSARSemiSignals(), new TileGSARSemiSignal2Wingsx5()));
            MAP.put(SEMI_SIGNAL_VRx3, () -> new ItemSemiSignalsRendererGSAR(new ModelGSARSemiSignalsVR(), new TileGSARSemiSignalVRx3()));
            MAP.put(STATIV_SEMI_HP, () -> new ItemStativSignalsRendererGSAR(new ModelGSARStativSemiSignals(), new TileGSARStativSemiSignals()));
            MAP.put(STATIV_SEMI_VR, () -> new ItemStativSignalsRendererGSAR(new ModelGSARStativSemiSignals(), new TileGSARStativSemiSignalsVR()));
            MAP.put(SWITCH_MANUAL, () -> new ItemSwitchRendererGSAR(new ModelSwitchesGSAR(), new TileSwitchManualGSAR()));
            MAP.put(SWITCH_ELECTRIC, () -> new ItemSwitchRendererGSAR(new ModelSwitchesGSAR(), new TileSwitchElectricGSAR()));
            MAP.put(METAL_ROD, () -> new GenericItemRenderer(new TileMetalRod()));
            MAP.put(BRIDGE_BEAMS, () -> new GenericItemRenderer(new TileBridgeBeams()));
            MAP.put(BRIDGE_BEAMS_CORNER, () -> new GenericItemRenderer(new TileBridgeBeamsCorner()));
            MAP.put(BRIDGE_BEAMS_CROSS, () -> new GenericItemRenderer(new TileBridgeBeamsCross()));
            MAP.put(BRIDGE_BEAMS_TRIPLE, () -> new GenericItemRenderer(new TileBridgeBeamsTriple()));
            MAP.put(BRIDGE_GROUND, () -> new GenericItemRenderer(new TileBridgeGround()));
            MAP.put(BRIDGE_GROUND_BEAMS, () -> new GenericItemRenderer(new TileBridgeGroundBeams()));
            MAP.put(LADDER, () -> new GenericItemRenderer(new TileLadder()));
            MAP.put(RAILING, () -> new GenericItemRenderer(new TileRailing()));
            MAP.put(RAILING_2, () -> new GenericItemRenderer(new TileRailing2()));
            MAP.put(RAILING_RODS, () -> new GenericItemRenderer(new TileRailingRods()));
        }

        private Renderers() {}
    }
}