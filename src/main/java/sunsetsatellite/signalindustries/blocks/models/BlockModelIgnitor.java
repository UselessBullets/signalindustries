package sunsetsatellite.signalindustries.blocks.models;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import sunsetsatellite.signalindustries.SignalIndustries;
import sunsetsatellite.signalindustries.blocks.BlockIgnitor;
import sunsetsatellite.signalindustries.inventories.TileEntityIgnitor;

import java.util.HashMap;

public class BlockModelIgnitor extends BlockModelStandard<BlockIgnitor> {

    protected HashMap<Side, IconCoordinate> defaultTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));
    protected HashMap<Side, IconCoordinate> readyTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));
    protected HashMap<Side, IconCoordinate> activeTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));
    protected HashMap<Side, IconCoordinate> invertedDefaultTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));
    protected HashMap<Side, IconCoordinate> invertedReadyTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));
    protected HashMap<Side, IconCoordinate> invertedActiveTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));
    protected HashMap<Side, IconCoordinate> readyOverbrightTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));
    protected HashMap<Side, IconCoordinate> activeOverbrightTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));
    protected HashMap<Side, IconCoordinate> invertedReadyOverbrightTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));
    protected HashMap<Side, IconCoordinate> invertedActiveOverbrightTextures = (HashMap<Side, IconCoordinate>) SignalIndustries.mapOf(Side.values(),SignalIndustries.arrayFill(new IconCoordinate[Side.values().length],BLOCK_TEXTURE_UNASSIGNED));

    public BlockModelIgnitor(Block block) {
        super(block);
        hasOverbright = true;
        defaultTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_inactive"));
        defaultTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_inactive"));
        defaultTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_inactive"));
        defaultTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_inactive"));
        defaultTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_inactive"));
        defaultTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_inactive"));

        invertedDefaultTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_inactive"));
        invertedDefaultTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_inactive"));
        invertedDefaultTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_inactive_inverted"));
        invertedDefaultTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_inactive_inverted"));
        invertedDefaultTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_inactive_inverted"));
        invertedDefaultTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_inactive_inverted"));

        readyTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_ready"));
        readyTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_ready"));
        readyTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready"));
        readyTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready"));
        readyTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready"));
        readyTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready"));

        activeTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_active"));
        activeTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_active"));
        activeTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active"));
        activeTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active"));
        activeTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active"));
        activeTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active"));

        invertedReadyTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_ready"));
        invertedReadyTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_ready"));
        invertedReadyTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_inverted"));
        invertedReadyTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_inverted"));
        invertedReadyTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_inverted"));
        invertedReadyTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_inverted"));

        invertedActiveTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_active"));
        invertedActiveTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_active"));
        invertedActiveTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_inverted"));
        invertedActiveTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_inverted"));
        invertedActiveTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_inverted"));
        invertedActiveTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_inverted"));

        //wrong
        /*readyOverbrightTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_ready_overbright"));
        readyOverbrightTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_ready_overbright"));
        readyOverbrightTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_overbright"));
        readyOverbrightTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_overbright"));
        readyOverbrightTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_overbright"));
        readyOverbrightTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_overbright"));

        activeOverbrightTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_active_overbright"));
        activeOverbrightTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_active_overbright"));
        activeOverbrightTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_overbright"));
        activeOverbrightTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_overbright"));
        activeOverbrightTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_overbright"));
        activeOverbrightTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_overbright"));

        invertedReadyOverbrightTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_ready_overbright"));
        invertedReadyOverbrightTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_ready_overbright"));
        invertedReadyOverbrightTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_overbright_inverted"));
        invertedReadyOverbrightTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_overbright_inverted"));
        invertedReadyOverbrightTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_overbright_inverted"));
        invertedReadyOverbrightTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_ready_overbright_inverted"));

        invertedActiveOverbrightTextures.put(Side.TOP, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_bottom_active_overbright"));
        invertedActiveOverbrightTextures.put(Side.BOTTOM, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_top_active_overbright"));
        invertedActiveOverbrightTextures.put(Side.NORTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_overbright_inverted"));
        invertedActiveOverbrightTextures.put(Side.SOUTH, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_overbright_inverted"));
        invertedActiveOverbrightTextures.put(Side.EAST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_overbright_inverted"));
        invertedActiveOverbrightTextures.put(Side.WEST, TextureRegistry.getTexture("signalindustries:block/reinforced_ignitor_active_overbright_inverted"));*/
    }

    @Override
    public IconCoordinate getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
        TileEntity tile = blockAccess.getBlockTileEntity(x,y,z);
        int meta = blockAccess.getBlockMetadata(x,y-1,z);
        if(tile == null) return defaultTextures.get(side);
        if(tile instanceof TileEntityIgnitor){
            TileEntityIgnitor ignitor = (TileEntityIgnitor) tile;
            if(ignitor.isBurning()){
                if(meta == 0 && blockAccess.getBlock(x,y-1,z) instanceof BlockIgnitor){
                    return invertedActiveTextures.get(side);
                } else {
                    return activeTextures.get(side);
                }
            } else if (ignitor.isReady()) {
                if(meta == 0 && blockAccess.getBlock(x,y-1,z) instanceof BlockIgnitor){
                    return invertedReadyTextures.get(side);
                } else {
                    return readyTextures.get(side);
                }
            } else {
                if(meta == 0 && blockAccess.getBlock(x,y-1,z) instanceof BlockIgnitor){
                    return invertedDefaultTextures.get(side);
                } else {
                    return defaultTextures.get(side);
                }
            }
        }
        return defaultTextures.get(side);
    }
}
