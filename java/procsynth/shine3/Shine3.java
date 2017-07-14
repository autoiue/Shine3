// Shine3.java

package procsynth.shine3;

import java.net.URLClassLoader;
import java.net.URL;
import java.util.jar.Manifest;
import java.util.jar.Attributes;
import java.io.IOException;
import java.security.Policy;

import procsynth.shine3.engine.*;
import procsynth.shine3.papplet.*;

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

	/** Self reference of main class.*/
	public static Shine3 S3;

	/** Self reference of main class.*/
	public static Modules modules;

	/** The BlockEngine instance.*/
	public static BlockEngine engine;

	/** The EngineInterface instance.*/
	public static EngineInterface papplet;

	/**
	* Initialize the main object.
	*
	* @param args the command line arguments passed at `java`
	*
	* @see #Shine3
	*/
	public static void main(String[] args) {
		Policy.setPolicy(new ModulesPermissions());
  		System.setSecurityManager(new SecurityManager());   

		modules = new Modules();
		modules.addPath(System.getProperty("user.home")+"/.shine3/modules/");
		S3 = new Shine3();
	}

	/**
	*	Initialize the application objects.
	* 	Shine3 is composed by three main parts :
	* 	The {@link BlockEngine} is the heart of Shine3, it is composed by multiple {@link Block} objects with inputs and outputs that we can wire the way we want. 
	*	The specific blocks used in Shine3 are in the package <code>procsynth.shine3.shine</code> it is fed in the engine after the instanciation using usig autodiscoveryb.
	* 	The {@link EngineInterface} is a GUI powered by Processing which manipulates the state of the BlockEngine and the blocks.
	*
	*	@see BlockEngine
	* 	@see Block
	*	@see EngineInterface
	*/
	private Shine3(){
		VERSION = getVersion();
		System.out.println("Shine3 "+ VERSION +" / procsynth");

		engine = new BlockEngine();
		papplet = new EngineInterface(engine);
	}

	
	/**
	*	Retrieves the version string from the manifest.
	* 	The version string reflect the state of the git repository.
	*	It is obtained by the command <code>git describe --tags --dirty --always</code>.
	*   <br>
	*	See build.xml file
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
