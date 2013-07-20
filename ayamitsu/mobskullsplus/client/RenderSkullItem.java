package ayamitsu.mobskullsplus.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

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

	@Override
	public boolean handleRenderType(ItemStack is, ItemRenderType type)
	{
		return type == ItemRenderType.EQUIPPED && is != null && RendererRegistry.contains(is.getItemDamage());
	}

	// 回転とかするのをやってもらうかどうかの判定
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return type == ItemRenderType.EQUIPPED && helper == ItemRendererHelper.BLOCK_3D;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack is, Object... data)
	{
		// 装備してる場合
		if (type == ItemRenderType.EQUIPPED)
		{
			GL11.glPushMatrix();
			// references from ForgeHooksClient renderEquippedItem
			GL11.glTranslatef(0.9375F, 0.0625F, 0.0F);
			GL11.glRotatef(335.0F, 0.0F, 0.0F, -1.0F);
			GL11.glRotatef(50.0F, 0.0F, -1.0F, 0.0F);
			GL11.glRotatef(90.0F, 0.0F, -1.0F, 0.0F);
			GL11.glTranslatef(0.0F, -0.0625F, 0.0F);
			GL11.glScalef(1.1875F, 1.1875F, 1.1875F);

			ISkullRenderer renderer = RendererRegistry.getSkullRenderer(is.getItemDamage());
			renderer.renderSkull(1, 0F, EnumSkullRenderType.EQUIPPED);
			GL11.glPopMatrix();
		}
	}

}