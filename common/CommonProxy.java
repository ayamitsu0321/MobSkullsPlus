package ayamitsu.mobskullsplus.common;

import net.minecraft.src.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void load()
	{
		GameRegistry.registerTileEntity(TileEntityMobSkull.class, "MobSkull");
		
		BlockBoundsRegistry.registerBlockBounds(0, "Creeper Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(1, "Skeleton Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(2, "Zombie Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(3, "PigZombie Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(4, "Blaze Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(5, "Wither Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(6, "Squid Head", new BlockBoundsCube(0.5625F, 0.75F, 0.5625F));// 12 : 0.5625 = 16 : x
		BlockBoundsRegistry.registerBlockBounds(7, "Slime Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(8, "MagmaCube Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(9, "Wither Skeleton Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(10, "Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(11, "Spider Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(12, "CaveSpider Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(13, "Enderman Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(14, "Ghast Head", new BlockBoundsCube(1.0F, 1.0F, 1.0F));
		BlockBoundsRegistry.registerBlockBounds(15, "Snowman Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(16, "Sheep Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(17, "Cow Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(18, "Red Cow Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(19, "Pig Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(20, "Villager Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(21, "Wolf Head", new BlockBoundsCube(0.5F, 0.625F, 0.5F));
		BlockBoundsRegistry.registerBlockBounds(22, "Chicken Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(23, "Golem Head", new BlockBoundsCube(0.5F, 0.625F, 0.5F));
	}
	
	public int getUniqueRenderId()
	{
		return -1;
	}
	
	public World getClientWorld()
	{
		return null;
	}
}
