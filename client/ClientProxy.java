package ayamitsu.mobskullsplus.client;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.common.*;

import net.minecraft.src.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	@Override
	public void load()
	{
		MinecraftForgeClient.preloadTexture(MobSkullsPlus.terrain);
		ClientRegistry.registerTileEntity(TileEntityMobSkull.class, "MobSkull", new TileEntityMobSkullRenderer());
		MinecraftForgeClient.registerItemRenderer(MobSkullsPlus.skull.blockID, new RenderSkullItem());
		RenderingRegistry.registerBlockHandler(new RenderSkullBlock(MobSkullsPlus.renderId));
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