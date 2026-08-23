package signalcraft.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ISCBlock {
    boolean openGui(World world, int x, int y, int z, EntityPlayer player);
}
