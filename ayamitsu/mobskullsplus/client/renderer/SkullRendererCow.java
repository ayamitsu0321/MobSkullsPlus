package ayamitsu.mobskullsplus.client.renderer;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import ayamitsu.mobskullsplus.client.EnumSkullRenderType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkullRendererCow extends SkullRendererDefault
{
	public SkullRendererCow(String tex, ModelBase modelbase)
	{
		super(tex, modelbase);
	}

	public SkullRendererCow(String tex, ModelBase ... modelbase)
	{
		super(tex, modelbase);
	}

	@SideOnly(Side.CLIENT)
	protected void callBackScale(int direction, float rotation, EnumSkullRenderType type)
	{
		if (type == EnumSkullRenderType.EQUIPPED)
		{
			float scale = 1.25F;
			GL11.glScalef(scale, scale, scale);
		}
	}

}