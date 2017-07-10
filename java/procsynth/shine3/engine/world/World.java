// World.java

package procsynth.shine3.engine.world;

import procsynth.shine3.engine.world.protocol.Protocol;
import procsynth.shine3.engine.world.protocol.resource.Resource;

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
	private List<Class<? extends Protocol>> availableProtocols = new ArrayList<>();
	/** @return available blocks */
	public List<Class<? extends Protocol>> getAvailableProtocols(){return new ArrayList(availableProtocols);}
	/**	List available Pesources */
	private List<Class<? extends Resource>> availableResources = new ArrayList<>();

	public World(){
		scanAvailableProtocols();
	}

	private void scanAvailableProtocols(){
		List<Class<? extends Protocol>> protocolSubclasses = new ArrayList<>();
		List<Class<? extends Resource>> resourceSubclasses = new ArrayList<>();

		// Scan for any class that inherits from Protocol class
		new FastClasspathScanner()
		.matchSubclassesOf(Protocol.class, protocolSubclasses::add)
		.scan();

		// Scan for any class that inherits from BlockFactory class
		new FastClasspathScanner()
		.matchSubclassesOf(Resource.class, resourceSubclasses::add)
		.scan();

		// Filter classes that aren't final to avoid to use incomplete implementations
		for(Class<? extends Protocol> b : protocolSubclasses){
			if(Modifier.isFinal(b.getModifiers())){
				availableProtocols.add(b);	
			}
		}
		System.out.println("FOUND "+availableProtocols.size()+" FINAL PROTOCOLS.");

		for(Class<? extends Resource> f : resourceSubclasses){
			if(Modifier.isFinal(f.getModifiers())){
				availableResources.add(f);	
			}
		}
		System.out.println("FOUND "+availableResources.size()+" RESOURCES.");
	}
}

