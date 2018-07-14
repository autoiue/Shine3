// Resource.java

package procsynth.shine3.engine.world.protocol.resource;

import procsynth.shine3.engine.Input;
import procsynth.shine3.engine.Output;

import java.util.logging.Logger;

/**
 * Communicates with resources using a particular protocol
 */
public abstract class Resource {

	private Output[] readable;
	private Input[] writable;

	private final Logger log = Logger.getLogger(this.getClass().getName());

	public abstract void tick();

}