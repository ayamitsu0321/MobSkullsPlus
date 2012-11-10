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
		
		//MobSkullsList.init();
	}
}