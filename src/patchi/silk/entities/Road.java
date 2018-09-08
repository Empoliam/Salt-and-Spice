package patchi.silk.entities;

import java.util.List;

/**
 * Intermediary region between two settlements.
 */
public class Road {

	/** Main World reference */
	public static final World WORLD = World.getMainWorld();
	
	/** Reference to settlement dataset ArrayList*/
	public static final List<Settlement> SETTLEMENTS = WORLD.getSettlementSet();

	/** Road ID. Corresponds to list index. */
	private String id;
		
	/** Connecting settlements */
	private Settlement connectingA;
	private Settlement connectingB;
	
	/** Road name. */
	private String name;
	
	/** Road length. */
	private int length;

	private String packedData;
		
	public Road(String id, String packedData) {
		this.id = id;
		this.packedData = packedData;
	}
	
	public void unpack() {
		
		String[] in = packedData.split(",");
		
		name = in[0];
		length = Integer.parseInt(in[2]);
		
		connectingA = WORLD.getSettlementByID(in[1].split(";")[0]);
		connectingB = WORLD.getSettlementByID(in[1].split(";")[1]);		
		connectingA.getRoads().add(this);
		connectingB.getRoads().add(this);
		
	}

	/**
	 * Returns the road ID.
	 *
	 * @return ID
	 */
	public String getID() {
		
		return id;	
	}
	
	/**
	 * Returns the road name.
	 *
	 * @return Road name
	 */
	public String getName() {

		return name;
	}
	
	/**
	 * Returns the length of the road.
	 *
	 * @return Road length
	 */
	public int getLength() {
		
		return length;
	}

	public Settlement getConnectingA() {
		return connectingA;
	}

	public Settlement getConnectingB() {
		return connectingB;
	}
}