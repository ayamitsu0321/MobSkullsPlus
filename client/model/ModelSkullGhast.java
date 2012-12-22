package ayamitsu.mobskullsplus.client.model;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelSkullGhast extends ModelSkullBase
{
	public ModelSkullGhast()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.skull = new net.minecraft.client.model.ModelRenderer(this, 0, 0);
		this.skull.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
	}
}