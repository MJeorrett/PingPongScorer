package org.mjeorrett.android.pingpongscorer.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.mjeorrett.android.pingpongscorer.R;
import org.mjeorrett.android.pingpongscorer.db.Player.PPSPlayer;
import org.mjeorrett.android.pingpongscorer.db.Player.PPSPlayerServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 04/12/2016.
 */

public class AddGameActivity extends AppCompatActivity {

    private Spinner mTeamAPlayerASpinner;
    private Spinner mTeamAPlayerBSpinner;
    private Spinner mTeamAScoreSpinner;
    private Spinner mTeamBPlayerASpinner;
    private Spinner mTeamBPlayerBSpinner;
    private Spinner mTeamBScoreSpinner;

    public static Intent newIntent( Context context ) {

        Intent intent = new Intent( context, AddGameActivity.class );
        return intent;
    }

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_edit_game);

        List<PPSPlayer> players = PPSPlayerServer.getInstance( this ).getPlayers();
        List<String> playerNames = new ArrayList<>();

        for ( PPSPlayer player : players ){
            playerNames.add( player.getNickname() );
        }

        ArrayAdapter<String> playerNameAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_item, playerNames );
        playerNameAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        mTeamAPlayerASpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_a_player_a_spinner );
        mTeamAPlayerASpinner.setAdapter( playerNameAdapter );

        mTeamAPlayerBSpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_a_player_b_spinner );
        mTeamAScoreSpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_a_score_spinner );
        mTeamBPlayerASpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_b_player_a_spinner );
        mTeamBPlayerBSpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_b_player_b_spinner );
        mTeamBScoreSpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_b_score_spinner );
    }
}
