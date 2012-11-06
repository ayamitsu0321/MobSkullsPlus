package ayamitsu.mobskullsplus.renderer;

import ayamitsu.mobskullsplus.ISkullRenderer;
import ayamitsu.mobskullsplus.EnumSkullRenderType;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererCube implements ISkullRenderer
{
	private ModelBase model;
	private String texture = "";
	private final int spriteIndex;
	private boolean isAlpha = false;
	
	public SkullRendererCube(int tex, ModelBase modelbase)
	{
		this.model = modelbase;
		this.spriteIndex = tex;
	}
	
	public SkullRendererCube setTextureFile(String str)
	{
		this.texture = str;
		return this;
	}
	
	public SkullRendererCube setAlpha(boolean flag)
	{
		this.isAlpha = flag;
		return this;
	}
	
	@Override
	public int getSpriteIndex(int meta)
	{
		return this.spriteIndex;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void renderSkull(int direction, float par5, EnumSkullRenderType type)
	{
		this.bindTextureByName(this.texture);
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		if (type == EnumSkullRenderType.EQUIPPED)
		{
			GL11.glTranslatef(0.0F, -0.25F, 0.0F);
		}
		
		if (direction != 1)
		{
			switch (direction)
            {
                case 2:
                    break;
                case 3:
                    par5 = 180.0F;
                    break;
                case 4:
                    par5 = 270.0F;
                    break;
                case 5:
                default:
                    par5 = 90.0F;
            }
		}
		
		if (this.isAlpha)
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
		
		float var1 = 0.0625F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, par5, 0.0F, var1);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}
	
	@Override
	public void setBlockBounds(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block)
	{
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@SideOnly(Side.CLIENT)
	protected void bindTextureByName(String par1Str)
    {
    	RenderEngine var2 = ModLoader.getMinecraftInstance().renderEngine;
    	
        if (var2 != null)
        {
            var2.bindTexture(var2.getTexture(par1Str));
        }
    }
}
