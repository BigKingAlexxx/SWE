package application.StateMachine;

import application.StateMachine.port.StateMachine.StateMachinePort;
import application.StateMachine.port.Subject.SubjectPort;

public interface StateMachineFactory {

	StateMachineFactory FACTORY = new StateMachineFactoryImpl();
	
	StateMachinePort stateMachinePort();
	
	SubjectPort subjectPort();
	
	
}
