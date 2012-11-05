package ayamitsu.mobskullsplus;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
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
	 * Block�̂����蔻���set
	 */
	public void setBlockBounds(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block);
	
	/**
	 * �e�N�X�`����̔ԍ�
	 */
	public int getSpriteIndex(int meta);
}