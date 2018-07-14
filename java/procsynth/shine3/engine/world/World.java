// World.java

package procsynth.shine3.engine.world;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import procsynth.shine3.Shine3;
import procsynth.shine3.engine.Block;
import procsynth.shine3.engine.Input;
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

	/** @return available protocols */
	public List<Class<?>> getAvailableProtocols() {
		return new ArrayList(availableProtocols);
	}

	/** List available Resources */
	private List<Class<?>> availableResources = new ArrayList<>();

	/** Engine to real world input */
	public final Input<State> mainInput = new Input<>("input", new State());


	/** World protocols */
	private List<Protocol> protocols = new ArrayList<>();
	/** World resources */
	private List<Resource> resources = new ArrayList<>();

	public World() {
		availableProtocols = Shine3.modules.getClasses(Protocol.class);
		availableResources = Shine3.modules.getClasses(Resource.class);

		for(Class<?> protoClass : availableProtocols) {
			addProto(protoClass);
		}
	}

	public void addProto(Class<?> protoClass) {
		if (availableProtocols.indexOf(protoClass) != -1) {
			try {
				protocols.add((Protocol) protoClass.newInstance());
				log.info("Added :" + protoClass.getSimpleName());
			} catch (InstantiationException | IllegalAccessException e) {
				log.severe(e.getMessage());
			}
		}
	}

	/** Called by the engine before the resolution of all blocks */
	public void tick(){

		for(Protocol proto : protocols){
			proto.tick();
		}

		for(Resource res : resources){
			res.tick();
		}
	}

	/** Called by the engine after the resolution of all blocks */
	public void apply(){

	}
}
