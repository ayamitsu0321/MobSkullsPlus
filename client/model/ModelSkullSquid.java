package ayamitsu.mobskullsplus.client.model;

import net.minecraft.src.ModelRenderer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullSquid extends ModelSkullBase
{
	public ModelSkullSquid()
	{
		this.textureWidth = 64;
	    this.textureHeight = 32;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-6.0F, -21.375F, -6.0F, 12, 16, 12);
        this.skull.rotationPointY += 8F;
	}
}
