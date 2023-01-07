package progetto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Progetto {
    public static void main(String[] Args){
        Macchinario macchinari[];
        Lavoratore lavoratori[];
        Prodotto prodotti[];
        ArrayList<String> idMacchinariWorker = new ArrayList<>();
        ArrayList<String> idMacchinariManager = new ArrayList<>();
        ArrayList<String> idMacchinariExecutive = new ArrayList<>();
        Scanner input =  new Scanner(System.in);
        String toAnalyze = input.nextLine();
        String[] quantitaPerClasse = toAnalyze.split(" ");
        lavoratori =  new Lavoratore[Integer.parseInt(quantitaPerClasse[0])];
        macchinari =  new Macchinario[Integer.parseInt(quantitaPerClasse[1])];
        prodotti =  new Prodotto[Integer.parseInt(quantitaPerClasse[2])];
        for(int i = 0; i < lavoratori.length; i++){
            toAnalyze = input.nextLine();
            String[] datiLavoratore = toAnalyze.split(" ");
            lavoratori[i] = new Lavoratore(datiLavoratore[0], datiLavoratore[1], datiLavoratore[2], Integer.parseInt(datiLavoratore[3]), datiLavoratore[4]);
            System.out.println("ciao");
        }
        for(int i = 0; i < 3; i++){
            /*
            toAnalyze = input.nextLine();
            toAnalyze = toAnalyze.substring(toAnalyze.lastIndexOf(" ") + 1);
            String idMacchinariDaInserire[] = toAnalyze.split(",");
            for (int j = 0; j < idMacchinariDaInserire.length; j++){
                switch(i){
                    case 0: // caso worker
                        idMacchinariWorker.add(idMacchinariDaInserire[j]);
                        break;
                    case 1: // caso manager
                        idMacchinariManager.add(idMacchinariDaInserire[j]);
                        break;
                    case 2: // caso executive
                        idMacchinariExecutive.add(idMacchinariDaInserire[j]);
                        break;
                    default:
                }
            }*/ //caso hardcoded
            toAnalyze = input.nextLine();
            String ArrayDaAnalizzare[] = toAnalyze.split(" -> ");
            String idMacchinariDaInserire[] = ArrayDaAnalizzare[1].split(",");
            for (int j = 0; j < idMacchinariDaInserire.length; j++){
                switch(ArrayDaAnalizzare[0]){
                    case "worker": // caso worker
                        idMacchinariWorker.add(idMacchinariDaInserire[j]);
                        break;
                    case "manager": // caso manager
                        idMacchinariManager.add(idMacchinariDaInserire[j]);
                        break;
                    case "executive": // caso executive
                        idMacchinariExecutive.add(idMacchinariDaInserire[j]);
                        break;
                    default:
                }
            } //caso che decide in base a cosa é stato scritto prima di " -> "
        }
        for (int i = 0; i < macchinari.length; i++){
            toAnalyze = input.nextLine();
            String[] ArrayDaAnalizzare = toAnalyze.split(" ");
            String[] numeriRange = ArrayDaAnalizzare[2].split(",");
            String[] macchinariInConflitto = ArrayDaAnalizzare[4].split(",");
            ArrayList<String> conflitti = new ArrayList<>();
            for(int j = 0; j < macchinariInConflitto.length; j++) { conflitti.add(macchinariInConflitto[j]);}
            macchinari[i] = new Macchinario(ArrayDaAnalizzare[0], ArrayDaAnalizzare[1], Integer.parseInt(numeriRange[0]), Integer.parseInt(numeriRange[1]), ArrayDaAnalizzare[3], conflitti);
        }
        for(int i = 0; i < prodotti.length; i++){
            toAnalyze = input.nextLine();
            String[] ArrayDaAnalizzare = toAnalyze.split(" ");
            String[] catenaArray = ArrayDaAnalizzare[3].split("->");
            ArrayList<String> catena = new ArrayList<>();
            for(int j = 0; j < catenaArray.length; j++) {catena.add(catenaArray[j]);}
        }
        for(int i = 0; i < lavoratori.length; i++){
            toAnalyze = input.nextLine();
            String[] ArrayToAnalyze = toAnalyze.split(" -> ");
            String[] macchinariDaAssegnareAlLavoratore = ArrayToAnalyze[1].split(",");
            for(int j = 0; j < lavoratori.length; j++){
                if (lavoratori[j].getIdLavoratore().equals(ArrayToAnalyze[0]))
                    for (int k = 0; k < macchinariDaAssegnareAlLavoratore.length; k++){
                        lavoratori[j].assegnaMacchinarioLavoratore(macchinariDaAssegnareAlLavoratore[k]);
                    }
            }
        }
        toAnalyze = input.nextLine();
        String[] resolveTask = toAnalyze.split(" ");
        if (resolveTask[0].equalsIgnoreCase("TASK1"))
        {
            //task1
        }
        else if (resolveTask[0].equalsIgnoreCase("TASK2")) //formato task2 nell'input: "TASK2 "
        {
            //task2
        }
        else if (resolveTask[0].equalsIgnoreCase("TASK3"))
        {
            //task3
        }
    }
}
