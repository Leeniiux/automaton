package automaton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import automaton.utils.AutomatonBuilder;
import automaton.utils.Parser;

/**
 * Automaton class, allowing the testing of inputs through instances of {@link #Automaton}
 */
public class Automaton {
    private String name;
    private State s0;
    private List<State> sf;

    /**
     * Public constructor of an {@link #Automaton}. Creates an {@link #Automaton} based on states and transitions.
     * Instances of {@link #Automaton} allow to run multiple inputs through an finite-state automaton.
     * In order to create an instance of an {@link #Automaton}, it is recommended to use AutomatonAPI's builders and parsers.
     * @see Parser
     * @see AutomatonBuilder
     *
     * @param name name of an {@link #Automaton}
     * @param s0 initial state of an {@link #Automaton}
     * @param sf final states of an {@link #Automaton}
     */
    public Automaton(String name, State s0, List<State> sf) {
        this.name = name;
        this.s0 = s0;
        this.sf = sf;
    }

    /**
     * Runs an input through an {@link #Automaton}.
     * Navigates states character by character to reach to an end state, which should be a final state of an {@link #Automaton}.
     *
     * @param input string running through {@link #Automaton} states
     * @return a boolean depending on whether the input has properly made it through the {@link #Automaton} or not.
     */
    public boolean accepts(String input) {
        State current = s0;
        for(char c : input.toCharArray()) {
            current = current.getDestination(c);
            if(current.isVoid()) break;
        }
        return sf.contains(current);
    }

    /**
     * The outcome of this method can be used to manipulate finite-state automata as .dot files.
     * @return a String formed as a .dot file.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ").append(name).append(" {\n\t#init ").append(s0.getName()).append("\n\t#end");
        for(State s : sf) sb.append(" ").append(s.getName());
        sb.append("\n\n");

        List<String> temp = s0.getTransitions();
        List<String> transitions = new ArrayList<>();
        temp.forEach(t -> {
            if(!transitions.contains(t)) transitions.add(t);
        });
        Collections.sort(transitions);

        transitions.forEach(t -> sb.append("\t").append(t).append("\n"));

        return sb.append("}").toString();
    }

    /**
     * Exports an instance of {@link #Automaton} as a file.
     * File extension should be specified in the path
     * This method will not override an elready existing file !
     * @param path path to the file to write in
     * @return true if an {@link #Automaton} properly is exported. Returns false otherwise
     */
    public boolean export(String path) { return export(path, false); }

    /**
     * Exports an instance of {@link #Automaton} as a file.
     * File extension should be specified in the path
     * @param path path to the file to write in
     * @param override whether this method should override an already existing file or not. Attention : No confirmation is needed
     * @return true if an {@link #Automaton} properly is exported. Returns false otherwise
     */
    public boolean export(String path, boolean override) {
        File file = new File(path);
        if(file.exists()) {
            if(override) {
                if (!file.delete()) return false;
            } else return false;
        }

        try {
            if(file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(path);
                fileWriter.write(toString());
                fileWriter.close();
                return true;
            }
        } catch(IOException ignore) { return false; }
        return false;
    }
}