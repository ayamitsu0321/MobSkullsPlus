package ayamitsu.mobskullsplus.client;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public interface ISkullRenderer
{
	/**
	 * ���ۂ̕`��
	 */
	
	@SideOnly(Side.CLIENT)
	public void renderSkull(int direction, float par5, EnumSkullRenderType type);
	
	/**
	 * �e�N�X�`����̔ԍ�
	 */
	public int getSpriteIndex(int meta);
}