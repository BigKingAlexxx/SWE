package application.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import application.Gui.Impl.MainFrame;
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
		SpielerImpl spielerRot = new SpielerImpl("Rot", 0);
		SpielerImpl spielerGelb = new SpielerImpl("Gelb", 11);
		SpielerImpl spielerGruen = new SpielerImpl("Gruen", 23);
		SpielerImpl spielerBlau = new SpielerImpl("Blau", 35);

		ArrayList<SpielerImpl> spieler = new ArrayList<SpielerImpl>();
		
		spieler.add(spielerRot);
		spieler.add(spielerGelb);
		spieler.add(spielerGruen);
		spieler.add(spielerBlau);
		
		int i = 0;

		FeldImpl feld = new FeldImpl();

		

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StateMachineFactory.FACTORY.stateMachinePort().stateMachine().setState(State.S.Initialisiert);

		while (true) {
			try {
				if (br.readLine().equals("x")) {
					SpielzugFactory.FACTORY.spielzugPort().spielzugManagement().wuerfeln(spieler.get(i), feld);
					ui.showFeld(spieler.get(i).getFarbe(), spielerRot, spielerGelb, spielerGruen, spielerBlau);
					if (i == 3) i = -1;
					i++;
				}
				System.out.println("------------------------------------------------------------------------------------");
			} catch (IOException e) {
				System.out.println("Fehler");
				e.printStackTrace();
			}
		}
	}

}
