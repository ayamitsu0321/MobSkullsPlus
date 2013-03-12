package ayamitsu.mobskullsplus.client.model;

import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkullWitch extends ModelSkullVillager
{
	public ModelRenderer hat;

	public ModelSkullWitch(float par1)
	{
		super(par1, 0.0F, 64, 128);
		// �͂�
		ModelRenderer var1 = (new ModelRenderer(this)).setTextureSize(64, 128);
		var1.setRotationPoint(0.0F, -2.0F, 0.0F);
		var1.setTextureOffset(0, 0).addBox(0.0F, 1.0F, -6.75F, 1, 1, 1, -0.25F);
		this.subSkull.addChild(var1);

		// �X�q
		this.hat = (new ModelRenderer(this)).setTextureSize(64, 128);
		this.hat.setRotationPoint(-5.0F, -10.0325F, -5.0F);
		this.hat.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
		this.skull.addChild(this.hat);

		ModelRenderer var3 = (new ModelRenderer(this)).setTextureSize(64, 128);
		var3.setRotationPoint(1.75F, -4.0F, 2.0F);
		var3.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
        var3.rotateAngleX = -0.05235988F;
        var3.rotateAngleZ = 0.02617994F;
		this.hat.addChild(var3);

		ModelRenderer var4 = (new ModelRenderer(this)).setTextureSize(64, 128);
        var4.setRotationPoint(1.75F, -4.0F, 2.0F);
        var4.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        var4.rotateAngleX = -0.10471976F;
        var4.rotateAngleZ = 0.05235988F;
        var3.addChild(var4);

        ModelRenderer var5 = (new ModelRenderer(this)).setTextureSize(64, 128);
        var5.setRotationPoint(1.75F, -2.0F, 2.0F);
        var5.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
        var5.rotateAngleX = -0.20943952F;
        var5.rotateAngleZ = 0.10471976F;
        var4.addChild(var5);
	}
}