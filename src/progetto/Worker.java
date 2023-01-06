package progetto;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Worker {
    //inizio static
    private static ArrayList<String> MacchinariWorker= new ArrayList<String>();
    private static ArrayList<String> MacchinariManager= new ArrayList<String>();
    private static ArrayList<String> MacchinariExecutive= new ArrayList<String>();
    public static ArrayList<String> getMacchinariWorker(){
        return MacchinariWorker;
    }
    public static void setMacchinariWorker(ArrayList<String> x){
        MacchinariWorker = x;
    }
    public static ArrayList<String> getMacchinariManager(){
        return MacchinariWorker;
    }
    public static void setMacchinariManager(ArrayList<String> x){
        MacchinariManager = x;
    }
    public static ArrayList<String> getMacchinariExecutive(){
        return MacchinariManager;
    }
    public static void setMacchinariExecutive(ArrayList<String> x){
        MacchinariWorker = x;
    }
    //fine static

    private String idWorker;
    private String nome;
    private String cognome;
    private int eta;
    private String ruolo;
    private ArrayList<String> macchinariSpecificiUtente;

}
