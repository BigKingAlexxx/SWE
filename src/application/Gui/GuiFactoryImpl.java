package application.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import application.Gui.Impl.View.ManageSpielzug.SpielzugView;
import application.Gui.Port.PortUI.UI;
import application.Gui.Port.PortUI.UIPort;
import application.Logic.LogicFactory;
import application.Logic.port.MVC.MVCPort;
import application.SpielEquipment.FeldImpl;
import application.Spieler.SpielerImpl;
import application.Spielzug.SpielzugFactory;
import application.Spielzug.port.PortSpielzug.SpielzugPort;
import application.StateMachine.StateMachineFactory;
import application.StateMachine.port.State;

public class GuiFactoryImpl implements GuiFactory, UIPort, UI {

	private SpielzugPort spielzugPort = LogicFactory.FACTORY.logicSpielzugPort();
	private MVCPort mvcPort = LogicFactory.FACTORY.MVCPort();
	private SpielzugView ui;

	@Override
	public UIPort uiPort() {
		return this;
	}

	@Override
	public UI ui() {
		if (this.ui == null) {
			this.ui = new SpielzugView(this.spielzugPort, this.mvcPort);
		}
		return this;
	}

	@Override
	public void startEventLoop() {
		FeldImpl feld = new FeldImpl();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			ui.showFeld(feld.getSpieler(feld.getCurrentPlayer()).getFarbe(), feld.getSpieler(0), feld.getSpieler(1), feld.getSpieler(2), feld.getSpieler(3));
			try {
				if (br.readLine().equals("x")) {
					SpielzugFactory.FACTORY.spielzugPort().spielzugManagement().wuerfeln(feld.getSpieler(feld.getCurrentPlayer()), feld);
				}
			} catch (IOException e) {
				System.out.println("Fehler");
				e.printStackTrace();
			}
		}
	}

}
