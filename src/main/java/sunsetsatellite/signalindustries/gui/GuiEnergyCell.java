package sunsetsatellite.signalindustries.gui;


import net.minecraft.client.gui.GuiButton;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;
import sunsetsatellite.catalyst.fluids.impl.GuiFluid;
import sunsetsatellite.catalyst.fluids.impl.containers.ContainerFluidTank;
import sunsetsatellite.catalyst.fluids.impl.tiles.TileEntityFluidItemContainer;
import sunsetsatellite.signalindustries.SignalIndustries;
import sunsetsatellite.signalindustries.blocks.base.BlockContainerTiered;
import sunsetsatellite.signalindustries.inventories.TileEntityEnergyCell;
import sunsetsatellite.signalindustries.util.Tier;

public class GuiEnergyCell extends GuiFluid {

    public String name = "Energy Cell";
    public EntityPlayer entityplayer;
    public TileEntityEnergyCell tile;
    public GuiEnergyCell(InventoryPlayer inventoryPlayer, TileEntity tile) {
        super(new ContainerFluidTank(inventoryPlayer, (TileEntityFluidItemContainer) tile),inventoryPlayer);
        this.entityplayer = inventoryPlayer.player;
        this.tile = (TileEntityEnergyCell) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f)
    {
        int i = mc.renderEngine.getTexture("/assets/catalyst-fluids/gui/tank_gui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

    }

    protected void drawGuiContainerForegroundLayer()
    {
        super.drawGuiContainerForegroundLayer();
        fontRenderer.drawString(name, 60, 6, 0xFF404040);
    }

    protected void buttonPressed(GuiButton guibutton) {
        if (!guibutton.enabled) {
            return;
        }
        if (guibutton.id == 0) {
            SignalIndustries.displayGui(entityplayer, () -> new GuiFluidIOConfig(entityplayer, inventorySlots, tile, this), inventorySlots, (IInventory) tile, tile.x, tile.y, tile.z);
        }
        if(tile.getTier() == Tier.INFINITE && guibutton.id == 1){
            tile.isInfiniteSource = !tile.isInfiniteSource;
            guibutton.displayString = tile.isInfiniteSource ? "INF" : "VOID";
        }
    }

    public void init()
    {
        controlList.add(new GuiButton(0, Math.round(width / 2) - 80, Math.round(height / 2) - 80, 20, 20, "F"));
        if(tile.getTier() == Tier.INFINITE){
            controlList.add(new GuiButton(1, Math.round(width / 2) - 80, Math.round(height / 2) - 30, 20, 20, tile.isInfiniteSource ? "INF" : "VOID"));
        }
        super.init();
    }
}
