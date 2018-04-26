package application.StateMachine.port.StateMachine;

import application.StateMachine.port.State;

public interface StateMachine {

	State getState();
	
	void setState(State state);
}
