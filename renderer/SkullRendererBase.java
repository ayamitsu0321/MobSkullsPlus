package ayamitsu.mobskullsplus.renderer;

import ayamitsu.mobskullsplus.ISkullRenderer;
import ayamitsu.mobskullsplus.EnumSkullRenderType;

import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.ModelBase;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public abstract class SkullRendererBase implements ISkullRenderer
{
	public static final float MIN = 0.0F;
	public static final float MAX = 1.0F;
	protected ModelBase[] models;
	protected String texture = "/mob/char.png";
	protected float xSize = 0.5F;
	protected float ySize = 0.5F;
	protected float zSize = 0.5F;
	public int spriteIndex = 0;
	
	public SkullRendererBase(ModelBase ... modelbase)
	{
		this.models = new ModelBase[modelbase.length];
		
		for (int i = 0; i < this.models.length; i++)
		{
			this.models[i] = modelbase[i];
		}
	}
	
	public SkullRendererBase setTexture(String str)
	{
		this.texture = str;
		return this;
	}
	
	public String getTexture(int count)
	{
		return texture;
	}
	
	public SkullRendererBase setSpriteIndex(int i)
	{
		this.spriteIndex = i;
		return this;
	}
	
	@Override
	public int getSpriteIndex(int meta)
	{
		return this.spriteIndex;
	}
	
	public SkullRendererBase setSize(float f, float f1, float f2)
	{
		this.xSize = f;
		this.ySize = f1;
		this.zSize = f2;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void renderSkull(int direction, float rotation, EnumSkullRenderType type)
	{
		//GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		float rot = this.getRotation(direction, rotation, type);
		
		try
		{
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			this.doTranslate(direction, rot, type);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			this.callBackScale(direction, rot, type);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			int count;
			
			for (count = 0; count < this.models.length; count++)
			{
				this.setAlpha(direction, rot, type, count);
				this.doRender(direction, rot, type, count);
			}
			
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		//GL11.glEnable(GL11.GL_CULL_FACE);
		//GL11.glPopMatrix();
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
	protected void bindTexture(String path)
    {
    	RenderEngine renderengine = ModLoader.getMinecraftInstance().renderEngine;
    	
        if (renderengine != null)
        {
            renderengine.bindTexture(renderengine.getTexture(path));
        }
    }
	
	@SideOnly(Side.CLIENT)
	protected void callBackScale(int direction, float rotation, EnumSkullRenderType type) {}
	
	@SideOnly(Side.CLIENT)
	protected void doTranslate(int direction, float rotation, EnumSkullRenderType type)
	{
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
                    break;
                case 4:
                    GL11.glTranslatef(0.24F, 0.25F, 0.0F);
                    break;
                case 5:
                default:
                    GL11.glTranslatef(-0.24F, 0.25F, 0.0F);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	protected float getRotation(int direction, float rotation, EnumSkullRenderType type)
	{
		if (direction != 1)
		{
			switch (direction)
			{
				case -1:
	            	return rotation;
                case 2:
                    return rotation;
                case 3:
                    return 180.0F;
                case 4:
                    return 270.0F;
                case 5:
                default:
                    return 90.0F;
			}
		}
		else
		{
			return rotation;
		}
	}
	
	@SideOnly(Side.CLIENT)
	protected void setAlpha(int direction, float rotation, EnumSkullRenderType type, int count) {}
	
	@SideOnly(Side.CLIENT)
	public void doRender(int direction, float rotation, EnumSkullRenderType type, int count)
	{
		this.bindTexture(this.getTexture(count));
		float var1 = 0.0625F;
		ModelBase model = models[count];
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, rotation, 0.0F, var1);
	}
}