package ayamitsu.mobskullsplus.model;

import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullCow extends ModelSkullBase
{
	public ModelSkullCow()
	{
        this.textureWidth = 64;
        this.textureHeight = 32;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-4.0F, -8.0F, -3.0F, 8, 8, 6, 0.0F);
		this.skull.setTextureOffset(22, 0).addBox(-5.0F, -9.0F, -1.0F, 1, 3, 1, 0.0F);
		this.skull.setTextureOffset(22, 0).addBox(4.0F, -9.0F, -1.0F, 1, 3, 1, 0.0F);
	}
}