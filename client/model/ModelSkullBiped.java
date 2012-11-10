package ayamitsu.mobskullsplus.client.model;

import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class ModelSkullBiped extends ModelSkullBase
{
	public ModelRenderer subSkull;
	
	public ModelSkullBiped()
	{
		this(0.0F);
	}
	
	public ModelSkullBiped(float f)
	{
		this(f, 0.0F, 64, 32);
	}
	
	public ModelSkullBiped(float f, float f1, int i, int j)
	{
        this.textureWidth = i;
        this.textureHeight = j;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
		this.subSkull = new ModelRenderer(this, 32, 0);
		this.subSkull.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f + 0.5F);
	}
	
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		float scale = 0.9F;
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(0.0F, -0.025F, 0.0F);
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.subSkull.render(par7);
	}
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.subSkull.rotateAngleY = this.skull.rotateAngleY;
		this.subSkull.rotateAngleX = this.skull.rotateAngleX;
	}
}