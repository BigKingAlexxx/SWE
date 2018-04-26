package application.StateMachine.port.Subject;

import application.StateMachine.port.State;

public interface Observer {

	void update(State newState);
}
