package ayamitsu.mobskullsplus.model;

import net.minecraft.src.ModelRenderer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullSpider extends ModelSkullBase
{
	public ModelSkullSpider()
	{
		this.skull = new ModelRenderer(this, 32, 4);
		this.skull.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
	}
}