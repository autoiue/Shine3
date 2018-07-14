//Drawable.java

package procsynth.shine3.papplet;

import java.util.*;

import processing.core.PGraphics;
import processing.core.PConstants;
import procsynth.shine3.Shine3;


public class Drawable {

	public enum Focus {
		NO,
		YES,
		CHILD	
	}

	private int x, y;
	protected PGraphics canvas;
	private boolean repaint = false;
	protected Focus focus = Focus.NO;
	private Drawable parent;
	private Drawable focusedChild;
	private List<Drawable> childs;

	public Drawable(int width, int height, int x, int y, Drawable parent){
		canvas = Shine3.ui.createGraphics(width, height, PConstants.P2D);
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	protected Drawable(PGraphics canvas){
		this.canvas = canvas;
		parent = null;
		this.x = 0;
		this.y = 0;
	}

	public PGraphics getCanvas(){
		if(mouseIn(this, Shine3.ui.mouseX, Shine3.ui.mouseX) && Shine3.ui.mousePressed){
			if(parent.requestFocus(this)){
				focus = Focus.YES;
			}
		}
		if(repaint){
			draw();
			repaint = false;
		}
		return canvas;
	}

	protected void draw(){
	}

	protected void repaint(){
		repaint = true;
	}

	protected boolean requestFocus(Drawable child){
		if(parent.requestFocus(this)){
			focus = Focus.CHILD;
			focusedChild = child;
			return true;
		}else{
			focus = Focus.NO;
			focusedChild = null;
			return false;
		}
	}

	public static boolean mouseIn(Drawable d, int mx, int my){
		return d.x < mx && mx < d.x + d.canvas.width &&
			d.y < my && my < d.y + d.canvas.height;
	}
}