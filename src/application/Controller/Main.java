package application.Controller;

import application.Gui.GuiFactory;

public class Main {

	public static void main(String[] args) {
		GuiFactory.FACTORY.uiPort().ui().startEventLoop();
	}

}
