// And.java

package procsynth.shine3.engine.blocks;

import procsynth.shine3.engine.Block;

/**
 * An 'AND' block.
 *
 * @author procsynth - Antoine Pintout
 * @since v0.0.1
 *
 */
public final class And extends Block {
	public And() {
		this.displayName = "and";
		newInput("A", false);
		newInput("B", false);
		newOutput("A & B", false);
	}

	public void tick() {
		set("A & B", (Boolean) get("A") && (Boolean) get("B"));
	}
}
