package signalcraft.blocks;

import net.minecraft.block.Block;
import signalcraft.blocks.gsar.signalsSO.BlockGSARStationSign;
import signalcraft.blocks.other.BlockTrackGravel;
import signalcraft.blocks.other.BlockTreatedPlanks;

public enum SCBlocks {
    TREATED_PLANKS(new BlockTreatedPlanks("blockTreatedPlanks")),
    TRACK_GRAVEL(new BlockTrackGravel("blockTrackGravel")),


    ;
    public Block block;

    SCBlocks(Block block) {
        this.block = block;
    }
}