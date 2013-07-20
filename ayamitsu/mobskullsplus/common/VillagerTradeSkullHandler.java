package ayamitsu.mobskullsplus.common;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import ayamitsu.mobskullsplus.MobSkullsPlus;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillagerTradeSkullHandler implements IVillageTradeHandler
{
	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
	{
		if (random.nextInt(2) == 0)
		{
			int rndOri = random.nextInt(5);// vanilla skull value
			int rndMod = random.nextInt(BlockBoundsRegistry.getMap().size());// mod skull value
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MobSkullsPlus.skull.blockID, 1, rndMod), new ItemStack(Item.skull, 1, rndOri)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.skull, 1, rndOri), new ItemStack(MobSkullsPlus.skull.blockID, 1, rndMod)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.rottenFlesh), new ItemStack(Item.bone), new ItemStack(MobSkullsPlus.skull.blockID, 1, rndMod)));
		}
	}
}
