package signalcraft;

import cpw.mods.fml.common.network.simpleimpl.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.event.*;
import signalcraft.messages.*;
import signalcraft.proxy.CommonProxy;

@Mod(modid = "signalcraft", name = "SignalCraft")
public class SignalCraft
{
    @Mod.Instance("signalcraft")
    public static SignalCraft instance;
    public static final String name = "SignalCraft";
    public static final String MOD_ID = "signalcraft";
    @SidedProxy(clientSide = "signalcraft.proxy.ClientProxy", serverSide = "signalcraft.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static final SimpleNetworkWrapper SCNet;
    public static final String[] Devs = {"Petsox", "tpeterka1", "Breeko", "hajdam"};
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        SignalCraft.proxy.preInit(event);
    }

    @Mod.EventHandler
    public void Init(final FMLInitializationEvent event) {
        SignalCraft.proxy.init(event);
        SignalCraft.proxy.registerRenderers();

        SignalCraft.SCNet.registerMessage(MessageStateUpdate.Handler.class, MessageStateUpdate.class, 1, Side.CLIENT);
        SignalCraft.SCNet.registerMessage(MessageNameUpdate.Handler.class, MessageNameUpdate.class, 2, Side.CLIENT);
        SignalCraft.SCNet.registerMessage(MessageActiveUpdate.Handler.class, MessageActiveUpdate.class, 3, Side.SERVER);
        SignalCraft.SCNet.registerMessage(MessageActiveUpdate.Handler.class, MessageActiveUpdate.class, 3, Side.CLIENT);
        SignalCraft.SCNet.registerMessage(MessagePairingsUpdate.Handler.class, MessagePairingsUpdate.class, 4, Side.SERVER);
        SignalCraft.SCNet.registerMessage(MessagePairingsUpdate.Handler.class, MessagePairingsUpdate.class, 4, Side.CLIENT);
        SignalCraft.SCNet.registerMessage(WrenchOwnerMessage.Handler.class, WrenchOwnerMessage.class, 9, Side.CLIENT);
        SignalCraft.SCNet.registerMessage(WrenchOwnerMessage.Handler.class, WrenchOwnerMessage.class, 9, Side.SERVER);
        SignalCraft.SCNet.registerMessage(WrenchModeMessage.Handler.class, WrenchModeMessage.class, 10, Side.CLIENT);
        SignalCraft.SCNet.registerMessage(WrenchModeMessage.Handler.class, WrenchModeMessage.class, 10, Side.SERVER);
        SignalCraft.SCNet.registerMessage(RenamerNameMessage.Handler.class, RenamerNameMessage.class, 11, Side.CLIENT);
        SignalCraft.SCNet.registerMessage(RenamerNameMessage.Handler.class, RenamerNameMessage.class, 11, Side.SERVER);
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        SignalCraft.proxy.postInit(event);
    }

    static {
        SCNet = NetworkRegistry.INSTANCE.newSimpleChannel("SCNet");
    }
}
