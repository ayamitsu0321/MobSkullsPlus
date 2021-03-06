package ayamitsu.mobskullsplus.client.model;


@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelSkullOcelot extends ModelSkullBase
{
	public ModelSkullOcelot()
	{
		this.setTextureOffset("head.main", 0, 0);
        this.setTextureOffset("head.nose", 0, 24);
        this.setTextureOffset("head.ear1", 0, 10);
        this.setTextureOffset("head.ear2", 6, 10);
        this.skull = new net.minecraft.client.model.ModelRenderer(this, "head");
        this.skull.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        this.skull.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        this.skull.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        this.skull.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        this.skull.setRotationPoint(0.0F, -2.0F, 0.0F);
	}
}