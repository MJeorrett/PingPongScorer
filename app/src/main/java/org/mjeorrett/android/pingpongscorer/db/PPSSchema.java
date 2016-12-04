package org.mjeorrett.android.pingpongscorer.db;

/**
 * Created by user on 03/12/2016.
 */

public class PPSSchema {

    public static final class PlayerTable {

        public static final String NAME = "player";

        public static final class Cols {

            public static final String UUID = "uuid";
            public static final String FIRST_NAME = "first_name";
            public static final String LAST_NAME = "last_name";
            public static final String NICKNAME = "nickname";
        }
    }

    public static final class GameTable {

        public static final String NAME = "game";

        public static final class Cols {

            public static final String UUID = "uuid";
            public static final String TEAM_A_PLAYER_A = "team_a_player_a";
            public static final String TEAM_A_PLAYER_B = "team_a_player_b";
            public static final String TEAM_A_SCORE = "team_a_score";
            public static final String TEAM_B_PLAYER_A = "team_b_player_a";
            public static final String TEAM_B_PLAYER_B = "team_b_player_b";
            public static final String TEAM_B_SCORE = "team_b_score";
        }
    }
}
