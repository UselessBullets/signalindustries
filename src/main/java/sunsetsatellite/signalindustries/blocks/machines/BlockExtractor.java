package sunsetsatellite.signalindustries.blocks.machines;


import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.core.util.BlockInstance;
import sunsetsatellite.catalyst.core.util.Direction;
import sunsetsatellite.catalyst.core.util.Vec3i;
import sunsetsatellite.catalyst.fluids.impl.tiles.TileEntityFluidPipe;
import sunsetsatellite.signalindustries.SIAchievements;
import sunsetsatellite.signalindustries.SignalIndustries;
import sunsetsatellite.signalindustries.blocks.base.BlockContainerTiered;
import sunsetsatellite.signalindustries.containers.ContainerExtractor;
import sunsetsatellite.signalindustries.containers.ContainerReinforcedExtractor;
import sunsetsatellite.signalindustries.gui.GuiExtractor;
import sunsetsatellite.signalindustries.gui.GuiReinforcedExtractor;
import sunsetsatellite.signalindustries.inventories.base.TileEntityTieredMachineBase;
import sunsetsatellite.signalindustries.inventories.machines.TileEntityExtractor;
import sunsetsatellite.signalindustries.inventories.machines.TileEntityReinforcedExtractor;
import sunsetsatellite.signalindustries.util.Tier;

import java.util.ArrayList;
import java.util.Random;

public class BlockExtractor extends BlockContainerTiered {

    public BlockExtractor(String key, int i, Tier tier, Material material) {
        super(key, i, tier, material);
        hasOverbright = true;
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        if(tier == Tier.REINFORCED){
            return new TileEntityReinforcedExtractor();
        }
        return new TileEntityExtractor();
    }

    @Override
    public String getDescription(ItemStack stack) {
        if(tier == Tier.REINFORCED){
            String s = super.getDescription(stack);
            return s+"\n"+ TextFormatting.YELLOW+"Multiblock"+ TextFormatting.WHITE;
        } else {
            return super.getDescription(stack);
        }
    }

    @Override
    public void onBlockRemoved(World world, int i, int j, int k, int data) {
        TileEntityTieredMachineBase tile = (TileEntityTieredMachineBase) world.getBlockTileEntity(i, j, k);
        if (tile != null) {
            for (Direction dir : Direction.values()) {
                TileEntity tile2 = dir.getTileEntity(world, tile);
                if (tile2 instanceof TileEntityFluidPipe) {
                    tile.unpressurizePipes((TileEntityFluidPipe) tile2, new ArrayList<>());
                }
            }
            Random random = new Random();
            for (int l = 0; l < tile.getSizeInventory(); ++l) {
                ItemStack itemstack = tile.getStackInSlot(l);
                if (itemstack != null) {
                    float f = random.nextFloat() * 0.8F + 0.1F;
                    float f1 = random.nextFloat() * 0.8F + 0.1F;
                    float f2 = random.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0) {
                        int i1 = random.nextInt(21) + 10;
                        if (i1 > itemstack.stackSize) {
                            i1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= i1;
                        EntityItem entityitem = new EntityItem(world, (float) i + f, (float) j + f1, (float) k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getMetadata()));
                        float f3 = 0.05F;
                        entityitem.xd = (float) random.nextGaussian() * f3;
                        entityitem.yd = (float) random.nextGaussian() * f3 + 0.2F;
                        entityitem.zd = (float) random.nextGaussian() * f3;
                        world.entityJoinedWorld(entityitem);
                    }
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
        } else {
            if (tier == Tier.REINFORCED) {
                TileEntityReinforcedExtractor tile = (TileEntityReinforcedExtractor) world.getBlockTileEntity(i, j, k);
                if (tile != null) {
                    if(tile.multiblock != null && tile.multiblock.isValidAt(world,new BlockInstance(this,new Vec3i(i,j,k),tile),Direction.getDirectionFromSide(world.getBlockMetadata(i,j,k)))){
                        SignalIndustries.displayGui(entityplayer,() -> new GuiReinforcedExtractor(entityplayer.inventory, tile),new ContainerReinforcedExtractor(entityplayer.inventory,tile),tile,i,j,k);
                        entityplayer.triggerAchievement(SIAchievements.HORIZONS);
                    } else {
                        entityplayer.sendMessage("event.signalindustries.invalidMultiblock");
                    }
                }
                return true;
            } else {
                TileEntityExtractor tile = (TileEntityExtractor) world.getBlockTileEntity(i, j, k);
                if (tile != null) {
                    SignalIndustries.displayGui(entityplayer, () -> new GuiExtractor(entityplayer.inventory, tile), new ContainerExtractor(entityplayer.inventory, tile), tile, i, j, k);
                }
                return true;
            }
        }
    }

}
