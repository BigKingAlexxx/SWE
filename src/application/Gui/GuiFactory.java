package application.Gui;

import application.Gui.Port.PortUI.UIPort;

public interface GuiFactory {


	GuiFactory FACTORY = new GuiFactoryImpl();
	
	UIPort uiPort();
}
