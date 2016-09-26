// EngineInterface.java

package procsynth.shine3.papplet;

import procsynth.shine3.engine.*;

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
		size(1280, 720, PApplet.P2D);
	}

	/**
	* 	Setup of the PApplet.
	*	Set renderer properties.
	*/
	public void setup() {
		frameRate(60);
		surface.setResizable(true);
	}

	/**
	* 	Main loop for the PApplet.
	*/
	public void draw() {
		background(0x26, 0x32, 0x38); // BLUE_GREY._900
	}
}

