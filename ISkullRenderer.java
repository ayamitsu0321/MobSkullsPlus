package ayamitsu.mobskullsplus;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public interface ISkullRenderer
{
	/**
	 * 実際の描画
	 */
	
	@SideOnly(Side.CLIENT)
	public void renderSkull(int direction, float par5, EnumSkullRenderType type);
	
	/**
	 * Blockのあたり判定のset
	 */
	public void setBlockBounds(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block);
	
	/**
	 * テクスチャ上の番号
	 */
	public int getSpriteIndex(int meta);
}