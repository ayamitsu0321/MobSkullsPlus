package ayamitsu.mobskullsplus.common;

import ayamitsu.mobskullsplus.*;

import net.minecraft.src.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.List;
import java.util.Iterator;
import cpw.mods.fml.common.network.PacketDispatcher;
//import org.lwjgl.input.Mouse;

public class ItemMobSkull extends ItemBlock
{
	public ItemMobSkull(int id)
	{
		super(id);
		this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int blockX, int blockY, int blockZ, int face, float hitX, float hitY, float hitZ)
	{
		// 下面
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

			if (!player.func_82247_a(blockX, blockY, blockZ, face, is))
			{
				return false;
			}
			else if (!Block.blocksList[this.getBlockID()].canPlaceBlockAt(world, blockX, blockY, blockZ))
			{
				return false;
			}
			else
			{
				world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.getBlockID(), face);
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
		// 下面
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

			if (!player.func_82247_a(blockX, blockY, blockZ, face, is))
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
	public String getItemNameIS(ItemStack is)
    {
    	int meta = is.getItemDamage();
        return super.getItemName() + "." + String.valueOf(meta);
    }
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int par4, boolean isHeld)
	{
		// マルチだとあれなのよね。
		// マウスの入力があれだから、どうにかPacketでやらないと
		// どうしましょ
		
		if (!world.isRemote || is == null)
		{
			return;
		}
		
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			
			// 右クリック, というより腕を振る
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
    public int getIconFromDamage(int meta)
    {
    	ayamitsu.mobskullsplus.client.ISkullRenderer renderer = ayamitsu.mobskullsplus.client.RendererRegistry.getSkullRenderer(meta);
    	return renderer == null ? 0 : renderer.getSpriteIndex(meta);
    }
	
	@Override
	public String getTextureFile()
	{
		return MobSkullsPlus.terrain;
	}

}