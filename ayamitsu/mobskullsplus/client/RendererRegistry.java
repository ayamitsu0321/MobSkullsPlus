package ayamitsu.mobskullsplus.client;

import java.util.HashMap;
import java.util.Map;

public final class RendererRegistry
{
	private static Map<Integer, ISkullRenderer> rendererMap = new HashMap<Integer, ISkullRenderer>();;

	public static void registerSkullRenderer(int id, ISkullRenderer renderer)
	{
		rendererMap.put(Integer.valueOf(id), renderer);
	}

	public static ISkullRenderer getSkullRenderer(int id)
	{
		return rendererMap.get(Integer.valueOf(id));
	}

	public static boolean contains(int id)
	{
		return  rendererMap.containsKey(Integer.valueOf(id));
	}

	public static Map<Integer, ISkullRenderer> getMap()
	{
		return rendererMap;
	}

}