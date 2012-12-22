package ayamitsu.mobskullsplus.client.model;

import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullSpider extends ModelSkullBase
{
	public ModelSkullSpider()
	{
		this.skull = new ModelRenderer(this, 32, 4);
		this.skull.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
	}
}