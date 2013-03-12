package ayamitsu.mobskullsplus.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import ayamitsu.mobskullsplus.MobSkullsPlus;
import cpw.mods.fml.common.registry.LanguageRegistry;

public final class BlockBoundsRegistry
{
	private static Map<Integer, IBlockBounds> blockBoundsMap = new HashMap<Integer, IBlockBounds>();

	public static void registerBlockBounds(int id, String name, IBlockBounds blockbounds)
	{
		LanguageRegistry.instance().addNameForObject(new ItemStack(MobSkullsPlus.skull.blockID, 1, id), "en_US", name);
		blockBoundsMap.put(Integer.valueOf(id), blockbounds);
	}

	public static IBlockBounds getBlockBounds(int id)
	{
		return blockBoundsMap.get(id);
	}

	public static Map getMap()
	{
		return blockBoundsMap;
	}
}