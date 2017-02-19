package procsynth.shine3.papplet;

import procsynth.shine3.utils.*;
import procsynth.shine3.engine.*;
import java.util.*;

/**
*	A class te generate different maps of the block tree.
*	@see EngineInterface
*/
class Mapgen{
	private static Map<Pair<Integer, Integer>, Block> map = new HashMap();
	private static Map<Integer, Integer> nextSpotOnLevel = new HashMap();
	public static int maxSpot = 0;
	public static int maxLevel = 0;
	public static int level = 0;

	/**
	 * @param blocks the block tree
	 * @return the block tree mapped on a 2D plane
	 */
	public static Map<Pair<Integer, Integer>, Block> getOrthoMap(List<Block> blocks){

		for(Block b : blocks){
			// if block not already in map
			if(!map.containsValue(b)){
				place(b, level);
				// level --;
			}
		}

		return map;
	}

	/**
	 * @param origin the block from which begin the tree
	 * @param level the level of that block in the tree
	 */
	private static void place(Block origin, int level){
		int nextSpot = 0;
		maxLevel = Math.max(level, maxLevel);

		if(nextSpotOnLevel.get(level) != null){
			nextSpot = nextSpotOnLevel.get(level);
			nextSpotOnLevel.put(level, nextSpot + 1);
			maxSpot = Math.max(nextSpot, maxSpot);
		}else{
			nextSpotOnLevel.put(level, 0);
		}

		if(!map.containsValue(origin)){
			map.put(new Pair(level, nextSpot), origin);
		}

		for(Input i : origin.getInputs().values()){
			Block source = i.getSourceBlock();
			if(source != null && !map.containsValue(source)){
				place(source, level + 1);
			}
		}
	}
}