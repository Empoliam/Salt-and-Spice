package patchi.silk.gui;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import patchi.silk.main.Main;
import patchi.silk.save.InititialiseData;
import patchi.silk.save.NewGame;

public class TitleScreen implements Screen {

	@Override
	public void displayOutput(AsciiPanel terminal) {

		InititialiseData.initialise();
		
		terminal.writeCenter("~~ Silk and Wolf ~~", terminal.getHeightInCharacters()/2 - 2);
		terminal.writeCenter("~~ " + Main.VERSION + " ~~", terminal.getHeightInCharacters()/2);
		terminal.writeCenter("n - New Game", terminal.getHeightInCharacters()/2 + 6);
		terminal.writeCenter("l - Load Saved Game", terminal.getHeightInCharacters()/2 + 7);

	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {

		int code = key.getKeyCode();

		if(code == KeyEvent.VK_N) {
			
			long start = System.currentTimeMillis();
			NewGame.newGame();
			System.out.println("Loaded in " + (System.currentTimeMillis()-start) + "ms");
			return new MainScreen();
			
		} else if ( code == KeyEvent.VK_L) {
			
			return new LoadScreen();
			
		} else {
			
			return this;
			
		}
	}

}


