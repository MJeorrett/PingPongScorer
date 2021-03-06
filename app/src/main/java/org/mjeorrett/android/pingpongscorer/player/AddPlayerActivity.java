package org.mjeorrett.android.pingpongscorer.player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.mjeorrett.android.pingpongscorer.R;
import org.mjeorrett.android.pingpongscorer.db.Player.PPSPlayerServer;

/**
 * Created by user on 03/12/2016.
 */

public class AddPlayerActivity extends AppCompatActivity {

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mNicknameEditText;
    private Button mSavebutton;

    public static Intent newIntent( Context context ) {

        Intent intent = new Intent( context, AddPlayerActivity.class );
        return intent;
    }

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_player );

        mFirstNameEditText = (EditText) findViewById( R.id.activity_add_player_first_name_edit_text );
        mLastNameEditText = (EditText) findViewById( R.id.activity_add_player_last_name_edit_text );
        mNicknameEditText = (EditText) findViewById( R.id.activity_add_player_nickname_edit_text );
        mSavebutton = (Button) findViewById( R.id.activity_add_player_save_button );

        mSavebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String nickname = mNicknameEditText.getText().toString();

                if ( PPSPlayerServer.getInstance( AddPlayerActivity.this ).nicknameFree( nickname) ) {

                    Toast.makeText( AddPlayerActivity.this, "Sorry nickname " + nickname + " is already taken", Toast.LENGTH_SHORT ).show();
                    return;
                }

                String firstName = mFirstNameEditText.getText().toString();
                String lastName = mLastNameEditText.getText().toString();
                PPSPlayerServer.getInstance( AddPlayerActivity.this ).addPlayer( firstName, lastName, nickname );
                finish();
            }
        });
    }
}
