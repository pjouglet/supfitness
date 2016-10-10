package supfitness.luciole.com.supfitness;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luciole on 23/03/2016.
 */
public class Database extends SQLiteOpenHelper   {

    private static final String DB_NAME = "supfitness";
    private static final int DB_VERSION = 2;
    public static final String WEIGHT_TABLE_NAME = "weights";
    private static final String WEIGHT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + WEIGHT_TABLE_NAME +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "weight INTEGER NOT NULL, " +
            "date VARCHAR(50) NOT NULL);";

    public Database(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WEIGHT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WEIGHT_TABLE_NAME);
        onCreate(db);
    }
}
