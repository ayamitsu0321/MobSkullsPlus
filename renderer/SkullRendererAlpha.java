package ayamitsu.mobskullsplus.renderer;

import ayamitsu.mobskullsplus.ISkullRenderer;
import ayamitsu.mobskullsplus.EnumSkullRenderType;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererAlpha extends SkullRendererDefault
{
	private boolean specialAlpha = false;
	
	public SkullRendererAlpha(int tex, ModelBase modelbase)
	{
		super(tex, modelbase);
	}
	
	public SkullRendererAlpha(int tex, ModelBase ... modelbase)
	{
		super(tex, modelbase);
	}
	
	public SkullRendererAlpha setAlphaSpecial(boolean flag)
	{
		this.specialAlpha = flag;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	protected void setAlpha(int direction, float rotation, EnumSkullRenderType type, int count)
	{
		GL11.glEnable(GL11.GL_BLEND);
		
		if (!this.specialAlpha)
		{
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
		else
		{
			float var1 = 1.0F;
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			//GL11.glDepthMask(true);//
			char var2 = 61680;
			int var3 = var2 % 65536;
			int var4 = var2 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var3 / 1.0F, (float)var4 / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var1);
		}
	}
}