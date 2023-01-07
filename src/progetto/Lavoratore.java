package progetto;

import java.util.ArrayList;

public class Lavoratore {
    private String idLavoratore;
    private String nome;
    private String cognome;
    private int eta;
    private String ruolo;
    private ArrayList<String> macchinariLavoratore;

    public String getIdLavoratore() {
        return idLavoratore;
    }

    public void setIdLavoratore(String idLavoratore) {
        this.idLavoratore = idLavoratore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public ArrayList<String> getMacchinariLavoratore() {
        return macchinariLavoratore;
    }

    public void setMacchinariLavoratore(ArrayList<String> macchinariLavoratore) {
        this.macchinariLavoratore = macchinariLavoratore;
    }
}
