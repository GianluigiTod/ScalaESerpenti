package src.game.giocatore;

import src.configurazione.Configurazione;
import src.game.Board;
import src.game.GameMediator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public abstract class AbstractGiocatore implements Giocatore {
    protected String nome;
    protected int posizione;
    protected String path;
    private File file;
    private boolean vincitore;
    protected int nTurniStop;
    protected final int dimensioneBoard;
    protected final Configurazione config;
    protected int tiro;
    protected final Dado dado;
    protected int numCarte;
    protected final boolean automatico;
    protected GameMediator mediator;


    //creare istanze di AbstractGiocatore è l'unico modo per considerare le possibili regole che potrebbero essere
    //implementate, inoltre creo delle sottoclassi perché mi conviene per il progetto che devo realizzare
    // se volessi creare un nuovo set di regole, bisognerebbe creare una nuova classe che implementa Giocatore  per definire
    //un nuovo comportamento
    public AbstractGiocatore(String nome, Configurazione config, GameMediator mediator, boolean automatico) {
        this.nome = nome;
        this.posizione = 1;
        this.path = config.getPath();
        file = new File(path);
        this.mediator=mediator;
        this.config = config;
        this.tiro = 0;
        dado=new Dado();
        this.numCarte = 0;
        dimensioneBoard=config.getnColonne()*config.getnRighe();
        this.automatico=automatico;
    }

    @Override
    public void gestisciTurno() {
        Giocatore.super.gestisciTurno();
    }

    @Override
    public void nuovoTurno() {
        //Scrivere il codice per avvisare l'Interfaccia grafica che è cominciato un nuovo turno e quindi eventualmente deve aggiornarsi
        //Ad esempio avevo pensato di rendere di nuovo non visibile il bottone per passare al nuovo turno

        mediator.notify(this, "cominciaTurno");

        stampa("È il turno del giocatore:"+nome+"\n"+nome+" sta per tirare i dadi");
    }

    @Override
    public void gestisciEvento() {
        mediator.notify(this, "gestisciEvento");//facciamo gestire l'evento alla cella della board
    }

    @Override
    public boolean isWinner() {
        return vincitore;
    }


    @Override
    public void gameOver() {
        if(posizione == dimensioneBoard){
            vincitore=true;
        }
    }

    @Override
    public void lancioDadi() {
        if(config.isUlterioriCarte() && nTurniStop!=0){
            if(numCarte==0){
                tiro=0;
                nTurniStop--;
            }else{
                boolean attiva = divietoSosta();//dove eventualmente setti il valore di nTurniStop a zero e numCarte--
                if(attiva){

                }
            }
        }
        if(nTurniStop==0){
            int tiro1=0;
            Integer tiro2=0;
            if(config.isDadoSingolo()){
                tiro = dado.roll();
            }else{
                if(config.isLancioDiUnDado()){
                    int[] ret = gestioneTiro(tiro1, tiro2);
                    tiro1=ret[0];
                    tiro2=ret[1];
                    if( tiro2!=0){
                        stampa("Il giocatore "+nome+" ha lanciato 2 dado");
                    }else{
                        stampa("Il giocatore "+nome+" ha lanciato 1 dado");
                    }
                }else{
                    tiro1 = dado.roll();
                    tiro2 = dado.roll();
                }
                tiro=tiro1+tiro2;
                if(config.isDoppioSei() && tiro1 == 6 && tiro2 == 6){
                    lancioDadi();
                }
            }
            stampa(nome+" ha fatto "+tiro);
        }else{
            tiro=0;
            nTurniStop--;
        }
        mediator.notify(this, "lancioEffettuato");
    }

    public abstract int[] gestioneTiro(int tiro1, int tiro2);

    public abstract boolean divietoSosta();

    @Override
    public void spostamento() {
        int nuovaPos = posizione + tiro;
        if(nuovaPos > dimensioneBoard){
            nuovaPos = dimensioneBoard - (nuovaPos - dimensioneBoard);
        }
        posizione=nuovaPos;
        stampa(nome+" è nella casella "+nuovaPos);
        mediator.notify(this, "spostamentoEffettuato");
    }

    private void stampa(String stringa){
        System.out.println(stringa);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(stringa);
            bw.newLine();
            bw.close();
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }


    class Dado{
        public int roll(){
            Random rand = new Random();
            return (rand.nextInt(6))+1;
        }
    }

    @Override
    public void finePartita(){
        mediator.notify(this, "finePartita");
    }


    //GETTER AND SETTER

    @Override
    public String getNome() {
        return nome;
    }


    @Override
    public int getPosizione() {
        return posizione;
    }

    @Override
    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    @Override
    public int getNTurniStop() {
        return nTurniStop;
    }

    @Override
    public void setNTurniStop(int nTurniStop) {
        nTurniStop = nTurniStop;
    }

    @Override
    public int getTiro() {
        return tiro;
    }


    @Override
    public int getNumCarte() {
        return numCarte;
    }

    @Override
    public void setNumCarte(int numCarte) {
        this.numCarte = numCarte;
    }

    @Override
    public boolean isAutomatico() {
        return automatico;
    }
}
