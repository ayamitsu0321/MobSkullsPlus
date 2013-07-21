package ayamitsu.mobskullsplus.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSkullHorse extends ModelSkullBase
{
	protected ModelRenderer earLeft;
	protected ModelRenderer earRight;
	protected ModelRenderer mane;
	protected ModelRenderer neck;

	public ModelSkullHorse()
	{
		this.textureHeight = 128;
		this.textureWidth = 128;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-2.5F, -10.0F, -2.5F, 5, 5, 7);
		//this.setModelRotation(this.skull, 0.5235988F, 0.0F, 0.0F);

		ModelRenderer model = new ModelRenderer(this, 24, 18);
		model.addBox(-2.0F, -9.95F, -8.0F, 4, 3, 6);
		//this.setModelRotation(model, 0.5235988F, 0.0F, 0.0F);
		this.skull.addChild(model);

		model = new ModelRenderer(this, 24, 27);
		model.addBox(-2.0F, -7.0F, -7.5F, 4, 2, 5);
		//this.setModelRotation(model, 0.5235988F, 0.0F, 0.0F);
		this.skull.addChild(model);

		this.earLeft = new ModelRenderer(this, 0, 0);
		this.earLeft.addBox(0.45F, -12.0F, 3.0F, 2, 3, 1);
		//this.setModelRotation(this.earLeft, 0.5235988F, 0.0F, 0.0F);

		this.earRight = new ModelRenderer(this, 0, 0);
		this.earRight.addBox(-2.45F, -12.0F, 3.0F, 2, 3, 1);
		//this.setModelRotation(this.earRight, 0.5235988F, 0.0F, 0.0F);

		this.mane = new ModelRenderer(this, 58, 0);
		this.mane.addBox(-1.0F, -11.5F, 4.0F, 2, 12, 4);
		//this.setModelRotation(this.mane, 0.5235988F, 0.0F, 0.0F);

		this.neck = new ModelRenderer(this, 0, 12);
		this.neck.addBox(-2.05F, -9.8F, -3.0F, 4, 10, 8);
		//this.setModelRotation(this.neck, 0.5235988F, 0.0F, 0.0F);
	}

	protected void setModelRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.earLeft.render(par7);
		this.earRight.render(par7);
		this.mane.render(par7);
		this.neck.render(par7);
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		//super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.skull.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.skull.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.earLeft.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.earLeft.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.earRight.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.earRight.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.mane.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.mane.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.neck.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.neck.rotateAngleX = par5 / (180F / (float)Math.PI);
	}
}
