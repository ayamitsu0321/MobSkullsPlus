package ayamitsu.mobskullsplus.client.model;

import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullSquid extends ModelSkullBase
{
	public ModelSkullSquid()
	{
		this.textureWidth = 64;
	    this.textureHeight = 32;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-6.0F, -16.0F, -6.0F, 12, 16, 12);
	}
}
