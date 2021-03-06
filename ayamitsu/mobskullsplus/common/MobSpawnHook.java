package ayamitsu.mobskullsplus.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import ayamitsu.mobskullsplus.MobSkullsPlus;

public class MobSpawnHook
{
	public MobSpawnHook() {}

	@ForgeSubscribe
	public void onLivinfSpecialSpawn(LivingSpawnEvent event)
	{
		if (event.entityLiving == null)
		{
			return;
		}

		EntityLivingBase mob = event.entityLiving;

		if (mob instanceof EntityZombie && !((EntityZombie)mob).isVillager())
		{
			ItemStack helmet = ((EntityZombie)mob).func_130225_q(3);

			if (helmet == null && mob.getRNG().nextInt(128) == 0)
			{
				//System.out.println("SET");
				int meta = mob.getRNG().nextInt(BlockBoundsRegistry.getMap().size());
				mob.setCurrentItemOrArmor(4, new ItemStack(MobSkullsPlus.skull.blockID, 1, meta));
			}
		}
	}
}