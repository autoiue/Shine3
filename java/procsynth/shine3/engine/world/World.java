// World.java

package procsynth.shine3.engine.world;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import procsynth.shine3.Shine3;
import procsynth.shine3.engine.world.protocol.Protocol;
import procsynth.shine3.engine.world.protocol.resource.Resource;

/**
 * Holds real world description
 *
 * @author procsynth - Antoine Pintout
 * @since v0.0.1
 */
public class World {

	private final Logger log = Logger.getLogger(this.getClass().getName());

	/** List available protocols */
	private List<Class<?>> availableProtocols = new ArrayList<>();

	/** @return available blocks */
	public List<Class<?>> getAvailableProtocols() {
		return new ArrayList(availableProtocols);
	}

	/** List available Pesources */
	private List<Class<?>> availableResources = new ArrayList<>();

	public World() {
		availableProtocols = Shine3.modules.getClasses(Protocol.class);
		availableResources = Shine3.modules.getClasses(Resource.class);
	}
}
