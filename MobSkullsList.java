package ayamitsu.mobskullsplus;

import java.util.Map;
import java.util.HashMap;

public final class MobSkullsList
{
	private static boolean hasInit = false;
	private static Map<Integer, ISkullRenderer> skullList;
	/*private static final int[] ids = new int[] {
		50, // creeper
		51, // skeleton
		52, // spider
		54, // zombie
		55, // slime
		56, // ghast
		57, // pigzombie
		58, // enderman
		59, // cave_spider
		61, // blaze
		62, // magmacube
		63, // dragon
		64, // wither
		65, // bat
		66, // witch
		90, // pig
		91, // sheep
		92, // cow
		93, // chicken
		94, // squid ‚­º:œc
		95, // wolf
		96, // mushroom_cow
		97, // snowman
		98, // ocelot
		99, // iron_golem
		120,// villager
	};*/
	
	private static void init()
	{
		if (hasInit)
		{
			return;
		}
		
		hasInit = true;
		skullList = new HashMap<Integer, ISkullRenderer>();
	}
	
	public static void registerSkullRenderer(int entityId, ISkullRenderer renderer)
	{
		skullList.put(Integer.valueOf(entityId), renderer);
	}
	
	public static ISkullRenderer getSkullRenderer(int entityId)
	{
		return skullList.get(Integer.valueOf(entityId));
	}
	
	public static Map getMap()
	{
		return skullList;
	}
	
	static
	{
		init();
	}
}