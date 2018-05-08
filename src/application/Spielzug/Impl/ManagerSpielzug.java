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
	private int figurNummer = 0;
	public int anzahlWuerfe = 0;
	public boolean showErgebnisse = true;
	
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
		if (ergebnisse.size() == 4)
			ergebnisse.remove(3);
		return this.ergebnisse;
	}
	
	@Override
	public void clearErgebnisse() {
		ergebnisse.clear();
	}

	@Override
	public int getGewuerfelteZahl() {
		return this.gewuerfelteZahl;
	}

	@Override
	public void pruefen(SpielerImpl spieler, FeldImpl feld) {
		this.showErgebnisse = true;
		if (this.anzahlWuerfe < 3) {
			
			if (this.gewuerfelteZahl == 6 && spieler.getAnzahlFigurHeimatfeld() == 0) {
				stateMachine.setState(State.S.FigurBewegen);
				feld.bewegeFigur(spieler.getFigurSpielfeld(this.figurNummer), this.gewuerfelteZahl);
				this.anzahlWuerfe = 0;
				this.figurNummer = 0;
				showErgebnisse = false;
				feld.nextPlayer();
				stateMachine.setState(State.S.Bestaetigen);
			}
			
			if (this.gewuerfelteZahl == 6 && spieler.getAnzahlFigurHeimatfeld() > 0) {
				if (feld.startFeldFrei(spieler)) {
					stateMachine.setState(State.S.FigurAufStartfeld);
					feld.setzeFigurFeld(spieler.getFigurHeimat(this.figurNummer), spieler);
					this.anzahlWuerfe = 0;
					this.figurNummer = 0;
					showErgebnisse = false;
					feld.nextPlayer();
					stateMachine.setState(State.S.Bestaetigen);
				}
				else {
					this.anzahlWuerfe = 0;
					feld.nextPlayer();
					stateMachine.setState(State.S.Bestaetigen);
				}
			}
			
			if (this.gewuerfelteZahl < 6 && spieler.getAnzahlFigurHeimatfeld() < 3) {
				stateMachine.setState(State.S.FigurBewegen);
				feld.bewegeFigur(spieler.getFigurSpielfeld(this.figurNummer), this.gewuerfelteZahl);
				this.anzahlWuerfe = 0;
				this.figurNummer = 0;
				feld.nextPlayer();
				showErgebnisse = false;
				stateMachine.setState(State.S.Bestaetigen);
			} else if (this.gewuerfelteZahl < 6 && spieler.getAnzahlFigurHeimatfeld() == 3) {
				stateMachine.setState(State.S.Wuerfeln);
				this.anzahlWuerfe += 1;
				wuerfeln(spieler, feld);
			}
			
		} else {
			this.anzahlWuerfe = 0;
			feld.nextPlayer();
			stateMachine.setState(State.S.Bestaetigen);
		}
		
	}

	@Override
	public void figurWaehlen(int nummer) {
		if (nummer == 1 || nummer == 2 || nummer == 3) {
			this.figurNummer = nummer;
		} else {
			System.out.println(stateMachine.getState().toString());
			stateMachine.setState(State.S.Bestaetigen);
		}
		
	}
	
	@Override
	public boolean getShowErgebnisse() {
		
		return this.showErgebnisse;
	}

}
