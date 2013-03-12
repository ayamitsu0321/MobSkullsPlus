package ayamitsu.mobskullsplus.client.model;


@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelSkullEnderman extends ModelSkullBase
{
	public net.minecraft.client.model.ModelRenderer subSkull;

	public ModelSkullEnderman()
	{
        this.textureWidth = 64;
        this.textureHeight = 32;
		this.skull = new net.minecraft.client.model.ModelRenderer(this, 0, 0);
		this.skull.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.subSkull = new net.minecraft.client.model.ModelRenderer(this, 0, 16);
		this.subSkull.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F - 0.5F);
	}

	public void render(net.minecraft.entity.Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.subSkull.render(par7);
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.subSkull.rotateAngleY = this.skull.rotateAngleY;
		this.subSkull.rotateAngleX = this.skull.rotateAngleX;
	}
}