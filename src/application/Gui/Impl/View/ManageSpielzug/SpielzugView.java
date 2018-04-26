package application.Gui.Impl.View.ManageSpielzug;

import java.util.ArrayList;

import application.Logic.port.MVC.MVCPort;
import application.Spieler.SpielerImpl;
import application.Spielzug.SpielzugFactory;
import application.Spielzug.port.PortSpielzug.SpielzugManagement;
import application.Spielzug.port.PortSpielzug.SpielzugPort;
import application.StateMachine.port.State;
import application.StateMachine.port.Subject.Observer;

public class SpielzugView implements Observer{
	
	private String[] farben = {"Rot", "Gelb", "Gruen", "Blau"};
	private int i = 0;
	private SpielzugManagement spielzugManagement;
	private MVCPort mvcPort;
	
	public SpielzugView(SpielzugPort spielzugPort, MVCPort mvcPort) {
		this.spielzugManagement = spielzugPort.spielzugManagement();
		this.mvcPort = mvcPort;
		this.mvcPort.subject().attach(this);
	}
	
	@Override
	public void update(State state) {
		if (State.S.Initialisiert == state) {
			System.out.println("Spieler Rot:	Fig1 H,			Fig2 H,			Fig3 H");
			System.out.println("Spieler Gelb:	Fig1 H,			Fig2 H,			Fig3 H");
			System.out.println("Spieler Gruen:	Fig1 H,			Fig2 H,			Fig3 H");
			System.out.println("Spieler Blau:	Fig1 H,			Fig2 H,			Fig3 H");
			System.out.println();
			System.out.println("Spieler Rot am Zug. Zum Würfeln X drücken und mit Enter bestätigen.");
			System.out.println("------------------------------------------------------------------------------------");
		}

	}

	public void showFeld(String farbe, SpielerImpl s1, SpielerImpl s2, SpielerImpl s3, SpielerImpl s4) {
		System.out.println("Spieler " + farbe + ":");
		
		ArrayList<Integer> ergebnisse = SpielzugFactory.FACTORY.spielzugPort().spielzugManagement().getErgebnisse();
		if (ergebnisse.size() == 4) ergebnisse.remove(3);
		System.out.println(ergebnisse);
		//for (int i = 0; i < ergebnisse.size() - 1; i++) System.out.println("Ergebnis: " + ergebnisse.get(i));
		SpielzugFactory.FACTORY.spielzugPort().spielzugManagement().clearErgebnisse();
		
		System.out.println(
				"Spieler Rot:	Fig1 " + s1.getFig1() + ", 		Fig2 " + s1.getFig2() + ", 		Fig3 " + s1.getFig3());
		System.out.println(
				"Spieler Gelb:	Fig1 " + s2.getFig1() + ", 		Fig2 " + s2.getFig2() + ", 		Fig3 " + s2.getFig3());
		System.out.println(
				"Spieler Gruen:	Fig1 " + s3.getFig1() + ", 		Fig2 " + s3.getFig2() + ", 		Fig3 " + s3.getFig3());
		System.out.println(
				"Spieler Blau:	Fig1 " + s4.getFig1() + ", 		Fig2 " + s4.getFig2() + ", 		Fig3 " + s4.getFig3());
		System.out.println();
		System.out.println("Spieler " + farben[(i + 1) % 4] + " am Zug. Zum Würfeln X drücken und mit Enter bestätigen.");
		i++;
	}

}
