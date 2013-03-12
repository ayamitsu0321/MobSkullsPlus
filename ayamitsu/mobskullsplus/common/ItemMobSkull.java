package ayamitsu.mobskullsplus.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ayamitsu.mobskullsplus.MobSkullsPlus;
import ayamitsu.mobskullsplus.client.ISkullRenderer;
import ayamitsu.mobskullsplus.client.RendererRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMobSkull extends ItemBlock
{
	protected Map<Integer, Icon> iconMap;

	public ItemMobSkull(int id)
	{
		super(id);
		this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int blockX, int blockY, int blockZ, int face, float hitX, float hitY, float hitZ)
	{
		// ����
		if (face == 0 || !world.getBlockMaterial(blockX, blockY, blockZ).isSolid())
		{
			return false;
		}
		else
		{
			if (face == 1)
			{
				++blockY;
			}

			if (face == 2)
			{
				--blockZ;
			}

			if (face == 3)
			{
				++blockZ;
			}

			if (face == 4)
			{
				--blockX;
			}

			if (face == 5)
			{
				++blockX;
			}

			if (!player.canPlayerEdit(blockX, blockY, blockZ, face, is))
			{
				return false;
			}
			else if (!Block.blocksList[this.getBlockID()].canPlaceBlockAt(world, blockX, blockY, blockZ))
			{
				return false;
			}
			else
			{
				world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.getBlockID(), face, 3);
				int rot = 0;

				if (face == 1)
				{
					rot = MathHelper.floor_double((double)(player.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
				}

				TileEntity tileentity = world.getBlockTileEntity(blockX, blockY, blockZ);

				if (tileentity instanceof TileEntityMobSkull)
				{
	                ((TileEntityMobSkull)tileentity).setSkullType(is.getItemDamage());
	                ((TileEntityMobSkull)tileentity).setSkullRotation(rot);
				}

				if (!player.capabilities.isCreativeMode)
				{
					--is.stackSize;
				}

				return true;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean canPlaceItemBlockOnSide(World world, int blockX, int blockY, int blockZ, int face, EntityPlayer player, ItemStack is)
	{
		// ����
		if (face == 0 || !world.getBlockMaterial(blockX, blockY, blockZ).isSolid() || !ayamitsu.mobskullsplus.client.RendererRegistry.contains(is.getItemDamage()))
		{
			return false;
		}
		else
		{
			if (face == 1)
			{
				++blockY;
			}

			if (face == 2)
			{
				--blockZ;
			}

			if (face == 3)
			{
				++blockZ;
			}

			if (face == 4)
			{
				--blockX;
			}

			if (face == 5)
			{
				++blockX;
			}

			if (!player.canPlayerEdit(blockX, blockY, blockZ, face, is))
			{
				return false;
			}
			else if (!Block.blocksList[this.getBlockID()].canPlaceBlockAt(world, blockX, blockY, blockZ))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}

	@Override
	public int getMetadata(int par1)
    {
        return par1;
    }

	@Override
	public String getUnlocalizedName(ItemStack is)
    {
    	int meta = is.getItemDamage();
        return super.getUnlocalizedName() + "." + String.valueOf(meta);
    }

	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int par4, boolean isHeld)
	{
		if (!world.isRemote || is == null)
		{
			return;
		}

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;

			// swing arm , mouse left click , held this , equip this
			if (player.swingProgressInt == -1 && org.lwjgl.input.Mouse.isButtonDown(0) && isHeld)
			{
				byte[] data = new byte[1];
				data[0] = 1;
				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload("mobskullsplus", data));
			}
		}
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack is, EntityLiving entityliving)
	{
		if (!(entityliving instanceof EntityPlayer))
		{
			ItemStack helmet = entityliving.getCurrentArmor(3);

			if (helmet == null)
			{
				ItemStack is1 = ItemStack.copyItemStack(is);
				is1.stackSize = 1;
				entityliving.setCurrentItemOrArmor(4, is1);
				--is.stackSize;

				return true;
			}
		}

		return false;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta)
    {
    	return this.iconMap.get(Integer.valueOf(meta));
    }

	/*@Override
	public String getTextureFile()
	{
		return MobSkullsPlus.terrain;
	}*/

	@SideOnly(Side.CLIENT)
    public void func_94581_a(IconRegister par1IconRegister)
	{
		this.iconMap = new HashMap<Integer, Icon>();

		for (Map.Entry<Integer, ISkullRenderer> entry : RendererRegistry.getMap().entrySet())
		{
			Icon icon = par1IconRegister.func_94245_a("ayamitsu/mobskullsplus:" + entry.getValue().getIconPath());
			this.iconMap.put(entry.getKey(), icon);
		}
	}

}