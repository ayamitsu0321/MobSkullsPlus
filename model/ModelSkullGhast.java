package ayamitsu.mobskullsplus.model;

import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullGhast extends ModelSkullBase
{
	public ModelSkullGhast()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
	}
}