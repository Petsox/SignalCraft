package signalcraft.items;

import net.minecraft.item.Item;
import signalcraft.items.circuits.*;
import signalcraft.items.crossings.ItemBarrierLong;
import signalcraft.items.crossings.ItemBarrierShort;
import signalcraft.items.crossings.ItemMetalGear;
import signalcraft.items.signals.*;

public enum SCItems {
    VYPRAVKA(new ItemVypravka("vypravka")),
    SIGNAL_RENAMER(new ItemSignalRenamer("signalRenamer")),
    SIGNAL_BONDER(new ItemSignalBonder("signalBonder")),
    WRENCH(new ItemWrench("wrench")),
    BARRIER_LONG(new ItemBarrierLong("barrierLong")),
    BARRIER_SHORT(new ItemBarrierShort("barrierShort")),
    CIRCUIT_RECEIVER(new ItemCircuitReceiver("circuitReceiver")),
    CIRCUIT_CONTROLLER(new ItemCircuitController("circuitController")),
    CIRCUIT_AZD(new ItemCircuitAZD("circuitAZD")),
    CIRCUIT_AZD97(new ItemCircuitAZD97("circuitAZD97")),
    CIRCUIT_EMPTY(new ItemCircuitEmpty("circuitEmpty")),
    CIRCUIT_SSSR(new ItemCircuitSSSR("circuitSSSR")),
    DISTANT_SIGN(new ItemDistantSign("distantSign")),
    METAL_GEAR(new ItemMetalGear("metalGear")),
    SEMAPHORE_ARM(new ItemSemaphoreArm("semaphoreArm")),
    TREATED_WOOD_PLATE(new ItemTreatedWoodPlate("treatedWoodPlate")),
    POLE_MAIN(new ItemPoleMain("itemPoleMain")),
    POLE_SEMI(new ItemPoleSemi("itemPoleSemi")),

    SSSR_2LIGHTS(new Item2LightsSSSR("item2LightsSSSR")),
    SSSR_3LIGHTS(new Item3LightsSSSR("item3LightsSSSR")),
    SSSR_POLE(new ItemPoleSSSR("itemPoleSSSR")),

    AZD70_POLE(new ItemPoleAZD70("itemPoleAZD70")),
    AZD70_1LIGHT(new Item1LightAZD70("item1LightAZD70")),
    AZD70_2LIGHTS(new Item2LightsAZD70("item2LightsAZD70")),
    AZD70_3LIGHTS(new Item3LightsAZD70("item3LightsAZD70")),
    AZD70_4LIGHTS(new Item4LightsAZD70("item4LightsAZD70")),
    AZD70_5LIGHTS(new Item5LightsAZD70("item5LightsAZD70")),
    AZD70_6LIGHTS(new Item6LightsAZD70("item6LightsAZD70")),

    ;
    public Item item;

    SCItems(Item item) {
        this.item = item;
    }
}