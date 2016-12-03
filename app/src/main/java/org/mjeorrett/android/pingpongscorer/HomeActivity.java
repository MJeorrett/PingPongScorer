package org.mjeorrett.android.pingpongscorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button mAddPlayerButton;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAddPlayerButton = (Button) findViewById( R.id.activity_home_add_player_button );
        mAddPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = AddPlayerActivity.newIntent( HomeActivity.this );
                startActivity( intent );
            }
        });
    }
}
