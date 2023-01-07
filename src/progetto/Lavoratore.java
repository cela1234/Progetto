package progetto;

import java.util.ArrayList;

public class Lavoratore {
    private String idLavoratore;
    private String nome;
    private String cognome;
    private int eta;
    private String ruolo;
    private ArrayList<String> macchinariLavoratore;

    public Lavoratore(String idLavoratore, String nome, String cognome, int eta, String ruolo){
        macchinariLavoratore = new ArrayList<>();
        this.idLavoratore = idLavoratore;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.ruolo = ruolo;
    }

    public String getIdLavoratore() {
        return idLavoratore;
    }
    public int getEta() {
        return eta;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }
    public String getRuolo() {
        return ruolo;
    }
    public ArrayList<String> getMacchinariLavoratore() {
        return macchinariLavoratore;
    }
    public void assegnaMacchinarioLavoratore(String idMacchinario) {
        this.macchinariLavoratore.add(idMacchinario);
    }
}
