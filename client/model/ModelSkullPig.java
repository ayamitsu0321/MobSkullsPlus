package ayamitsu.mobskullsplus.client.model;

import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelSkullPig extends ModelSkullBase
{
	public ModelSkullPig()
	{
		this(0.0F);
	}
	
	public ModelSkullPig(float par1)
	{
		int var2 = 6;
        this.textureWidth = 64;
        this.textureHeight = 32;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.skull.setTextureOffset(16, 16).addBox(-2.0F, -4.0F, -5.0F, 4, 3, 1, par1);
	}
	
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
	}
}