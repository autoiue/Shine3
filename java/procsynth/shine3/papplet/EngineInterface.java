// EngineInterface.java

package procsynth.shine3.papplet;

import procsynth.shine3.engine.*;
import procsynth.shine3.*;

import processing.core.*;
import processing.event.*;

/**
 *  PApplet graphic interface for Shine3 engine.
 *  The EngineInterface is a Processing sketch that render the state of an engine and its blocks.
 *  It is also used to interact with it using the mouse and keyboard. 
 *
 *	@author procsynth - Antoine Pintout
 *	@since  v0.0.1
 */
public class EngineInterface extends PApplet{

	/**
	*	Stores the arguments passed the Processing sketch 
	*/
	private static final String[] PAPPLET_ARGS = new String[] { "" };

	/**
	*	The reference of the BlockEngine that is interfaced
	*/
	private BlockEngine engine;


	/**
	*	This creates a PApplet sketch that will render a representation of the BlockEngine.
	*
	*	@param engine the BlockEngine to be interfaced
	*
	*	@see BlockEngine 
	*	
	*/
	public EngineInterface(BlockEngine engine){

		this.engine = engine;
		PApplet.runSketch(PAPPLET_ARGS, this);

	}

	/**
	* 	Settings of the PApplet.
	*/
	public void settings() {
		size(1280, 720, PApplet.P3D);
		smooth(8);
	}

	/**
	* 	Setup of the PApplet.
	*	Set renderer properties.
	*/
	public void setup() {
		frameRate(160);
		surface.setResizable(true);
		hint(PApplet.DISABLE_DEPTH_TEST);
	}

	/**
	* 	Main loop for the PApplet.
	*/
	public void draw() {
		background(0x00, 0x10, 0x21);

		// stroke(0x2A, 0xFC, 0x98);
		// line(200, 100, width - 100, height - 200);

		// stroke(0xED, 0x25, 0x4E);
		// line(100, 100, width - 100, height - 100);

		// stroke(0x2D, 0xE1, 0xFC);
		// line(100, 200, width - 200, height - 100);

		// stroke(0xF9, 0xDC, 0x5C);
		// line(width - 100, 100, 100, height - 100);

		fill(0xED, 0x25, 0x4E);
		textAlign(PApplet.LEFT);
		text(frameRate, 10, height - 10);

		fill(0x2A, 0xFC, 0x98);
		textAlign(PApplet.RIGHT);
		text(Shine3.VERSION, width-10, height - 10);

		fill(0x2D, 0xE1, 0xFC);
		textAlign(PApplet.CENTER);
		text("shine 3 / procsynth", width/2, height-10);

		pushMatrix();
		ortho();
		translate(width/2, height/2);
		rotateX(PApplet.HALF_PI*height/(mouseY/2+height/2));
		rotateZ(PApplet.QUARTER_PI*1.2f);
		noFill();
		stroke(0x2A, 0xFC, 0x98);
		box(150);
		translate(200, 200);
		box(150);
		popMatrix();
	}
}

