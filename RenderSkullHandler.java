package ayamitsu.mobskullsplus;

import net.minecraft.src.*;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderSkullHandler implements ISimpleBlockRenderingHandler
{
	int renderId;
	
	public RenderSkullHandler(int id)
	{
		this.renderId = id;
	}
	
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		ISkullRenderer skullRenderer = MobSkullsList.getSkullRenderer(metadata);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(1.8F, 1.8F, 1.8F);
		skullRenderer.renderSkull(-1, 90F, EnumSkullRenderType.EQUIPPED);
		//TileEntityMobSkullRenderer.skullRenderer.doRenderSkull(0F, 0F, 0F, -1, 0F, metadata);
	}

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

    public boolean shouldRender3DInInventory()
	{
		return true;
	}

    public int getRenderId()
	{
		return this.renderId;
	}
}
