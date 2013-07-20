package ayamitsu.mobskullsplus.client;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import ayamitsu.mobskullsplus.MobSkullsPlus;
import ayamitsu.mobskullsplus.client.model.ModelSkullBiped;
import ayamitsu.mobskullsplus.client.model.ModelSkullChicken;
import ayamitsu.mobskullsplus.client.model.ModelSkullCow;
import ayamitsu.mobskullsplus.client.model.ModelSkullEnderman;
import ayamitsu.mobskullsplus.client.model.ModelSkullGhast;
import ayamitsu.mobskullsplus.client.model.ModelSkullIronGolem;
import ayamitsu.mobskullsplus.client.model.ModelSkullMagmaCube;
import ayamitsu.mobskullsplus.client.model.ModelSkullOcelot;
import ayamitsu.mobskullsplus.client.model.ModelSkullPig;
import ayamitsu.mobskullsplus.client.model.ModelSkullSheep1;
import ayamitsu.mobskullsplus.client.model.ModelSkullSheep2;
import ayamitsu.mobskullsplus.client.model.ModelSkullSlime;
import ayamitsu.mobskullsplus.client.model.ModelSkullSpider;
import ayamitsu.mobskullsplus.client.model.ModelSkullVillager;
import ayamitsu.mobskullsplus.client.model.ModelSkullWitch;
import ayamitsu.mobskullsplus.client.model.ModelSkullWolf;
import ayamitsu.mobskullsplus.client.model.ModelSkullZombieVillager;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererChicken;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererCow;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererCube;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererDefault;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererMulti;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererOcelot;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererPig;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererSheep;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererSquid;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererVillager;
import ayamitsu.mobskullsplus.client.renderer.SkullRendererWolf;
import ayamitsu.mobskullsplus.common.CommonProxy;
import ayamitsu.mobskullsplus.common.TileEntityMobSkull;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void load()
	{
		ClientRegistry.registerTileEntity(TileEntityMobSkull.class, "MobSkull", new TileEntityMobSkullRenderer());
		MinecraftForgeClient.registerItemRenderer(MobSkullsPlus.skull.blockID, new RenderSkullItem());
		RenderingRegistry.registerBlockHandler(new RenderSkullBlock(MobSkullsPlus.renderId));

		RendererRegistry.registerSkullRenderer(0, ((SkullRendererDefault)new SkullRendererDefault("creeper", new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/creeper/creeper.png"));
		RendererRegistry.registerSkullRenderer(1, ((SkullRendererDefault)new SkullRendererDefault("skeleton", new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/skeleton/skeleton.png"));
		RendererRegistry.registerSkullRenderer(2, ((SkullRendererDefault)new SkullRendererDefault("zombie", new ModelSkeletonHead(0, 0, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/zombie/zombie.png"));
		RendererRegistry.registerSkullRenderer(3, ((SkullRendererDefault)new SkullRendererDefault("pigzombie", new ModelSkullBiped(0.0F, 0.0F, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/zombie_pigman.png"));
		RendererRegistry.registerSkullRenderer(4, ((SkullRendererDefault)new SkullRendererDefault("blaze", new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/blaze.png"));
		RendererRegistry.registerSkullRenderer(5, ((SkullRendererDefault)new SkullRendererDefault("wither", new ModelSkeletonHead(0, 0, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/wither/wither.png"));
		RendererRegistry.registerSkullRenderer(6, ((SkullRendererSquid)new SkullRendererSquid("squid")).setTexture("textures/entity/squid.png"));
		RendererRegistry.registerSkullRenderer(7, ((SkullRendererMulti)new SkullRendererMulti("slime", new ModelSkullSlime(16), new ModelSkullSlime(0)).setSize(0.5F, 0.5F, 0.5F)).setTexture("textures/entity/slime/slime.png", "textures/entity/slime/slime.png").setAlpha(false, true));
		RendererRegistry.registerSkullRenderer(8, ((SkullRendererDefault)new SkullRendererDefault("magmacube", new ModelSkullMagmaCube())).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/slime/magmacube.png"));
		RendererRegistry.registerSkullRenderer(9, ((SkullRendererDefault)new SkullRendererDefault("skeleton_wither", new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/skeleton/wither_skeleton.png"));
		RendererRegistry.registerSkullRenderer(10, ((SkullRendererDefault)new SkullRendererDefault("char", new ModelSkeletonHead(0, 0, 64, 32))).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/steve.png"));
		RendererRegistry.registerSkullRenderer(11, ((SkullRendererMulti)new SkullRendererMulti("spider", new ModelSkullSpider(), new ModelSkullSpider()).setSize(0.5F, 0.5F, 0.5F)).setTexture("textures/entity/spider/spider.png", "textures/entity/spider_eyes.png").setAlphaSpecial(false, true));
		RendererRegistry.registerSkullRenderer(12, ((SkullRendererMulti)new SkullRendererMulti("cavespider", new ModelSkullSpider(), new ModelSkullSpider()).setSize(0.5F, 0.5F, 0.5F)).setTexture("textures/entity/spider/cave_spider.png", "textures/entity/spider_eyes.png").setAlphaSpecial(false, true));
		RendererRegistry.registerSkullRenderer(13, ((SkullRendererMulti)new SkullRendererMulti("enderman", new ModelSkullEnderman(), new ModelSkullEnderman()).setSize(0.5F, 0.5F, 0.5F)).setTexture("textures/entity/enderman/enderman.png", "textures/entity/enderman/enderman_eyes.png").setAlphaSpecial(false, true));
		RendererRegistry.registerSkullRenderer(14, ((SkullRendererCube)new SkullRendererCube("ghast", new ModelSkullGhast())).setTexture("textures/entity/ghast/ghast.png"));
		RendererRegistry.registerSkullRenderer(15, ((SkullRendererDefault)new SkullRendererDefault("snowman", new ModelSkeletonHead(0, 0, 64, 64))).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/snowman.png"));
		RendererRegistry.registerSkullRenderer(16, ((SkullRendererSheep)new SkullRendererSheep("sheep", new ModelSkullSheep1(), new ModelSkullSheep2()).setSize(0.5F, 0.5F, 0.5F)).setTexture("textures/entity/sheep/sheep_fur.png", "textures/entity/sheep/sheep.png"));
		RendererRegistry.registerSkullRenderer(17, ((SkullRendererCow)new SkullRendererCow("cow", new ModelSkullCow())).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/cow/cow.png"));
		RendererRegistry.registerSkullRenderer(18, ((SkullRendererCow)new SkullRendererCow("mooshroom", new ModelSkullCow())).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/cow/mooshroom.png"));
		RendererRegistry.registerSkullRenderer(19, ((SkullRendererPig)new SkullRendererPig("pig", new ModelSkullPig(), new ModelSkullPig(0.5F)).setSize(0.5F, 0.5F, 0.5F)).setTexture("textures/entity/pig/pig.png", "textures/entity/pig/pig.png"));
		RendererRegistry.registerSkullRenderer(20, ((SkullRendererVillager)new SkullRendererVillager("villager", new ModelSkullVillager(0.0F))).setSize(0.5F, 0.625F, 0.5F).setTextureFile("textures/entity/villager/villager.png"));
		RendererRegistry.registerSkullRenderer(21, ((SkullRendererDefault)new SkullRendererWolf("wolf", new ModelSkullWolf()).setSize(0.5F, 0.5F, 0.5F)).setScale(1.125F).setTexture("textures/entity/wolf/wolf.png"));
		RendererRegistry.registerSkullRenderer(22, ((SkullRendererDefault)new SkullRendererChicken("chicken", new ModelSkullChicken())).setSize(0.5F, 0.5F, 0.5F).setTexture("textures/entity/chicken.png"));
		RendererRegistry.registerSkullRenderer(23, ((SkullRendererVillager)new SkullRendererVillager("irongolem", new ModelSkullIronGolem())).setSize(0.5F, 0.625F, 0.5F).setTextureFile("textures/entity/iron_golem.png"));
		RendererRegistry.registerSkullRenderer(24, ((SkullRendererVillager)new SkullRendererVillager("witch", new ModelSkullWitch(0.0F))).setSize(0.5F, 0.625F, 0.5F).setTextureFile("textures/entity/witch.png"));
		RendererRegistry.registerSkullRenderer(25, ((SkullRendererVillager)new SkullRendererVillager("zombie_villager", new ModelSkullZombieVillager())).setSize(0.5F, 0.625F, 0.5F).setTextureFile("textures/entity/zombie/zombie_villager.png"));
		RendererRegistry.registerSkullRenderer(26, ((SkullRendererDefault)new SkullRendererOcelot("cat_siamese", new ModelSkullOcelot()).setSize(0.5F, 0.5F, 0.5F)).setScale(1.125F).setTexture("textures/entity/cat/siamese.png"));
		RendererRegistry.registerSkullRenderer(27, ((SkullRendererDefault)new SkullRendererOcelot("cat_red", new ModelSkullOcelot()).setSize(0.5F, 0.5F, 0.5F)).setScale(1.125F).setTexture("textures/entity/cat/red.png"));
		RendererRegistry.registerSkullRenderer(28, ((SkullRendererDefault)new SkullRendererOcelot("cat_black", new ModelSkullOcelot()).setSize(0.5F, 0.5F, 0.5F)).setScale(1.125F).setTexture("textures/entity/cat/black.png"));
		RendererRegistry.registerSkullRenderer(29, ((SkullRendererCube)new SkullRendererCube("ghast_fire", new ModelSkullGhast())).setTexture("textures/entity/ghast/ghast_shooting.png"));
	}

	@Override
	public int getUniqueRenderId()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
}