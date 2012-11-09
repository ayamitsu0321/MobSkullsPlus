package ayamitsu.mobskullsplus.renderer;

import ayamitsu.mobskullsplus.ISkullRenderer;
import ayamitsu.mobskullsplus.EnumSkullRenderType;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererWolf extends SkullRendererDefault
{
	public SkullRendererWolf(int tex, ModelBase modelbase)
	{
		super(tex, modelbase);
	}
	
	public SkullRendererWolf(int tex, ModelBase ... modelbase)
	{
		super(tex, modelbase);
	}
	
	@SideOnly(Side.CLIENT)
	protected void doTranslate(int direction, float rotation, EnumSkullRenderType type)
	{
		if (type == EnumSkullRenderType.EQUIPPED)
		{
			GL11.glTranslatef(0.0F, 0.0125F, 0.0F);
		}
		
		if (direction != 1)
		{
			float f = 0.365F;// 0.24
			
			switch (direction)
			{
				case -1:
            		GL11.glTranslatef(0.0F, -0.25F, 0.0F);
	            	break;
                case 2:
                    GL11.glTranslatef(0.0F, 0.25F, f);
                    break;
                case 3:
                    GL11.glTranslatef(0.0F, 0.25F, -f);
                    break;
                case 4:
                    GL11.glTranslatef(f, 0.25F, 0.0F);
                    break;
                case 5:
                default:
                    GL11.glTranslatef(-f, 0.25F, 0.0F);
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected void callBackScale(int direction, float rotation, EnumSkullRenderType type)
	{
		if (type == EnumSkullRenderType.EQUIPPED)
		{
			GL11.glScalef(this.defaultScale, this.defaultScale, this.defaultScale);
		}
	}
}