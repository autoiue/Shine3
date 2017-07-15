// DummyShine.java

package procsynth.shine3.shine.blocks;

import procsynth.shine3.engine.*;

/**
 *  A shine specific dummy final block.
 *
 *	@author procsynth - Antoine Pintout
 *	@since  v0.0.1
 *
 */
public final class DummyShine extends Block{
	
	public DummyShine(){
		this.displayName = "shine";
		newOutput("1", 1);
		newOutput("2", 2);
		newOutput("3", 3);
	}
}

