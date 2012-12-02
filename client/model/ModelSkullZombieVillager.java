package ayamitsu.mobskullsplus.client.model;

import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullZombieVillager extends ModelSkullBiped
{
	public ModelSkullZombieVillager()
	{
		this(0.0F, 0.0F, false);
	}
	
	public ModelSkullZombieVillager(float par1, float par2, boolean par3)
	{
		super(par1, 0.0F, 64, par3 ? 32 : 64);
		
		if (par3)
        {
            this.skull = new ModelRenderer(this, 0, 0);
            this.skull.addBox(-4.0F, -10.0F, -4.0F, 8, 6, 8, par1);
            this.skull.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        }
        else
        {
            this.skull = new ModelRenderer(this);
            this.skull.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.skull.setTextureOffset(0, 32).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, par1);
            this.skull.setTextureOffset(24, 32).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, par1);
        }
	}
	
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.skull.render(par7);
		this.subSkull.render(par7);
	}
}