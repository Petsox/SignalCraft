package signalcraft.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.EnumHelper;
import signalcraft.entities.controllers.signals.lightSignals.TileRedControllerLightSignals;
import signalcraft.entities.controllers.signals.TileRedReceiverSignals;
import signalcraft.entities.gsar.signalsBU.TileGSARModernBarrierL;
import signalcraft.entities.gsar.signalsBU.TileGSARModernBarrierR;
import signalcraft.entities.gsar.signalsHP.TileGSARStativLightSignals;
import signalcraft.entities.gsar.signalsHP.TileGSARStativSemiSignals;
import signalcraft.entities.gsar.signalsLF.TileGSARSignalLF1;
import signalcraft.entities.gsar.signalsLF.TileGSARSignalLF6;
import signalcraft.entities.gsar.signalsLF.TileGSARSignalLF7;
import signalcraft.entities.gsar.signalsSH.TileGSARSignSignalSH2;
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
import signalcraft.entities.signals.signSignals.TilePrUpoz;
import signalcraft.entities.signals.lightSignals.azd70.*;
import signalcraft.entities.signals.lightSignals.sssr.*;
import signalcraft.entities.signals.signSignals.TileSpeedSign;
import signalcraft.gui.controllers.GuiRedstoneController;
import signalcraft.gui.controllers.GuiRedstoneReceiver;
import signalcraft.gui.gsar.*;
import signalcraft.gui.levelCrossings.azd.*;
import signalcraft.gui.levelCrossings.sssr.GuiSSSR;
import signalcraft.gui.levelCrossings.sssr.GuiSSSRHead;
import signalcraft.gui.levelCrossings.sssr.GuiSSSRSingle;
import signalcraft.gui.levelCrossings.sssr.GuiSSSRSingleHead;
import signalcraft.gui.levelCrossings.vud.GuiVUD;
import signalcraft.gui.signals.lightSignals.azd70.*;
import signalcraft.gui.signals.lightSignals.sssr.*;
import signalcraft.gui.signals.signSignals.GuiPrUpoz;
import signalcraft.gui.signals.signSignals.GuiSpeedSign;
import signalcraft.signalUtils.Consts;

import java.util.function.Function;
import java.util.function.Supplier;

public enum SCGuis {

    SPEED_SIGN_GUI(Consts.GuiIDs.SPEED_SIGN, TileSpeedSign::new, (tile) -> new GuiSpeedSign((TileSpeedSign) tile)),
    PR_UPOZ_SIGN_GUI(Consts.GuiIDs.PR_UPOZ, TilePrUpoz::new, (tile) -> new GuiPrUpoz((TilePrUpoz) tile)),
    AZD71_GUI(Consts.GuiIDs.AZD71, TileAZD71::new, (tile) -> new GuiAZD71((TileAZD71) tile)),
    AZD97_GUI(Consts.GuiIDs.AZD97, TileAZD97::new, (tile) -> new GuiAZD97((TileAZD97) tile)),
    AZD71_HEAD_GUI(Consts.GuiIDs.AZD71_HEAD, TileAZD71Head::new, (tile) -> new GuiAZD71Head((TileAZD71Head) tile)),
    AZD97_HEAD_GUI(Consts.GuiIDs.AZD97_HEAD, TileAZD97Head::new, (tile) -> new GuiAZD97Head((TileAZD97Head) tile)),
    VUD_GUI(Consts.GuiIDs.VUD, TileVUD::new, (tile) -> new GuiVUD((TileVUD) tile)),
    AZD99_GUI(Consts.GuiIDs.AZD99, TileAZD99::new, (tile) -> new GuiAZD99((TileAZD99) tile)),
    SSSR_GUI(Consts.GuiIDs.SSSR, TileSSSR::new, (tile) -> new GuiSSSR((TileSSSR) tile)),
    SSSR_SINGLE_GUI(Consts.GuiIDs.SSSR_SINGLE, TileSSSRSingle::new, (tile) -> new GuiSSSRSingle((TileSSSRSingle) tile)),
    SSSR_HEAD_GUI(Consts.GuiIDs.SSSR_HEAD, TileSSSRHead::new, (tile) -> new GuiSSSRHead((TileSSSRHead) tile)),
    SSSR_SINGLE_HEAD_GUI(Consts.GuiIDs.SSSR_SINGLE_HEAD, TileSSSRSingleHead::new, (tile) -> new GuiSSSRSingleHead((TileSSSRSingleHead) tile)),
    AZD_2_LIGHTS_GUI(Consts.GuiIDs.AZD_2_LIGHTS, TileAZD2Lights::new, (tile) -> new GuiAZD2Lights((TileAZD2Lights) tile)),
    AZD_2_LIGHTS_T_GUI(Consts.GuiIDs.AZD_2_LIGHTS_T, TileAZD2LightsT::new, (tile) -> new GuiAZD2LightsT((TileAZD2LightsT) tile)),
    AZD_3_LIGHTS_GUI(Consts.GuiIDs.AZD_3_LIGHTS, TileAZD3Lights::new, (tile) -> new GuiAZD3Lights((TileAZD3Lights) tile)),
    AZD_3_LIGHTS_T_GUI(Consts.GuiIDs.AZD_3_LIGHTS_T, TileAZD3LightsT::new, (tile) -> new GuiAZD3LightsT((TileAZD3LightsT) tile)),
    AZD_4_LIGHTS_GUI(Consts.GuiIDs.AZD_4_LIGHTS, TileAZD4Lights::new, (tile) -> new GuiAZD4Lights((TileAZD4Lights) tile)),
    AZD_5_LIGHTS_GUI(Consts.GuiIDs.AZD_5_LIGHTS, TileAZD5Lights::new, (tile) -> new GuiAZD5Lights((TileAZD5Lights) tile)),
    AZD_6_LIGHTS_GUI(Consts.GuiIDs.AZD_6_LIGHTS, TileAZD6Lights::new, (tile) -> new GuiAZD6Lights((TileAZD6Lights) tile)),
    AZD_AB3_GUI(Consts.GuiIDs.AZD_AB3, TileAZDAB3::new, (tile) -> new GuiAZDAB3((TileAZDAB3) tile)),
    AZD_AB4_GUI(Consts.GuiIDs.AZD_AB4, TileAZDAB4::new, (tile) -> new GuiAZDAB4((TileAZDAB4) tile)),
    AZD_DIST_GUI(Consts.GuiIDs.AZD_DIST, TileAZDPr::new, (tile) -> new GuiAZDPr((TileAZDPr) tile)),
    AZD_RE_DIST_GUI(Consts.GuiIDs.AZD_RE_DIST, TileAZDOPr::new, (tile) -> new GuiAZDOPr((TileAZDOPr) tile)),
    AZD_SHUNT_GUI(Consts.GuiIDs.AZD_SHUNT, TileAZDPosun::new, (tile) -> new GuiAZDPosun((TileAZDPosun) tile)),
    AZD_INSERTED_GUI(Consts.GuiIDs.AZD_INSERTED, TileAZDVloz::new, (tile) -> new GuiAZDVloz((TileAZDVloz) tile)),
    SSSR_5_LIGHTS_GUI(Consts.GuiIDs.SSSR_5_LIGHTS, TileSSSR5Lights::new, (tile) -> new GuiSSSR5Lights((TileSSSR5Lights) tile)),
    SSSR_4_LIGHTS_GUI(Consts.GuiIDs.SSSR_4_LIGHTS, TileSSSR4Lights::new, (tile) -> new GuiSSSR4Lights((TileSSSR4Lights) tile)),
    SSSR_3_LIGHTS_GUI(Consts.GuiIDs.SSSR_3_LIGHTS, TileSSSR3Lights::new, (tile) -> new GuiSSSR3Lights((TileSSSR3Lights) tile)),
    SSSR_2_LIGHTS_GUI(Consts.GuiIDs.SSSR_2_LIGHTS, TileSSSR2Lights::new, (tile) -> new GuiSSSR2Lights((TileSSSR2Lights) tile)),
    SSSR_SHUNT(Consts.GuiIDs.SSSR_SHUNT, TileSSSRPosun::new, (tile) -> new GuiSSSRPosun((TileSSSRPosun) tile)),
    SSSR_AB3(Consts.GuiIDs.SSSR_AB3, TileSSSRAB3::new, (tile) -> new GuiSSSRAB3((TileSSSRAB3) tile)),
    SSSR_AB4(Consts.GuiIDs.SSSR_AB4, TileSSSRAB4::new, (tile) -> new GuiSSSRAB4((TileSSSRAB4) tile)),
    SSSR_DISTANT(Consts.GuiIDs.SSSR_DISTANT, TileSSSRPr::new, (tile) -> new GuiSSSRPr((TileSSSRPr) tile)),
    SSSR_DISTANT_REPEATING(Consts.GuiIDs.SSSR_DISTANT_REPEATING, TileSSSROPr::new, (tile) -> new GuiSSSROPr((TileSSSROPr) tile)),
    SSSR_INSERTED(Consts.GuiIDs.SSSR_INSERTED, TileSSSRVloz::new, (tile) -> new GuiSSSRVloz((TileSSSRVloz) tile)),
    REDSTONE_CONTROLLER_GUI(Consts.GuiIDs.REDSTONE_CONTROLLER, TileRedControllerLightSignals::new, (tile) -> new GuiRedstoneController((TileRedControllerLightSignals) tile)),
    REDSTONE_RECEIVER_GUI(Consts.GuiIDs.REDSTONE_RECEIVER, TileRedReceiverSignals::new, (tile) -> new GuiRedstoneReceiver((TileRedReceiverSignals) tile)),
    //GSAR GUIs

    STATIV_HP_GUI(Consts.GuiIDs.STATIV_HP, TileGSARStativLightSignals::new, (tile) -> new GuiStativLightSignalsGSAR((TileGSARStativLightSignals) tile)),
    STATIV_SEMI_HP_GUI(Consts.GuiIDs.SEMI_STATIV_HP, TileGSARStativSemiSignals::new, (tile) -> new GuiStativSemiSignalsGSAR((TileGSARStativSemiSignals) tile)),
    SWITCH_MANUAL_GUI(Consts.GuiIDs.SWITCH_MANUAL, TileSwitchManualGSAR::new, (tile) -> new GuiSwitchGSAR((TileSwitchManualGSAR) tile)),
    SWITCH_ELECTRIC_GUI(Consts.GuiIDs.SWITCH_ELECTRIC, TileSwitchElectricGSAR::new, (tile) -> new GuiSwitchGSAR((TileSwitchElectricGSAR) tile)),
    MODERN_BARRIER_L_GUI(Consts.GuiIDs.MODERN_BARRIERS, TileGSARModernBarrierL::new, (tile) -> new GuiModernBarriersGSAR((TileGSARModernBarrierL) tile)),
    MODERN_BARRIER_R_GUI(Consts.GuiIDs.MODERN_BARRIERS, TileGSARModernBarrierR::new, (tile) -> new GuiModernBarriersGSAR((TileGSARModernBarrierR) tile)),
    SIGN_LF1_GUI(Consts.GuiIDs.SIGN_LF1, TileGSARSignalLF1::new, (tile) -> new GuiSignSignalsGSAR((TileGSARSignalLF1) tile)),
    SIGN_LF6_GUI(Consts.GuiIDs.SIGN_LF6, TileGSARSignalLF6::new, (tile) -> new GuiSignSignalsGSAR((TileGSARSignalLF6) tile)),
    SIGN_LF7_GUI(Consts.GuiIDs.SIGN_LF7, TileGSARSignalLF7::new, (tile) -> new GuiSignSignalsGSAR((TileGSARSignalLF7) tile)),
    SIGNAL_SH2_GUI(Consts.GuiIDs.SIGNAL_SH2, TileGSARSignSignalSH2::new, (tile) -> new GuiSignalSH2GSAR((TileGSARSignSignalSH2) tile)),
    SIGN_HECTOMETER_GUI(Consts.GuiIDs.HECTOMETER_SIGN, TileGSARHectometer::new, (tile) -> new GuiSignSignalsGSAR((TileGSARHectometer) tile)),
    SIGNAL_LEVER_GUI(Consts.GuiIDs.SIGNAL_LEVER, TileGSARSignalLever::new, (tile) -> new GuiSignalLeverGSAR((TileGSARSignalLever) tile)),
    STATION_SIGN_GUI(Consts.GuiIDs.STATION_SIGN, TileGSARStationSign::new, (tile) -> new GuiStationSignGSAR((TileGSARStationSign) tile)),

    ;

    public final Consts.GuiIDs id;
    public final Supplier<TileEntity> tileFactory;
    public final Function<TileEntity, GuiScreen> guiFactory;
    private Class<? extends TileEntity> tileClass;

    SCGuis(Consts.GuiIDs id, Supplier<TileEntity> tileFactory, Function<TileEntity, GuiScreen> guiFactory) {
        this.id = id;
        this.tileFactory = tileFactory;
        this.guiFactory = guiFactory;
    }

    private Class<? extends TileEntity> getTileClass() {
        if (this.tileClass == null) {
            this.tileClass = this.tileFactory.get().getClass();
        }
        return this.tileClass;
    }

    /**
     * Registers a new GUI for addon mods, appending a genuine new enum constant
     * at runtime via Forge's {@link EnumHelper}, e.g.
     * {@code SCGuis.register("MY_GUI", MyConsts.GuiIDs.MY_GUI, MyTile::new, (tile) -> new MyGui((MyTile) tile));}
     */
    public static synchronized SCGuis register(String name, Consts.GuiIDs id, Supplier<TileEntity> tileFactory, Function<TileEntity, GuiScreen> guiFactory) {
        for (SCGuis existing : values()) {
            if (existing.name().equals(name)) {
                throw new IllegalArgumentException("An SCGuis entry named '" + name + "' is already registered.");
            }
        }
        return EnumHelper.addEnum(SCGuis.class, name, new Class<?>[]{Consts.GuiIDs.class, Supplier.class, Function.class}, new Object[]{id, tileFactory, guiFactory});
    }

    public static GuiScreen handleGuiById(int guiId, TileEntity tile) {
        for (SCGuis scGui : values()) {
            if (scGui.id.getId() == guiId) {
                return scGui.guiFactory.apply(tile);
            }
        }
        return null;
    }
    public static TileEntity getTileByGuiId(int guiId) {
        for (SCGuis scGui : values()) {
            if (scGui.id.getId() == guiId) {
                return scGui.tileFactory.get(); // call Supplier.get()
            }
        }
        return null;
    }

    /**
     * Checks that the tile entity actually found at the packet's coordinates is the
     * type this GUI expects, before it gets cast in {@link #handleGuiById}. A stale
     * SPacketEditorOpen (or a foreign tile, e.g. RailCraft's TileHidden, occupying the
     * position) would otherwise cause a ClassCastException.
     */
    public static boolean isTileValidForGui(int guiId, TileEntity tile) {
        if (tile == null) {
            return false;
        }
        for (SCGuis scGui : values()) {
            if (scGui.id.getId() == guiId) {
                return scGui.getTileClass().isInstance(tile);
            }
        }
        return false;
    }
}