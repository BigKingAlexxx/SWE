package application.StateMachine;

import application.StateMachine.Impl.StateMachineImpl;
import application.StateMachine.port.State;
import application.StateMachine.port.StateMachine.StateMachine;
import application.StateMachine.port.StateMachine.StateMachinePort;
import application.StateMachine.port.Subject.Observer;
import application.StateMachine.port.Subject.Subject;
import application.StateMachine.port.Subject.SubjectPort;

public class StateMachineFactoryImpl implements StateMachineFactory, SubjectPort, StateMachinePort, StateMachine, Subject
{
	private StateMachineImpl stateMachine;

	private void mkStateMachine() {
		if (stateMachine == null)
			stateMachine = new StateMachineImpl();
	}

	public SubjectPort subjectPort() {
		return this;
	}

	public StateMachinePort stateMachinePort() {
		return this;
	}

	public synchronized StateMachine stateMachine() {
		this.mkStateMachine();
		return this;
	}

	public synchronized Subject subject() {
		this.mkStateMachine();
		return this;
	}

	public synchronized void attach(Observer obs) {
		this.stateMachine.attach(obs);
	}

	public synchronized void detach(Observer obs) {
		this.stateMachine.detach(obs);
	}

	public synchronized State getState() {
		return this.stateMachine.getState();
	}

	public synchronized void setState(State state) {
		this.stateMachine.setState(state);
	}
}
