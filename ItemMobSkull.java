package ayamitsu.mobskullsplus;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import java.util.List;
import java.util.Iterator;
import org.lwjgl.input.Mouse;

public class ItemMobSkull extends ItemBlock
{
	public ItemMobSkull(int id)
	{
		super(id);
		this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack is, EntityPlayer player, World world, int blockX, int blockY, int blockZ, int face, float hitX, float hitY, float hitZ)
	{
		// ‰º–Ê
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
	                ((TileEntityMobSkull)tileentity).setEntityId(is.getItemDamage());
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
	
	public int getMetadata(int par1)
    {
        return par1;
    }
	
	@Override
	public String getItemNameIS(ItemStack is)
    {
    	int entityId = is.getItemDamage();
        return super.getItemName() + "." + String.valueOf(entityId);
    }
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int par4, boolean isHeld)
	{
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			
			if (player.swingProgressInt == -1 && Mouse.isButtonDown(0) && isHeld)
			{
				ItemStack helmet = player.getCurrentArmor(3);
				
				if (helmet == null)
				{
					ItemStack is1 = ItemStack.copyItemStack(is);
					is1.stackSize = 1;
					player.setCurrentItemOrArmor(3, is1);
					
					if (--is.stackSize <= 0)
					{
						player.inventory.mainInventory[player.inventory.currentItem] = null;
                		MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, is));
					}
				}
			}
		}
		else if (entity instanceof EntityLiving)
		{
			EntityLiving mob = (EntityLiving)entity;
			ItemStack helmet = mob.getCurrentArmor(3);
			
			if (helmet == null)
			{
				ItemStack is1 = ItemStack.copyItemStack(is);
				is1.stackSize = 1;
				mob.setCurrentItemOrArmor(4, is1);
				
				if (--is.stackSize <= 0)
				{
					mob.setCurrentItemOrArmor(4, null);
				}
			}
		}
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int meta)
    {
    	ISkullRenderer renderer = MobSkullsList.getSkullRenderer(meta);
    	return renderer == null ? 0 : renderer.getSpriteIndex(meta);
    }

}