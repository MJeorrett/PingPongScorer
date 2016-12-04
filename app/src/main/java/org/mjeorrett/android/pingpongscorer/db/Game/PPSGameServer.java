package org.mjeorrett.android.pingpongscorer.db.Game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import org.mjeorrett.android.pingpongscorer.db.PPSDbHelper;
import org.mjeorrett.android.pingpongscorer.db.Player.PPSPlayerCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mjeorrett.android.pingpongscorer.db.PPSSchema.*;

/**
 * Created by user on 04/12/2016.
 */

public class PPSGameServer {

    private static PPSGameServer sInstance;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PPSGameServer getInstance( Context context ) {

        if ( sInstance == null ) sInstance = new PPSGameServer( context );
        return sInstance;
    }

    private PPSGameServer( Context context ) {

        mContext = context.getApplicationContext();
        mDatabase = new PPSDbHelper( context ).getWritableDatabase();
    }

    public void addGame(
            UUID teamAPlayerA, UUID teamAPlayerB, int teamAScore,
            UUID teamBPlayerA, UUID teamBPlayerB, int teamBScore ) {

        PPSGame game = new PPSGame(
                teamAPlayerA, teamAPlayerB, teamAScore,
                teamBPlayerA, teamBPlayerB, teamBScore );

        ContentValues contentValues = PPSGameServer.createContentValues( game );
        mDatabase.insert( GameTable.NAME, null, contentValues );
    }

    public List<PPSGame> getPlayers() {

        List<PPSGame> games = new ArrayList<>();
        PPSGameCursorWrapper cursor = this.queryGames( null, null );
        cursor.moveToFirst();

        try {
            while (!cursor.isAfterLast()) {

                PPSGame game = cursor.getGame();
                games.add(game);
            }
        }
        finally {
            cursor.close();
        }

        return games;
    }

    private PPSGameCursorWrapper queryGames( @Nullable String whereClause, @Nullable String[] whereArgs ) {

        Cursor cursor = mDatabase.query(
                GameTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null );

        return new PPSGameCursorWrapper( cursor );
    }

    private static ContentValues createContentValues( PPSGame game ) {

        ContentValues contentValues = new ContentValues();

        contentValues.put( GameTable.Cols.UUID, game.getUUID().toString() );
        contentValues.put( GameTable.Cols.TEAM_A_PLAYER_A, game.getTeamAPlayerA().toString() );
        contentValues.put( GameTable.Cols.TEAM_A_PLAYER_B, game.getTeamAPlayerB().toString() );
        contentValues.put( GameTable.Cols.TEAM_A_SCORE, game.getTeamAScore() );
        contentValues.put( GameTable.Cols.TEAM_B_PLAYER_A, game.getTeamBPlayerA().toString() );
        contentValues.put( GameTable.Cols.TEAM_B_PLAYER_B, game.getTeamBPlayerB().toString() );
        contentValues.put( GameTable.Cols.TEAM_B_SCORE, game.getTeamBScore() );

        return contentValues;
    }
}
