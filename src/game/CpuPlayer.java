package src.game;

import src.configurazione.Configurazione;

import java.util.Random;

public class CpuPlayer extends AbstractGiocatore{

    public CpuPlayer(String nome, Configurazione config, Board board) {
        super(nome, config, board);
    }

    @Override
    public int[] gestioneTiro(int tiro1, int tiro2) {
        Random random = new Random();
        Double numDadi = random.nextDouble();
        if(numDadi <0.5){
            tiro1=dado.roll();
            tiro2=0;
        }else{
            tiro1=dado.roll();
            tiro2=dado.roll();
        }
        return new int[]{tiro1, tiro2};
    }

    @Override
    public boolean divietoSosta() {//La CPU sceglierà sempre
        nTurniStop=0;
        numCarte--;
        return true;
    }

    @Override
    public void nuovoTurno() {
        try{
            Thread.sleep(4000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        super.nuovoTurno();
    }

    @Override
    public void lancioDadi() {
        try{
            Thread.sleep(4000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        super.lancioDadi();
    }

    @Override
    public void gestisciEvento() {
        try{
            Thread.sleep(4000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        super.gestisciEvento();
    }

    @Override
    public void spostamento() {
        try{
            Thread.sleep(4000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        super.spostamento();
    }
}
