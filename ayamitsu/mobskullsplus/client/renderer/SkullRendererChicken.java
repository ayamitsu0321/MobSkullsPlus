package ayamitsu.mobskullsplus.client.renderer;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import ayamitsu.mobskullsplus.client.EnumSkullRenderType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkullRendererChicken extends SkullRendererDefault
{
	public SkullRendererChicken(int tex, ModelBase modelbase)
	{
		super(tex, modelbase);
	}

	public SkullRendererChicken(int tex, ModelBase ... modelbase)
	{
		super(tex, modelbase);
	}

	@SideOnly(Side.CLIENT)
	protected void doTranslate(int direction, float rotation, EnumSkullRenderType type)
	{
		if (type == EnumSkullRenderType.EQUIPPED)
		{
			GL11.glTranslatef(0.0F, 0.4375F, 0.0F);
		}

		if (direction != 1)
		{
			float f = 0.4275F;// 0.24

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
}