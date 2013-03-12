package ayamitsu.mobskullsplus.client;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import ayamitsu.mobskullsplus.common.TileEntityMobSkull;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class TileEntityMobSkullRenderer extends TileEntitySpecialRenderer
{
	public static TileEntityMobSkullRenderer skullRenderer;

	@Override
	public void setTileEntityRenderer(TileEntityRenderer par1TileEntityRenderer)
    {
        super.setTileEntityRenderer(par1TileEntityRenderer);
        skullRenderer = this;
    }

	public void doRenderSkull(float par1, float par2, float par3, int par4, float par5, int par6)
    {
    	ISkullRenderer renderer = RendererRegistry.getSkullRenderer(par6);
        GL11.glPushMatrix();
    	GL11.glTranslatef(par1 + 0.5F, par2, par3 + 0.5F);
    	renderer.renderSkull(par4, par5, EnumSkullRenderType.BLOCK);
		GL11.glPopMatrix();
    }

	public void renderTileEntitySkullAt(TileEntityMobSkull par1TileEntityMobSkull, double par2, double par4, double par6, float par8)
    {
        this.doRenderSkull((float)par2, (float)par4, (float)par6, par1TileEntityMobSkull.getBlockMetadata() & 7, (float)(par1TileEntityMobSkull.getSkullRotation() * 360) / 16.0F, par1TileEntityMobSkull.getSkullType());
    }

	@Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntitySkullAt((TileEntityMobSkull)par1TileEntity, par2, par4, par6, par8);
    }
}