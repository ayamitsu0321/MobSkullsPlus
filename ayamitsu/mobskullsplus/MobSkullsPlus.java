package ayamitsu.mobskullsplus;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import ayamitsu.mobskullsplus.common.BlockBoundsCube;
import ayamitsu.mobskullsplus.common.BlockBoundsRegistry;
import ayamitsu.mobskullsplus.common.BlockMobSkull;
import ayamitsu.mobskullsplus.common.CommonProxy;
import ayamitsu.mobskullsplus.common.ItemMobSkull;
import ayamitsu.mobskullsplus.common.MobSpawnHook;
import ayamitsu.mobskullsplus.common.VillagerTradeSkullHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

@Mod(
	modid = "MobSkullsPlus",
	name = "MobSkullsPlus",
	version = "1.0.4"
)
@NetworkMod(
	clientSideRequired = true,
	serverSideRequired = false
)
public class MobSkullsPlus
{
	@Mod.Instance("MobSkullsPlus")
	public static MobSkullsPlus instance;

	@SidedProxy(clientSide = "ayamitsu.mobskullsplus.client.ClientProxy", serverSide = "ayamitsu.mobskullsplus.common.CommonProxy")
	public static CommonProxy proxy;

	public static Block skull;
	public static int skullId;
	public static int renderId;

	// config
	public static boolean addModSkullToTrade;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		try
		{
			config.load();
			this.skullId = config.getBlock("MobSkull", 3422).getInt();
			this.addModSkullToTrade = config.get("setting", "addModSkullToTrade", true).getBoolean(true);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Error Massage");
		}
		finally
		{
			config.save();
		}
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		this.renderId = proxy.getUniqueRenderId();
		this.skull = new BlockMobSkull(this.skullId).setUnlocalizedName("mobSkull").setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(this.skull, "mobSkull");
		Item.itemsList[this.skull.blockID] = null;
		Item.itemsList[this.skull.blockID] = new ItemMobSkull(this.skull.blockID - 256).setUnlocalizedName("mobSkull");
		LanguageRegistry.instance().addNameForObject(this.skull, "en_US", "MobSkull");
		MinecraftForge.EVENT_BUS.register(new MobSpawnHook());
		proxy.load();

		BlockBoundsRegistry.registerBlockBounds(0, "Creeper Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(1, "Skeleton Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(2, "Zombie Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(3, "PigZombie Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(4, "Blaze Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(5, "Wither Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(6, "Squid Head", new BlockBoundsCube(0.535F, 0.74F, 0.535F));
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
		BlockBoundsRegistry.registerBlockBounds(20, "Villager Head", new BlockBoundsCube(0.5F, 0.625F, 0.5F));
		BlockBoundsRegistry.registerBlockBounds(21, "Wolf Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(22, "Chicken Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(23, "Golem Head", new BlockBoundsCube(0.5F, 0.625F, 0.5F));
		BlockBoundsRegistry.registerBlockBounds(24, "Witch Head", new BlockBoundsCube(0.5F, 0.625F, 0.5F));
		BlockBoundsRegistry.registerBlockBounds(25, "Zombie Villager Head", new BlockBoundsCube(0.5F, 0.625F, 0.5F));
		BlockBoundsRegistry.registerBlockBounds(26, "Siamese Ocelot Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(27, "Red Ocelot Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(28, "Black Ocelot Head", new BlockBoundsCube());
		BlockBoundsRegistry.registerBlockBounds(29, "Ghast Fire Head", new BlockBoundsCube(1.0F, 1.0F, 1.0F));
		BlockBoundsRegistry.registerBlockBounds(30, "Horse Head", new BlockBoundsCube(0.7F, 0.75F, 0.7F));
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if (this.addModSkullToTrade)
		{
			int max = 5;

			for (Integer villagerId : VillagerRegistry.getRegisteredVillagers())
			{
				if (villagerId > max)
				{
					max = villagerId;
				}
			}

			for (int i = 0; i < max; i++)
			{
				if ((i & 1) == 0)
				{
					VillagerRegistry.instance().registerVillageTradeHandler(i, new VillagerTradeSkullHandler());
				}
			}
		}
	}

}