package application.Spieler;

import java.util.ArrayList;
import java.util.Arrays;

import application.SpielEquipment.FigurImpl;

public class SpielerImpl {

	ArrayList<FigurImpl> figurenSpielfeld;
	ArrayList<FigurImpl> heimatfeld;
	private int startPos;
	private String farbe;
	public FigurImpl figure1;
	public FigurImpl figure2;
	public FigurImpl figure3;

	public SpielerImpl(String farbe, int startPos) {
		figurenSpielfeld = new ArrayList<>();
		this.startPos = startPos;
		this.farbe = farbe;
		figure1 = new FigurImpl(1, farbe);
		figure2 = new FigurImpl(2, farbe);
		figure3 = new FigurImpl(3, farbe);
		heimatfeld = new ArrayList<>(Arrays.asList(figure1, figure2, figure3));
	}

	public int getAnzahlFigurHeimatfeld() {
		return heimatfeld.size();
	}

	public int getStartPos() {
		return this.startPos;
	}

	public FigurImpl getFigur(int index) {
		return figurenSpielfeld.get(index);
	}

	public FigurImpl getFigurHeimat() {
		return this.heimatfeld.get(0);
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
