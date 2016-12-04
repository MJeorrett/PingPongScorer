package org.mjeorrett.android.pingpongscorer.db.Player;

import java.util.UUID;

/**
 * Created by user on 03/12/2016.
 */

public class PPSPlayer {

    private UUID mUUID;
    private String mFirstName;
    private String mLastName;
    private String mNickname;

    public PPSPlayer( UUID uuid, String firstName, String lastName, String nickname ) {

        mUUID = uuid;
        mFirstName = firstName;
        mLastName = lastName;
        mNickname = nickname;
    }

    public PPSPlayer( String firstName, String lastName, String nickname ) {

        this( UUID.randomUUID(), firstName, lastName, nickname );
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getNickname() {
        return mNickname;
    }

    public String fullDescription() {

        return mFirstName + mLastName + " ( " + mNickname + " )";
    }
}
