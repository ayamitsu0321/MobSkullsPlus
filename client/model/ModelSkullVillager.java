package ayamitsu.mobskullsplus.client.model;

import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullVillager extends ModelSkullBase
{
	public ModelRenderer subSkull;
	
	public ModelSkullVillager(float par1)
	{
		this(par1, 0.0F, 64, 64);
	}
	
	public ModelSkullVillager(float par1, float par2, int par3, int par4)
	{
		this.skull = (new ModelRenderer(this)).setTextureSize(par3, par4);
		this.skull.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, par1);
		this.subSkull = (new ModelRenderer(this)).setTextureSize(par3, par4);
		this.subSkull.setTextureOffset(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, par1);
		this.skull.addChild(this.subSkull);
	}
	
	/*public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.subSkull.render(par7);
	}
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.subSkull.rotateAngleY = this.skull.rotateAngleY;
		this.subSkull.rotateAngleX = this.skull.rotateAngleX;
	}*/
}