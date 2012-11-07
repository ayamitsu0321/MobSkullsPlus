package ayamitsu.mobskullsplus.renderer;

import ayamitsu.mobskullsplus.ISkullRenderer;
import ayamitsu.mobskullsplus.EnumSkullRenderType;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererMulti implements ISkullRenderer
{
	/** �ő�̃T�C�Y��1.0F�Ƒz�� */
	public static final float MIN = 0.0F;
	public static final float MAX = 1.0F;
	ModelBase mainModel;
	ModelBase subModel;
	private String mainTex = "";
	private String subTex = "";
	private float xSize = 0.5F;
	private float ySize = 0.5F;
	private float zSize = 0.5F;
	private boolean mainAlpha = false;
	private boolean subAlpha = false;
	private final int spriteIndex;
	private boolean subAlphaSpecial = false;;
	
	public SkullRendererMulti(int tex, ModelBase modelbase, ModelBase modelbase1)
	{
		this.mainModel = modelbase;
		this.subModel = modelbase1;
		this.spriteIndex = tex;
	}
	
	public SkullRendererMulti setTextureFile(String str, String str1)
	{
		this.mainTex = str;
		this.subTex = str1;
		return this;
	}
	
	public SkullRendererMulti setSize(float x, float y, float z)
	{
		this.xSize = x;
		this.ySize = y;
		this.zSize = z;
		return this;
	}
	
	public SkullRendererMulti setAlpha(boolean flag)
	{
		return this.setAlpha(flag, flag);
	}
	
	public SkullRendererMulti setAlpha(boolean flag, boolean flag1)
	{
		this.mainAlpha = flag;
		this.subAlpha = flag1;
		return this;
	}
		
	public SkullRendererMulti setAlphaSpecial(boolean flag)
	{
		this.subAlphaSpecial = flag;
		
		if (flag)
		{
			this.setAlpha(flag);
		}
		
		return this;
	}
	
	@Override
	public int getSpriteIndex(int meta)
	{
		return this.spriteIndex;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void renderSkull(int direction, float par5, EnumSkullRenderType type)
	{
		this.bindTextureByName(this.mainTex);
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
		
		float var10 = 0.0625F;
		
		if (this.mainAlpha)
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
		
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
		this.mainModel.render((Entity)null, 0.0F, 0.0F, 0.0F, par5, 0.0F, var10);
		GL11.glDisable(GL11.GL_BLEND);
		this.bindTextureByName(this.subTex);
		
		if (this.subAlpha)
		{
			GL11.glEnable(GL11.GL_BLEND);
			
			if (!this.subAlphaSpecial)
			{
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}
			else
			{
				float var4 = 1.0F;
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				GL11.glDisable(GL11.GL_LIGHTING);
				//GL11.glDepthMask(true);//
				char var5 = 61680;
				int var6 = var5 % 65536;
				int var7 = var5 / 65536;
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var6 / 1.0F, (float)var7 / 1.0F);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
			}
			
		}
		
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
    }
}