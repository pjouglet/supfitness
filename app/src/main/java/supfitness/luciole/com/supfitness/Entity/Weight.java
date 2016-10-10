package supfitness.luciole.com.supfitness.Entity;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import supfitness.luciole.com.supfitness.Database;

/**
 * Created by Luciole on 23/03/2016.
 */
public class Weight {

    private int weigth;
    private String date;
    private static ArrayList<Weight> weightList;
    private static Context context;

    //Constructeur
    public Weight(int weigth, String date){
        this.weigth = weigth;
        this.date = date;
    }

    /**
     *
     * @param context le context
     * @return Liste d'entité "Weight"
     */
    public static ArrayList<Weight> getAll(Context context){
        Weight.context = context;
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        weightList = new ArrayList<>();

        //On récupére notre BDD en lecture uniquement
        Database database = new Database(context);
        SQLiteDatabase db = database.getReadableDatabase();

        //On défini nos paramètres de sélection et on récupère l'ensemble des entrées qui correspondent à ces critères
        String[] columns = {"weight", "date"};
        Cursor result = db.query(Database.WEIGHT_TABLE_NAME, columns, null, null, null, null, null);

        //On parcours les résultas de la requête qu'on ajoute dans un "ArrayList" de "Weight"
        result.moveToFirst();
        while (!result.isAfterLast()) {
            weightList.add(new Weight(result.getInt(0), result.getString(1)));
            result.moveToNext();
        }

        sortList();
        return weightList;
    }

    /**
     *
     * @return la date de l'entité
     */
    public String getDate(){
        return this.date;
    }

    /**
     *
     * @return la valeur "weight" de l'entité
     */
    public int getWeigth(){
        return this.weigth;
    }

    /**
     * La fonction est appellée lorsque l'on souhaite ajouter une entrée (Après le dialog avec le NumberPicker)
     * @param weight La valeur "weight" a ajouter en BDD
     */
    public static void addWeight(int weight){
        //Le "DateFormat permet ici de transformer la date courante en "String"
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        weightList.add(new Weight(weight, df.format(new Date())));

        //On récupére notre BDD en lecture/ecriture
        Database database = new Database(Weight.context);
        SQLiteDatabase db = database.getWritableDatabase();

        //On créé un "ContentValues" qui va contenir les valeurs à insérer
        ContentValues values = new ContentValues();
        values.put("weight", weight);
        values.put("date", df.format(new Date()));
        //On insére en base
        db.insert(Database.WEIGHT_TABLE_NAME, null, values);
    }

    /**
     * La fonction est appellée lorsqu'on clique sur un bouton "delete" de la liste
     * @param date La date que l'on souhaite supprimer
     */
    public static void deleteWeight(String date){
        //On récupére notre BDD en lecture/écriture
        Database database = new Database(Weight.context);
        SQLiteDatabase db = database.getWritableDatabase();
        String[] args = { date };
        db.delete(Database.WEIGHT_TABLE_NAME, "date=?", args);
    }

    /**
     *
     * @param date La date de l'entité qu'on souhaite récupérer
     * @return la valeur "weight" de l'entité trouvée ou 0
     */
    public static int getWeightByDate(Date date){
        for(Weight weight : weightList){
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            if(weight.getDate().equals(df.format(date))){
                return weight.getWeigth();
            }
        }
        return 0;
    }

    /*
    * On trie le tableau par date décroissante
     */
    private static void sortList(){
        Collections.sort(weightList, new Comparator<Weight>() {
            @Override
            public int compare(Weight lhs, Weight rhs) {
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                try {
                    return df.parse(lhs.getDate()).compareTo(df.parse(rhs.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
}
