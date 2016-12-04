package org.mjeorrett.android.pingpongscorer.db.Player;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import org.mjeorrett.android.pingpongscorer.db.PPSDbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<PPSPlayer> getPlayers() {

        List<PPSPlayer> players = new ArrayList<>();
        PPSPlayerCursorWrapper cursor = this.queryPlayers( null, null );
        cursor.moveToFirst();

        try {
            while ( !cursor.isAfterLast() ) {

                players.add( cursor.getPlayer() );
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

        return players;
    }

    public PPSPlayer getPlayer( UUID uuid ) {

        String whereClause = PlayerTable.Cols.UUID + "= ?";
        String[] whereArgs = { uuid.toString() };
        PPSPlayerCursorWrapper cursor = this.queryPlayers( whereClause, whereArgs );

        try {
            if ( cursor.getCount() == 0 ) return null;

            cursor.moveToFirst();
            return cursor.getPlayer();
        }
        finally {
            cursor.close();
        }
    }

    private PPSPlayerCursorWrapper queryPlayers( @Nullable String whereClause, @Nullable String[] whereArgs ) {

        Cursor cursor = mDatabase.query(
                PlayerTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null );

        return new PPSPlayerCursorWrapper( cursor );
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
