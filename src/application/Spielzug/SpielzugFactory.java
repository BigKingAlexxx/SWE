package application.Spielzug;

import application.Spielzug.port.PortSpielzug.SpielzugPort;

public interface SpielzugFactory {

	SpielzugPort spielzugPort();
	
	SpielzugFactory FACTORY = new SpielzugFactoryImpl();
}
