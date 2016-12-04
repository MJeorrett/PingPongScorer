package org.mjeorrett.android.pingpongscorer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.mjeorrett.android.pingpongscorer.db.Player.PPSPlayer;
import org.mjeorrett.android.pingpongscorer.db.Player.PPSPlayerServer;

import java.util.UUID;

/**
 * Created by user on 03/12/2016.
 */

public class ViewPlayerActivity extends AppCompatActivity {

    private static final String KEY_PLAYER_ID = "org.mjeorrett.android.pingpongscorer.player_id";

    private PPSPlayer mPlayer;

    private TextView mFirstNameTextView;
    private TextView mLastNameTextView;
    private TextView mNicknameTextView;

    public static Intent newIntent(Context context, UUID playerId ) {

        Intent intent = new Intent( context, ViewPlayerActivity.class );
        intent.putExtra( KEY_PLAYER_ID, playerId );
        return intent;
    }

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_view_player );

        Bundle extras = getIntent().getExtras();
        UUID uuid = (UUID) extras.getSerializable( KEY_PLAYER_ID );

        mPlayer = PPSPlayerServer.getInstance( this ).getPlayer( uuid );

        mFirstNameTextView = (TextView) findViewById( R.id.activity_view_player_first_name_text_view );
        mFirstNameTextView.setText( mPlayer.getFirstName() );

        mLastNameTextView = (TextView) findViewById( R.id.activity_view_player_last_name_text_view );
        mLastNameTextView.setText( mPlayer.getLastName() );

        mNicknameTextView = (TextView) findViewById( R.id.activity_view_player_nickname_text_view );
        mNicknameTextView.setText( mPlayer.getNickname() );
    }
}
