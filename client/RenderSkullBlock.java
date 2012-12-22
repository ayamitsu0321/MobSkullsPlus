package ayamitsu.mobskullsplus.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderSkullBlock implements ISimpleBlockRenderingHandler
{
	int renderId;

	public RenderSkullBlock(int id)
	{
		this.renderId = id;
	}

	@Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderblocks)
	{
		ISkullRenderer renderer = RendererRegistry.getSkullRenderer(metadata);

		if (renderer != null)
		{
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(1.8F, 1.8F, 1.8F);
			renderer.renderSkull(-1, 90F, EnumSkullRenderType.EQUIPPED);
			GL11.glScalef(1.0F, 1.0F, -1.0F);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			//GL11.glEnable(GL11.GL_CULL_FACE);
		}
	}

	@Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return this.renderId == modelId;
	}

	@Override
    public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
    public int getRenderId()
	{
		return this.renderId;
	}
}
