package src.game;

import src.configurazione.Configurazione;
import src.configurazione.Scala;
import src.configurazione.Serpente;
import src.game.caselle.Casella;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gioco {
    private static Gioco instance;
    private final List<Giocatore> giocatori;
    private Casella[][] tabellone;
    private final Configurazione configurazione;
    private Giocatore currentPlayer;
    private Board board;
    private final List<Scala> listaScale;
    private final List<Serpente> listaSerpenti;

    // Enum per i tipi di caselle
    public enum TipoCasella {
        NORMALE, SOSTA, PREMIO, PESCA
    }

    // Costruttore privato per Singleton
    private Gioco(Configurazione configurazione) {
        this.configurazione = configurazione;
        giocatori = configurazione.getGiocatori();
        listaScale = configurazione.getListaScale();
        listaSerpenti = configurazione.getListaSerpenti();
        inizializzaTabellone();
        currentPlayer = null; // Nessun giocatore corrente all'inizio
    }

    // Metodo per ottenere l'unica istanza di Gioco
    public static synchronized Gioco getInstance(Configurazione configurazione) {
        if (instance == null) {
            instance = new Gioco(configurazione);
        }
        return instance;
    }

    // Metodo per inizializzare il tabellone
    private void inizializzaTabellone() {
        int righe = configurazione.getnRighe();
        int colonne = configurazione.getnColonne();
        tabellone = new Casella[righe][colonne];
        for (int r = 0; r < righe; r++) {
            for (int c = 0; c < colonne; c++) {
                boolean determinato = false;
                int numeroCasella = r * colonne + c + 1;
                for(Scala scala : listaScale){
                    if(numeroCasella==scala.getCasellaInizio()){
                        tabellone[r][c] = new CasellaPiediScala(numeroCasella, scala.getCasellaFine());
                        determinato = true;
                    }else if(numeroCasella==scala.getCasellaFine()){
                        tabellone[r][c] = new CasellaCimaScala(numeroCasella);
                        determinato = true;
                    }
                }
                if(!determinato){
                    for(Serpente serpente : listaSerpenti){
                        if(numeroCasella==serpente.getCasellaInizio()){
                            tabellone[r][c] = new CasellaTestaSerpente(numeroCasella, serpente.getCasellaFine());
                            determinato = true;
                        }else if(numeroCasella==serpente.getCasellaFine()){
                            tabellone[r][c] = new CasellaCodaSerpente(numeroCasella);
                        }
                    }
                }
                if(!determinato){
                    TipoCasella tipo = scegliTipoCasella();
                    tabellone[r][c] = creaCasella(numeroCasella, tipo);
                }
            }
        }
    }

    // Metodo per iniziare il gioco
    public void play() {
        // Creare la board e disegnare la matrice di celle
        board = new Board(tabellone);

        while (!isGameOver()) {
            for (Giocatore giocatore : giocatori) {
                currentPlayer = giocatore;
                currentPlayer.gestisciTurno(board);//gli passo la board così se avviene una modifica ci pensa il giocatore a segnalarla
                if (isGameOver()) {
                    break;
                }
            }
        }
    }

    // Metodo per verificare se la partita è finita
    private boolean isGameOver() {
        return currentPlayer != null && currentPlayer.isWinner();
    }


    // Metodo per scegliere casualmente il tipo di casella
    private TipoCasella scegliTipoCasella() {
        Random random = new Random();
        double rand = random.nextDouble();
        if (rand < 0.7) {
            return TipoCasella.NORMALE;
        } else {
            List<TipoCasella> tipiNonNormali = new ArrayList<>();
            if (configurazione.isCasellaSosta()) {
                tipiNonNormali.add(TipoCasella.SOSTA);
            }
            if (configurazione.isCasellaPremio()) {
                tipiNonNormali.add(TipoCasella.PREMIO);
            }
            if (configurazione.isCasellaPesca()) {
                tipiNonNormali.add(TipoCasella.PESCA);
            }

            int numNonNormali = tipiNonNormali.size();
            double probPerTipo = 0.3 / numNonNormali;
            double probabilitaCorrente = 0.7;

            for (TipoCasella tipo : tipiNonNormali) {
                if (rand < probabilitaCorrente + probPerTipo) {
                    return tipo;
                } else {
                    probabilitaCorrente += probPerTipo;
                }
            }
            return TipoCasella.NORMALE;
        }
    }



    // Metodo per creare una casella di un determinato tipo
    private Casella creaCasella(int numeroCasella, TipoCasella tipo) {
        switch (tipo) {
            case NORMALE:

                return new CasellaNormale(numeroCasella);
            case SOSTA:
                return new CasellaSosta(numeroCasella);
            case PREMIO:
                return new CasellaPremio(numeroCasella);
            case PESCA:
                return new CasellaPesca(numeroCasella);
            default:
                // Se il tipo non è valido, creiamo una casella normale di default
                return new CasellaNormale(numeroCasella);
        }
    }
}
