package ayamitsu.mobskullsplus.client.registry;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.client.*;
import ayamitsu.mobskullsplus.client.model.*;
import ayamitsu.mobskullsplus.client.renderer.*;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ModelSkeletonHead;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.client.SpriteHelper;

import java.util.Map;
import java.util.HashMap;

public final class RendererRegistry
{
	private static boolean hasInit = false;
	private static Map<Integer, ISkullRenderer> rendererMap;
	
	public static void registerSkullRenderer(int id, String name, ISkullRenderer renderer)
	{
		LanguageRegistry.instance().addNameForObject(new ItemStack(MobSkullsPlus.skull.blockID, 1, id), "en_US", name);
		rendererMap.put(Integer.valueOf(id), renderer);
	}
	
	public static ISkullRenderer getSkullRenderer(int id)
	{
		return rendererMap.get(Integer.valueOf(id));
	}
	
	public static boolean contains(int id)
	{
		return  rendererMap.containsKey(Integer.valueOf(id));
	}
	
	public static int addOverride(String tex)
	{
		return RenderingRegistry.addTextureOverride(MobSkullsPlus.terrain, tex);
	}
	
	public static Map getMap()
	{
		return rendererMap;
	}
	
	private static void init()
	{
		if (hasInit)
		{
			return;
		}
		
		hasInit = true;
		rendererMap = new HashMap<Integer, ISkullRenderer>();
		SpriteHelper.registerSpriteMapForFile(MobSkullsPlus.terrain, 
			"0000000000000000" +
			"0000000011111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111" +
			"1111111111111111"
		);
		
		registerSkullRenderer(0, "Creeper Head", ((SkullRendererDefault)new SkullRendererDefault(4, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/creeper.png"));
		registerSkullRenderer(1, "Skeleton Head", ((SkullRendererDefault)new SkullRendererDefault(0, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/skeleton.png"));
		registerSkullRenderer(2, "Zombie Head", ((SkullRendererDefault)new SkullRendererDefault(2, new ModelSkeletonHead(0, 0, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/zombie.png"));
		registerSkullRenderer(3, "PigZombie Head", ((SkullRendererDefault)new SkullRendererDefault(7, new ModelSkullBiped(0.0F, 0.0F, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/pigzombie.png"));
		registerSkullRenderer(4, "Blaze Head", ((SkullRendererDefault)new SkullRendererDefault(11, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/fire.png"));
		registerSkullRenderer(5, "Wither Head", ((SkullRendererDefault)new SkullRendererDefault(22, new ModelSkeletonHead(0, 0, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/wither.png"));
		registerSkullRenderer(6, "Squid Head", new SkullRendererSquid());
		registerSkullRenderer(7, "Slime Head", ((SkullRendererMulti)new SkullRendererMulti(6, new ModelSkullSlime(16), new ModelSkullSlime(0)).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/slime.png", "/mob/slime.png").setAlpha(false, true));
		registerSkullRenderer(8, "MagmaCube Head", ((SkullRendererDefault)new SkullRendererDefault(12, new ModelSkullMagmaCube())).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/lava.png"));
		registerSkullRenderer(9, "Wither Skeleton Head", ((SkullRendererDefault)new SkullRendererDefault(1, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/skeleton_wither.png"));
		registerSkullRenderer(10, "Head", ((SkullRendererDefault)new SkullRendererDefault(3, new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/char.png"));
		registerSkullRenderer(11, "Spider Head", ((SkullRendererMulti)new SkullRendererMulti(9, new ModelSkullSpider(), new ModelSkullSpider()).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/spider.png", "/mob/spider_eyes.png").setAlphaSpecial(false, true));
		registerSkullRenderer(12, "CaveSpider Head", ((SkullRendererMulti)new SkullRendererMulti(10, new ModelSkullSpider(), new ModelSkullSpider()).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/cavespider.png", "/mob/spider_eyes.png").setAlphaSpecial(false, true));
		registerSkullRenderer(13, "Enderman Head", ((SkullRendererMulti)new SkullRendererMulti(8, new ModelSkullEnderman(), new ModelSkullEnderman()).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/enderman.png", "/mob/enderman_eyes.png").setAlphaSpecial(false, true));
		registerSkullRenderer(14, "Ghast Head", ((SkullRendererCube)new SkullRendererCube(13, new ModelSkullGhast())).setTexture("/mob/ghast.png"));
		registerSkullRenderer(15, "Snowman Head", ((SkullRendererDefault)new SkullRendererDefault(14, new ModelSkeletonHead(0, 0, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/snowman.png"));
		registerSkullRenderer(16, "Sheep Head", ((SkullRendererSheep)new SkullRendererSheep(17, new ModelSkullSheep1(), new ModelSkullSheep2()).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/sheep_fur.png", "/mob/sheep.png"));
		registerSkullRenderer(17, "Cow Head", ((SkullRendererCow)new SkullRendererCow(18, new ModelSkullCow())).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/cow.png"));
		registerSkullRenderer(18, "Red Cow Head", ((SkullRendererCow)new SkullRendererCow(19, new ModelSkullCow())).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/redcow.png"));
		registerSkullRenderer(19, "Pig Head", ((SkullRendererPig)new SkullRendererPig(20, new ModelSkullPig(), new ModelSkullPig(0.5F)).setSize(0.5F, 0.5F, 0.5F)).setTexture("/mob/pig.png", "/mob/pig.png"));
		registerSkullRenderer(20, "Villager Head", ((SkullRendererVillager)new SkullRendererVillager(21, new ModelSkullVillager(0.0F))).setSize(0.5F, 0.625F, 0.5F).setTextureFile("/mob/villager/villager.png"));
		registerSkullRenderer(21, "Wolf Head", ((SkullRendererDefault)new SkullRendererWolf(15, new ModelSkullWolf()).setSize(0.5F, 0.5F, 0.5F)).setScale(1.125F).setTexture("/mob/wolf.png"));
		registerSkullRenderer(22, "Chicken Head", ((SkullRendererDefault)new SkullRendererChicken(16, new ModelSkullChicken())).setSize(0.5F, 0.5F, 0.5F).setTexture("/mob/chicken.png"));
		registerSkullRenderer(23, "Golem Head", ((SkullRendererVillager)new SkullRendererVillager(23, new ModelSkullIronGolem())).setSize(0.5F, 0.625F, 0.5F).setTextureFile("/mob/villager_golem.png"));
	}
	
	static
	{
		init();
	}
}