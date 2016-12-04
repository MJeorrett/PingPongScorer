package org.mjeorrett.android.pingpongscorer.db.Player;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.mjeorrett.android.pingpongscorer.db.PPSSchema;

import java.util.UUID;

import static org.mjeorrett.android.pingpongscorer.db.PPSSchema.*;

/**
 * Created by user on 03/12/2016.
 */

public class PPSPlayerCursorWrapper extends CursorWrapper {

    public PPSPlayerCursorWrapper( Cursor cursor ) {

        super( cursor );
    }

    public PPSPlayer getPlayer() {

        String uuidString = getString( getColumnIndex( PlayerTable.Cols.UUID ) );
        UUID uuid = UUID.fromString( uuidString );
        String firstName = getString( getColumnIndex( PlayerTable.Cols.FIRST_NAME ) );
        String lastName = getString( getColumnIndex( PlayerTable.Cols.LAST_NAME ) );
        String nickName = getString( getColumnIndex( PlayerTable.Cols.NICKNAME ) );

        PPSPlayer player = new PPSPlayer( uuid, firstName, lastName, nickName );
        return player;
    }
}
