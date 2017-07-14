// World.java

package procsynth.shine3.engine.world;

import procsynth.shine3.engine.world.protocol.Protocol;
import procsynth.shine3.engine.world.protocol.resource.Resource;
import procsynth.shine3.*;

import java.util.*;
import java.lang.reflect.Modifier;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

/**
 * Holds real world description
 *
 *	@author procsynth - Antoine Pintout
 *	@since  v0.0.1
 */
public class World{


	/**	List available protocols */
	private List<Class<?>> availableProtocols = new ArrayList<>();
	/** @return available blocks */
	public List<Class<?>> getAvailableProtocols(){return new ArrayList(availableProtocols);}
	/**	List available Pesources */
	private List<Class<?>> availableResources = new ArrayList<>();

	public World(){
		availableProtocols = Shine3.modules.getClasses(Protocol.class);
		availableResources = Shine3.modules.getClasses(Resource.class);
	}
}

