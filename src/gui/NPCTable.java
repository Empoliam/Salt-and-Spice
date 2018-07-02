package gui;

import java.util.ArrayList;

import entities.NPC;
import entities.World;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * A debug UI element used to display all NPCs and their details.
 */
public class NPCTable extends VBox{

	/** Main World reference */
	public static final World WORLD = World.getMainWorld();
		
	/** Reference to NPC dataset*/
	static final ArrayList<NPC> NPCS = WORLD.getNPCS();
	
	/** Table object */
	private TableView<NPC> table; 
	
	/**
	 * Instantiates the NPC Table.
	 */
	@SuppressWarnings("unchecked")
	public NPCTable() 
	{
		
		ObservableList<NPC> listTest = FXCollections.observableArrayList(NPCS);

		TableColumn<NPC,Number> idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(n -> n.getValue().getIdProperty());

		TableColumn<NPC,String> fnameColumn = new TableColumn<>("First Name");
		fnameColumn.setMinWidth(100);
		fnameColumn.setCellValueFactory(n -> n.getValue().getFirstNameProperty());

		TableColumn<NPC,String> lnameColumn = new TableColumn<>("Last Name");
		lnameColumn.setMinWidth(100);
		lnameColumn.setCellValueFactory(n -> n.getValue().getLastNameProperty());

		TableColumn<NPC,String> locationColumn = new TableColumn<>("Location");
		locationColumn.setMinWidth(100);
		locationColumn.setCellValueFactory(n -> n.getValue().locationNameProperty());

		TableColumn<NPC,Number> departureColumn = new TableColumn<>("Departure time");
		departureColumn.setMinWidth(100);
		departureColumn.setCellValueFactory(n -> n.getValue().getDepartureHoursProperty());
		
		TableColumn<NPC,Boolean> travellingColumn = new TableColumn<>("Travelling");
		travellingColumn.setMinWidth(100);
		travellingColumn.setCellValueFactory(n -> n.getValue().getTravellingProperty());
		
		TableColumn<NPC, Number> remainingDistanceColumn = new TableColumn<>("Remaining Distance");
		remainingDistanceColumn.setMinWidth(100);
		remainingDistanceColumn.setCellValueFactory(n -> n.getValue().getRemainingDistanceProperty());
		
		table = new TableView<NPC>();	
		table.setItems(listTest);
		table.getColumns().addAll(idColumn,fnameColumn,lnameColumn,locationColumn,departureColumn,travellingColumn,remainingDistanceColumn);		
		table.setEditable(false);
		
		this.getChildren().add(table);
		
	}
	
	/**
	 * Refresh table values.
	 */
	public void refresh() {
		table.refresh();
	}

}
