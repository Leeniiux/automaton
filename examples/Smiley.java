package examples;

import automaton.Automaton;
import automaton.exceptions.AtmIllegalOperationException;
import automaton.exceptions.AtmTooManyArgumentsException;
import automaton.utils.Parser;

import java.io.FileNotFoundException;

public class Smiley {
    public static void main(String[] args) {
        Automaton automaton = null;
        try { automaton = Parser.fromFile("files/smiley.dot");
        } catch(FileNotFoundException | AtmIllegalOperationException | AtmTooManyArgumentsException e) { System.out.println("There has been an error while parsing your automaton !"); }
        if(automaton == null) return;

        System.out.println("\":-)\" - ACCEPTED : " + automaton.accepts(":-)"));
        System.out.println("\";')\" - ACCEPTED : " + automaton.accepts(";')"));
        System.out.println("\";-\" - ACCEPTED : " + automaton.accepts(";-"));
    }
}
