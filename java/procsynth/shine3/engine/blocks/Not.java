// Not.java

package procsynth.shine3.engine.blocks;

import procsynth.shine3.engine.*;

/**
 *  An 'NOT' block.
 *
 *	@author procsynth - Antoine Pintout
 *	@since  v0.0.1
 *
 */
public final class Not extends Block{
	public Not(){
		this.displayName = "not";
		newInput("A", false);
		newOutput("NOT A", false);
	}

	public void tick(){
		set("NOT A", !(Boolean)get("A"));
	}
}

