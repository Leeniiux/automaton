package automaton.builder;

import automaton.Automaton;
import automaton.utils.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutomatonBuilder {
    private String name = "";
    private State s0 = null;
    private final List<State> sf = new ArrayList<>();
    private final Map<String, State> states = new HashMap<>();

    public AutomatonBuilder() {}

    public void setName(String name) {
        this.name = name;
    }

    public State createState(String name) {
        State state = new State(name);
        if(states.containsKey(name)) return states.get(name);
        states.put(name, state);
        return state;
    }

    public void init(String name) {
        State init = createState(name);
        this.s0 = init;
    }

    public boolean addFinal(String name) {
        State fin = createState(name);
        return this.sf.add(fin);
    }

    public boolean removeFinal(String name) {
        State fin = createState(name);
        return this.sf.remove(fin);
    }

    public Automaton build() {
        return !this.name.equals("") && this.s0 != null && !this.sf.isEmpty() ? new Automaton(name, s0, sf) : null;
    }
}
