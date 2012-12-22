package ayamitsu.mobskullsplus.client.model;

import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
}