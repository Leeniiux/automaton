package examples;

import automaton.Automaton;
import automaton.exceptions.AtmIllegalOperationException;
import automaton.exceptions.AtmTooManyArgumentsException;
import automaton.utils.Parser;

import java.io.FileNotFoundException;

public class Hour {
    public static void main(String[] args) {
        Automaton automaton = null;
        try { automaton = Parser.fromFile("files/hour.dot");
        } catch(FileNotFoundException | AtmIllegalOperationException | AtmTooManyArgumentsException e) { System.out.println("There has been an error while parsing your automaton !"); }
        if(automaton == null) return;

        System.out.println("\"12:36\" - ACCEPTED : " + automaton.accepts("12:36"));
        System.out.println("\"04:10\" - ACCEPTED : " + automaton.accepts("04:10"));
        System.out.println("\"16:60\" - ACCEPTED : " + automaton.accepts("16:60"));
        System.out.println("\"24:30\" - ACCEPTED : " + automaton.accepts("24:30"));
        System.out.println("\"10:304\" - ACCEPTED : " + automaton.accepts("10:304"));
    }
}
