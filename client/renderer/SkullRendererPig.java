package ayamitsu.mobskullsplus.client.renderer;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.client.*;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererPig extends SkullRendererMulti
{
	public SkullRendererPig(int tex, ModelBase modelbase, ModelBase modelbase1)
	{
		super(tex, modelbase, modelbase1);
	}
	
	@SideOnly(Side.CLIENT)
	protected void callBackScale(int direction, float rotation, EnumSkullRenderType type)
	{
		float scale = 0.8125F;
		GL11.glScalef(scale, scale, scale);
	}

	@SideOnly(Side.CLIENT)
	protected void doTranslate(int direction, float rotation, EnumSkullRenderType type)
	{
		super.doTranslate(direction, rotation, type);
		
		if (direction != 1)
		{
			GL11.glTranslatef(0.0F, 0.046875F, 0.0F);
		}
		else
		{
			GL11.glTranslatef(0.0F, 0.025F, 0.0F);
		}
	}
}