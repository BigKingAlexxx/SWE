package application.SpielEquipment;

import java.util.ArrayList;

import application.Gui.ConsoleColors;
import application.Spieler.SpielerImpl;

public class FeldImpl {

	private ArrayList<FigurImpl> feld;
	private int feldnummer;

	public FeldImpl() {
		this.feld = new ArrayList<>(48);
		for (int i = 0; i < 48; i++)
			this.feld.add(null);
	}

	public ArrayList<FigurImpl> getFigurArray() {
		return this.feld;
	}

	public boolean startFeldFrei(SpielerImpl spieler) {
		if (this.feld.get(spieler.getStartPos()) == null) {
			return true;
		} else {
			System.out.println(ConsoleColors.RED_BOLD + "Startfeld besetzt!" + ConsoleColors.RESET);
			return false;
		}

	}

	public void bewegeFigur(SpielerImpl spieler, FigurImpl figur, int wuerfelZahl) {
		int tmpPos = this.feld.indexOf(figur);
		if (tmpPos != -1) {
			if (!figurKollidiertGegner(figur, (tmpPos + wuerfelZahl) % 48)) {
				this.feld.set((tmpPos + wuerfelZahl) % 48, figur);
				figur.setPosition((tmpPos + wuerfelZahl) % 48);
				this.feld.set(tmpPos, null);
			} else {
				spieler.figurSetzenHeimat(this.feld.get((tmpPos + wuerfelZahl) % 48));
				this.feld.get((tmpPos + wuerfelZahl) % 48).setPosition(-2);
				this.feld.set((tmpPos + wuerfelZahl) % 48, figur);
				figur.setPosition((tmpPos + wuerfelZahl) % 48);
				this.feld.set(tmpPos, null);
			}
		}
		this.feldnummer = (tmpPos + wuerfelZahl) % 48;
	}

	public int getFeldnummer() {
		return this.feldnummer;
	}

	public void setzeFigurFeld(FigurImpl figur, SpielerImpl spieler) {
		if (figur.getFarbe().equals("Rot")) {
			this.feld.set(0, figur);
			spieler.figurSetzenFeld();
			figur.setPosition(0);
		}

		if (figur.getFarbe().equals("Gelb")) {
			this.feld.set(11, figur);
			spieler.figurSetzenFeld();
			figur.setPosition(11);
		}

		if (figur.getFarbe().equals("Gruen")) {
			this.feld.set(23, figur);
			spieler.figurSetzenFeld();
			figur.setPosition(23);
		}

		if (figur.getFarbe().equals("Blau")) {
			this.feld.set(35, figur);
			spieler.figurSetzenFeld();
			figur.setPosition(35);
		}
	}

	public void figurErsetzen(SpielerImpl spieler, FigurImpl figur) {

		int tmpIndex1 = figur.getPosition();
		int tmpIndex2 = this.feldnummer;
		spieler.figurSetzenHeimat(this.feld.get(feldnummer));
		this.feld.set(tmpIndex2, figur);
		figur.setPosition(tmpIndex2);
		this.feld.set(tmpIndex1, null);

	}

	public boolean figurKollidiertGegner(FigurImpl figur, int feldnummer) {
		if (this.feld.get(feldnummer) != null) {
			System.out.println(ConsoleColors.RED_BOLD + "Wissenstest!" + ConsoleColors.RESET);
			return !figur.getFarbe().equals(this.feld.get(feldnummer).getFarbe());
		}
		return false;
	}
}
