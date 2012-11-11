package ayamitsu.mobskullsplus.client.renderer;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.client.*;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererCube extends SkullRendererDefault
{
	public SkullRendererCube(int tex, ModelBase modelbase)
	{
		super(tex, modelbase);
	}
	
	public SkullRendererCube(int tex, ModelBase ... modelbase)
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
