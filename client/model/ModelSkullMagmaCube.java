package ayamitsu.mobskullsplus.client.model;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelSkullMagmaCube extends ModelSkullBase
{
	ModelRenderer[] models = new ModelRenderer[8];
	public ModelSkullMagmaCube()
	{
		for (int var1 = 0; var1 < this.models.length; ++var1)
        {
            byte var2 = 0;
            int var3 = var1;

            if (var1 == 2)
            {
                var2 = 24;
                var3 = 10;
            }
            else if (var1 == 3)
            {
                var2 = 24;
                var3 = 19;
            }

            this.models[var1] = new ModelRenderer(this, var2, var3);
            this.models[var1].addBox(-4.0F, (float)(16 + var1), -4.0F, 8, 1, 8);
        }

        this.skull = new ModelRenderer(this, 0, 16);
        this.skull.addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4);
	}
	
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    	GL11.glTranslatef(0.0F, -1.5F, 0.0F);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        ModelRenderer[] var8 = this.models;
        int var9 = var8.length;

        for (int var10 = 0; var10 < var9; ++var10)
        {
            ModelRenderer var11 = var8[var10];
            var11.render(par7);
        }
    }
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		
		for (int i = 0; i < this.models.length; i++)
		{
			this.models[i].rotateAngleY = this.skull.rotateAngleY;
			this.models[i].rotateAngleX = this.skull.rotateAngleX;
		}
	}
}