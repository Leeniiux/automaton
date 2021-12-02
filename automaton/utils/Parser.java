package automaton.utils;

import automaton.Automaton;
import automaton.builder.AutomatonBuilder;
import automaton.exceptions.AtmIllegalOperationException;
import automaton.exceptions.AtmTooManyArgumentsException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Parser {
    private static String transition = "([^->])*->([^->])*\\[( ?)label( ?)=( ?)\".+\".*]";

    public static Automaton fromFile(String path) throws FileNotFoundException, AtmTooManyArgumentsException, AtmIllegalOperationException {
        AutomatonBuilder ab = new AutomatonBuilder();

        boolean name = false;
        boolean init = false;
        boolean end = false;

        BufferedReader bf = new BufferedReader(new FileReader(path));
        for(String line : bf.lines().collect(Collectors.toList())) {
            line = line.trim();

            if(line.isEmpty()) continue;

            if(line.startsWith("digraph")) {
                if(name) throw new AtmTooManyArgumentsException();
                String temp = line.replace("digraph", "").trim();
                ab.setName(temp.substring(0, temp.length()-1).trim());
                name = true;
            }

            if(line.startsWith("#init")) {
                if(init) throw new AtmTooManyArgumentsException();
                ab.init(line.replace("#init", "").trim());
                init = true;
            }

            else if(line.startsWith("#end")) {
                if(end) throw new AtmTooManyArgumentsException();
                Arrays.asList(line.replace("#end", "").trim().split(" ")).forEach(ab::addFinal);
                end = true;
            }

            else if(line.matches(transition)) {
                String from = line.split("->")[0].trim();
                String to = line.split("->")[1].trim().split("\\[")[0].trim();

                String temp = line.split("\\[", 2)[1].trim();
                int first = temp.indexOf("\"");
                int last = temp.indexOf("\"", first+1);
                String regex = temp.substring(first+1, last);

                ab.createState(from).addTransition(regex, ab.createState(to));
            }
        }

        Automaton automaton = ab.build();
        if(automaton == null) throw new AtmIllegalOperationException();
        return automaton;
    }
}
