// DummyModule.java

package dummy.shinemodule;

import procsynth.shine3.engine.*;

/**
 *  A dummy Shine module to test subclasses discovery
 *
 *	@author procsynth - Antoine Pintout
 *	@since  v0.0.1
 *
 */
public final class DummyModule extends Block{

	public DummyModule(){
		newInput("ear", "");
		newOutput("mouth", "");
	}

	public void tick(){
		String heard = "I heard: " + (String) get("ear");
		System.out.println(heard);
		set("mouth", heard);    
	}
}

