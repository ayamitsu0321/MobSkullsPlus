package ayamitsu.mobskullsplus.client;

import java.util.HashMap;
import java.util.Map;

import ayamitsu.mobskullsplus.MobSkullsPlus;
import cpw.mods.fml.client.SpriteHelper;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public final class RendererRegistry
{
	private static boolean hasInit = false;
	private static Map<Integer, ISkullRenderer> rendererMap;

	public static void registerSkullRenderer(int id, String name, ISkullRenderer renderer)
	{
		init();
		LanguageRegistry.instance().addNameForObject(new net.minecraft.item.ItemStack(MobSkullsPlus.skull.blockID, 1, id), "en_US", name);
		rendererMap.put(Integer.valueOf(id), renderer);
	}

	public static ISkullRenderer getSkullRenderer(int id)
	{
		init();
		return rendererMap.get(Integer.valueOf(id));
	}

	public static boolean contains(int id)
	{
		init();
		return  rendererMap.containsKey(Integer.valueOf(id));
	}

	public static int addOverride(String tex)
	{
		init();
		return RenderingRegistry.addTextureOverride(MobSkullsPlus.terrain, tex);
	}

	public static Map getMap()
	{
		init();
		return rendererMap;
	}

	private static void init()
	{
		if (hasInit)
		{
			return;
		}

		hasInit = true;
		rendererMap = new HashMap<Integer, ISkullRenderer>();
		SpriteHelper.registerSpriteMapForFile(MobSkullsPlus.terrain,
			"0000000000000000" +
			"0000000011111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111"
		);
	}
}