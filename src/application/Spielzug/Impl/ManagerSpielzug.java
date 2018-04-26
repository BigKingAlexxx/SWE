package application.Spielzug.Impl;

import java.util.ArrayList;
import java.util.Random;

import application.SpielEquipment.FeldImpl;
import application.SpielEquipment.FigurImpl;
import application.Spieler.SpielerImpl;
import application.Spielzug.port.PortSpielzug.SpielzugManagement;
import application.StateMachine.port.State;
import application.StateMachine.port.StateMachine.StateMachine;
import application.StateMachine.port.StateMachine.StateMachinePort;


public class ManagerSpielzug implements SpielzugManagement{
	
	private int gewuerfelteZahl;
	private Random rand = new Random();
	private StateMachine stateMachine;
	private ArrayList<Integer> ergebnisse = new ArrayList<>();
	
	public int anzahlWuerfe = 0;
	
	public ManagerSpielzug(StateMachinePort stateMachinePort) {
		this.stateMachine = stateMachinePort.stateMachine();
	}

	@Override
	public void wuerfeln(SpielerImpl spieler, FeldImpl feld) {
		this.gewuerfelteZahl = rand.nextInt(6) + 1;
		ergebnisse.add(gewuerfelteZahl);
		stateMachine.setState(State.S.Pruefen);
		pruefen(spieler, feld);
	}
	
	@Override
	public ArrayList<Integer> getErgebnisse() {
		return this.ergebnisse;
	}
	
	@Override
	public void clearErgebnisse() {
		ergebnisse.clear();
	}

	@Override
	public void wissensstreiterBewegen(SpielerImpl spieler, FigurImpl figur, int figurNummer, FeldImpl feld) {
		if (stateMachine.getState() == State.S.FigurBewegen) feld.bewegeFigur(spieler, spieler.getFigur(figurNummer), this.gewuerfelteZahl);
		
		if (feld.figurKollidiertGegner(figur, feld.getFeldnummer())) stateMachine.setState(State.S.Wissenstest);
		
		if (stateMachine.getState() == State.S.Wissenstest) feld.figurErsetzen(spieler, figur);
		
		if (stateMachine.getState() == State.S.FigurAufStartfeld && feld.startFeldFrei(spieler)) {
			spieler.figurSetzenFeld();
			feld.setzeFigurFeld(figur, spieler);
		}
		
		stateMachine.setState(State.S.Bestaetigen);
	}

	@Override
	public void bestaetigen(SpielerImpl spieler) {
		ergebnisse.clear();
		stateMachine.setState(State.S.NaechsterSpieler);
	}

	@Override
	public int getGewuerfelteZahl() {
		return this.gewuerfelteZahl;
	}

	@Override
	public void pruefen(SpielerImpl spieler, FeldImpl feld) {
		if (this.anzahlWuerfe < 3) {
			
			if (this.gewuerfelteZahl == 6 && spieler.getAnzahlFigurHeimatfeld() == 0) {
				stateMachine.setState(State.S.FigurBewegen);
				for (int i = 0; i < 3; i++) {
					if (spieler.getFigur(i) != null) {
						feld.bewegeFigur(spieler, spieler.getFigur(i), this.gewuerfelteZahl);
						break;
					}
				}
				this.anzahlWuerfe = 0;
				stateMachine.setState(State.S.Bestaetigen);
			}
			
			if (this.gewuerfelteZahl == 6 && spieler.getAnzahlFigurHeimatfeld() > 0) {
				if (feld.startFeldFrei(spieler)) {
					stateMachine.setState(State.S.FigurAufStartfeld);
					feld.setzeFigurFeld(spieler.getFigurHeimat(), spieler);
					this.anzahlWuerfe = 0;
					stateMachine.setState(State.S.Bestaetigen);
				}
				else {
					this.anzahlWuerfe = 0;
					stateMachine.setState(State.S.Bestaetigen);
				}
			}
			
			if (this.gewuerfelteZahl < 6 && spieler.getAnzahlFigurHeimatfeld() < 3) {
				stateMachine.setState(State.S.FigurBewegen);
				for (int i = 0; i < 3; i++) {
					if (spieler.getFigur(i) != null) {
						feld.bewegeFigur(spieler, spieler.getFigur(i), this.gewuerfelteZahl);
						break;
					}
				}
				this.anzahlWuerfe = 0;
				stateMachine.setState(State.S.Bestaetigen);
			} else if (this.gewuerfelteZahl < 6 && spieler.getAnzahlFigurHeimatfeld() == 3) {
				stateMachine.setState(State.S.Wuerfeln);
				this.anzahlWuerfe += 1;
				wuerfeln(spieler, feld);
			}
			
		} else {
			this.anzahlWuerfe = 0;
			stateMachine.setState(State.S.Bestaetigen);
		}
		
	}

}
