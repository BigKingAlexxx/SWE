package application.Logic;

import application.Logic.port.PortLogicSpielzug.LogicSpielzugPort;
import application.Logic.port.MVC.MVCPort;


public interface LogicFactory {
	
	LogicFactory FACTORY = new LogicFactoryImpl();
	
	LogicSpielzugPort logicSpielzugPort();
	
	MVCPort MVCPort();
}
