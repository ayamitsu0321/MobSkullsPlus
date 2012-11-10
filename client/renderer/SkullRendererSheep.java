package ayamitsu.mobskullsplus.client.renderer;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.client.*;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererSheep extends SkullRendererMulti
{
	public SkullRendererSheep(int tex, ModelBase modelbase, ModelBase modelbase1)
	{
		super(tex, modelbase, modelbase1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected void doTranslate(int direction, float rotation, EnumSkullRenderType type)
	{
		super.doTranslate(direction, rotation, type);
		GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
		
		if (direction == -1)
		{
			//GL11.glTranslatef(0.0F, 0.25F, 0.0F);
		}
		
		if (type == EnumSkullRenderType.EQUIPPED)
		{
			//GL11.glTranslatef(0.0F, 0.0F, 0.0F);
		}
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
	
	/*@SideOnly(Side.CLIENT)
	@Override
	public void renderSkull(int direction, float par5, EnumSkullRenderType type)
	{
		this.bindTextureByName(this.mainTex);
        GL11.glDisable(GL11.GL_CULL_FACE);
		
		GL11.glTranslatef(0.0F, 0.0625F, 0.0F);
		
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
		
		float var10 = 0.0625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
		
		if (type == EnumSkullRenderType.EQUIPPED)
		{
			float scale = 1.25F;
			GL11.glScalef(scale, scale, scale);
		}
		
        GL11.glEnable(GL11.GL_ALPHA_TEST);
		this.mainModel.render((Entity)null, 0.0F, 0.0F, 0.0F, par5, 0.0F, var10);
		
		this.bindTextureByName(this.subTex);
		this.subModel.render((Entity)null, 0.0F, 0.0F, 0.0F, par5, 0.0F, var10);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	@Override
	public void setBlockBounds(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block)
	{
		int direction = iblockaccess.getBlockMetadata(blockX, blockY, blockZ) & 7;

        switch (direction)
        {
            case 1:
            default:
                block.setBlockBounds(this.getMinSize(this.xSize), MIN, this.getMinSize(this.xSize), this.getMaxSize(this.xSize), MIN + this.ySize, this.getMaxSize(this.zSize));
                break;
            case 2:
                block.setBlockBounds(this.getMinSize(this.xSize), this.getMinSize(this.ySize), MAX - this.zSize, this.getMaxSize(this.xSize), this.getMaxSize(this.ySize), MAX);
                break;
            case 3:
                block.setBlockBounds(this.getMinSize(this.xSize), this.getMinSize(this.ySize), MIN, this.getMaxSize(this.xSize), this.getMaxSize(this.ySize), MIN + this.zSize);
                break;
            case 4:
                block.setBlockBounds(MAX - this.xSize, this.getMinSize(this.ySize), this.getMinSize(this.zSize), MAX, this.getMaxSize(this.ySize), this.getMaxSize(this.zSize));
                break;
            case 5:
                block.setBlockBounds(MIN, this.getMinSize(this.ySize), this.getMinSize(this.zSize), MIN + this.xSize, this.getMaxSize(this.ySize), this.getMaxSize(this.zSize));
        }
	}
	
	private float getMinSize(float size)
	{
		return (MAX - size) / 2.0F;
	}
	
	private float getMaxSize(float size)
	{
		return MAX - ((MAX - size) / 2.0F);
	}
	
	@SideOnly(Side.CLIENT)
	protected void bindTextureByName(String par1Str)
    {
    	RenderEngine var2 = ModLoader.getMinecraftInstance().renderEngine;
    	
        if (var2 != null)
        {
            var2.bindTexture(var2.getTexture(par1Str));
        }
    }*/
}