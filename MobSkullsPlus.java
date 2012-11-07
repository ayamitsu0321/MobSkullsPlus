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
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.BitSet;

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
	public static int renderId;
	public static final String terrain = "/ayamitsu/mobskullsplus/terrain.png";
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		MinecraftForgeClient.preloadTexture(terrain);
		this.renderId = RenderingRegistry.getNextAvailableRenderId();
		this.skull = new BlockMobSkull(3422).setBlockName("mobSkull").setCreativeTab(CreativeTabs.tabDecorations);
		//this.skullItem = new ItemMobSkull(12455, this.skull);
		GameRegistry.registerBlock(this.skull);
		Item.itemsList[this.skull.blockID] = null;
		Item.itemsList[this.skull.blockID] = new ItemMobSkull(this.skull.blockID - 256).setItemName("mobSkull");
		ClientRegistry.registerTileEntity(TileEntityMobSkull.class, "MobSkull", new TileEntityMobSkullRenderer());
		LanguageRegistry.instance().addNameForObject(this.skull, "en_US", "MobSkull");
		MinecraftForgeClient.registerItemRenderer(this.skull.blockID, new RenderSkullItem());
		RenderingRegistry.registerBlockHandler(new RenderSkullHandler(this.renderId));
		
		ModelBase defaultHead = new ModelSkeletonHead(0, 0, 64, 32);
		ModelBase specilaHead = new ModelSkeletonHead(0, 0, 64, 64);
		ModelBase multiHead = new ModelSkullSpider();
		ModelBase endermanHead = new ModelSkullEnderman();
		ModelBase bipedHead = new ModelSkullBiped(0.0F, 0.0F, 64, 64);
		
		MobSkullsList.registerSkullRenderer(0, "Creeper Head", ((SkullRendererDefault)new SkullRendererDefault(4, defaultHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/creeper.png"));
		MobSkullsList.registerSkullRenderer(1, "Skeleton Head", ((SkullRendererDefault)new SkullRendererDefault(0, defaultHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/skeleton.png"));
		MobSkullsList.registerSkullRenderer(2, "Zombie Head", ((SkullRendererDefault)new SkullRendererDefault(2, specilaHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/zombie.png"));
		MobSkullsList.registerSkullRenderer(3, "PigZombie Head", ((SkullRendererDefault)new SkullRendererDefault(7, bipedHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/pigzombie.png"));
		MobSkullsList.registerSkullRenderer(4, "Blaze Head", ((SkullRendererDefault)new SkullRendererDefault(11, defaultHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/fire.png"));
		MobSkullsList.registerSkullRenderer(5, "Wither Head", ((SkullRendererDefault)new SkullRendererDefault(22, specilaHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/wither.png"));
		MobSkullsList.registerSkullRenderer(6, "Squid Head", new SkullRendererSquid());
		MobSkullsList.registerSkullRenderer(7, "Slime Head", new SkullRendererSlime());
		MobSkullsList.registerSkullRenderer(8, "MagmaCube Head", ((SkullRendererDefault)new SkullRendererDefault(12, new ModelSkullMagmaCube())).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/lava.png"));
		MobSkullsList.registerSkullRenderer(9, "Wither Skeleton Head", ((SkullRendererDefault)new SkullRendererDefault(1, defaultHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/skeleton_wither.png"));
		MobSkullsList.registerSkullRenderer(10, "Head", ((SkullRendererDefault)new SkullRendererDefault(3, defaultHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/char.png"));
		MobSkullsList.registerSkullRenderer(11, "Spider Head", ((SkullRendererMulti)new SkullRendererMulti(9, multiHead, multiHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/spider.png", "/mob/spider_eyes.png").setAlphaSpecial(true));
		MobSkullsList.registerSkullRenderer(12, "CaveSpider Head", ((SkullRendererMulti)new SkullRendererMulti(10, multiHead, multiHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/cavespider.png", "/mob/spider_eyes.png").setAlphaSpecial(true));
		MobSkullsList.registerSkullRenderer(13, "Enderman Head", ((SkullRendererMulti)new SkullRendererMulti(8, endermanHead, endermanHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/enderman.png", "/mob/enderman_eyes.png").setAlphaSpecial(true));
		MobSkullsList.registerSkullRenderer(14, "Ghast Head", ((SkullRendererCube)new SkullRendererCube(13, new ModelSkullGhast())).setTextureFile("/mob/ghast.png"));
		MobSkullsList.registerSkullRenderer(15, "Snowman Head", ((SkullRendererDefault)new SkullRendererDefault(14, specilaHead)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/snowman.png"));
		MobSkullsList.registerSkullRenderer(16, "Sheep Head", ((SkullRendererSheep)new SkullRendererSheep(17)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/sheep_fur.png", "/mob/sheep.png"));
		MobSkullsList.registerSkullRenderer(17, "Cow Head", ((SkullRendererDefault)new SkullRendererDefault(18, new ModelSkullCow())).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/cow.png"));
	}
}