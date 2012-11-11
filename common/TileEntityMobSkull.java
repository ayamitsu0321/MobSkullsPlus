package ayamitsu.mobskullsplus.common;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class TileEntityMobSkull extends TileEntity
{
	/** 描画するタイプ, ItemStackのitemDamageから値をもらう */
	private int skullType;
	
	/** 設置したときの向き */
	private int rotation;
	
	@Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setByte("Type", (byte)(this.skullType & 255));
		nbttagcompound.setByte("Rot", (byte)(this.rotation & 255));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		this.skullType = nbttagcompound.getByte("Type");
		this.rotation = nbttagcompound.getByte("Rot");
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData packet)
	{
		this.readFromNBT(packet.customParam1);
	}
	
	/**
	 * EntityのIDをset
	 * もしこちらで設定したIDに含まれてなかったらZombieのIDをかえす
	 */
	public void setSkullType(int id)
	{
		this.skullType = id;
	}
	
	/**
	 * EntityのIDを取得
	 */
	public int getSkullType()
	{
		return this.skullType;
	}
	
	/**
	 * Renderの描画で使う
	 * Blockの向き
	 */
	public void setSkullRotation(int rot)
	{
		this.rotation = rot;
	}
	
	@SideOnly(Side.CLIENT)
	public int getSkullRotation()
	{
		return this.rotation;
	}
}