package ayamitsu.mobskullsplus.model;

import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullSheep2 extends ModelSkullSheep1
{
	public ModelRenderer subSkull;
	
	public ModelSkullSheep2()
	{
		super();
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-3.0F, -6.0F, -4.0F, 6, 6, 8, 0.0F);
	}
}