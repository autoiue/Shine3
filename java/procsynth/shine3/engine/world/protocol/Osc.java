// Osc.java

package procsynth.shine3.engine.world.protocol;

import com.illposed.osc.*;
import com.illposed.osc.utility.JavaRegexAddressSelector;
import procsynth.shine3.engine.Output;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Communicates with Osc
 */
public final class Osc extends Protocol implements OSCListener {

	private OSCPortIn receiver;
	private OSCPortOut sender;
	private DatagramSocket port;

	private Map<String,Output<?>> outputs;

	public Osc() {
		outputs = new HashMap<>();
		try {
			port = new DatagramSocket(OSCPort.defaultSCOSCPort());
			receiver = new OSCPortIn(port);
			receiver.addListener(new JavaRegexAddressSelector("/.+"), this);
			receiver.startListening();
			log.info("OSC listening and sending on port "+OSCPort.defaultSCOSCPort());
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick() {
		//System.out.println("OSC");
	}

	@Override
	public void acceptMessage(Date date, OSCMessage oscMessage) {
		log.info(oscMessage.getAddress());
	}
}
