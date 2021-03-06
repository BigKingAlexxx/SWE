package application.Spielzug.port.PortSpielzug;

import java.util.ArrayList;

import application.SpielEquipment.FeldImpl;
import application.SpielEquipment.FigurImpl;
import application.Spieler.SpielerImpl;

public interface SpielzugManagement {
	
	void pruefen(SpielerImpl spieler, FeldImpl feld);
	void wuerfeln(SpielerImpl spieler, FeldImpl feld);
	int getGewuerfelteZahl();
	ArrayList<Integer> getErgebnisse();
	void clearErgebnisse();
	void figurWaehlen(int nummer);
	boolean getShowErgebnisse();
}