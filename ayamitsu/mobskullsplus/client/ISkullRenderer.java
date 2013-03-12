package ayamitsu.mobskullsplus.client;

import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface ISkullRenderer
{
	/**
	 * do render
	 */
	void renderSkull(int direction, float par5, EnumSkullRenderType type);

	/**
	 * return texture path
	 * example: return "icon"; -> path is "mods/ayamitsu/mobskullsplus/textures/blocks/icon.png"
	 */
	String getIconPath();
}