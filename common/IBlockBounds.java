package ayamitsu.mobskullsplus.common;

import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Block;

public interface IBlockBounds
{
	/**
	 * Block�̂����蔻���set
	 */
	public void setBlockBounds(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block);
	
}