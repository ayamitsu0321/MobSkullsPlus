package ayamitsu.mobskullsplus.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMobSkull extends TileEntity
{
	/** �`�悷��^�C�v, ItemStack��itemDamage����l�����炤 */
	private int skullType;

	/** �ݒu�����Ƃ��̌� */
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
	public net.minecraft.network.packet.Packet getDescriptionPacket()
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
	 * Entity��ID��set
	 * ����������Őݒ肵��ID�Ɋ܂܂�ĂȂ�������Zombie��ID��������
	 */
	public void setSkullType(int id)
	{
		this.skullType = id;
	}

	/**
	 * Entity��ID���擾
	 */
	public int getSkullType()
	{
		return this.skullType;
	}

	/**
	 * Render�̕`��Ŏg��
	 * Block�̌�
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