package application.Spieler;

import java.util.ArrayList;
import java.util.Arrays;

import application.SpielEquipment.FigurImpl;

public class SpielerImpl {

	public ArrayList<FigurImpl> figurenSpielfeld;
	public ArrayList<FigurImpl> heimatfeld;
	private int startPos;
	private String farbe;
	public FigurImpl figure1;
	public FigurImpl figure2;
	public FigurImpl figure3;

	public SpielerImpl(String farbe, int startPos) {
		figurenSpielfeld = new ArrayList<>();
		this.startPos = startPos;
		this.farbe = farbe;
		figure1 = new FigurImpl(this, farbe, 1);
		figure2 = new FigurImpl(this, farbe, 2);
		figure3 = new FigurImpl(this, farbe, 3);
		heimatfeld = new ArrayList<>(Arrays.asList(figure1, figure2, figure3));
	}

	public int getAnzahlFigurHeimatfeld() {
		return heimatfeld.size();
	}

	public int getStartPos() {
		return this.startPos;
	}

	public FigurImpl getFigurSpielfeld(int nummer) {
		if (nummer == 1) return figure1;
		if (nummer == 2) return figure2;
		if (nummer == 3) return figure3;
		return null;
	}

	public FigurImpl getFigurHeimat(int nummer) {
		if (nummer == 1) return figure1;
		if (nummer == 2) return figure2;
		if (nummer == 3) return figure3;
		return null;
	}

	public void figurSetzenFeld() {
		for (int i = 0; i < heimatfeld.size(); i++) {
			if (!heimatfeld.isEmpty()) {
				figurenSpielfeld.add(heimatfeld.get(i));
				heimatfeld.remove(i);
			}
			break;
		}
	}

	public void figurSetzenHeimat(FigurImpl figur) {
		if (!figurenSpielfeld.isEmpty()) {
			heimatfeld.add(figur);
			figurenSpielfeld.remove(figur);
		}
	}

	public String getFarbe() {
		return this.farbe;
	}

	public String getFig1() {
		int a = figure1.getPosition() + 1;
		return a == -1 ? "H" : "F" + a;
	}

	public String getFig2() {
		int a = figure2.getPosition() + 1;
		return a == -1 ? "H" : "F" + a;
	}

	public String getFig3() {
		int a = figure3.getPosition() + 1;
		return a == -1 ? "H" : "F" + a;
	}

}
