package ayamitsu.mobskullsplus.model;

import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullSheep1 extends ModelSkullBase
{
	public ModelRenderer subSkull;
	
	public ModelSkullSheep1()
	{
		this.textureWidth = 64;
        this.textureHeight = 32;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-3.0F, -6.0F, -4.0F, 6, 6, 6, 0.0F);
	}
}