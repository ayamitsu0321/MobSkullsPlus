package ayamitsu.mobskullsplus.client.renderer;

import org.lwjgl.opengl.GL11;

import ayamitsu.mobskullsplus.client.EnumSkullRenderType;
import ayamitsu.mobskullsplus.client.model.ModelSkullSquid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkullRendererSquid extends SkullRendererBase
{
	public SkullRendererSquid(String tex)
	{
		super(new ModelSkullSquid());
		this.iconPath = tex;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void callBackScale(int direction, float rotation, EnumSkullRenderType type)
	{
		GL11.glScalef(0.734375F, 0.734375F, 0.734375F);// 0.75
	}

	@SideOnly(Side.CLIENT)
	protected void doTranslate(int direction, float rotation, EnumSkullRenderType type)
	{
		if (direction != 1)
		{
			switch (direction)
			{
				case -1:
            		GL11.glTranslatef(0.0F, -0.234375F, 0.0F);
	            	break;
                case 2:
                    GL11.glTranslatef(0.0F, 0.140625F, 0.224375F);
                    break;
                case 3:
                    GL11.glTranslatef(0.0F, 0.140625F, -0.224375F);
                    break;
                case 4:
                    GL11.glTranslatef(0.224375F, 0.140625F, 0.0F);
                    break;
                case 5:
                default:
                    GL11.glTranslatef(-0.224375F, 0.140625F, 0.0F);
			}
		}
	}
}
