// Modules.java

package procsynth.shine3;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

/**
 * Manage the loading of external jars.
 *
 * @author procsynth - Antoine Pintout
 * @since v0.0.1
 */

public class Modules extends URLClassLoader {

	private final Logger log = Logger.getLogger(this.getClass().getName());

	public Modules() {
		super(new URL[0]);
	}

	/**
	 * Scan the system and externally loaded classpaths to find all final subclasses
	 * of a superclasses.
	 *
	 * @param c
	 *            the superclass to match against classes in classpath
	 * @return the class matching the criteria
	 */
	public List<Class<?>> getClasses(Class c) {
		List<Class<?>> subclasses = new ArrayList<>();
		List<Class<?>> availableSubclasses = new ArrayList<>();

		// Scan for any class that inherits from c class
		FastClasspathScanner FCS = new FastClasspathScanner();
		FCS.addClassLoader(this);
		FCS.matchSubclassesOf(c, subclasses::add).scan();

		// Filter classes that aren't final to avoid to use incomplete implementations
		for (Class<?> b : subclasses) {
			if (Modifier.isFinal(b.getModifiers())) {
				availableSubclasses.add(b);
				log.fine("found: " + b.getName() + " (" + c.getSimpleName() + ")");
				if (b.getClassLoader() != null)
					log.fine(" < " + b.getClassLoader().toString());
			}
		}

		return availableSubclasses;
	}

	/**
	 * Look into a folder for .jar files and add them to classpath
	 * 
	 * @param folder
	 *            the folder in which to look for jar files
	 */
	public void addPath(String folder) {
		try {
			File f = new File(folder);
			File[] listOfFiles = f.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					addURL(listOfFiles[i].toURI().toURL());
					log.info("Added " + listOfFiles[i].toString() + " to classpath.");
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.info("Failed to load " + folder);
		}
	}

}