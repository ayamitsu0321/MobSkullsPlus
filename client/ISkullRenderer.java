package ayamitsu.mobskullsplus.client;

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
	 * テクスチャ上の番号
	 */
	public int getSpriteIndex(int meta);
}