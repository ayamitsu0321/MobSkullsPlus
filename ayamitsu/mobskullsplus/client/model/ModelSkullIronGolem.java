package ayamitsu.mobskullsplus.client.model;

import net.minecraft.client.model.ModelRenderer;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelSkullIronGolem extends ModelSkullBase
{
	public ModelSkullIronGolem()
	{
		this(0.0F);
	}

	public ModelSkullIronGolem(float par1)
	{
		this.textureWidth = 128;
        this.textureHeight = 128;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, par1);
        this.skull.setTextureOffset(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, par1);
	}
}
