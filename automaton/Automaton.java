package automaton;

import automaton.utils.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Automaton {
    private String name;
    private State s0;
    private List<State> sf;

    public Automaton(String name, State s0, List<State> sf) {
        this.name = name;
        this.s0 = s0;
        this.sf = sf;
    }

    public int accepts(String input) {
        State current = s0;
        for(char c : input.toCharArray()) {
            current = current.getDestination(c);
            if(current == null) break;
        }
        return current == null ? -1 : sf.contains(current) ? 1 : 0;
    }

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
}
