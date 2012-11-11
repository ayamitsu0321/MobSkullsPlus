package ayamitsu.mobskullsplus.common;

import net.minecraft.src.*;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.network.IPacketHandler;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager network, Packet250CustomPayload packet, Player player)
	{
		if (!packet.channel.equals("mobskullsplus"))
		{
			return;
		}
		
		if (packet.data[0] == 1 && player instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)player;
			this.onPlayerLeftClick(entityplayer);
		}
	}
	
	public void onPlayerLeftClick(EntityPlayer player)
	{
		ItemStack helmet = player.getCurrentArmor(3);
		ItemStack is = player.inventory.getCurrentItem();
		
		if (helmet == null && is != null)
		{
			ItemStack is1 = is.splitStack(1);
			player.setCurrentItemOrArmor(3, is1);
			
			if (is.stackSize <= 0)
			{
				player.inventory.mainInventory[player.inventory.currentItem] = null;
			}
		}
	}
}