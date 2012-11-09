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
		GameRegistry.registerBlock(this.skull);
		Item.itemsList[this.skull.blockID] = null;
		Item.itemsList[this.skull.blockID] = new ItemMobSkull(this.skull.blockID - 256).setItemName("mobSkull");
		ClientRegistry.registerTileEntity(TileEntityMobSkull.class, "MobSkull", new TileEntityMobSkullRenderer());
		LanguageRegistry.instance().addNameForObject(this.skull, "en_US", "MobSkull");
		MinecraftForgeClient.registerItemRenderer(this.skull.blockID, new RenderSkullItem());
		RenderingRegistry.registerBlockHandler(new RenderSkullBlock(this.renderId));
		
		MobSkullsList.registerSkullRenderer(0, "Creeper Head", ((SkullRendererDefault)new SkullRendererDefault(4, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/creeper.png"));
		MobSkullsList.registerSkullRenderer(1, "Skeleton Head", ((SkullRendererDefault)new SkullRendererDefault(0, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/skeleton.png"));
		MobSkullsList.registerSkullRenderer(2, "Zombie Head", ((SkullRendererDefault)new SkullRendererDefault(2, new ModelSkeletonHead(0, 0, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/zombie.png"));
		MobSkullsList.registerSkullRenderer(3, "PigZombie Head", ((SkullRendererDefault)new SkullRendererDefault(7, new ModelSkullBiped(0.0F, 0.0F, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/pigzombie.png"));
		MobSkullsList.registerSkullRenderer(4, "Blaze Head", ((SkullRendererDefault)new SkullRendererDefault(11, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/fire.png"));
		MobSkullsList.registerSkullRenderer(5, "Wither Head", ((SkullRendererDefault)new SkullRendererDefault(22, new ModelSkeletonHead(0, 0, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/wither.png"));
		MobSkullsList.registerSkullRenderer(6, "Squid Head", new SkullRendererSquid());
		//MobSkullsList.registerSkullRenderer(7, "Slime Head", new SkullRendererSlime());
		MobSkullsList.registerSkullRenderer(7, "Slime Head", ((SkullRendererMulti)new SkullRendererMulti(6, new ModelSkullSlime(16), new ModelSkullSlime(0)).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/slime.png", "/mob/slime.png").setAlpha(false, true));
		MobSkullsList.registerSkullRenderer(8, "MagmaCube Head", ((SkullRendererDefault)new SkullRendererDefault(12, new ModelSkullMagmaCube())).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/lava.png"));
		MobSkullsList.registerSkullRenderer(9, "Wither Skeleton Head", ((SkullRendererDefault)new SkullRendererDefault(1, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/skeleton_wither.png"));
		MobSkullsList.registerSkullRenderer(10, "Head", ((SkullRendererDefault)new SkullRendererDefault(3, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/char.png"));
		MobSkullsList.registerSkullRenderer(11, "Spider Head", ((SkullRendererMulti)new SkullRendererMulti(9, new ModelSkullSpider(), new ModelSkullSpider()).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/spider.png", "/mob/spider_eyes.png").setAlphaSpecial(false, true));
		MobSkullsList.registerSkullRenderer(12, "CaveSpider Head", ((SkullRendererMulti)new SkullRendererMulti(10, new ModelSkullSpider(), new ModelSkullSpider()).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/cavespider.png", "/mob/spider_eyes.png").setAlphaSpecial(false, true));
		MobSkullsList.registerSkullRenderer(13, "Enderman Head", ((SkullRendererMulti)new SkullRendererMulti(8, new ModelSkullEnderman(), new ModelSkullEnderman()).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/enderman.png", "/mob/enderman_eyes.png").setAlphaSpecial(false, true));
		MobSkullsList.registerSkullRenderer(14, "Ghast Head", ((SkullRendererCube)new SkullRendererCube(13, new ModelSkullGhast())).setTexture("/mob/ghast.png"));
		MobSkullsList.registerSkullRenderer(15, "Snowman Head", ((SkullRendererDefault)new SkullRendererDefault(14, new ModelSkeletonHead(0, 0, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/snowman.png"));
		MobSkullsList.registerSkullRenderer(16, "Sheep Head", ((SkullRendererSheep)new SkullRendererSheep(17, new ModelSkullSheep1(), new ModelSkullSheep2()).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/sheep_fur.png", "/mob/sheep.png"));
		MobSkullsList.registerSkullRenderer(17, "Cow Head", ((SkullRendererCow)new SkullRendererCow(18, new ModelSkullCow())).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/cow.png"));
		MobSkullsList.registerSkullRenderer(18, "Red Cow Head", ((SkullRendererCow)new SkullRendererCow(19, new ModelSkullCow())).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/redcow.png"));
		//MobSkullsList.registerSkullRenderer(17, "Cow Head", ((SkullRendererCow)new SkullRendererCow(18)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/cow.png"));
		//MobSkullsList.registerSkullRenderer(18, "Red Cow Head", ((SkullRendererCow)new SkullRendererCow(19)).setSize(0.5F, 0.5F, 0.5F).setTextureFile("/mob/redcow.png"));
		MobSkullsList.registerSkullRenderer(19, "Pig Head", ((SkullRendererPig)new SkullRendererPig(20, new ModelSkullPig(), new ModelSkullPig(0.5F)).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/pig.png", "/mob/pig.png"));
		MobSkullsList.registerSkullRenderer(20, "Villager Head", ((SkullRendererVillager)new SkullRendererVillager(21, new ModelSkullVillager(0.0F))).setSize(0.5F, 0.625F, 0.5F).setTextureFile("/mob/villager/villager.png"));
		MobSkullsList.registerSkullRenderer(21, "Wolf Head", ((SkullRendererDefault)new SkullRendererWolf(15, new ModelSkullWolf()).setSize(0.5F, 0.5F, 0.5F)).setScale(1.125F).setTexture("/mob/wolf.png"));
		MobSkullsList.registerSkullRenderer(22, "Chicken Head", ((SkullRendererDefault)new SkullRendererChicken(16, new ModelSkullChicken())).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/chicken.png"));
	}
}