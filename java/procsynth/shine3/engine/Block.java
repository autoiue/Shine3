// Block.java

package procsynth.shine3.engine;

/**
 * A Block is a piece of program that has inputs and outputs.
 * Blocks can by wired together through their inputs and outputs to form a complex program.
 * There is two types of inputs or outputs : content and control. Types can be wired indifferently.
 *
 *    
 *
 *	@author procsynth - Antoine Pintout
 *	@since  v0.0.1
 *
 *  @see BlockEngine
 *  @see BlockFactory
 */
public class Block{

	/** 
	*	A unique string that will be used to reconstruct the block objet when needed.
	*
	*	@see #generateBlockIdString
	*/
	private final String idString;
	/** @return the idString of the block instance. */
	public String getIdString(){ return idString;}

	/** Used to construct a totally new Block instance */
	public Block(){	
		this.idString = generateBlockIdString();
	}

	/** 
	*	Used to restore a Block instance.
	*
	* 	@param idString the string to restore the block from
	*/
	public Block(String idString){	
		this.idString = idString;
	}

	/** 
	*	Generate a unique string that will be used to reconstruct the block objet when needed.
	*	
	*	It's structure is:
	*	<code>{fully qualified classname of block class of blockFactory class}:{uniqueid string}</code>
	*
	*	@return the id string
	*/
	private String generateBlockIdString(){
		return this.getClass().getName()+":"+"4(totallyFairRandomNumberFromDice)";
	}

}

