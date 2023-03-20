package sunsetsatellite.signalindustries.api.impl.guidebookpp;

import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import sunsetsatellite.fluidapi.api.FluidStack;
import sunsetsatellite.guidebookpp.GuidebookPlusPlus;
import sunsetsatellite.guidebookpp.IContainerRecipeBase;
import sunsetsatellite.signalindustries.SignalIndustries;

import java.util.ArrayList;

public class ContainerGuidebookCrystalCutterRecipe extends ContainerGuidebookRecipeBase
    implements IContainerRecipeBase {

    ItemStack machine;

    public ContainerGuidebookCrystalCutterRecipe(ItemStack stack, ArrayList<ItemStack> itemInputs, ArrayList<FluidStack> fluidInputs, ArrayList<ItemStack> itemOutputs, ArrayList<FluidStack> fluidOutputs) {
        machine = stack;
        this.addSlot(new SlotGuidebook(0, 9, 10, itemInputs.get(0), false));
        this.addSlot(new SlotGuidebook(0, -19, 10, new ItemStack(fluidInputs.get(0).liquid,fluidInputs.get(0).amount), false));
        this.addSlot(new SlotGuidebook(1, 69, 19,itemOutputs.get(0), false));
        this.addSlot(new SlotGuidebook(2, 9, 45, new ItemStack(SignalIndustries.energyFlowing,160), false));
        this.addSlot(new SlotGuidebook(3,36,18,stack,true));
    }

    public void drawContainer(GuiGuidebook guidebook, int xSize, int ySize, int index){
        RenderItem itemRenderer = new RenderItem();
        int i = GuidebookPlusPlus.mc.renderEngine.getTexture("/assets/signalindustries/gui/generic_machine_double_recipe.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuidebookPlusPlus.mc.renderEngine.bindTexture(i);
        int j = (guidebook.width - xSize) / 2;
        int k = (guidebook.height - ySize) / 2;
        int xPos = j + 29 + 158 * (index / 3);
        int yPos = k + 30 + 62 * (index % 3);
        int yOffset = 0;
        guidebook.drawTexturedModalRect(xPos - 20, yPos, 138, yOffset, 121, 54);

    };

    @Override
    public void quickMoveItems(int i, EntityPlayer entityPlayer, boolean bl, boolean bl2) {

    }
}
