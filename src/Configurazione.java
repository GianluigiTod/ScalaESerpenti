package src;

import java.util.List;

public class Configurazione {
    private final Integer nRighe;
    private final Integer nColonne;
    private final boolean dadoSingolo;
    private final boolean lancioDiUnDado;
    private final boolean doppioSei;
    private final boolean casellaSosta;
    private final boolean casellaPremio;
    private final boolean casellaPesca;
    private final boolean ulterioriCarte;
    private final List<String> giocatori;

    public static class Builder{
        //parametro richiesto necessariamente
        private final List<String> giocatori;

        //parametri opzionali
        private Integer nRighe = null; private Integer nColonne = null;
        private boolean dadoSingolo = false; private boolean lancioDiUnDado = false;
        private boolean doppioSei = false; private boolean casellaSosta = false;
        private boolean casellaPremio = false;private boolean casellaPesca = false;
        private boolean ulterioriCarte = false;

        public Builder(List<String> giocatori) {
            this.giocatori = giocatori;
        }

        public Builder nRighe(Integer val){
            if(val > 0){
                nRighe = val; return this;
            }else{
                throw new IllegalArgumentException("Il numero di righe deve essere maggiore di zero");
            }
        }
        public Builder nColonne(Integer val){
            if(val > 0){
                nColonne = val; return this;
            }else{
                throw new IllegalArgumentException("Il numero di colonne deve essere maggiore di zero");
            }
        }
        public Builder dadoSingolo(boolean val){
            if(lancioDiUnDado && val){
                throw new IllegalArgumentException("L'estensione del dado singolo non è compatibile con quella del lancio di un solo dado");
            }else if(doppioSei && val) {
                throw new IllegalArgumentException("L'estensione del dado singolo non è compatibile con quella del doppio sei");
            }else{
                dadoSingolo = val; return this;
            }
        }
        public Builder lancioDiUnDado(boolean val){
            if(dadoSingolo && val) {
                throw new IllegalArgumentException("L'estensione del dado singolo non è compatibile con quella del lancio di un solo dado");
            }else{
                lancioDiUnDado = val; return this;
            }
        }
        public Builder doppioSei(boolean val){
            if(dadoSingolo && val) {
                throw new IllegalArgumentException("L'estensione del dado singolo non è compatibile con quella del doppio sei");
            }else{
                doppioSei = val; return this;
            }
        }
        public Builder casellaSosta(boolean val){casellaSosta = val; return this;}
        public Builder casellaPremio(boolean val){casellaPremio = val; return this;}
        public Builder casellaPesca(boolean val){casellaPesca = val; return this;}
        public Builder ulterioriCarte(boolean val){
            if(!casellaPesca && val) {
                throw new IllegalArgumentException("L'estensione delle ulteriori carte prevede quella delle caselle 'Pesca una carta'");
            }else{
                ulterioriCarte = val; return this;
            }
        }
        public Configurazione build(){return new Configurazione(this);}
    }//Builder
    private Configurazione(Builder builder){
        giocatori = builder.giocatori;
        nRighe = builder.nRighe;
        nColonne = builder.nColonne;
        dadoSingolo = builder.dadoSingolo;
        lancioDiUnDado = builder.lancioDiUnDado;
        doppioSei = builder.doppioSei;
        casellaSosta = builder.casellaSosta;
        casellaPremio = builder.casellaPremio;
        casellaPesca = builder.casellaPesca;
        ulterioriCarte = builder.ulterioriCarte;

    }


}
