package org.mjeorrett.android.pingpongscorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.mjeorrett.android.pingpongscorer.game.AddGameActivity;
import org.mjeorrett.android.pingpongscorer.player.AddPlayerActivity;
import org.mjeorrett.android.pingpongscorer.player.PlayerListActivity;

public class HomeActivity extends AppCompatActivity {

    private Button mAddPlayerButton;
    private Button mViewPlayersButton;
    private Button mAddGamebutton;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAddPlayerButton = (Button) findViewById( R.id.activity_home_add_player_button );
        mAddPlayerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = AddPlayerActivity.newIntent( HomeActivity.this );
                startActivity( intent );
            }
        });

        mViewPlayersButton = (Button) findViewById( R.id.activity_home_view_players_button );
        mViewPlayersButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View view ) {

                Intent intent = PlayerListActivity.newIntent( HomeActivity.this );
                startActivity( intent );
            }
        });

        mAddGamebutton = (Button) findViewById( R.id.activity_home_add_game_button );
        mAddGamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = AddGameActivity.newIntent( HomeActivity.this );
                startActivity( intent );
            }
        });
    }
}
