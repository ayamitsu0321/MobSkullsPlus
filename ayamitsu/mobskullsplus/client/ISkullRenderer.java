package ayamitsu.mobskullsplus.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface ISkullRenderer
{
	/**
	 * do render
	 */
	void renderSkull(int direction, float par5, EnumSkullRenderType type);

	/**
	 * return texture path
	 * example: return "icon"; -> path is "assets//mobskullsplus/textures/blocks/icon.png"
	 */
	String getIconPath();
}