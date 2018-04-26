package application.StateMachine.port.Subject;

public interface Subject {

	void attach(Observer obs);
	void detach(Observer obs);
}
