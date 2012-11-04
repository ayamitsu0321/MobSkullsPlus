package ayamitsu.mobskullsplus;

import net.minecraft.src.*;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class RenderSkullItem implements IItemRenderer
{
	/**
	 * ItemRenderType
	 *  ENTITY,
	 *  EQUIPPED,
	 *  INVENTORY,
	 *  FIRST_PERSON_MAP
	 *
	 * ItemRendererHelper
	 *  ENTITY_ROTATION,
	 *  ENTITY_BOBBING,
	 *  EQUIPPED_BLOCK,
	 *  BLOCK_3D,
	 *  INVENTORY_BLOCK
	 */
	
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type == ItemRenderType.EQUIPPED && MobSkullsList.contains(item.getItemDamage());
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return  !(type == ItemRenderType.EQUIPPED || type == ItemRenderType.FIRST_PERSON_MAP || helper == ItemRendererHelper.ENTITY_ROTATION || type == ItemRenderType.INVENTORY);
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack is, Object... data)
	{
		ISkullRenderer renderer = MobSkullsList.getSkullRenderer(is.getItemDamage());
		renderer.renderSkull(1, 0F, EnumSkullRenderType.EQUIPPED);
	}
}