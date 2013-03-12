package ayamitsu.mobskullsplus.client.model;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelSkullChicken extends ModelSkullBase
{
	public ModelSkullChicken()
	{
        this.textureWidth = 64;
        this.textureHeight = 32;
		this.skull = new net.minecraft.client.model.ModelRenderer(this, 0, 0);
		this.skull.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
		this.skull.setTextureOffset(14, 0).addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
		this.skull.setTextureOffset(14, 4).addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
	}
}