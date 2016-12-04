package org.mjeorrett.android.pingpongscorer.db.Game;

import java.util.UUID;

/**
 * Created by user on 03/12/2016.
 */

public class PPSGame {

    private UUID mUUID;
    private UUID mTeamAPlayerA;
    private UUID mTeamAPlayerB;
    private int mTeamAScore;
    private UUID mTeamBPlayerA;
    private UUID mTeamBPlayerB;
    private int mTeamBScore;

    public PPSGame(
            UUID uuid,
            UUID teamAPlayerA, UUID teamAPlayerB, int teamAScore,
            UUID teamBPlayerA, UUID teamBPlayerB, int teamBScore ) {

        mUUID = uuid;
        mTeamAPlayerA = teamAPlayerA;
        mTeamAPlayerB = teamAPlayerB;
        mTeamAScore = teamAScore;
        mTeamBPlayerA = teamBPlayerA;
        mTeamBPlayerB = teamBPlayerB;
        mTeamBScore = teamBScore;
    }

    public PPSGame(
            UUID teamAPlayerA, UUID teamAPlayerB, int teamAScore,
            UUID teamBPlayerA, UUID teamBPlayerB, int teamBScore ) {

        this(
                UUID.randomUUID(),
                teamAPlayerA, teamAPlayerB, teamAScore,
                teamBPlayerA, teamBPlayerB, teamBScore );
    }

    public UUID getUUID() {
        return mUUID;
    }

    public UUID getTeamAPlayerA() {
        return mTeamAPlayerA;
    }

    public UUID getTeamAPlayerB() {
        return mTeamAPlayerB;
    }

    public int getTeamAScore() {
        return mTeamAScore;
    }

    public UUID getTeamBPlayerA() {
        return mTeamBPlayerA;
    }

    public UUID getTeamBPlayerB() {
        return mTeamBPlayerB;
    }

    public int getTeamBScore() {
        return mTeamBScore;
    }
}
