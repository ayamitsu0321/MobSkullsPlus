package ayamitsu.mobskullsplus.renderer;

import ayamitsu.mobskullsplus.ISkullRenderer;
import ayamitsu.mobskullsplus.EnumSkullRenderType;

import net.minecraft.src.*;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SkullRendererDefault extends SkullRendererBase
{
	protected float defaultScale = 1.0F;
	
	public SkullRendererDefault(int tex, ModelBase modelbase)
	{
		this(tex, new ModelBase[] { modelbase });
	}
	
	public SkullRendererDefault(int tex, ModelBase ... modelbase)
	{
		super(modelbase);
		this.spriteIndex = tex;
	}
	
	public SkullRendererBase setScale(float f)
	{
		this.defaultScale = f;
		return this;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected void callBackScale(int direction, float rotation, EnumSkullRenderType type)
	{
		GL11.glScalef(this.defaultScale, this.defaultScale, this.defaultScale);
	}
}