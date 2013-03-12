package ayamitsu.mobskullsplus.client.renderer;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import ayamitsu.mobskullsplus.client.EnumSkullRenderType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkullRendererCube extends SkullRendererDefault
{
	public SkullRendererCube(String tex, ModelBase modelbase)
	{
		super(tex, modelbase);
	}

	public SkullRendererCube(String tex, ModelBase ... modelbase)
	{
		super(tex, modelbase);
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void doTranslate(int direction, float rotation, EnumSkullRenderType type)
	{
		if (type == EnumSkullRenderType.EQUIPPED)
		{
			GL11.glTranslatef(0.0F, -0.25F, 0.0F);
		}
	}
}
