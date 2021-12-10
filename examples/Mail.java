package examples;

import automaton.Automaton;
import automaton.exceptions.AtmIllegalOperationException;
import automaton.exceptions.AtmTooManyArgumentsException;
import automaton.utils.Parser;

import java.io.FileNotFoundException;

public class Mail {
    public static void main(String[] args) {
        Automaton automaton = null;
        try { automaton = Parser.fromFile("files/mail.dot");
        } catch(FileNotFoundException | AtmIllegalOperationException | AtmTooManyArgumentsException e) { System.out.println("There has been an error while parsing your automaton !"); }
        if(automaton == null) return;

        System.out.println("\"clement.lavedrine@etu.univ-nantes.fr\" - ACCEPTED : " + automaton.accepts("clement.lavedrine@etu.univ-nantes.fr"));
        System.out.println("\"clement.lavedrine@etu.univ-nantes.\" - ACCEPTED : " + automaton.accepts("clement.lavedrine@etu.univ-nantes."));
        System.out.println("\"clement.lavedrine@etu.univ-nantes\" - ACCEPTED : " + automaton.accepts("clement.lavedrine@etu.univ-nantes"));
        System.out.println("\"clement.lavedrine@etu.univ-\" - ACCEPTED : " + automaton.accepts("clement.lavedrine@etu.univ-"));
    }
}
