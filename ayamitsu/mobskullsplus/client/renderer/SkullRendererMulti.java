package ayamitsu.mobskullsplus.client.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ayamitsu.mobskullsplus.client.EnumSkullRenderType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkullRendererMulti extends SkullRendererDefault
{
	public ResourceLocation subTexture;
	private boolean alphaMain = false;
	private boolean alphaSub = false;
	private boolean specialAlphaMain = false;
	private boolean specialAlphaSub = false;

	public SkullRendererMulti(String tex, ModelBase modelbase, ModelBase modelbase1)
	{
		super(tex, new ModelBase[] { modelbase, modelbase1 });
	}

	public SkullRendererMulti setTexture(String str, String str1)
	{
		this.setTexture(str);
		this.subTexture = new ResourceLocation(str1);
		return this;
	}

	@Override
	public ResourceLocation getTexture(int count)
	{
		return count == 0 ? this.texture : this.subTexture;
	}

	public SkullRendererMulti setAlpha(boolean flag, boolean flag1)
	{
		this.alphaMain = flag;
		this.alphaSub = flag1;
		return this;
	}

	public SkullRendererMulti setAlphaSpecial(boolean flag, boolean flag1)
	{
		this.specialAlphaMain = flag;
		this.specialAlphaSub = flag1;
		return this.setAlpha(flag, flag1);
	}

	@SideOnly(Side.CLIENT)
	protected void setAlpha(int direction, float rotation, EnumSkullRenderType type, int count)
	{
		GL11.glDisable(GL11.GL_BLEND);

		if (count == 0)
		{
			if (this.alphaMain)
			{
				GL11.glEnable(GL11.GL_BLEND);
				this.doAlpha(this.specialAlphaMain);
			}
		}
		else
		{
			if (this.alphaSub)
			{
				GL11.glEnable(GL11.GL_BLEND);
				this.doAlpha(this.specialAlphaSub);
			}
		}
	}

	public void doAlpha(boolean special)
	{
		if (!special)
		{
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
		else
		{
			float var1 = 1.0F;
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
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