package ayamitsu.mobskullsplus.client;


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