package ayamitsu.mobskullsplus;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.List;
import java.util.Iterator;

public class ItemMobSkull extends Item
{
	private final Block modelBlock;
	
	public ItemMobSkull(int id, Block block)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.modelBlock = block;
	}
	
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int blockX, int blockY, int blockZ, int face, float par8, float par9, float par10)
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
			else if (!this.modelBlock.canPlaceBlockAt(world, blockX, blockY, blockZ))
			{
				return false;
			}
			else
			{
				world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.modelBlock.blockID, face);
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
				
				--is.stackSize;
				return true;
			}
		}
	}
	
	public void getSubItems(int id, CreativeTabs tab, List list)
    {
    	for (Iterator iterator = MobSkullsList.getMap().keySet().iterator(); iterator.hasNext();)
    	{
    		Object obj = iterator.next();
    		
    		if (obj instanceof Integer)
    		{
    			list.add(new ItemStack(id, 1, ((Integer)obj).intValue()));
    		}
    	}
    }
}