package signalcraft.models;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.SignalCraft;

public enum ModelRegistry {
    SPEED_SIGN(SignalCraft.MOD_ID + ":models/signs/rychlostnik.obj"),

    AZD62_2LIGHT(SignalCraft.MOD_ID + ":models/azd65/65_2svet.obj"),
    // AZD_1LIGHT..AZD_6LIGHT (azd_1svet.obj..azd_6svet.obj) were replaced by the
    // shared PartLibraryRegistry.AZD70 parts library -- see tools/objdedupe.
    AZD_2LIGHT_T(SignalCraft.MOD_ID + ":models/azd70/azd_2svetT.obj"),
    AZD_3LIGHT_T(SignalCraft.MOD_ID + ":models/azd70/azd_3svetT.obj"),
    AZD_4LIGHT_T(SignalCraft.MOD_ID + ":models/azd70/azd_4svetT.obj"),
    AZD_5LIGHT_T(SignalCraft.MOD_ID + ":models/azd70/azd_5svetT.obj"),
    AZD_AB3(SignalCraft.MOD_ID + ":models/azd70/azd_3ab.obj"),
    AZD_AB4(SignalCraft.MOD_ID + ":models/azd70/azd_4ab.obj"),
    AZD_OPR(SignalCraft.MOD_ID + ":models/azd70/azd_opr3.obj"),
    AZD_PR(SignalCraft.MOD_ID + ":models/azd70/azd_pr2.obj"),
    AZD_SHUNT(SignalCraft.MOD_ID + ":models/azd70/azd_se2.obj"),
    AZD_SHUNT_T(SignalCraft.MOD_ID + ":models/azd70/azd_2svetTse.obj"),
    AZD_INSERTED(SignalCraft.MOD_ID + ":models/azd70/azd_vloz.obj"),
    AZD_71(SignalCraft.MOD_ID + ":models/azd/azd71_vyst.obj"),
    AZD_97(SignalCraft.MOD_ID + ":models/azd/azd97_vyst.obj"),
    AZD_71_HEAD(SignalCraft.MOD_ID + ":models/azd/azd71_vyst_zav.obj"),
    AZD_97_HEAD(SignalCraft.MOD_ID + ":models/azd/azd97_vyst_zav.obj"),
    AZD_99(SignalCraft.MOD_ID + ":models/azd/azd99.obj"),
    VUD(SignalCraft.MOD_ID + ":models/vud/noveVUD.obj"),
    SSSR(SignalCraft.MOD_ID + ":models/sssr/sssrVyst_noveTextury.obj"),
    SSSR_SINGLE(SignalCraft.MOD_ID + ":models/sssr/sssr_vyst_single.obj"),
    SSSR_HEAD(SignalCraft.MOD_ID + ":models/sssr/sssrVystZav_noveTextury.obj"),
    SSSR_SINGLE_HEAD(SignalCraft.MOD_ID + ":models/sssr/sssr_vyst_single_head.obj"),
    SSSR_5LIGHT_T(SignalCraft.MOD_ID + ":models/sssr_nav/sssr_5svetT_nove.obj"),
    // SSSR_2LIGHT..SSSR_5LIGHT (sssr_2svet_nove.obj..sssr_5svet_nove.obj) were
    // replaced by the shared PartLibraryRegistry.SSSR_NAV parts library --
    // see tools/objdedupe.
    SSSR_4LIGHT_T(SignalCraft.MOD_ID + ":models/sssr_nav/sssr_4svetT_nove.obj"),
    SSSR_3LIGHT_T(SignalCraft.MOD_ID + ":models/sssr_nav/sssr_3svetT_nove.obj"),
    SSSR_3LIGHT_T_MECH(SignalCraft.MOD_ID + ":models/sssr_nav/sssr_3svetT_Mech_nove.obj"),
    SSSR_2LIGHT_T(SignalCraft.MOD_ID + ":models/sssr_nav/sssr_2svetT_nove.obj"),
    SSSR_2LIGHT_T_MECH(SignalCraft.MOD_ID + ":models/sssr_nav/sssr_2svetT_Mech_nove.obj"),
    // SSSR_DISTANT/SSSR_SHUNT/SSSR_INSERTED/SSSR_AB3/SSSR_AB4 (sssr_pr_nove.obj,
    // sssr_2se_nove.obj, sssr_vl_nove.obj, sssr_3ab_nove.obj, sssr_4ab_nove.obj)
    // were folded into the shared PartLibraryRegistry.SSSR_NAV parts library --
    // see tools/objdedupe.
    SSSR_SHUNT_T(SignalCraft.MOD_ID + ":models/sssr_nav/sssr_2seT_nove.obj"),
    PR_UPOZ(SignalCraft.MOD_ID + ":models/signs/pr_upozornovadlo.obj"),

    GSAR_WN(SignalCraft.MOD_ID + ":models/gsar/SignalWN.obj"),
    GSAR_NE13(SignalCraft.MOD_ID + ":models/gsar/SignalNE13.obj"),
    GSAR_LIGHT_SIGNALS(SignalCraft.MOD_ID + ":models/gsar/lightSignals.obj"),
    GSAR_SEMI_SIGNALS(SignalCraft.MOD_ID + ":models/gsar/SemiSignals.obj"),
    GSAR_SEMI_SIGNALS_VR(SignalCraft.MOD_ID + ":models/gsar/SemiSignalsVR.obj"),
    GSAR_SEMI_STATIV(SignalCraft.MOD_ID + ":models/gsar/SignalStativSemi.obj"),
    GSAR_BU_STATIV(SignalCraft.MOD_ID + ":models/gsar/StativBU.obj"),
    GSAR_RA_STATIV(SignalCraft.MOD_ID + ":models/gsar/StativRA.obj"),
    GSAR_BARRIER(SignalCraft.MOD_ID + ":models/gsar/HalfBarriers.obj"),
    GSAR_FULL_BARRIER(SignalCraft.MOD_ID + ":models/gsar/FullBarriers.obj"),
    GSAR_RAILCROSS_LIGHT(SignalCraft.MOD_ID + ":models/gsar/RailCrossLight.obj"),
    GSAR_ROD(SignalCraft.MOD_ID + ":models/gsar/MetalRod.obj"),
    GSAR_BARRIER_STOP(SignalCraft.MOD_ID + ":models/gsar/FullBarriersS.obj"),
    GSAR_RAILCROSS(SignalCraft.MOD_ID + ":models/gsar/RailCross.obj"),
    GSAR_RAILCROSS_FENCE(SignalCraft.MOD_ID + ":models/gsar/RailCrossFence.obj"),
    GSAR_RAILCROSS_FLASH(SignalCraft.MOD_ID + ":models/gsar/RailCrossFlash.obj"),
    GSAR_LF(SignalCraft.MOD_ID + ":models/gsar/SignalLF7.obj"),
    GSAR_LF1(SignalCraft.MOD_ID + ":models/gsar/SignalLF1.obj"),
    GSAR_LF6(SignalCraft.MOD_ID + ":models/gsar/SignalLF6.obj"),
    GSAR_PF2(SignalCraft.MOD_ID + ":models/gsar/SignalPF2.obj"),
    GSAR_PF2Z(SignalCraft.MOD_ID + ":models/gsar/SignalPF2Z.obj"),
    GSAR_BU(SignalCraft.MOD_ID + ":models/gsar/SignalBU.obj"),
    GSAR_BUZ(SignalCraft.MOD_ID + ":models/gsar/SignalBUZ.obj"),
    GSAR_BU2(SignalCraft.MOD_ID + ":models/gsar/SignalBU2.obj"),
    GSAR_NE1(SignalCraft.MOD_ID + ":models/gsar/SignalNE1.obj"),
    GSAR_NE3(SignalCraft.MOD_ID + ":models/gsar/SignalNE3.obj"),
    GSAR_NE6(SignalCraft.MOD_ID + ":models/gsar/SignalNE6.obj"),
    GSAR_NE7(SignalCraft.MOD_ID + ":models/gsar/SignalNE7.obj"),
    GSAR_RA10(SignalCraft.MOD_ID + ":models/gsar/SignalRA10.obj"),
    GSAR_RA11(SignalCraft.MOD_ID + ":models/gsar/SignalRA11.obj"),
    GSAR_RA11A(SignalCraft.MOD_ID + ":models/gsar/SignalRA11a.obj"),
    GSAR_SH(SignalCraft.MOD_ID + ":models/gsar/SignalSH.obj"),
    GSAR_SH2(SignalCraft.MOD_ID + ":models/gsar/SignalSH2.obj"),
    GSAR_HECTOSIGN(SignalCraft.MOD_ID + ":models/gsar/HectoSign.obj"),
    GSAR_SIGNAL_LEVER(SignalCraft.MOD_ID + ":models/gsar/SignalLever.obj"),
    GSAR_STATION_SIGN(SignalCraft.MOD_ID + ":models/gsar/StationSigns.obj"),

    MECH_2ARMS(SignalCraft.MOD_ID + ":models/mech/2ramena.obj"),
    MECH_1ARM(SignalCraft.MOD_ID + ":models/mech/1rameno.obj"),
    MECH_PREDVEST(SignalCraft.MOD_ID + ":models/mech/predvest.obj"),
    MECH_SERAZOVACI(SignalCraft.MOD_ID + ":models/mech/serazovaci.obj"),

    GSAR_BRIDGE_BEAMS(SignalCraft.MOD_ID + ":models/gsar/BridgeBeams.obj"),
    GSAR_BRIDGE_BEAMS_CORNER(SignalCraft.MOD_ID + ":models/gsar/BridgeBeamsCorner.obj"),
    GSAR_BRIDGE_BEAMS_CROSS(SignalCraft.MOD_ID + ":models/gsar/BridgeBeamsCross.obj"),
    GSAR_BRIDGE_BEAMS_TRIPLE(SignalCraft.MOD_ID + ":models/gsar/BridgeBeamsTripple.obj"),
    GSAR_GROUND(SignalCraft.MOD_ID + ":models/gsar/BridgeGround.obj"),
    GSAR_GROUND_BEAMS(SignalCraft.MOD_ID + ":models/gsar/BridgeGroundBeams.obj"),
    GSAR_LADDER(SignalCraft.MOD_ID + ":models/gsar/BridgeLadder.obj"),
    GSAR_RAILING(SignalCraft.MOD_ID + ":models/gsar/BridgeRailing.obj"),
    GSAR_RAILING2(SignalCraft.MOD_ID + ":models/gsar/BridgeRailing2.obj"),
    GSAR_RAILING_RODS(SignalCraft.MOD_ID + ":models/gsar/BridgeRailingRods.obj"),
    ;
    private final ResourceLocation location;
    private IModelCustom model;

    ModelRegistry(String path) {
        this.location = new ResourceLocation(path);
    }

    public void load() {
        if (model == null) {
            model = AdvancedModelLoader.loadModel(location);
        }
    }

    public IModelCustom getModel() {
        return model;
    }
}