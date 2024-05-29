package src.game;

import src.configurazione.Configurazione;
import src.game.caselle.Casella;
import src.game.giocatore.Giocatore;

import java.util.List;

public class BoardStandard implements Board {
    private static BoardStandard instance = null;
    private Casella[][] tabellone;
    private Configurazione conf;
    private int nColonne;
    private int nRighe;
    private boolean automatico;

    private BoardStandard(Casella[][] tabellone, Configurazione conf//VERIFICARE SE SERVE DAVVERO ALLA BOARD DATO CHE IL TABELLONE È GIà STATO CREATO
                           , List<Giocatore> giocatori){
        this.tabellone = tabellone;
        this.conf = conf;
        this.nColonne=conf.getnColonne();
        this.nRighe=conf.getnRighe();
        this.automatico= Boolean.parseBoolean(null);//SE NEL GIOCATORE NON SI OBBLIGA LA SEQUENZA DI UTILIZZO DEI
        //METODI, PREPARARE DELLE EVENTUALI ECCEZZIONI
        draw();
    }

    public static BoardStandard getInstance(Casella[][] tabellone, Configurazione conf, List<Giocatore> giocatori){
        if(instance == null){
            instance = new BoardStandard(tabellone, conf, giocatori);
        }
        return instance;
    }

    @Override
    public void gestisciEvento(int posizione) {
        int numCasella = posizione;
        int riga = (int) Math.ceil((double) numCasella / nColonne);
        int colonna = numCasella % nColonne;
        if (colonna == 0) {
            colonna = nColonne;
        }
        tabellone[riga][colonna].gestisciEvento();
    }

    @Override
    public void avvisoEventoEffettuato() {

    }

    @Override
    public void cominciaTurno(String nome, boolean automatico) {
        this.automatico = automatico;
        //FAR APPARIRE DELLE COSE SULLO SCHERMO
    }

    @Override
    public void avvisoLancioEffettuato() {

    }

    @Override
    public void avvisoSpostamentoEffettuato() {

    }

    @Override
    public void avvisoFinePartita() {

    }

    @Override
    public void draw() {
        for (int r = 0; r < nRighe; r++) {
            for (int c = 0; c < nColonne; c++) {
                tabellone[r][c].draw();
            }
        }
    }
}
