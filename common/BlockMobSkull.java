package ayamitsu.mobskullsplus.common;

import java.util.Iterator;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ayamitsu.mobskullsplus.MobSkullsPlus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMobSkull extends BlockContainer
{
	public BlockMobSkull(int id)
	{
		super(id, Material.circuits);
		this.blockIndexInTexture = 104;
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
	}

	// Render����^�C�v
	@Override
	public int getRenderType()
	{
		return MobSkullsPlus.renderId;
		//return -1;
	}

	// ���𓧉߂��邩
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	// Render����Ƃ��͕��ʂ�Block����Ȃ���(�܂�yBlock�݂����Ȃ̂ł͂Ȃ�)
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	// �T�C�Y�̐ݒ�
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

	// �T�C�Y�̎擾
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	// �����ꂽ�Ƃ�
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	{
		int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var6);
	}

	// BlockContainer�Ƃ�����TileEntity������Block�ɕK�{
	@Override
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityMobSkull();
	}

	// Block���̂��h���b�v����Ƃ���EntityItem�ɂ��郁�^�f�[�^
    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        TileEntity var5 = par1World.getBlockTileEntity(par2, par3, par4);
        return var5 instanceof TileEntityMobSkull ? ((TileEntityMobSkull)var5).getSkullType() : super.getDamageValue(par1World, par2, par3, par4);
    }

	// �h���b�v���̃��^�f�[�^
	@Override
    public int damageDropped(int par1)
    {
        return par1;
    }

	// �A�C�e���̃h���b�v
	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {}

	// �̌@���ꂽ�Ƃ�
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

	// ���킳�ꂽ�Ƃ�
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

	// �N���G�C�e�B�u�̃^�u�ɕ\��
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, java.util.List list)
	{
		for (Iterator iterator = ayamitsu.mobskullsplus.client.RendererRegistry.getMap().keySet().iterator(); iterator.hasNext();)
		{
			Integer entityId = (Integer)iterator.next();
			list.add(new ItemStack(id, 1, entityId.intValue()));
		}
	}
}