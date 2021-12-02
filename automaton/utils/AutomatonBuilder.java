package automaton.utils;

import automaton.Automaton;
import automaton.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AutomatonBuilder class, allowing the storing and building of variables as instances of Automaton.
 */
public class AutomatonBuilder {
    private String name = "";
    private State s0 = null;
    private final List<State> sf = new ArrayList<>();
    private final Map<String, State> states = new HashMap<>();

    /**
     * Public constructor of an {@link #AutomatonBuilder}. Creates an instance of {@link #AutomatonBuilder}.
     */
    public AutomatonBuilder() {}

    /**
     * Sets the name of an {@link #AutomatonBuilder}.
     * @param name element defining the name of an {@link #AutomatonBuilder}.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates a new instance of State, or gets the stored State if its name already exists.
     * @see State
     * @param name element defining the name of a State.
     * @return a new instance of State if it did not exist already. Returns the already existing instance otherwise.
     */
    public State createState(String name) {
        State state = new State(name);
        if(states.containsKey(name)) return states.get(name);
        states.put(name, state);
        return state;
    }

    /**
     * Sets the initial state of an Automaton according to its name.
     * @see Automaton
     * @param name element defining the name of the initial state.
     */
    public void init(String name) {
        State init = createState(name);
        this.s0 = init;
    }

    /**
     * Adds a final state to an Automaton by its name.
     * @see Automaton
     * @param name element defining the name of the State added as a final state.
     * @return true if the state has correctly been added. Returns false otherwise.
     */
    public boolean addFinal(String name) {
        State fin = createState(name);
        return this.sf.add(fin);
    }

    /**
     * Builds an {@link #AutomatonBuilder} as an instance of Automaton.
     * @see Automaton
     * @return an instance of Automaton.
     */
    public Automaton build() {
        return !this.name.equals("") && this.s0 != null && !this.sf.isEmpty() ? new Automaton(name, s0, sf) : null;
    }
}
