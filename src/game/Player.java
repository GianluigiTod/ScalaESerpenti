package src.game;

import src.configurazione.Configurazione;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Player extends AbstractGiocatore{

    public Player(String nome, Configurazione config, Board board) {
        super(nome, config, board);
    }

    @Override
    public int[] gestioneTiro(int tiro1, int tiro2) {
        int numDadi = board.chiediNumDadi();
        if(numDadi == 1){
            tiro1=dado.roll();
            tiro2=0;
        }else{
            tiro1=dado.roll();
            tiro2=dado.roll();
        }
        return new int[]{tiro1, tiro2};
    }

    @Override
    public boolean divietoSosta() {
        boolean attivare = board.chiediUsaCartaDivieto();
        if(attivare){
            nTurniStop=0;
            numCarte--;
            return true;
        }
        return false;
    }

    @Override
    public void nuovoTurno() {
        board.avvisaNuovoTurno(nome);//il nome lo puoi usare per settare nella board il giocatore/pedina corrente, così ad esempio quando
        //dobbiamo aggiornare una certa pedina, sappiamo quale dobbiamo aggiornare, comunque avere informazioni riguardo
        //a chi sta visualizzando l'interfaccia in quel momento è sempre buono e potrebbe servire in qualsiasi altra
        //implementazione dell'interfaccia grafica(ricordo che Board è un'interfaccia che fa da mediatore tra Giocatore e la GUI)
        super.nuovoTurno();
    }

    @Override
    public void gestisciEvento() {
        board.avvisoGestioneEvento();
        board.attendiGestioneEvento();
        super.gestisciEvento();
    }

    @Override
    public void lancioDadi() {
        board.avvisoLancioDadi();//avvisa la board che sta per avvenire il lancio dei dadi
        //e quindi vede di gestire l'interfaccia grafica in modo da renderla adatta a quello che sta per accadere
        //ad esempio posso far comparire il tasto "lancio dadi"
        board.attendiLancioDadi();//questo metodo fa in modo che l'interfaccia grafica aspetta fino a quando
        //l'utente non clicca un certo pulsante, in questo caso il bottone "lancio dadi"
        super.lancioDadi();
    }

    @Override
    public void spostamento() {
        board.avvisoSpostamento();
        board.attendiSpostamento();
        super.spostamento();
    }
}
