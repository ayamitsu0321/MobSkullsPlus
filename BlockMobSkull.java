package ayamitsu.mobskullsplus;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.Random;

public class BlockMobSkull extends BlockContainer
{
	public BlockMobSkull(int id)
	{
		super(id, Material.circuits);
		this.blockIndexInTexture = 104;
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		TileEntityMobSkull mobSkull = (TileEntityMobSkull)par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		int type = mobSkull.getEntityId();
		ISkullRenderer renderer = MobSkullsList.getSkullRenderer(type);
		renderer.setBlockBounds(par1IBlockAccess, par2, par3, par4, this);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	{
		int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var6);
	}
	
	@Override
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityMobSkull();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return MobSkullsPlus.skullItem.shiftedIndex;
    }

    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        TileEntity var5 = par1World.getBlockTileEntity(par2, par3, par4);
        return var5 instanceof TileEntityMobSkull ? ((TileEntityMobSkull)var5).getEntityId() : super.getDamageValue(par1World, par2, par3, par4);
    }

	@Override
    public int damageDropped(int par1)
    {
        return par1;
    }
	
	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {}
	
	@Override
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
    {
        if (par6EntityPlayer.capabilities.isCreativeMode)
        {
            par5 |= 8;
            par1World.setBlockMetadataWithNotify(par2, par3, par4, par5);
        }

        super.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        if (!par1World.isRemote)
        {
            if ((par6 & 8) == 0)
            {
                this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(Item.skull.shiftedIndex, 1, this.getDamageValue(par1World, par2, par3, par4)));
            }

            super.breakBlock(par1World, par2, par3, par4, par5, par6);
        }
    }
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return MobSkullsPlus.skullItem.shiftedIndex;
    }
	
}