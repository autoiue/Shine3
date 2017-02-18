// EngineInterface.java

package procsynth.shine3.papplet;

import procsynth.shine3.*;
import procsynth.shine3.engine.*;
import procsynth.shine3.utils.*;

import processing.core.*;
import processing.opengl.*;
import processing.event.*;

import java.util.*;

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
		size(1920, 1080, PApplet.P3D);
		smooth(8);
	}

	/**	Setup of the PApplet. Set renderer properties. */
	public void setup() {
		frameRate(160);
		surface.setResizable(true);
		hint(PApplet.DISABLE_DEPTH_TEST);

		textFont(loadFont("Raleway-Regular-18.vlw"));
	}

	/**
	* 	Main loop of the PApplet.
	*/
	public void draw() {
		background(0x00, 0x10, 0x21);

		drawStatus();
		drawMinimap();
		drawBlockMenu();

		pushMatrix();

		// setup 3D camera

		// tweak frustrum near and far plane to avoid clipping;
		ortho(-width/2, width/2, -height/2, height/2, -height, 2*height);
		camera(Math.min(width, height)/1.8f, Math.min(width, height)/2.0f, (Math.min(width, height)/2.0f) / tan(PI*28.0f / 180.0f), 0, 0, 0, 0, 0, -1);

		u.captureViewMatrix(this);
		u.calculatePickPoints(this, mouseX, mouseY);
		PVector mouse = u.intersect(0);

		// todo draw block tree

		// drawCursor(mouse);

		popMatrix();
	}

	private void drawStatus(){
		textSize(18);

		fill(Palette.RED);
		textAlign(PApplet.LEFT);
		text(Math.round(frameRate), 12, height - 12);
		fill(Palette.GREEN);
		text(Math.round(Shine3.engine.getTickRate()), 40, height - 12);

		textAlign(PApplet.RIGHT);
		text(Shine3.VERSION, width-12, height - 12);

		fill(Palette.BLUE);
		textAlign(PApplet.CENTER);
		text("shine 3 / procsynth", width/2, height-10);
	}

	private void drawCursor(PVector mouse){

		pushMatrix();
		translate(mouse.x, mouse.y);

		stroke(Palette.RED);
		line(0, 0, 0, 100, 0, 0);
		stroke(Palette.GREEN);
		line(0, 0, 0, 0, -100, 0);
		stroke(Palette.BLUE);
		line(0, 0, 0, 0, 0, 100);

		// rotateZ(PI);
		fill(Palette.GREEN);
		textAlign(PApplet.LEFT, PApplet.BOTTOM);
		text(mouseX+";"+mouseY+";", 10, -10);
		popMatrix();
	}

	private void drawBlockMenu(){

		int offset = 40;

		stroke(Palette.RED);
		for (Class<? extends Block> c : Shine3.engine.getAvailableBlocks()) {
			fill(Palette.RED);
			text(c.getSimpleName().toLowerCase(), 30, offset);
			offset += 25;
		}	
	}

	private void drawMinimap(){
		
		List<Block> blocks = Shine3.engine.getBlocks();
		Map<Pair<Integer, Integer>, Block> map = Mapgen.getOrthoMap(blocks);

		System.out.println("min: "+ Mapgen.level +" max: "+ Mapgen.maxLevel +" width: "+Mapgen.maxSpot);

		stroke(Palette.RED);
		rectMode(CENTER);
		ellipseMode(CENTER);
		textAlign(LEFT);
		fill(Palette.RED);
		noFill();

		for(int i = 0; i <= Mapgen.maxLevel; i++ ){
			for(int j = 0; j <= Mapgen.maxSpot; j++){
				if(map.containsKey(new Pair(i,j))){
					Block b = map.get(new Pair(i,j));

					stroke(Palette.RED);
					rect(width - 80 - j*90, 50 + (Mapgen.maxLevel - i)*50, 80, 40);
					text(b.getDisplayName(), width - 115 - j*90, 50 + (Mapgen.maxLevel - i)*50);
					int oofs = 5;
					for(Output o : b.getOutputs().values()){
						if((o.value() instanceof Boolean) && (Boolean)o.value()){
							stroke(Palette.YELLOW);
						}else{
							stroke(Palette.BLUE);
						}
						ellipse(width - 115 - j*90 + oofs, 60 + (Mapgen.maxLevel - i)*50, 10, 10);
						oofs += 15;
					}
				}
			}
		}

	}


}

