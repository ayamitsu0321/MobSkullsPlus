package ayamitsu.mobskullsplus.client.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import ayamitsu.mobskullsplus.client.EnumSkullRenderType;
import ayamitsu.mobskullsplus.client.ISkullRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkullRendererVillager implements ISkullRenderer
{
	/** �ő�̃T�C�Y��1.0F�Ƒz�� */
	public static final float MIN = 0.0F;
	public static final float MAX = 1.0F;
	ModelBase model;
	private String texture = "";
	private float xSize = 0.5F;
	private float ySize = 0.625F;
	private float zSize = 0.5F;
	private boolean isAlpha = false;
	private final String iconPath;

	public SkullRendererVillager(String tex, ModelBase modelbase)
	{
		this.model = modelbase;
		this.iconPath = tex;
	}

	public SkullRendererVillager setTextureFile(String str)
	{
		this.texture = str;
		return this;
	}

	public SkullRendererVillager setSize(float x, float y, float z)
	{
		this.xSize = x;
		this.ySize = y;
		this.zSize = z;
		return this;
	}

	public SkullRendererVillager setAlpha(boolean flag)
	{
		this.isAlpha = flag;
		return this;
	}

	@Override
	public String getIconPath()
	{
		return this.iconPath;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderSkull(int direction, float par5, EnumSkullRenderType type)
	{
		this.bindTextureByName(this.texture);
        GL11.glDisable(GL11.GL_CULL_FACE);

		if (direction != 1)
		{
			switch (direction)
            {
	            case -1:
            		GL11.glTranslatef(0.0F, -0.25F, 0.0F);
	            	break;
                case 2:
                    GL11.glTranslatef(0.0F, 0.1875F, 0.24F);
                    break;
                case 3:
                    GL11.glTranslatef(0.0F, 0.1875F, -0.24F);
                    par5 = 180.0F;
                    break;
                case 4:
                    GL11.glTranslatef(0.24F, 0.1875F, 0.0F);
                    par5 = 270.0F;
                    break;
                case 5:
                default:
                    GL11.glTranslatef(-0.24F, 0.1875F, 0.0F);
                    par5 = 90.0F;
            }
		}

		float var10 = 0.0625F;

		if (this.isAlpha)
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, par5, 0.0F, var10);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	@SideOnly(Side.CLIENT)
	protected void bindTextureByName(String par1Str)
    {
		RenderEngine var2 = ModLoader.getMinecraftInstance().renderEngine;

        if (var2 != null)
        {
        	var2.func_98187_b(par1Str);
            //var2.bindTexture(var2.getTexture(par1Str));
        }
    }
}