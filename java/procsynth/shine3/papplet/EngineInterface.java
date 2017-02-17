// EngineInterface.java

package procsynth.shine3.papplet;

import procsynth.shine3.engine.*;
import procsynth.shine3.*;

import processing.core.*;
import processing.opengl.*;
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

	/**	Stores the arguments passed the Processing sketch */
	private static final String[] PAPPLET_ARGS = new String[] { "" };

	/**	The reference of the BlockEngine that is interfaced. */
	private BlockEngine engine;

	/**	OPenGL like GluUnproject. */
	private Unproject u = new Unproject();

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

	/** Settings of the PApplet.*/
	public void settings() {
		size(1280, 720, PApplet.P3D);
		smooth(8);
	}

	/**	Setup of the PApplet. Set renderer properties. */
	public void setup() {
		frameRate(160);
		surface.setResizable(true);
		hint(PApplet.DISABLE_DEPTH_TEST);

	}

	/**
	* 	Register a view that will be made available in the interface
	*
	*	@see View
	*
	*	@param v The view to register
	*/
	public void registerView(View v) {

	}

	/**
	* 	Main loop of the PApplet.
	*/
	public  void draw() {
		background(0x00, 0x10, 0x21);

		textSize(12);

		fill(0xED, 0x25, 0x4E); // rouge
		textAlign(PApplet.LEFT);
		text(Math.round(frameRate)+" FPS", 10, height - 10);
		fill(0x2A, 0xFC, 0x98); // vert
		text(Math.round(Shine3.engine.getTickRate())+" TPS", 60, height - 10);

		fill(0x2A, 0xFC, 0x98);
		textAlign(PApplet.RIGHT);
		text(Shine3.VERSION, width-10, height - 10);

		fill(0x2D, 0xE1, 0xFC);
		textAlign(PApplet.CENTER);
		text("shine 3 / procsynth", width/2, height-10);

		pushMatrix();

		// tweak frustrum near and far plane to avoid clipping;
		ortho(-width/2, width/2, -height/2, height/2, -height, 2*height);
		camera(Math.min(width, height)/1.8f, Math.min(width, height)/2.0f, (Math.min(width, height)/2.0f) / tan(PI*50.0f / 180.0f), 0, 0, 0, 0, 0, -1);

		u.captureViewMatrix(this);
		u.calculatePickPoints(this, mouseX, mouseY);
		PVector mouse = u.intersect(0);

		noFill();

		pushMatrix();
		translate(-300, 0);
		stroke(0xED, 0x25, 0x4E); // rouge
		box(150);
		translate(200, 0);
		stroke(0x2A, 0xFC, 0x98); // vert
		box(150);
		translate(200, 0);
		stroke(0x2D, 0xE1, 0xFC); // bleu
		box(150);
		translate(200, 0);
		stroke(0xF9, 0xDC, 0x5C); // jaune
		box(150);

		popMatrix();

		translate(mouse.x, mouse.y);

		stroke(0xED, 0x25, 0x4E); // rouge
		line(0, 0, 0, 100, 0, 0);
		stroke(0x2A, 0xFC, 0x98); // vert
		line(0, 0, 0, 0, -100, 0);
		stroke(0x2D, 0xE1, 0xFC); // bleu
		line(0, 0, 0, 0, 0, 100);

		// rotateZ(PI);
		fill(0x2A, 0xFC, 0x98); // vert
		textAlign(PApplet.LEFT, PApplet.BOTTOM);
		text(mouseX+";"+mouseY+";", 10, -10);

		popMatrix();
	}

}

