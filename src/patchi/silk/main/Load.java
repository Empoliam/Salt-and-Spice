package patchi.silk.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;

import patchi.silk.entities.Character;
import patchi.silk.entities.Road;
import patchi.silk.entities.Settlement;
import patchi.silk.entities.World;
import patchi.silk.market.GlobalStock;

/**
 * Handles all file loading at the start of the game.
 */
public class Load {
	
	/** Main World reference */
	public static final World WORLD = World.getMainWorld();
	
	/** Reference to settlement dataset ArrayList*/
	public static final List<Settlement> SETTLEMENTS = WORLD.getSettlementSet();

	/** Reference to roads dataset. */
	private static final List<Road> ROADS = WORLD.getRoadSet();
	
	/** Reference to global stock dataset */
	static final HashMap<Integer,GlobalStock> STOCKS = WORLD.getGlobalStockSet();
	
	/** Reference to character dataset*/
	static final List<Character> CHARACTERS = WORLD.getCharacterSet();
	
	/**
	 * Load settlements.
	 */
	public static void settlements() {

		try {
			
			BufferedReader br = new BufferedReader(new FileReader("resources/settlements.csv"));
			String line = br.readLine();

			while (line != null) {

				int idSplitPoint = line.indexOf(",");
				
				SETTLEMENTS.add(new Settlement(line.substring(0, idSplitPoint), line.substring(idSplitPoint+1, line.length())));
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
				
				int idSplitPoint = line.indexOf(",");
				
				ROADS.add(new Road(line.substring(0, idSplitPoint), line.substring(idSplitPoint+1, line.length())));
				line = br.readLine();
			}
			
			br.close();
		}
		catch(FileNotFoundException e){}
		catch(IOException e){}
	}
	
	/**
	 * Load Characters.
	 */
	public static void characters() { 
	
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("resources/characters.csv"));
			String line = br.readLine();
			
			while(line!=null) {
				
				CHARACTERS.add(new Character(line));
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
	
	public static void unpack() {
		
		for(Settlement S : SETTLEMENTS) {
			S.unpack();
		}
		
		for(Road R : ROADS) {
			R.unpack();
		}
		
		for(Character N : CHARACTERS) {
			N.unpack();
		}
		
	}
}