// BlockFactory.java

package procsynth.shine3.engine;

import java.util.logging.Logger;

/**
 * A BlockFactory make blocks. It is use when a system cannont be represented by
 * only one Block, for example, the MIDI protocol can be handled in the factory
 * and each block from the factory will represent a different bus.
 *
 * @author procsynth - Antoine Pintout
 * @since v0.0.1
 *
 * @see Block
 * @see BlockEngine
 */
public abstract class BlockFactory {

	private final Logger log = Logger.getLogger(this.getClass().getName());

	public BlockFactory() {
	}
}
