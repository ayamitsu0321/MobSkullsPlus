package ayamitsu.mobskullsplus.common;

import net.minecraft.src.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void load()
	{
		GameRegistry.registerTileEntity(TileEntityMobSkull.class, "MobSkull");
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
