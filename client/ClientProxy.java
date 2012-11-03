package ayamitsu.mobskullsplus.client;

import ayamitsu.mobskullsplus.CommonProxy;
import net.minecraft.src.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ClientProxy extends CommonProxy
{
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
}