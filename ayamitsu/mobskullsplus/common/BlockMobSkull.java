package ayamitsu.mobskullsplus.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ayamitsu.mobskullsplus.MobSkullsPlus;
import ayamitsu.mobskullsplus.client.ISkullRenderer;
import ayamitsu.mobskullsplus.client.RendererRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMobSkull extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	protected Icon[] icons;

	public BlockMobSkull(int id)
	{
		super(id, Material.circuits);
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
	}

	// use client side only
	@Override
	public int getRenderType()
	{
		return MobSkullsPlus.renderId;
	}

	// not opaque block
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	//  not render normal cube block
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	// set size
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		TileEntityMobSkull mobSkull = (TileEntityMobSkull)par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		int type = mobSkull.getSkullType();
		IBlockBounds blockBounds = BlockBoundsRegistry.getBlockBounds(type);

		if (blockBounds != null)
		{
			blockBounds.setBlockBounds(par1IBlockAccess, par2, par3, par4, this);
		}
		else
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
	{
		int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 3);
	}

	@Override
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityMobSkull();
	}

    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        TileEntity var5 = par1World.getBlockTileEntity(par2, par3, par4);
        return var5 instanceof TileEntityMobSkull ? ((TileEntityMobSkull)var5).getSkullType() : super.getDamageValue(par1World, par2, par3, par4);
    }

	@Override
    public int damageDropped(int par1)
    {
        return par1;
    }

	@Override
	public Icon getIcon(int par1, int par2)
	{
		return Block.skull.getIcon(par1, par2);
	}

	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {}

	@Override
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
    {
        if (par6EntityPlayer.capabilities.isCreativeMode)
        {
            par5 |= 8;
            par1World.setBlockMetadataWithNotify(par2, par3, par4, par5, 3);
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
                this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this.blockID, 1, this.getDamageValue(par1World, par2, par3, par4)));
            }

            super.breakBlock(par1World, par2, par3, par4, par5, par6);
        }
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list)
	{
		for (Integer integer : RendererRegistry.getMap().keySet())
		{
			list.add(new ItemStack(id, 1, integer.intValue()));
		}
	}

	@SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
		int i = -1;
		this.icons = new Icon[RendererRegistry.getMap().values().size()];

		for (ISkullRenderer renderer : RendererRegistry.getMap().values())
		{
			this.icons[++i] = par1IconRegister.registerIcon("mobskullsplus:" + renderer.getIconPath());
		}
    }
}