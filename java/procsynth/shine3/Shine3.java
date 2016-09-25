// Shine3.java

package procsynth.shine3;

import java.net.URLClassLoader;
import java.net.URL;
import java.util.jar.Manifest;
import java.util.jar.Attributes;
import java.io.IOException;

import procsynth.shine3.engine.BlockEngine;
import procsynth.shine3.world.World;
import procsynth.shine3.papplet.EngineInterface;

/**
 * Main class for Shine3
 *
 *	@author procsynth - Antoine Pintout
 *	@since  v0.0.1
 */
public class Shine3{

	/**
	* Holds the version string retrieved by getVersion()
	*
	* @see #getVersion
	*/
	public static String VERSION = "unknow";

	/**
	* Holds the main application objects to be quickly retrieved by blocks.
	*
	* @see Block
	*/
	public static Shine3 S3;
	public static BlockEngine engine;
	public static World world;

	/**
	* Initialize the main object.
	*
	* @see #Shine3
	*/
	public static void main(String[] args) {

		S3 = new Shine3();
		
	}

	/**
	*	Initialize the application objects.
	* 	Shine3 is composed by three main parts :
	* 	The {@link BlockEngine} is the heart of Shine3, it is composed by multiple {@link Block} objects with inputs and outputs that we can wire the way we want. The specific blocks used in Shine3 are in the package <code>procsynth.shine3.shine</code> it is fed in the engine after the instanciation using {@link BlockEngine#feed}.
	* 	The {@link EngineInterface} is a GUI powered by Processing which manipulates the state of the BlockEngine and the blocks.
	* 	{@link World} manage a description of states of the real world. It is manipulated by the Block objects described in the package <code>procsynth.shine3.shine</code>.
	*
	*	@see BlockEngine
	* 	@see BlockEngine#feed
	* 	@see Block
	*	@see World
	*	@see EngineInterface
	*/
	private Shine3(){
		VERSION = getVersion();
		System.out.println("Shine3 "+ VERSION +" / procsynth");
		

		engine = new BlockEngine();
		world = new World();
		engine.feed("procsynth.shine3.shine");
		
		new EngineInterface(engine);
	}

	
	/**
	*	Retrieves the version string from the manifest.
	* 	The version string describe the state of the git repository.
	*	It is obtained by the command <code>git describe --tags --dirty --always</code>
	*
	* 	@return the version string
	*/
	private String getVersion(){
		String version = "unknow";
		// Load the jar ressources
		URLClassLoader cl = (URLClassLoader) getClass().getClassLoader();
		// In the manifest file, retrieve the field that contains the version string
		try {
			URL url = cl.findResource("META-INF/MANIFEST.MF");
			Manifest manifest = new Manifest(url.openStream());
			Attributes attr = manifest.getMainAttributes();
			version = attr.getValue("Specification-Version");
		} catch (IOException E) {}
		return version;
	}
}
