package sunsetsatellite.signalindustries.blocks;


import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import sunsetsatellite.catalyst.core.util.Connection;
import sunsetsatellite.catalyst.core.util.Direction;
import sunsetsatellite.catalyst.fluids.impl.containers.ContainerFluidTank;
import sunsetsatellite.catalyst.fluids.impl.tiles.TileEntityFluidPipe;
import sunsetsatellite.signalindustries.SignalIndustries;
import sunsetsatellite.signalindustries.blocks.base.BlockContainerTiered;
import sunsetsatellite.signalindustries.gui.GuiSIFluidTank;
import sunsetsatellite.signalindustries.inventories.TileEntitySIFluidTank;
import sunsetsatellite.signalindustries.util.IOPreview;
import sunsetsatellite.signalindustries.util.Tier;


import java.util.ArrayList;

public class BlockSIFluidTank extends BlockContainerTiered {

    public BlockSIFluidTank(String key, int i, Tier tier, Material material) {
        super(key, i, tier, material);
        withOverbright();
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntitySIFluidTank();
    }

    @Override
    public void onBlockRemoved(World world, int i, int j, int k, int data) {
        TileEntitySIFluidTank tile = (TileEntitySIFluidTank) world.getBlockTileEntity(i, j, k);
        if (tile != null) {
            for (Direction dir : Direction.values()) {
                TileEntity tile2 = dir.getTileEntity(world,tile);
                if (tile2 instanceof TileEntityFluidPipe) {
                    tile.unpressurizePipes((TileEntityFluidPipe) tile2,new ArrayList<>());
                }
            }
        }
        super.onBlockRemoved(world, i, j, k, data);
    }

    @Override
    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(world.isClientSide)
        {
            return true;
        } else
        {
            TileEntitySIFluidTank tile = (TileEntitySIFluidTank) world.getBlockTileEntity(i, j, k);
            if(tile != null) {
                SignalIndustries.displayGui(entityplayer,() -> new GuiSIFluidTank(entityplayer.inventory, tile),new ContainerFluidTank(entityplayer.inventory,tile),tile,i,j,k);
            }
            return true;
        }
    }

    @Override
    public boolean isSolidRender() {
        return false;
    }
}
