package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import entities.NPC;
import entities.Road;
import entities.Settlement;
import entities.World;
import market.GlobalStock;

/**
 * Handles all file loading at the start of the game.
 */
public class Load {
	
	/** NPC ID counter. Ensures that all NPC objects have a unique ID.<br>
	 *	All NPC IDs start at 2. Lawrence always has ID '0' and Holo always has ID '1'.
	 */
	private static int NPC_ID_COUNTER = 2;

	/** Main World reference */
	public static final World WORLD = World.getMainWorld();
	
	/** Reference to settlement dataset ArrayList*/
	public static final List<Settlement> SETTLEMENTS = WORLD.getSettlementsSet();

	/** Reference to roads dataset. */
	private static final List<Road> ROADS = WORLD.getRoadSet();
	
	/** Reference to global stock dataset */
	static final HashMap<Integer,GlobalStock> STOCKS = WORLD.getGlobalStockSet();
	
	/** Reference to NPC dataset*/
	static final HashMap<Integer,NPC> NPCS = WORLD.getNPCSSet();
	
	/**
	 * Load settlements.
	 */
	public static void settlements() {

		try {
			
			BufferedReader br = new BufferedReader(new FileReader("resources/settlements.csv"));
			String line = br.readLine();

			while (line != null) {

				SETTLEMENTS.add(new Settlement(line.split(",")));
				line = br.readLine();
			}

			br.close();
		} 
		catch (FileNotFoundException e) {}
		catch (IOException e) {}
	}

	/**
	 * Load roads.
	 */
	public static void roads() {
		
		try {
		
			BufferedReader br = new BufferedReader(new FileReader("resources/roads.csv"));
			String line = br.readLine();
			
			while(line != null) {
				
				ROADS.add(new Road(line.split(",")));
				line = br.readLine();
			}
			
			br.close();
		}
		catch(FileNotFoundException e){}
		catch(IOException e){}
	}
	
	/**
	 * Load NPCs.
	 */
	public static void npcs() { 
	
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("resources/npcs.csv"));
			String line = br.readLine();
			
			while(line!=null) {
				
				NPCS.put(NPC_ID_COUNTER,new NPC(NPC_ID_COUNTER,line.split(",")));
				NPC_ID_COUNTER ++;
				line = br.readLine();
			}
			
			br.close();
		}
		catch(FileNotFoundException e){}
		catch(IOException e){}
		
	}

	public static void stocks() { 
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("resources/stocks.csv"));
			String line = br.readLine();
			
			while(line!=null) {
				
				STOCKS.put(GlobalStock.getIdTracker(),new GlobalStock(line));
				line = br.readLine();
			}
			
			br.close();
		}
		catch(FileNotFoundException e){}
		catch(IOException e){}
		
	}
}
