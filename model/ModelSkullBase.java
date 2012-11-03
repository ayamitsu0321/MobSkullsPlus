package ayamitsu.mobskullsplus.model;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ModelSkullBase extends ModelBase
{
	public ModelRenderer skull;
	
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.skull.render(par7);
	}
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.skull.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.skull.rotateAngleX = par5 / (180F / (float)Math.PI);
	}
}