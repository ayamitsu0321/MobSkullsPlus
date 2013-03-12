package ayamitsu.mobskullsplus.client.model;

import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullWolf extends ModelSkullBase
{
	public ModelSkullWolf()
	{
		float var1 = 0.0F;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-3.0F, -6.0F, -2.0F, 6, 6, 4, var1);
		this.skull.setTextureOffset(16, 14).addBox(-3.0F, -8.0F, 0.0F, 2, 2, 1, var1);
		this.skull.setTextureOffset(16, 14).addBox(1.0F, -8.0F, 0.0F, 2, 2, 1, var1);
		this.skull.setTextureOffset(0, 10).addBox(-1.5F, -3.0F, -5.0F, 3, 3, 4, var1);
	}
}