package ayamitsu.mobskullsplus.client;

public interface ISkullRenderer
{
	/**
	 * ���ۂ̕`��
	 */

	@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
	public void renderSkull(int direction, float par5, EnumSkullRenderType type);

	/**
	 * �e�N�X�`����̔ԍ�
	 */
	public int getSpriteIndex(int meta);
}