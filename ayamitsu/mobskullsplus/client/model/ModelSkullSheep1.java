package ayamitsu.mobskullsplus.client.model;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelSkullSheep1 extends ModelSkullBase
{
	public ModelSkullSheep1()
	{
		this.textureWidth = 64;
        this.textureHeight = 32;
		this.skull = new net.minecraft.client.model.ModelRenderer(this, 0, 0);
		this.skull.addBox(-3.0F, -6.0F, -2.5F, 6, 6, 6, 0.6F);
	}
}