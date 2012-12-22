package ayamitsu.mobskullsplus.common;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public class BlockBoundsCube implements IBlockBounds
{
	public static float MIN = 0F;
	public static float MAX = 1F;
	public float xSize = 0.5F;
	public float ySize = 0.5F;
	public float zSize = 0.5F;

	public BlockBoundsCube() {}

	public BlockBoundsCube(float f, float f1, float f2)
	{
		this.xSize = f;
		this.ySize = f1;
		this.zSize = f2;
	}

	public BlockBoundsCube setSize(float f, float f1, float f2)
	{
		this.xSize = f;
		this.ySize = f1;
		this.zSize = f2;
		return this;
	}

	@Override
	public void setBlockBounds(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block)
	{
		int direction = iblockaccess.getBlockMetadata(blockX, blockY, blockZ) & 7;

        switch (direction)
        {
            case 1:
            default:
                block.setBlockBounds(this.getMinSize(this.xSize), MIN, this.getMinSize(this.xSize), this.getMaxSize(this.xSize), MIN + this.ySize, this.getMaxSize(this.zSize));
                break;
            case 2:
                block.setBlockBounds(this.getMinSize(this.xSize), this.getMinSize(this.ySize), MAX - this.zSize, this.getMaxSize(this.xSize), this.getMaxSize(this.ySize), MAX);
                break;
            case 3:
                block.setBlockBounds(this.getMinSize(this.xSize), this.getMinSize(this.ySize), MIN, this.getMaxSize(this.xSize), this.getMaxSize(this.ySize), MIN + this.zSize);
                break;
            case 4:
                block.setBlockBounds(MAX - this.xSize, this.getMinSize(this.ySize), this.getMinSize(this.zSize), MAX, this.getMaxSize(this.ySize), this.getMaxSize(this.zSize));
                break;
            case 5:
                block.setBlockBounds(MIN, this.getMinSize(this.ySize), this.getMinSize(this.zSize), MIN + this.xSize, this.getMaxSize(this.ySize), this.getMaxSize(this.zSize));
        }
	}

	private float getMinSize(float size)
	{
		return (MAX - size) / 2.0F;
	}

	private float getMaxSize(float size)
	{
		return MAX - ((MAX - size) / 2.0F);
	}
}