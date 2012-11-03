package ayamitsu.mobskullsplus;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class TileEntityMobSkull extends TileEntity
{
	/** �`�悷��^�C�v, ItemStack��itemDamage����l�����炤 */
	private int entityId;
	
	/** �ݒu�����Ƃ��̌��� */
	private int rotation;
	
	@Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setByte("EntityID", (byte)(this.entityId & 255));
		nbttagcompound.setByte("Rot", (byte)(this.rotation & 255));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		this.entityId = nbttagcompound.getByte("EntityID");
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
	 * Entity��ID��set
	 * ����������Őݒ肵��ID�Ɋ܂܂�ĂȂ�������Zombie��ID��������
	 */
	public void setEntityId(int id)
	{
		this.entityId = id;
	}
	
	/**
	 * Entity��ID���擾
	 */
	public int getEntityId()
	{
		return this.entityId;
	}
	
	/**
	 * Render�̕`��Ŏg��
	 * Block�̌���
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