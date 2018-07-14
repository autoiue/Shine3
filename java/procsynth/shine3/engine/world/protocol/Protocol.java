// Protocol.java

package procsynth.shine3.engine.world.protocol;

import java.util.logging.Logger;

/**
 * Implements a protocol to communicate with the world.
 *
 * i.e.: Ola, Serial, MIDI, ...
 */
public abstract class Protocol {

	protected final Logger log = Logger.getLogger(this.getClass().getName());

	public Protocol(){}
	public abstract void tick();

}
