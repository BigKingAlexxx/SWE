package application.Spielzug;

import java.util.ArrayList;

import application.SpielEquipment.FeldImpl;
import application.SpielEquipment.FigurImpl;
import application.Spieler.SpielerImpl;
import application.Spielzug.Impl.ManagerSpielzug;
import application.Spielzug.port.PortSpielzug.SpielzugManagement;
import application.Spielzug.port.PortSpielzug.SpielzugPort;
import application.StateMachine.StateMachineFactory;
import application.StateMachine.StateMachineFactoryImpl;
import application.StateMachine.port.State;
import application.StateMachine.port.StateMachine.StateMachine;
import application.StateMachine.port.StateMachine.StateMachinePort;

public class SpielzugFactoryImpl implements SpielzugFactory, SpielzugPort, SpielzugManagement {
	
	private StateMachinePort stateMachinePort = StateMachineFactory.FACTORY.stateMachinePort();
	private StateMachine stateMachine;
	private ManagerSpielzug managerSpielzug;

	@Override
	public SpielzugPort spielzugPort() {
		return this;
	}

	@Override
	public synchronized SpielzugManagement spielzugManagement() {
		if (this.managerSpielzug == null) {
			this.stateMachine = this.stateMachinePort.stateMachine();
			this.managerSpielzug = new ManagerSpielzug(this.stateMachinePort);
		}
		return this;
	}

	@Override
	public synchronized void wuerfeln(SpielerImpl spieler, FeldImpl feld) {
		if (stateMachine.getState() == State.S.Wuerfeln || stateMachine.getState() == State.S.Initialisiert || stateMachine.getState() == State.S.Bestaetigen)
			this.managerSpielzug.wuerfeln(spieler, feld);
	}

	@Override
	public synchronized int getGewuerfelteZahl() {
		return this.managerSpielzug.getGewuerfelteZahl();
	}

	@Override
	public void pruefen(SpielerImpl spieler, FeldImpl feld) {
		if (stateMachine.getState() == State.S.Pruefen)
			this.managerSpielzug.pruefen(spieler, feld);
	}

	public void setAnzahlWuerfe(int num) {
		this.managerSpielzug.anzahlWuerfe = num;
	}

	@Override
	public ArrayList<Integer> getErgebnisse() {
		return this.managerSpielzug.getErgebnisse();
	}
	
	@Override
	public void clearErgebnisse() {
		this.managerSpielzug.clearErgebnisse();
	}

	@Override
	public void figurWaehlen(int nummer) {
		if (stateMachine.getState() == State.S.FigurAufStartfeld || stateMachine.getState() == State.S.FigurBewegen) {
			this.managerSpielzug.figurWaehlen(nummer);
		}	
	}

	@Override
	public boolean getShowErgebnisse() {
		return this.managerSpielzug.getShowErgebnisse();
	}
}
