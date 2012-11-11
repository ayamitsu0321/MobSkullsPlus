package ayamitsu.mobskullsplus.client;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.client.model.*;
import ayamitsu.mobskullsplus.client.renderer.*;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ModelSkeletonHead;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.client.SpriteHelper;

import java.util.Map;
import java.util.HashMap;

public final class RendererRegistry
{
	private static boolean hasInit = false;
	private static Map<Integer, ISkullRenderer> rendererMap;
	
	public static void registerSkullRenderer(int id, String name, ISkullRenderer renderer)
	{
		init();
		LanguageRegistry.instance().addNameForObject(new ItemStack(MobSkullsPlus.skull.blockID, 1, id), "en_US", name);
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