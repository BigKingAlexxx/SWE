package application.Logic;

import application.Logic.port.PortLogicSpielzug.LogicSpielzugPort;
import application.Spielzug.SpielzugFactory;
import application.Spielzug.port.PortSpielzug.SpielzugManagement;
import application.Spielzug.port.PortSpielzug.SpielzugPort;
import application.StateMachine.StateMachineFactory;
import application.StateMachine.port.StateMachine.StateMachine;
import application.StateMachine.port.Subject.Subject;
import application.StateMachine.port.Subject.SubjectPort;
import application.Logic.port.MVC.MVCPort;

public class LogicFactoryImpl implements LogicSpielzugPort, LogicFactory, MVCPort{
	
	private SpielzugPort spielzugPort = SpielzugFactory.FACTORY.spielzugPort();
	private SubjectPort subjectPort = StateMachineFactory.FACTORY.subjectPort();


	@Override
	public LogicSpielzugPort logicSpielzugPort() {
		// TODO Auto-generated method stub
		return this;
	}


	@Override
	public SpielzugManagement spielzugManagement() {
		return spielzugPort.spielzugManagement();
	}


	@Override
	public MVCPort MVCPort() {
		return this;
	}


	@Override
	public Subject subject() {
		return subjectPort.subject();
	}


	@Override
	public void setAnzahlWuerfe(int num) {
		// TODO Auto-generated method stub
		
	}

}
