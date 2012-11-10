package ayamitsu.mobskullsplus.client.model;

import net.minecraft.src.ModelRenderer;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelSkullSheep1 extends ModelSkullBase
{
	public ModelSkullSheep1()
	{
		this.textureWidth = 64;
        this.textureHeight = 32;
		this.skull = new ModelRenderer(this, 0, 0);
		this.skull.addBox(-3.0F, -6.0F, -2.5F, 6, 6, 6, 0.6F);
	}
}