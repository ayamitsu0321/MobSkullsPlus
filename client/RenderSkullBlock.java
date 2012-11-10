package ayamitsu.mobskullsplus.client;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.client.registry.RendererRegistry;

import net.minecraft.src.*;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

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
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
		
		////TileEntityMobSkullRenderer.skullRenderer.doRenderSkull(0F, 0F, 0F, -1, 0F, metadata);
	}

	@Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (this.renderId == modelId)
		{
			return true;
		}
		
		return false;
		/*Tessellator var1 = Tessellator.instance;
		
		TileEntity skull = world.getBlockTileEntity(x, y, z);
		
		if (skull instanceof TileEntityMobSkull)
		{
			var1.draw();
			TileEntityMobSkullRenderer.skullRenderer.renderTileEntityAt((TileEntityMobSkull)skull, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, 0.0F);
			var1.startDrawingQuads();
			return true;
		}
		
		return false;*/
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
