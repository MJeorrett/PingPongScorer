package org.mjeorrett.android.pingpongscorer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.mjeorrett.android.pingpongscorer.db.Player.PPSPlayer;
import org.mjeorrett.android.pingpongscorer.db.Player.PPSPlayerServer;

import java.util.List;

/**
 * Created by user on 03/12/2016.
 */

public class PlayerListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PlayerAdaptor mPlayerAdaptor;

    public static Intent newIntent( Context context ) {

        Intent intent = new Intent( context, PlayerListActivity.class );
        return intent;
    }

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_player_list );

        mRecyclerView = (RecyclerView) findViewById( R.id.player_recycler_view );
        mRecyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        updateUI();
    }

    private void updateUI() {

        PPSPlayerServer playerServer = PPSPlayerServer.getInstance( this );
        List<PPSPlayer> players = playerServer.getPlayers();

        if ( mPlayerAdaptor == null ) {

            mPlayerAdaptor = new PlayerAdaptor( players );
            mRecyclerView.setAdapter( mPlayerAdaptor );
        }
        else {
            mPlayerAdaptor.setPlayers( players );
            mPlayerAdaptor.notifyDataSetChanged();
        }
    }

    private class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private PPSPlayer mPlayer;
        private TextView mTextView;

        public PlayerHolder( View itemView ) {

            super(itemView);
            mTextView = (TextView) itemView.findViewById( R.id.list_item_player_text_view );
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick( View view ) {

            Intent intent = ViewPlayerActivity.newIntent( PlayerListActivity.this, mPlayer.getUUID() );
            startActivity( intent );
        }

        public void bindPlayer( PPSPlayer player ) {

            mPlayer = player;
            mTextView.setText( player.fullDescription() );
        }
    }

    private class PlayerAdaptor extends RecyclerView.Adapter<PlayerHolder> {

        private List<PPSPlayer> mPlayers;

        public PlayerAdaptor( List<PPSPlayer> players ) {

            mPlayers = players;
        }

        @Override
        public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate( R.layout.list_item_player, parent, false );
            return new PlayerHolder( view );
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position) {

            PPSPlayer player = mPlayers.get( position );
            holder.bindPlayer( player );
        }

        public void setPlayers( List<PPSPlayer> players ) {

            mPlayers = players;
        }

        @Override
        public int getItemCount() {

            return mPlayers.size();
        }
    }
}
