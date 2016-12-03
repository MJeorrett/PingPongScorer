package org.mjeorrett.android.pingpongscorer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static org.mjeorrett.android.pingpongscorer.db.PPSSchema.*;

/**
 * Created by user on 03/12/2016.
 */

public class PPSDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "pingPongScorer.db";

    public PPSDbHelper(Context context ) {
        super( context, DATABASE_NAME, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "CREATE TABLE " + PlayerTable.NAME + "("
                + "_id INTEGER primary key autoincrement, "
                + PlayerTable.Cols.UUID + ", "
                + PlayerTable.Cols.FIRST_NAME + ", "
                + PlayerTable.Cols.LAST_NAME + ", "
                + PlayerTable.Cols.NICKNAME + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
