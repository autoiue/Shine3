// Or.java

package procsynth.shine3.engine.blocks;

import procsynth.shine3.engine.Block;

/**
 * An 'OR' block.
 *
 * @author procsynth - Antoine Pintout
 * @since v0.0.1
 *
 */
public final class Or extends Block {
	public Or() {
		this.displayName = "or";
		newInput("A", false);
		newInput("B", false);
		newOutput("A & B", false);
	}

	public void tick() {
		set("A & B", (Boolean) get("A") && (Boolean) get("B"));
	}
}
