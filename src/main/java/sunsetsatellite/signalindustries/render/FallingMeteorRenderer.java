package sunsetsatellite.signalindustries.render;

import net.minecraft.client.render.RenderBlocks;
import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityFallingSand;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import org.lwjgl.opengl.GL11;
import sunsetsatellite.signalindustries.entities.EntityFallingMeteor;

public class FallingMeteorRenderer extends EntityRenderer<EntityFallingMeteor> {
    //private final RenderBlockSimple blockRenderer = new RenderBlockSimple();

    public FallingMeteorRenderer() {
        this.shadowSize = 0.5F;
    }

    public void doRenderFallingSand(Tessellator tessellator, EntityFallingMeteor entity, double d, double d1, double d2, float f, float f1) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        this.loadTexture("/terrain.png");
        Block block = Block.blocksList[entity.blockID];
        GL11.glPushMatrix();
        GL11.glDisable(2896);
        GL11.glColor4f(1,1,1,1);
        BlockModel<?> model = BlockModelDispatcher.getInstance().getDispatch(block);
        model.render(tessellator,MathHelper.floor_double(entity.x), MathHelper.floor_double(entity.y), MathHelper.floor_double(entity.z));
        GL11.glEnable(2896);
        GL11.glPopMatrix();
        GL11.glEnable(2884);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Tessellator tessellator, EntityFallingMeteor entity, double x, double y, double z, float yaw, float partialTick) {
        this.doRenderFallingSand(tessellator,entity, x, y, z, yaw, partialTick);
    }
}
