package ayamitsu.mobskullsplus;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public interface ISkullRenderer
{
	/**
	 * é¿ç€ÇÃï`âÊ
	 */
	
	@SideOnly(Side.CLIENT)
	public void renderSkull(int direction, float par5, EnumSkullRenderType type);
	
	/**
	 * BlockÇÃÇ†ÇΩÇËîªíËÇÃset
	 */
	public void setBlockBounds(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block);
}