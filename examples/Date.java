package examples;

import automaton.Automaton;
import automaton.exceptions.AtmIllegalOperationException;
import automaton.exceptions.AtmTooManyArgumentsException;
import automaton.utils.Parser;

import java.io.FileNotFoundException;

public class Date {
    public static void main(String[] args) {
        Automaton automaton = null;
        try { automaton = Parser.fromFile("files/date.dot");
        } catch(FileNotFoundException | AtmIllegalOperationException | AtmTooManyArgumentsException e) { System.out.println("There has been an error while parsing your automaton !"); }
        if(automaton == null) return;

        System.out.println("\"10/12/2020\" - ACCEPTED : " + automaton.accepts("10/12/2020"));
        System.out.println("\"00/12/2020\" - ACCEPTED : " + automaton.accepts("00/12/2020"));
        System.out.println("\"10/12/0000\" - ACCEPTED : " + automaton.accepts("10/12/0000"));
        System.out.println("\"30/02/2020\" - ACCEPTED : " + automaton.accepts("30/02/2020"));
        System.out.println("\"31/12/2020\" - ACCEPTED : " + automaton.accepts("31/12/2020"));
        System.out.println("\"31/11/2020\" - ACCEPTED : " + automaton.accepts("31/11/2020"));
    }
}
