package src.game;

import src.Configurazione;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Configurazione config;
    private Board board;
    private List<Giocatore> giocatori;
    private Dado dado1;
    private Dado dado2;
    private Giocatore vincitore = null;
    private File file;
    private int IDCurrentPlayer = 0;
    private Giocatore currentPlayer = null;

    public Game(Configurazione config, Board board) {
        int c =1;
        this.config = config;
        this.giocatori = new ArrayList<>();
        for(String nome : config.getGiocatori()){
            giocatori.add(new Giocatore(nome, c));
            c++;
        }
        this.board= board;
        dado1 = new Dado();
        dado2 = new Dado();
        file = new File(config.getPath());
    }

    public void play(){//metodo eseguito solo se il gioco è in automatico
        while(true){
            currentPlayer = nuovoTurno();
            int tiro = lancioDadi();
            spostamento(tiro);
            if(vincitore!=null){
                break;
            }
            Cella cellaCorrente = board.getCella(currentPlayer.getPosizione());
            if(cellaCorrente.getTipo() != "cellaNormale"){//questa istruzione potebbe essere cambiata in corso dopera a seconda di come vogliamo implementare il resto
                gestisciEvento();
            }
        }
    }



    public int lancioDadi(){
        int ret = 0;
        if(config.isDadoSingolo()){
            ret = dado1.roll();
        }else{
            int tiro1 = dado1.roll();
            int tiro2 = 0;
            if(config.isLancioDiUnDado()){
                tiro2 = dado2.roll();
            }
            if(config.isDoppioSei() && tiro1 == 6 && tiro2 == 6){
                ret = tiro1 + tiro2 + lancioDadi();
            }
            ret = tiro1 + tiro2;
        }
        System.out.println(currentPlayer+" ha fatto "+ret);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(currentPlayer+" ha fatto "+ret);
            bw.newLine();
            bw.close();
        }catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void spostamento(int tiro){
        int nuovaPos = currentPlayer.getPosizione() + tiro;
        if(nuovaPos > board.getDimensione()){
            nuovaPos = board.getDimensione() - (nuovaPos - board.getDimensione());
        }
        currentPlayer.setPosizione(nuovaPos);
        if(config.isAutomatico()){
            board.aggiornaPos(currentPlayer);
        }
        System.out.println(currentPlayer+" è arrivato nella casella "+nuovaPos);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(currentPlayer+" è arrivato nella casella "+nuovaPos);
            bw.newLine();
            bw.close();
        }catch(IOException ioe){
            System.out.println(ioe);
        }
        isGameOver();
    }

    public Giocatore nuovoTurno(){
        //Scrivere il codice per avvisare l'Interfaccia grafica che è cominciato un nuovo turno e quindi eventualmente deve aggiornarsi
        //Ad esempio avevo pensato di rendere di nuovo non visibile il bottone per passare al nuovo turno



        Giocatore ret = giocatori.get(IDCurrentPlayer);
        System.out.println("È il turno del giocatore:"+ret);
        System.out.println(ret+" sta per tirare i dadi");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("È il turno del giocatore:"+ret+"\n"+ret+" sta per tirare i dadi");
            bw.newLine();
        }catch(IOException ioe) {
            System.out.println(ioe);
        }
        IDCurrentPlayer++;
        return ret;
    }



    private void isGameOver(){
        if(currentPlayer.getPosizione() == board.getDimensione()){
            vincitore = currentPlayer;
            System.out.println("Vincitore: "+vincitore);
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write("Vincitore: "+vincitore+"\n");
                bw.close();
            }catch(IOException ioe){
                System.out.println(ioe);
            }
        }
    }





}
