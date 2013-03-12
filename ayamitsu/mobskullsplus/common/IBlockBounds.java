package ayamitsu.mobskullsplus.common;

import net.minecraft.block.Block;

public interface IBlockBounds
{
	/**
	 * Block�̂����蔻���set
	 */
	public void setBlockBounds(net.minecraft.world.IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block);

}