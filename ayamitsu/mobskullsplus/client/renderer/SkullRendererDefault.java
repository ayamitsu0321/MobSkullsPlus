package ayamitsu.mobskullsplus.client.renderer;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import ayamitsu.mobskullsplus.client.EnumSkullRenderType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkullRendererDefault extends SkullRendererBase
{
	protected float defaultScale = 1.0F;

	public SkullRendererDefault(String tex, ModelBase modelbase)
	{
		super(modelbase);
		this.iconPath = tex;
	}

	public SkullRendererDefault(String tex, ModelBase ... modelbase)
	{
		super(modelbase);
		this.iconPath = tex;
	}

	public SkullRendererBase setScale(float f)
	{
		this.defaultScale = f;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void callBackScale(int direction, float rotation, EnumSkullRenderType type)
	{
		GL11.glScalef(this.defaultScale, this.defaultScale, this.defaultScale);
	}
}