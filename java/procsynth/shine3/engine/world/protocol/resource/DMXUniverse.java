// DMXUniverse.java

package procsynth.shine3.engine.world.protocol.resource;

import procsynth.shine3.engine.Input;
import procsynth.shine3.engine.Output;

import java.util.List;

/**
 * Communicates with DMX universes
 */
public final class DMXUniverse extends Resource {

	private Output[] readable;
	private Input[] writable;

	public DMXUniverse() {
		writable = new Input[512];
		readable = new Output[0];

		for (int i = 0; i < writable.length; i++) {
			writable[i] = new Input<Integer>(String.valueOf(i), 0);
		}
	}

	@Override
	public void tick() {

	}
}