package org.mjeorrett.android.pingpongscorer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static org.mjeorrett.android.pingpongscorer.db.PPSSchema.*;

/**
 * Created by user on 03/12/2016.
 */

public class PPSPlayerServer {

    private static PPSPlayerServer sInstance;

    private Context mAppContext;
    private SQLiteDatabase mDatabase;

    public static PPSPlayerServer getInstance(Context context ) {

        if ( sInstance == null ) sInstance = new PPSPlayerServer( context );
        return sInstance;
    }

    private PPSPlayerServer(Context context ) {

        mAppContext = context;
        mDatabase = new PPSDbHelper( context ).getWritableDatabase();
    }

    public void addPlayer( String firstName, String lastName, String nickName ) {

        PPSPlayer player = new PPSPlayer( firstName, lastName, nickName );
        ContentValues contentValues = PPSPlayerServer.createContentValues( player );
        mDatabase.insert( PlayerTable.NAME, null, contentValues );
    }

    private static ContentValues createContentValues( PPSPlayer player ) {

        ContentValues contentValues = new ContentValues();
        contentValues.put( PlayerTable.Cols.UUID, player.getUUID().toString() );
        contentValues.put( PlayerTable.Cols.FIRST_NAME, player.getFirstName() );
        contentValues.put( PlayerTable.Cols.LAST_NAME, player.getLastName() );
        contentValues.put( PlayerTable.Cols.NICKNAME, player.getNickname() );

        return  contentValues;
    }
}
