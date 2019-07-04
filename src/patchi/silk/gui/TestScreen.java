package patchi.silk.gui;

import java.awt.event.KeyEvent;
import java.util.List;
import asciiPanel.AsciiPanel;
import patchi.silk.entities.Settlement;
import patchi.silk.entities.World;

public class TestScreen implements Screen{
	
	public static final World WORLD = World.getMainWorld();
	public static final List<Settlement> SETTLEMENTS = WORLD.getSettlementSet();

	public TestScreen() {

	}

	@Override
	public void displayOutput(AsciiPanel terminal) {

		Settlement s = SETTLEMENTS.get(0);
		List<Integer> data = s.getDailyPop();
		int xSize = data.size() + 4;
		int ySize = 20;
		
		AsciiShapeUtil.drawBox(terminal, 1, 1, xSize+2, ySize+2);
		AsciiGraphMaker.drawGraph(terminal, xSize, ySize, 2, 2, data, 0, Float.NEGATIVE_INFINITY);

	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {

		switch(key.getKeyCode()) {

		case(KeyEvent.VK_ESCAPE):
			return new MainScreen();

		default:
			return this;


		}

	}

}