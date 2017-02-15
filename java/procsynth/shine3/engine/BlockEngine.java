// BlockEngine.java

package procsynth.shine3.engine;

import java.util.*;
import java.lang.reflect.Modifier;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

/**
 *	
 *	@author procsynth - Antoine Pintout
 *	@since  v0.0.1
 */
public class BlockEngine{

	/**	List available blocks */
	private List<Class<? extends Block>> availableBlocks = new ArrayList<>();

	/**	List available factories */
	private List<Class<? extends BlockFactory>> availableFactories = new ArrayList<>();

	/** Stores all blocks currently in the engine */
	private List<Block> blocks = new ArrayList();

	/** Instanciate a BlockEngine and start the engine thread. */
	public BlockEngine(){	
		scanAvailable();

		// TODO start thread
	}

	/**
	*	Scan the classpath to find all subclasses of superclasses {@link Block} and {@link BlockFactory}.
	*	<br>
	*	Classes as to be final.
	*/
	private void scanAvailable(){
		List<Class<? extends Block>> blockSubclasses = new ArrayList<>();
		List<Class<? extends BlockFactory>> blockFactorySubclasses = new ArrayList<>();

		new FastClasspathScanner()
		.matchSubclassesOf(Block.class, blockSubclasses::add)
		.scan();
		new FastClasspathScanner()
		.matchSubclassesOf(BlockFactory.class, blockFactorySubclasses::add)
		.scan();

		for(Class<? extends Block> b : blockSubclasses){
			if(Modifier.isFinal(b.getModifiers())){
				availableBlocks.add(b);	
			}
		}
		System.out.println("FOUND "+availableBlocks.size()+" FINAL BLOCKS.");

		for(Class<? extends BlockFactory> f : blockFactorySubclasses){
			if(Modifier.isFinal(f.getModifiers())){
				availableFactories.add(f);	
			}
		}
		System.out.println("FOUND "+availableFactories.size()+" FACTORIES.");

		try{
			addBlock(Block.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	*	Add a new block to the engine
	*	
	* 	@param blockClass the class to create the object from
	*	
	*	@throws EngineExceptions.UnknowBlockClass if the class definition is unknown
	*	@throws InstantiationException if instanciation goes wrong.
	*	@throws IllegalAccessException if instanciation goes wrong.
	*/
	public void addBlock(Class<? extends Block> blockClass) throws EngineExceptions.UnknowBlockClass, InstantiationException, IllegalAccessException{
		if(availableBlocks.indexOf(blockClass) == -1){
			throw new EngineExceptions.UnknowBlockClass();
		}
		Block b = blockClass.newInstance();
		blocks.add(b);
		System.out.println(b.getIdString());
	}
}

