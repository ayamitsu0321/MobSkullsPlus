package ayamitsu.mobskullsplus.model;

import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelSkullSheep1 extends ModelSkullBase
{
	public ModelSkullSheep1()
	{
		this.textureWidth = 64;
        this.textureHeight = 32;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-3.0F, -6.0F, -2.5F, 6, 6, 6, 0.6F);
		//this.skull.setRotationPoint(0.0F, 0.0F, -1.5F);
	}
	
	/*public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
	}*/
}