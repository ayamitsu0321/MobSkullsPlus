package ayamitsu.mobskullsplus;

import java.util.Map;
import java.util.HashMap;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.client.SpriteHelper;

public final class MobSkullsList
{
	private static boolean hasInit = false;
	private static Map<Integer, ISkullRenderer> skullList;
	
	private static void init()
	{
		if (hasInit)
		{
			return;
		}
		
		hasInit = true;
		skullList = new HashMap<Integer, ISkullRenderer>();
		SpriteHelper.registerSpriteMapForFile(MobSkullsPlus.terrain, 
			"0000000000000000" +
			"0000000111111111" +
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
	
	public static void registerSkullRenderer(int entityId, String name, ISkullRenderer renderer)
	{
		LanguageRegistry.instance().addNameForObject(new ItemStack(MobSkullsPlus.skull.blockID, 1, entityId), "en_US", name);
		skullList.put(Integer.valueOf(entityId), renderer);
	}
	
	public static ISkullRenderer getSkullRenderer(int entityId)
	{
		return skullList.get(Integer.valueOf(entityId));
	}
	
	public static boolean contains(int entityId)
	{
		return skullList.containsKey(Integer.valueOf(entityId));
	}
	
	public static int addOverride(String tex)
	{
		return RenderingRegistry.addTextureOverride(MobSkullsPlus.terrain, tex);
	}
	
	public static Map getMap()
	{
		return skullList;
	}
	
	static
	{
		init();
	}
}