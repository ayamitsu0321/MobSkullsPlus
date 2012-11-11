package ayamitsu.mobskullsplus;

import ayamitsu.mobskullsplus.common.*;

import net.minecraft.src.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import java.util.logging.Level;
import java.util.BitSet;

@Mod(
	modid = "MobSkullsPlus",
	name = "MobSkullsPlus",
	version = "1.0.0"
)
@NetworkMod(
	clientSideRequired = true,
	serverSideRequired = false,
	packetHandler = ayamitsu.mobskullsplus.common.PacketHandler.class
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
	public static final String terrain = "/ayamitsu/mobskullsplus/terrain.png";
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		try
		{
			config.load();
			int def = 3422;
			Property prop = config.getBlock("MobSkull", def);
			this.skullId = prop.getInt(def);
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
	
	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		//MinecraftForgeClient.preloadTexture(terrain);
		this.renderId = proxy.getUniqueRenderId();
		this.skull = new BlockMobSkull(this.skullId).setBlockName("mobSkull").setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(this.skull);
		Item.itemsList[this.skull.blockID] = null;
		Item.itemsList[this.skull.blockID] = new ItemMobSkull(this.skull.blockID - 256).setItemName("mobSkull");
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
		
		/*GameRegistry.addRecipe(new ItemStack(this.skull.blockID, 1, 20),
			new Object[] {
				"##",
				'#', Block.dirt
			}
		);*/
		//MobSkullsList.init();
	}
}