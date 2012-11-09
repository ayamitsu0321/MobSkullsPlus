package ayamitsu.mobskullsplus.model;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullSlime extends ModelBase
{
	ModelRenderer slimeBodies;
    ModelRenderer slimeRightEye;
    ModelRenderer slimeLeftEye;
    ModelRenderer slimeMouth;
	
	public ModelSkullSlime(int par1)
    {
        this.slimeBodies = new ModelRenderer(this, 0, par1);
        this.slimeBodies.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);

        if (par1 > 0)
        {
            this.slimeBodies = new ModelRenderer(this, 0, par1);
            this.slimeBodies.addBox(-3.0F, -7.0F, -3.0F, 6, 6, 6);
            this.slimeRightEye = new ModelRenderer(this, 32, 0);
            this.slimeRightEye.addBox(-3.25F, -6.0F, -3.5F, 2, 2, 2);
            this.slimeLeftEye = new ModelRenderer(this, 32, 4);
            this.slimeLeftEye.addBox(1.25F, -6.0F, -3.5F, 2, 2, 2);
            this.slimeMouth = new ModelRenderer(this, 32, 8);
            this.slimeMouth.addBox(0.0F, -3.0F, -3.5F, 1, 1, 1);
        }
    }
	
	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.slimeBodies.render(par7);

        if (this.slimeRightEye != null)
        {
            this.slimeRightEye.render(par7);
            this.slimeLeftEye.render(par7);
            this.slimeMouth.render(par7);
        }
    }
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		this.slimeBodies.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.slimeBodies.rotateAngleX = par5 / (180F / (float)Math.PI);
		
		if (this.slimeRightEye != null)
		{
			this.slimeRightEye.rotateAngleY = this.slimeBodies.rotateAngleY;
            this.slimeLeftEye.rotateAngleY = this.slimeBodies.rotateAngleY;
            this.slimeMouth.rotateAngleY = this.slimeBodies.rotateAngleY;
			this.slimeRightEye.rotateAngleX = this.slimeBodies.rotateAngleX;
            this.slimeLeftEye.rotateAngleX = this.slimeBodies.rotateAngleX;
            this.slimeMouth.rotateAngleX = this.slimeBodies.rotateAngleX;
		}
	}
}