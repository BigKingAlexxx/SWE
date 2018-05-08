package application.Gui.Impl.View.ManageSpielzug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import application.Logic.port.MVC.MVCPort;
import application.Spieler.SpielerImpl;
import application.Spielzug.SpielzugFactory;
import application.Spielzug.port.PortSpielzug.SpielzugManagement;
import application.Spielzug.port.PortSpielzug.SpielzugPort;
import application.StateMachine.port.State;
import application.StateMachine.port.Subject.Observer;

public class SpielzugView implements Observer{
	
	private SpielzugManagement spielzugManagement;
	private MVCPort mvcPort;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private String input;
	
	private String farbe;
	private SpielerImpl s1;
	private SpielerImpl s2;
	private SpielerImpl s3;
	private SpielerImpl s4;
	
	public SpielzugView(SpielzugPort spielzugPort, MVCPort mvcPort) {
		this.spielzugManagement = spielzugPort.spielzugManagement();
		this.mvcPort = mvcPort;
		this.mvcPort.subject().attach(this);
	}
	
	@Override
	public void update(State state) {
		
		if (State.S.FigurAufStartfeld == state) {
			System.out.println("Spieler " + this.farbe + ":");
			ArrayList<Integer> ergebnisse = SpielzugFactory.FACTORY.spielzugPort().spielzugManagement().getErgebnisse();
			System.out.println(ergebnisse);
			SpielzugFactory.FACTORY.spielzugPort().spielzugManagement().clearErgebnisse();
			System.out.println("Bitte Figur wählen (1, 2, 3) um aufs Feld zu schicken.");
			try {
				input = br.readLine();
				if (input.equals("1")) spielzugManagement.figurWaehlen(Integer.parseInt(input));
				if (input.equals("2")) spielzugManagement.figurWaehlen(Integer.parseInt(input));
				if (input.equals("3")) spielzugManagement.figurWaehlen(Integer.parseInt(input));
				
			} catch (NumberFormatException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		
		if (State.S.FigurBewegen == state) {
			System.out.println("Spieler " + this.farbe + ":");
			ArrayList<Integer> ergebnisse = SpielzugFactory.FACTORY.spielzugPort().spielzugManagement().getErgebnisse();
			System.out.println(ergebnisse);
			SpielzugFactory.FACTORY.spielzugPort().spielzugManagement().clearErgebnisse();
			System.out.println("Bitte Figur wählen (1, 2, 3) um zu bewegen.");
			try {
				input = br.readLine();
				if (input.equals("1")) spielzugManagement.figurWaehlen(Integer.parseInt(input));
				if (input.equals("2")) spielzugManagement.figurWaehlen(Integer.parseInt(input));
				if (input.equals("3")) spielzugManagement.figurWaehlen(Integer.parseInt(input));
				
			} catch (NumberFormatException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		
		if (State.S.Bestaetigen == state) {
			if (spielzugManagement.getShowErgebnisse()) {
				System.out.println("Spieler " + this.farbe + ":");
				ArrayList<Integer> ergebnisse = SpielzugFactory.FACTORY.spielzugPort().spielzugManagement()
						.getErgebnisse();
				
				System.out.println(ergebnisse);
				SpielzugFactory.FACTORY.spielzugPort().spielzugManagement().clearErgebnisse();
			}
		}
	}

	public void showFeld(String farbe, SpielerImpl s1, SpielerImpl s2, SpielerImpl s3, SpielerImpl s4) {
		
		this.farbe = farbe;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		
		System.out.println(
				"Spieler Rot:	Fig1 " + this.s1.getFig1() + ", 		Fig2 " + this.s1.getFig2() + ", 		Fig3 " + this.s1.getFig3());
		System.out.println(
				"Spieler Gelb:	Fig1 " + this.s2.getFig1() + ", 		Fig2 " + this.s2.getFig2() + ", 		Fig3 " + this.s2.getFig3());
		System.out.println(
				"Spieler Gruen:	Fig1 " + this.s3.getFig1() + ", 		Fig2 " + this.s3.getFig2() + ", 		Fig3 " + this.s3.getFig3());
		System.out.println(
				"Spieler Blau:	Fig1 " + this.s4.getFig1() + ", 		Fig2 " + this.s4.getFig2() + ", 		Fig3 " + this.s4.getFig3());
		System.out.println();
		System.out.println("Spieler " + farbe + " am Zug. Zum Würfeln x drücken und mit Enter bestätigen.");
		System.out.println("------------------------------------------------------------------------------------");
	}

}
