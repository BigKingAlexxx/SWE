package application.StateMachine.Impl;

import java.util.ArrayList;
import java.util.List;

import application.StateMachine.port.State;
import application.StateMachine.port.Subject.Observer;

public class StateMachineImpl {
	
	private List<Observer> observers = new ArrayList<>();
	private State currentState;

	public StateMachineImpl() {
		this.currentState = State.S.Initialisiert;
	}

	public void attach(Observer obs) {
		this.observers.add(obs);
		obs.update(currentState);
	}

	public void detach(Observer obs) {
		this.observers.remove(obs);
	}

	public State getState() {
		return this.currentState;
	}

	public void setState(State state) {
		currentState = state;
		observers.forEach(obs -> obs.update(state));
	}

}
