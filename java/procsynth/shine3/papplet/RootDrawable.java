//Drawable.java

package procsynth.shine3.papplet;

import processing.core.PGraphics;

public class RootDrawable extends Drawable {


	public RootDrawable(PGraphics canvas){
		super(canvas);
	}

	@Override
	protected boolean requestFocus(Drawable d){
		focus = Focus.CHILD;
		return true;
	}
}