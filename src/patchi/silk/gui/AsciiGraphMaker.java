package patchi.silk.gui;

import java.awt.Color;
import java.util.List;

import asciiPanel.AsciiPanel;

public class AsciiGraphMaker {

	private AsciiGraphMaker() {}

	public static <N extends Number> void drawGraph(AsciiPanel terminal, int xSize, int ySize, int xTL, int yTL, List<N> data) {

		float lMax = Float.NEGATIVE_INFINITY;
		float lMin = Float.POSITIVE_INFINITY;

		for(N F : data) {
			lMax = Math.max(lMax, F.floatValue());
			lMin = Math.min(lMin, F.floatValue());
		}

		drawGraph(terminal, xSize, ySize, xTL, yTL, data, lMin, lMax);

	}

	public static <N extends Number> void drawGraph(AsciiPanel terminal, int xSize, int ySize, int xTL, int yTL, List<N> data, float lMin, float lMax) {

		int xCursor = 0;
		int yCursor = 0;

		int yRel = 0;

		if(lMax == Float.NEGATIVE_INFINITY) {
			for(N F : data) {
				lMax = Math.max(lMax, F.floatValue());
			} 
		}

		float yDiv = (float) (ySize-1) / (lMax-lMin);		

		xCursor = xTL;
		while(yRel < ySize) {
			yCursor = yTL - yRel + ySize - 1;
			terminal.setCursorPosition(xCursor, yCursor);
			terminal.write(Integer.toString(yRel));
			yRel++;
		}
		
		xCursor += 4;
		
		float p = Float.NaN;
		int yOld = 0;

		for(N F : data) {

			if(xCursor < xTL + xSize - 1) {

				int y  = (int) Math.floor((F.floatValue()-lMin)*yDiv);

				if(Float.isNaN(p)) {

					yRel = y;
					yCursor = yTL - yRel + ySize-1;
					terminal.setCursorPosition(xCursor, yCursor);
					terminal.write((char) 196);

				} else {

					if(y > yOld) {

						xCursor++;

						terminal.setCursorPosition(xCursor, yCursor);
						terminal.write((char) 217);

						while(yRel < y) {

							yRel++;
							yCursor = yTL - yRel + ySize-1;
							terminal.setCursorPosition(xCursor, yCursor);
							terminal.write((char) 179);

						}

						yCursor = yTL - y + ySize-1;
						terminal.setCursorPosition(xCursor, yCursor);
						terminal.write((char)218);

					} else if(y < yOld){

						xCursor++;

						terminal.setCursorPosition(xCursor, yCursor);
						terminal.write((char) 191);

						while(yRel > y) {

							yRel--;
							yCursor = yTL - yRel + ySize-1;
							terminal.setCursorPosition(xCursor, yCursor);
							terminal.write((char) 179);

						}

						yCursor = yTL - y + ySize-1;
						terminal.setCursorPosition(xCursor, yCursor);
						terminal.write((char)192);

					} else {

						xCursor++;

						terminal.setCursorPosition(xCursor, yCursor);
						terminal.write((char) 196);

					};

				}

				p = F.floatValue();
				yOld = y;

			}

		}

	}

}
