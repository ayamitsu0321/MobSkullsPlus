package ayamitsu.mobskullsplus;

import net.minecraft.src.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.Random;

public class RenderSkullItem implements IItemRenderer
{
	/**
	 * ItemRenderType
	 *  ENTITY, 2D
	 *  EQUIPPED, 2D & 3D
	 *  INVENTORY, 2D
	 *  FIRST_PERSON_MAP 2D
	 *
	 * ItemRendererHelper
	 *  ENTITY_ROTATION,
	 *  ENTITY_BOBBING,
	 *  EQUIPPED_BLOCK,
	 *  BLOCK_3D,
	 *  INVENTORY_BLOCK
	 */
	
	public Random random = new Random();
	public RenderManager renderManager = RenderManager.instance;
	public RenderEngine renderEngine = ModLoader.getMinecraftInstance().renderEngine;
	public Minecraft mc = ModLoader.getMinecraftInstance();
	
	@Override
	public boolean handleRenderType(ItemStack is, ItemRenderType type)
	{
		return type != ItemRenderType.EQUIPPED && is != null && MobSkullsList.contains(is.getItemDamage());
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		// 頭に被ったときの描画
		if (type == ItemRenderType.EQUIPPED && helper == ItemRendererHelper.BLOCK_3D)
		{
			return true;
		}
		
		return  !(type == ItemRenderType.EQUIPPED || type == ItemRenderType.FIRST_PERSON_MAP || helper == ItemRendererHelper.ENTITY_ROTATION || type == ItemRenderType.INVENTORY);
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack is, Object... data)
	{
		if (type == ItemRenderType.EQUIPPED)
		{
			ISkullRenderer renderer = MobSkullsList.getSkullRenderer(is.getItemDamage());
			renderer.renderSkull(1, 0F, EnumSkullRenderType.EQUIPPED);
		}
		else
		{
			GL11.glPushMatrix();
			this.loadTexture(is.getItem().getTextureFile());// テクスチャのbind
			Item item = is.getItem();
			int icon = item.getIconFromDamage(is.getItemDamage());// テクスチャ上の番号
			
			// 複数テクスチャ
			if (item.requiresMultipleRenderPasses())
			{
				this.doRenderItem(is, type, icon, 0);
				
				for (int layer = 1; layer < item.getRenderPasses(is.getItemDamage()); layer++)
				{
					this.doRenderItem(is, type, icon, layer);
				}
			}
			else
			{
				this.doRenderItem(is, type, icon, 0);
			}
			
			GL11.glPopMatrix();
		}
	}
	
	public void loadTexture(String texture)
	{
		this.renderEngine.bindTexture(renderEngine.getTexture(texture));
	}
	
	public void doRenderItem(ItemStack is, ItemRenderType type, int icon, int layer)
	{
		Item item = is.getItem();
		int color = item.getColorFromItemStack(is, layer);// 乗算する色
		float r = (float)(color >> 16 & 255) / 255.0F;
		float g = (float)(color >> 8 & 255) / 255.0F;
		float b = (float)(color & 255) / 255.0F;
		GL11.glColor4f(r, g, b, 1.0F);// 乗算
		
		//if (type == ItemRenderType.ENTITY) {}
		
		/*if (type == ItemRenderType.EQUIPPED)// 手に持ってるとき
		{
			this.renderEquipped(is, icon);
		}
		else */if (type == ItemRenderType.INVENTORY)// GUIのとこ
		{
            this.renderTexturedQuad(0, 0, icon % 16 * 16, icon / 16 * 16, 16, 16);
		}
		else if (type != ItemRenderType.EQUIPPED)
		{
			this.renderEntityItem(icon, is.stackSize);// EntityItem
		}
	}
	
	//from RenderItem、EntityItemのRender
	public void renderEntityItem(int par1, int par2)
    {
        Tessellator var3 = Tessellator.instance;
        float var4 = (float)((par1 % 16) * 16 + 0) / 256.0F;
        float var5 = (float)((par1 % 16) * 16 + 16) / 256.0F;
        float var6 = (float)((par1 / 16) * 16 + 0) / 256.0F;
        float var7 = (float)((par1 / 16) * 16 + 16) / 256.0F;
        float var8 = 1.0F;
        float var9 = 0.5F;
        float var10 = 0.25F;

        for (int var11 = 0; var11 < par2; ++var11)
        {
            GL11.glPushMatrix();

            if (var11 > 0)
            {
                float var12 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var13 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
                float var14 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
                GL11.glTranslatef(var12, var13, var14);
            }
        	
            GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            var3.startDrawingQuads();
            var3.setNormal(0.0F, 1.0F, 0.0F);
            var3.addVertexWithUV((double)(0.0F - var9), (double)(0.0F - var10), 0.0D, (double)var4, (double)var7);
            var3.addVertexWithUV((double)(var8 - var9), (double)(0.0F - var10), 0.0D, (double)var5, (double)var7);
            var3.addVertexWithUV((double)(var8 - var9), (double)(1.0F - var10), 0.0D, (double)var5, (double)var6);
            var3.addVertexWithUV((double)(0.0F - var9), (double)(1.0F - var10), 0.0D, (double)var4, (double)var6);
            var3.draw();
            GL11.glPopMatrix();
        }
    }
	
	//手にもってるときのRender
	public void renderEquipped(ItemStack is, int icon)
	{
		//RenderPlayer
		Tessellator var4 = Tessellator.instance;
		Item item = is.getItem();
		int tex = icon;
		float var6 = ((float)(tex % 16 * 16) + 0.0F) / 256.0F;
        float var7 = ((float)(tex % 16 * 16) + 15.99F) / 256.0F;
        float var8 = ((float)(tex / 16 * 16) + 0.0F) / 256.0F;
        float var9 = ((float)(tex / 16 * 16) + 15.99F) / 256.0F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		//draw
		this.renderItemIn2D(var4, var7, var8, var6, var9);
		
		//エフェクトをもってるなら
		if (item.hasEffect(is))
		{
			GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_LIGHTING);
            this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("%blur%/misc/glint.png"));
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            float var13 = 0.76F;
            GL11.glColor4f(0.5F * var13, 0.25F * var13, 0.8F * var13, 1.0F);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            float var14 = 0.125F;
            GL11.glScalef(var14, var14, var14);
            float var15 = (float)(System.currentTimeMillis() % 3000L) / 3000.0F * 8.0F;
            GL11.glTranslatef(var15, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            this.renderItemIn2D(var4, 0.0F, 0.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(var14, var14, var14);
            var15 = (float)(System.currentTimeMillis() % 4873L) / 4873.0F * 8.0F;
            GL11.glTranslatef(-var15, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            this.renderItemIn2D(var4, 0.0F, 0.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
		}
		
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}
	
	//手にもってるときのRender
	public void renderItemIn2D(Tessellator par1Tessellator, float par2, float par3, float par4, float par5)
    {
        float var6 = 1.0F;
        float var7 = 0.0625F;
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 0.0F, 1.0F);
        par1Tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)par2, (double)par5);
        par1Tessellator.addVertexWithUV((double)var6, 0.0D, 0.0D, (double)par4, (double)par5);
        par1Tessellator.addVertexWithUV((double)var6, 1.0D, 0.0D, (double)par4, (double)par3);
        par1Tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, (double)par2, (double)par3);
        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 0.0F, -1.0F);
        par1Tessellator.addVertexWithUV(0.0D, 1.0D, (double)(0.0F - var7), (double)par2, (double)par3);
        par1Tessellator.addVertexWithUV((double)var6, 1.0D, (double)(0.0F - var7), (double)par4, (double)par3);
        par1Tessellator.addVertexWithUV((double)var6, 0.0D, (double)(0.0F - var7), (double)par4, (double)par5);
        par1Tessellator.addVertexWithUV(0.0D, 0.0D, (double)(0.0F - var7), (double)par2, (double)par5);
        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        int var8;
        float var9;
        float var10;
        float var11;

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = (float)var8 / 16.0F;
            var10 = par2 + (par4 - par2) * var9 - 0.001953125F;
            var11 = var6 * var9;
            par1Tessellator.addVertexWithUV((double)var11, 0.0D, (double)(0.0F - var7), (double)var10, (double)par5);
            par1Tessellator.addVertexWithUV((double)var11, 0.0D, 0.0D, (double)var10, (double)par5);
            par1Tessellator.addVertexWithUV((double)var11, 1.0D, 0.0D, (double)var10, (double)par3);
            par1Tessellator.addVertexWithUV((double)var11, 1.0D, (double)(0.0F - var7), (double)var10, (double)par3);
        }

        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(1.0F, 0.0F, 0.0F);

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = (float)var8 / 16.0F;
            var10 = par2 + (par4 - par2) * var9 - 0.001953125F;
            var11 = var6 * var9 + 0.0625F;
            par1Tessellator.addVertexWithUV((double)var11, 1.0D, (double)(0.0F - var7), (double)var10, (double)par3);
            par1Tessellator.addVertexWithUV((double)var11, 1.0D, 0.0D, (double)var10, (double)par3);
            par1Tessellator.addVertexWithUV((double)var11, 0.0D, 0.0D, (double)var10, (double)par5);
            par1Tessellator.addVertexWithUV((double)var11, 0.0D, (double)(0.0F - var7), (double)var10, (double)par5);
        }

        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = (float)var8 / 16.0F;
            var10 = par5 + (par3 - par5) * var9 - 0.001953125F;
            var11 = var6 * var9 + 0.0625F;
            par1Tessellator.addVertexWithUV(0.0D, (double)var11, 0.0D, (double)par2, (double)var10);
            par1Tessellator.addVertexWithUV((double)var6, (double)var11, 0.0D, (double)par4, (double)var10);
            par1Tessellator.addVertexWithUV((double)var6, (double)var11, (double)(0.0F - var7), (double)par4, (double)var10);
            par1Tessellator.addVertexWithUV(0.0D, (double)var11, (double)(0.0F - var7), (double)par2, (double)var10);
        }

        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, -1.0F, 0.0F);

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = (float)var8 / 16.0F;
            var10 = par5 + (par3 - par5) * var9 - 0.001953125F;
            var11 = var6 * var9;
            par1Tessellator.addVertexWithUV((double)var6, (double)var11, 0.0D, (double)par4, (double)var10);
            par1Tessellator.addVertexWithUV(0.0D, (double)var11, 0.0D, (double)par2, (double)var10);
            par1Tessellator.addVertexWithUV(0.0D, (double)var11, (double)(0.0F - var7), (double)par2, (double)var10);
            par1Tessellator.addVertexWithUV((double)var6, (double)var11, (double)(0.0F - var7), (double)par4, (double)var10);
        }

        par1Tessellator.draw();
    }
	
	//GUIの中のItemのRender
	public void renderTexturedQuad(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), 0D, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + par6) * var8));
        var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), 0D, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + par6) * var8));
        var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), 0D, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + 0) * var8));
        var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), 0D, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + 0) * var8));
        var9.draw();
    }
	
	
}