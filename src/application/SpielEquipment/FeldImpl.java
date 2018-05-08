package application.SpielEquipment;

import java.util.ArrayList;

import application.Gui.ConsoleColors;
import application.Spieler.SpielerImpl;

public class FeldImpl {
	
	private SpielerImpl spieler1;
	private SpielerImpl spieler2;
	private SpielerImpl spieler3;
	private SpielerImpl spieler4;
	private ArrayList<SpielerImpl> spielerArray = new ArrayList<>();
	private int currentPlayer = 0;

	private ArrayList<FigurImpl> feld;

	public FeldImpl() {
		this.feld = new ArrayList<>(48);
		for (int i = 0; i < 48; i++)
			this.feld.add(null);
		spieler1 = new SpielerImpl("Rot", 0);
		spieler2 = new SpielerImpl("Gelb", 11);
		spieler3 = new SpielerImpl("Gruen", 23);
		spieler4 = new SpielerImpl("Blau", 35);
		spielerArray.add(spieler1);
		spielerArray.add(spieler2);
		spielerArray.add(spieler3);
		spielerArray.add(spieler4);
	}

	public ArrayList<FigurImpl> getFigurArray() {
		return this.feld;
	}
	
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public void nextPlayer() {
		this.currentPlayer += 1;
		if (this.currentPlayer == spielerArray.size()) this.currentPlayer = 0;
	}
	
	public SpielerImpl getSpieler(int index) {
		return this.spielerArray.get(index);
	}

	public boolean startFeldFrei(SpielerImpl spieler) {
		if (this.feld.get(spieler.getStartPos()) == null) {
			return true;
		} else {
			System.out.println(ConsoleColors.RED_BOLD + "Startfeld besetzt!" + ConsoleColors.RESET);
			return false;
		}

	}

	public void bewegeFigur(FigurImpl figur, int wuerfelZahl) {
		if (figur != null) {
			int tmpPos = this.feld.indexOf(figur);
			if (tmpPos != -1) {
				if (!figurKollidiertGegner(figur, (tmpPos + wuerfelZahl) % 48)) {
					this.feld.set((tmpPos + wuerfelZahl) % 48, figur);
					figur.setPosition((tmpPos + wuerfelZahl) % 48);
					this.feld.set(tmpPos, null);
				} else {
					this.feld.get((tmpPos + wuerfelZahl) % 48).getSpieler()
							.figurSetzenHeimat(this.feld.get((tmpPos + wuerfelZahl) % 48));
					this.feld.get((tmpPos + wuerfelZahl) % 48).setPosition(-2);
					this.feld.set((tmpPos + wuerfelZahl) % 48, figur);
					figur.setPosition((tmpPos + wuerfelZahl) % 48);
					this.feld.set(tmpPos, null);
				}
			}
		}
	}

	public void setzeFigurFeld(FigurImpl figur, SpielerImpl spieler) {
		if (figur != null) {
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
	}

	public boolean figurKollidiertGegner(FigurImpl figur, int feldnummer) {
		if (this.feld.get(feldnummer) != null) {
			System.out.println(ConsoleColors.RED_BOLD + "Wissenstest!" + ConsoleColors.RESET);
			return !figur.getFarbe().equals(this.feld.get(feldnummer).getFarbe());
		}
		return false;
	}
}
