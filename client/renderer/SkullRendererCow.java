package ayamitsu.mobskullsplus.client.renderer;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.client.*;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererCow extends SkullRendererDefault
{
	public SkullRendererCow(int tex, ModelBase modelbase)
	{
		super(tex, modelbase);
	}
	
	public SkullRendererCow(int tex, ModelBase ... modelbase)
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