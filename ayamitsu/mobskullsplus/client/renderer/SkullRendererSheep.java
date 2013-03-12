package ayamitsu.mobskullsplus.client.renderer;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import ayamitsu.mobskullsplus.client.EnumSkullRenderType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkullRendererSheep extends SkullRendererMulti
{
	public SkullRendererSheep(String tex, ModelBase modelbase, ModelBase modelbase1)
	{
		super(tex, modelbase, modelbase1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void doTranslate(int direction, float rotation, EnumSkullRenderType type)
	{
		super.doTranslate(direction, rotation, type);
		GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
	}

	@Override
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