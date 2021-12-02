package automaton;

import automaton.utils.Parser;
import automaton.utils.AutomatonBuilder;

import java.util.*;
import java.util.regex.Pattern;

/**
 * State class, allowing the testing of input chars through instances of {@link #State}
 */
public class State {
    private final Map<Pattern, State> transitions = new HashMap<>();
    private String name;
    private boolean isVoid = false;

    /**
     * Public constructor of a {@link #State}. Creates a {@link #State} based on its given name.
     * Instances of {@link #State} allow to add transitions and links to other instances of {@link #State} through regular expression patterns.
     * In order to create an instance of a {@link #State}, it is recommended to use AutomatonAPI's builders and parsers.
     * @see AutomatonBuilder
     * @see Parser
     *
     * @param name name of a {@link #State}
     */
    public State(String name) { this.name = name; }

    /**
     * Private constructor of a {@link #State}. Creates a {@link #State} based on a boolean defining whether it should function as a void state or not.
     * @param isVoid element defining whether the {@link #State} is a void state or not.
     */
    private State(boolean isVoid) { this.isVoid = isVoid; }

    /**
     * @return true if the {@link #State} is a void state. Returns false otherwise.
     */
    public boolean isVoid() { return isVoid; }

    /**
     * Adds a transition to a {@link #State}.
     * @param regex regular expression that should match the input in order to reach its associated destination.
     * @param destination state reached when the input matches the given regular expression.
     */
    public void addTransition(String regex, State destination) { transitions.put(Pattern.compile(regex), destination); }

    /**
     * Tests a character as an input of all matchers and gets to its destination state.
     * @param input character to test as input of a {@link #State}
     * @return a {@link #State}, destination of the input after its character has been tested.
     * Returns a void state if no state can be reached.
     */
    public State getDestination(char input) {
        for(Pattern pattern : transitions.keySet()) {
            if(pattern.matcher(String.valueOf(input)).find()) {
                return transitions.get(pattern);
            }
        } return new State(true);
    }

    /**
     * @return the name of a {@link #State}
     */
    public String getName() { return name; }

    /**
     * This method is a recursive method. It is used in Automaton.
     * @see Automaton
     *
     * @return a list of String containing all transitions contained, as human readable strings.
     */
    public List<String> getTransitions() {
        List<String> temp = new ArrayList<>();
        this.transitions.forEach((regex, destination) -> {
            temp.add(this.getName() + " -> " + destination.getName() + " [label=\"" + regex + "\"]");
            temp.addAll(destination.getTransitions());
        });
        return temp;
    }

}
