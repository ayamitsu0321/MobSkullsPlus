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
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderblocks) {}

	@Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		// render in TileEntitySpecialRenderer
		return this.renderId == modelId;
	}

	@Override
    public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
    public int getRenderId()
	{
		return this.renderId;
	}
}
