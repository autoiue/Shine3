// Input.java

package procsynth.shine3.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * An input is a part of a block that is connected to another block output. It
 * describe to which block it is connected, which data type is exchanged, and
 * what's the value of the data.
 *
 * Each block keeps a list of which of its inputs are connected to which other
 * block's outputs.
 *
 * @author procsynth - Antoine Pintout
 * @since v0.0.1
 *
 * @see Engine
 * @see Block
 */
public class Input<T> {

	private final Logger log = Logger.getLogger(this.getClass().getName());

	/** The source output. */
	private Output<T> source;

	/** The value of the input. */
	private T value;
	/** The default value of the input. */
	private final T defaultValue;
	/** The display name of the input. */
	private String name;

	/** @return the display name of the input. */
	private String getName() {
		return name;
	}

	/** Internal pool holding all available inputs */
	private static Map<String, Input<?>> inputPool = new HashMap<>();

	public Input(String name, T defaultValue) {
		this.name = name;
		this.defaultValue = defaultValue;
	}

	/** Retrieve the last value from the source block. */
	public void update() {
		if (source != null) {
			value = source.value();
		}
	}

	/**
	 * Get the input value.
	 * 
	 * @return the value of the source output
	 */
	public T value() {
		if (value == null) {
			return defaultValue;
		} else {
			return value;
		}
	}

	/**
	 * Get the sourceBlock
	 * 
	 * @return the block to which the source belongs
	 */
	public Block getSourceBlock() {
		if (source == null) {
			return null;
		} else {
			return source.getParent();
		}
	}

	/**
	 * Set the source
	 * 
	 * @param source
	 *            the output to link to this input
	 */
	public void setSource(Output<T> source) {
		this.source = source;
	}

}