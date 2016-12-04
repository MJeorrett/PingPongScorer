package org.mjeorrett.android.pingpongscorer.db.Game;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import static org.mjeorrett.android.pingpongscorer.db.PPSSchema.*;

/**
 * Created by user on 03/12/2016.
 */

public class PPSGameCursorWrapper extends CursorWrapper {

    public PPSGameCursorWrapper( Cursor cursor ) {

        super( cursor );
    }

    public PPSGame getGame() {

        String uuidString = getString(getColumnIndex( GameTable.Cols.UUID ) );
        String teamAPlayerAString = getString(getColumnIndex( GameTable.Cols.TEAM_A_PLAYER_A ) );
        String teamAPlayerBString = getString(getColumnIndex( GameTable.Cols.TEAM_A_PLAYER_B ) );
        int teamAScore = getInt(getColumnIndex( GameTable.Cols.TEAM_A_SCORE ) );
        String teamBPlayerAString = getString(getColumnIndex( GameTable.Cols.TEAM_B_PLAYER_A ) );
        String teamBPlayerBString = getString(getColumnIndex( GameTable.Cols.TEAM_B_PLAYER_B ) );
        int teamBScore = getInt(getColumnIndex( GameTable.Cols.TEAM_B_SCORE ) );

        UUID uuid = UUID.fromString( uuidString );
        UUID teamAPlayerA = UUID.fromString( teamAPlayerAString );
        UUID teamAPlayerB = UUID.fromString( teamAPlayerBString );
        UUID teamBPlayerA = UUID.fromString( teamBPlayerAString );
        UUID teamBPlayerB = UUID.fromString( teamBPlayerBString );

        PPSGame game = new PPSGame(
                uuid,
                teamAPlayerA, teamAPlayerB, teamAScore,
                teamBPlayerA, teamBPlayerB, teamBScore );

        return game;
    }
}
