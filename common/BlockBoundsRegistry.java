package ayamitsu.mobskullsplus.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import ayamitsu.mobskullsplus.MobSkullsPlus;
import cpw.mods.fml.common.registry.LanguageRegistry;

public final class BlockBoundsRegistry
{
	private static boolean hasInit = false;
	private static Map<Integer, IBlockBounds> blockBoundsMap;

	public static void registerBlockBounds(int id, String name, IBlockBounds blockbounds)
	{
		init();
		LanguageRegistry.instance().addNameForObject(new ItemStack(MobSkullsPlus.skull.blockID, 1, id), "en_US", name);
		blockBoundsMap.put(Integer.valueOf(id), blockbounds);
	}

	public static IBlockBounds getBlockBounds(int id)
	{
		init();
		return blockBoundsMap.get(id);
	}

	public static Map getMap()
	{
		init();
		return blockBoundsMap;
	}

	private static void init()
	{
		if (hasInit)
		{
			return;
		}

		hasInit = true;
		blockBoundsMap = new HashMap<Integer, IBlockBounds>();
	}
}