/**
 *  Main program for Shine3
 *
 *	@author procsynth - Antoine Pintout
 *	@since  24-09-2016`
 */

package procsynth.shine3;

import java.net.URLClassLoader;
import java.net.URL;
import java.util.jar.Manifest;
import java.util.jar.Attributes;
import java.io.IOException;
import processing.core.*;
import processing.event.*;

public class Shine3 extends PApplet{

	public static String VERSION = "unknow";
	private static final String[] PAPPLET_ARGS = new String[] { "procsynth.shine3.Shine3" };

	public static Shine3 m;

	public static void main(String[] args) {
		PApplet.main(PAPPLET_ARGS);
	}

	public void settings() {
		size(1280, 720, PApplet.P2D);
	}

	public void setup() {
		VERSION = getVersion();
		println("Shine3 "+ VERSION + " / procsynth");
		
		m = this;
		frameRate(60);
		surface.setResizable(true);
	}

	public void draw() {
		background(0x26, 0x32, 0x38); // BLUE_GREY._900
	}

	private String getVersion(){
		String version = "unknow";
		URLClassLoader cl = (URLClassLoader) getClass().getClassLoader();
		try {
			URL url = cl.findResource("META-INF/MANIFEST.MF");
			Manifest manifest = new Manifest(url.openStream());
			Attributes attr = manifest.getMainAttributes();
			version = attr.getValue("Specification-Version");
		} catch (IOException E) {
		}
		return version;
	}
}
