package automaton.utils;

import java.util.*;
import java.util.regex.Pattern;

public class State {
    private final Map<Pattern, State> transitions = new HashMap<>();
    private String name;

    public State(String name) { this.name = name; }

    public void addTransition(String regex, State destination) { transitions.put(Pattern.compile(regex), destination); }

    public State getDestination(char input) {
        for(Pattern pattern : transitions.keySet()) {
            if(pattern.matcher(String.valueOf(input)).find()) {
                return transitions.get(pattern);
            }
        } return null;
    }

    public String getName() { return name; }

    public List<String> getTransitions() {
        List<String> temp = new ArrayList<>();
        this.transitions.forEach((regex, destination) -> {
            temp.add(this.getName() + " -> " + destination.getName() + " [label=\"" + regex + "\"]");
            temp.addAll(destination.getTransitions());
        });
        return temp;
    }
}
