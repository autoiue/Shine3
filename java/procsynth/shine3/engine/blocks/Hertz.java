// Hertz.java

package procsynth.shine3.engine.blocks;

import procsynth.shine3.engine.Block;

/**
 * A block that is switching its output to 1 or 0 every second.
 *
 * @author procsynth - Antoine Pintout
 * @since v0.0.1
 *
 */
public final class Hertz extends Block {
	public Hertz() {
		this.displayName = "hertz";
		newOutput("hertz", false);
	}

	public void tick() {
		set("hertz", System.currentTimeMillis() % 2000 >= 1000);
	}
}
