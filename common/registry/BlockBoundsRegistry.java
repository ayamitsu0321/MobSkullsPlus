package ayamitsu.mobskullsplus.common.registry;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.common.blockbounds.BlockBoundsCube;
import ayamitsu.mobskullsplus.common.*;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.LanguageRegistry;

import java.util.Map;
import java.util.HashMap;

public final class BlockBoundsRegistry
{
	private static boolean hasInit = false;
	private static Map<Integer, IBlockBounds> blockBoundsMap;
	
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
	
	private static void init()
	{
		if (hasInit)
		{
			return;
		}
		
		hasInit = true;
		blockBoundsMap = new HashMap<Integer, IBlockBounds>();
		registerBlockBounds(0, "Creeper Head", new BlockBoundsCube());
		registerBlockBounds(1, "Skeleton Head", new BlockBoundsCube());
		registerBlockBounds(2, "Zombie Head", new BlockBoundsCube());
		registerBlockBounds(3, "PigZombie Head", new BlockBoundsCube());
		registerBlockBounds(4, "Blaze Head", new BlockBoundsCube());
		registerBlockBounds(5, "Wither Head", new BlockBoundsCube());
		registerBlockBounds(6, "Squid Head", new BlockBoundsCube(0.535F, 0.74F, 0.535F));
		registerBlockBounds(7, "Slime Head", new BlockBoundsCube());
		registerBlockBounds(8, "MagmaCube Head", new BlockBoundsCube());
		registerBlockBounds(9, "Wither Skeleton Head", new BlockBoundsCube());
		registerBlockBounds(10, "Head", new BlockBoundsCube());
		registerBlockBounds(11, "Spider Head", new BlockBoundsCube());
		registerBlockBounds(12, "CaveSpider Head", new BlockBoundsCube());
		registerBlockBounds(13, "Enderman Head", new BlockBoundsCube());
		registerBlockBounds(14, "Ghast Head", new BlockBoundsCube(1.0F, 1.0F, 1.0F));
		registerBlockBounds(15, "Snowman Head", new BlockBoundsCube());
		registerBlockBounds(16, "Sheep Head", new BlockBoundsCube());
		registerBlockBounds(17, "Cow Head", new BlockBoundsCube());
		registerBlockBounds(18, "Red Cow Head", new BlockBoundsCube());
		registerBlockBounds(19, "Pig Head", new BlockBoundsCube());
		registerBlockBounds(20, "Villager Head", new BlockBoundsCube());
		registerBlockBounds(21, "Wolf Head", new BlockBoundsCube(0.5F, 0.625F, 0.5F));
		registerBlockBounds(22, "Chicken Head", new BlockBoundsCube());
		registerBlockBounds(23, "Golem Head", new BlockBoundsCube(0.5F, 0.625F, 0.5F));

		
		
	}
	
	static
	{
		init();
	}
}