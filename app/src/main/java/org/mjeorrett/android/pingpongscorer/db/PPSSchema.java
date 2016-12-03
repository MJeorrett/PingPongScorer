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
}
