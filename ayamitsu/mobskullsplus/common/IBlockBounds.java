package ayamitsu.mobskullsplus.common;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public interface IBlockBounds
{
	/**
	 * set block bounds with skull
	 */
	public void setBlockBounds(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block);

}