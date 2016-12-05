package org.mjeorrett.android.pingpongscorer.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    private EditText mTeamAScoreEditText;
    private Spinner mTeamBPlayerASpinner;
    private Spinner mTeamBPlayerBSpinner;
    private EditText mTeamBScoreEditText;
    private Button mSaveButton;

    public static Intent newIntent( Context context ) {

        Intent intent = new Intent( context, AddGameActivity.class );
        return intent;
    }

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_edit_game);

        List<PPSPlayer> players = PPSPlayerServer.getInstance( this ).getPlayers();
        final List<String> playerNames = new ArrayList<>();

        for ( PPSPlayer player : players ){
            playerNames.add( player.getNickname() );
        }

        ArrayAdapter<String> playerAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_item, playerNames );
        playerAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        mTeamAPlayerASpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_a_player_a_spinner );
        mTeamAPlayerASpinner.setAdapter( playerAdapter );


        mTeamAPlayerBSpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_a_player_b_spinner );
        mTeamAPlayerBSpinner.setAdapter( playerAdapter );

        mTeamAScoreEditText = (EditText) findViewById( R.id.activity_add_edit_game_team_a_score_edit_text);

        mTeamBPlayerASpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_b_player_a_spinner );
        mTeamBPlayerASpinner.setAdapter( playerAdapter );

        mTeamBPlayerBSpinner = (Spinner) findViewById( R.id.activity_add_edit_game_team_b_player_b_spinner );
        mTeamBPlayerBSpinner.setAdapter( playerAdapter );

        mTeamBScoreEditText = (EditText) findViewById( R.id.activity_add_edit_game_team_b_score_edit_text);

        mSaveButton = (Button) findViewById( R.id.activity_add_edit_game_save_button );
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> selectedNames = new ArrayList<>();

                String teamAPlayerA = (String) mTeamAPlayerASpinner.getSelectedItem();

                if ( teamAPlayerA == null ) {
                    Toast.makeText( AddGameActivity.this, "Please select player A in Team A", Toast.LENGTH_SHORT ).show();
                    return;
                }

                selectedNames.add( teamAPlayerA );

                String teamAPlayerB = (String) mTeamAPlayerBSpinner.getSelectedItem();

                if ( teamAPlayerB == null ) {
                    Toast.makeText( AddGameActivity.this, "Please select player B in Team A", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if ( selectedNames.contains( teamAPlayerB ) ) {
                    Toast.makeText( AddGameActivity.this, "Please select different players", Toast.LENGTH_SHORT ).show();
                    return;
                }

                selectedNames.add( teamAPlayerB );

                String teamBPlayerA = (String) mTeamBPlayerASpinner.getSelectedItem();

                if ( teamBPlayerA == null ) {
                    Toast.makeText( AddGameActivity.this, "Please select player A in Team B", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if ( selectedNames.contains( teamBPlayerA ) ) {
                    Toast.makeText( AddGameActivity.this, "Please select different players", Toast.LENGTH_SHORT ).show();
                    return;
                }

                selectedNames.add( teamBPlayerA );

                String teamBPlayerB = (String) mTeamBPlayerBSpinner.getSelectedItem();

                if ( teamBPlayerB == null ) {
                    Toast.makeText( AddGameActivity.this, "Please select player B in Team B", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if ( selectedNames.contains( teamBPlayerB ) ) {
                    Toast.makeText( AddGameActivity.this, "Please select different players", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if ( mTeamAScoreEditText.getText().toString().equals( "" ) ) {
                    Toast.makeText( AddGameActivity.this, "Please enter a score for Team A", Toast.LENGTH_SHORT ).show();
                }

                if ( mTeamBScoreEditText.getText().toString().equals( "" ) ) {
                    Toast.makeText( AddGameActivity.this, "Please enter a score for Team B", Toast.LENGTH_SHORT ).show();
                }
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        mTeamAPlayerASpinner.setSelection( -1 );
    }
}
