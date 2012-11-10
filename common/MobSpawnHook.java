package ayamitsu.mobskullsplus.common;

import ayamitsu.mobskullsplus.*;
import ayamitsu.mobskullsplus.common.registry.BlockBoundsRegistry;

import net.minecraft.src.*;

import net.minecraftforge.event.entity.living.LivingSpecialSpawnEvent;
import net.minecraftforge.event.ForgeSubscribe;

import java.util.Random;

public class MobSpawnHook
{
	public MobSpawnHook() {}
	
	@ForgeSubscribe
	public void onLivinfSpecialSpawn(LivingSpecialSpawnEvent event)
	{
		if (event.entityLiving == null)
		{
			return;
		}
		
		EntityLiving mob = event.entityLiving;
		
		if (mob instanceof EntityZombie && !((EntityZombie)mob).isVillager())
		{
			ItemStack helmet = mob.getCurrentArmor(3);
			
			if (helmet == null && mob.getRNG().nextInt(64) == 0)
			{
				System.out.println("SET");
				int meta = mob.getRNG().nextInt(BlockBoundsRegistry.getMap().size());
				mob.setCurrentItemOrArmor(4, new ItemStack(MobSkullsPlus.skull.blockID, 1, meta));
			}
		}
	}
}