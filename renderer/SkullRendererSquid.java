package ayamitsu.mobskullsplus.renderer;

import ayamitsu.mobskullsplus.ISkullRenderer;
import ayamitsu.mobskullsplus.model.ModelSkullSquid;
import ayamitsu.mobskullsplus.EnumSkullRenderType;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererSquid implements ISkullRenderer
{
	private ModelSkullSquid model;
	
	public SkullRendererSquid()
	{
		this.model = new ModelSkullSquid();
	}
	
	@Override
	public int getSpriteIndex(int meta)
	{
		return 5;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void renderSkull(int direction, float par5, EnumSkullRenderType type)
	{
		this.bindTextureByName("/mob/squid.png");
        GL11.glDisable(GL11.GL_CULL_FACE);

		if (direction != 1)
		{
			switch (direction)
            {
	            case -1:
            		GL11.glTranslatef(0.0F, -0.25F, 0.0F);
	            	break;
                case 2:
                    GL11.glTranslatef(0.0F, 0.25F, 0.24F);
                    break;
                case 3:
                    GL11.glTranslatef(0.0F, 0.25F, -0.24F);
                    par5 = 180.0F;
                    break;
                case 4:
                    GL11.glTranslatef(0.24F, 0.25F, 0.0F);
                    par5 = 270.0F;
                    break;
                case 5:
                default:
                    GL11.glTranslatef(-0.24F, 0.25F, 0.0F);
                    par5 = 90.0F;
            }
		}
		else
		{
			float var11 = 0.125F;
			GL11.glTranslatef(0.0F, var11, 0.0F);
		}
		
		float var10 = 0.0625F;
		float scale = 0.74F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glScalef(scale, scale, scale);
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, par5, 0.0F, var10);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}
	
	@Override
	public void setBlockBounds(IBlockAccess blockaccess, int blockX, int blockY, int blockZ, Block block)
	{
		int direction = blockaccess.getBlockMetadata(blockX, blockY, blockZ) & 7;

		switch (direction)
		{
			case 1:
			default:
				block.setBlockBounds(0.225F, 0.0F, 0.225F, 0.775F, 0.75F, 0.775F);
				break;

			case 2:
				block.setBlockBounds(0.225F, 0.13F, 0.465F, 0.775F, 0.87F, 1.0F);
				break;

			case 3:
				block.setBlockBounds(0.225F, 0.13F, 0.0F, 0.775F, 0.87F, 0.535F);
				break;

			case 4:
				block.setBlockBounds(0.465F, 0.13F, 0.225F, 1.0F, 0.87F, 0.775F);
				break;

			case 5:
				block.setBlockBounds(0.0F, 0.13F, 0.225F, 0.535F, 0.87F, 0.775F);
		}
	}

	
	@SideOnly(Side.CLIENT)
	protected void bindTextureByName(String par1Str)
	{
		RenderEngine var2 = ModLoader.getMinecraftInstance().renderEngine;

		if (var2 != null)
		{
			var2.bindTexture(var2.getTexture(par1Str));
		}
	}
}
