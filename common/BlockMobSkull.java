package ayamitsu.mobskullsplus.common;

import ayamitsu.mobskullsplus.*;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.Random;
import java.util.List;
import java.util.Iterator;

public class BlockMobSkull extends BlockContainer
{
	public BlockMobSkull(int id)
	{
		super(id, Material.circuits);
		this.blockIndexInTexture = 104;
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
	}
	
	// Renderするタイプ
	@Override
	public int getRenderType()
	{
		return MobSkullsPlus.renderId;
		//return -1;
	}
	
	// 光を透過するか
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	// Renderするときは普通のBlockじゃないよ(つまり土Blockみたいなのではない)
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	// サイズの設定
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		TileEntityMobSkull mobSkull = (TileEntityMobSkull)par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		int type = mobSkull.getEntityId();
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
	
	// サイズの取得
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	
	// おかれたとき
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	{
		int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var6);
	}
	
	// BlockContainerというかTileEntityをもつBlockに必須
	@Override
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityMobSkull();
	}

	// Block自体がドロップするときにEntityItemにつけるメタデータ
    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        TileEntity var5 = par1World.getBlockTileEntity(par2, par3, par4);
        return var5 instanceof TileEntityMobSkull ? ((TileEntityMobSkull)var5).getEntityId() : super.getDamageValue(par1World, par2, par3, par4);
    }

	// ドロップ時のメタデータ
	@Override
    public int damageDropped(int par1)
    {
        return par1;
    }
	
	// アイテムのドロップ
	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {}
	
	// 採掘されたとき
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
	
	// こわされたとき
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
	
	// クリエイティブのタブに表示
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list)
	{
		for (Iterator iterator = ayamitsu.mobskullsplus.client.RendererRegistry.getMap().keySet().iterator(); iterator.hasNext();)
		{
			Integer entityId = (Integer)iterator.next();
			list.add(new ItemStack(id, 1, entityId.intValue()));
		}
	}
}