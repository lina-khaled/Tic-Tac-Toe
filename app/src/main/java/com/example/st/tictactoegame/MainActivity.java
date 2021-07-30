package com.example.st.tictactoegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activeUser = 0;
    // consider all places are empty
    int [] game = {2,2,2,2,2,2,2,2,2};
    // all trials to win the gane (either x or o )
    int [][] winnerTries = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean active = true;

public void drop(View view){

    ImageView image = (ImageView)view;
    image.setTranslationY(-1000);
    int ImageTag = Integer.parseInt(image.getTag().toString());
    // use tags to check if the place is empty ( if it's empty then you can play X or O
    // Bur if it's nit, then you can't put another X or O above that place ( because there's already one)
    if(game[ImageTag]==2 && active) {
        game[ImageTag] = activeUser;
        // 0 = x ,, 1 = o ,, 2 = empty
        if (activeUser == 0) {
            image.setImageResource(R.drawable.x);
            activeUser = 1;

        } else {
            image.setImageResource(R.drawable.o);
            activeUser = 0;
        }
        image.animate().translationYBy(1000).setDuration(200);
        // Loop the  game array and check if the indexes we mentioned in  winnerTries is the same
        // we check those places in winnerTries inside the game array
        for (int[] winner : winnerTries) {
            if (game[winner[0]] == game[winner[1]] && game[winner[1]] == game[winner[2]] && game[winner[0]] != 2) {
                active = false;
                String winnerPlayer;
                // because we already changed active user
                if (activeUser == 1) {
                    winnerPlayer = "X";
                } else {
                    winnerPlayer = "O";
                }
                // if anyone won then the winner message & the play again button must be shown and appear
                Button winnerButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerMessage = (TextView) findViewById(R.id.winMessage);
                winnerMessage.setText(winnerPlayer+" has won");
                winnerButton.setVisibility(View.VISIBLE);
                winnerMessage.setVisibility(View.VISIBLE);
            }
        }
    }
}
            public void playAgainButton(View view){

                // Make the button and the text dissapear when click the button (Make them invisible again)
                Button button = (Button) findViewById(R.id.playAgainButton);
                TextView text = (TextView) findViewById(R.id.winMessage);
                button.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);

                // Loop the gridLayout to remove all images
                GridLayout grid = (GridLayout)findViewById(R.id.gridLayout);
                for(int i=0;i<grid.getChildCount();i++){
                    ImageView image = (ImageView) grid.getChildAt(i);
                    image.setImageDrawable(null);
                }
                // refresh the array ( all empty again ) as well as ( activeUser && active )
                for(int i =0 ; i< game.length ; i++){
                    game[i] =2;
                }
                activeUser = 0;
                active = true;
            }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
