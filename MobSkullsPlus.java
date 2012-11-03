package ayamitsu.mobskullsplus;

import ayamitsu.mobskullsplus.renderer.*;
import ayamitsu.mobskullsplus.model.*;

import net.minecraft.src.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.client.registry.ClientRegistry;

@Mod(
	modid = "MobSkullsPlus",
	name = "MobSkullsPlus",
	version = "1.0.0"
)
@NetworkMod(
	clientSideRequired = true,
	serverSideRequired = false,
	packetHandler = PacketHandler.class
)
public class MobSkullsPlus
{
	@Mod.Instance("MobSkullsPlus")
	public static MobSkullsPlus instance;
	
	@SidedProxy(clientSide = "ayamitsu.mobskullsplus.client.ClientProxy", serverSide = "ayamitsu.mobskullsplus.client.ClientProxy")
	public static CommonProxy proxy;
	
	public static Block skull;
	public static Item skullItem;
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		this.skull = new BlockMobSkull(3422).setBlockName("mobSkull");
		this.skullItem = new ItemMobSkull(12455, this.skull);
		GameRegistry.registerBlock(this.skull);
		ClientRegistry.registerTileEntity(TileEntityMobSkull.class, "MobSkull", new TileEntityMobSkullRenderer());
		LanguageRegistry.instance().addNameForObject(this.skull, "en_US", "MobSkull");
		ModelBase defaultHead = new ModelSkeletonHead(0, 0, 64, 32);
		ModelBase specilaHead = new ModelSkeletonHead(0, 0, 64, 64);
		MobSkullsList.registerSkullRenderer(50, ((SkullRendererDefault)new SkullRendererDefault(defaultHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/creeper.png"));
		MobSkullsList.registerSkullRenderer(51, ((SkullRendererDefault)new SkullRendererDefault(defaultHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/skeleton.png"));
		MobSkullsList.registerSkullRenderer(54, ((SkullRendererDefault)new SkullRendererDefault(specilaHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/zombie.png"));
		MobSkullsList.registerSkullRenderer(57, ((SkullRendererDefault)new SkullRendererDefault(specilaHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/pigzombie.png"));
		MobSkullsList.registerSkullRenderer(61, ((SkullRendererDefault)new SkullRendererDefault(defaultHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/fire.png"));
		MobSkullsList.registerSkullRenderer(64, ((SkullRendererDefault)new SkullRendererDefault(specilaHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/wither.png"));
		MobSkullsList.registerSkullRenderer(94, new SkullRendererSquid());
		MobSkullsList.registerSkullRenderer(55, new SkullRendererSlime());
		MobSkullsList.registerSkullRenderer(62, ((SkullRendererDefault)new SkullRendererDefault(new ModelSkullMagmaCube())).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/lava.png"));
	}
}