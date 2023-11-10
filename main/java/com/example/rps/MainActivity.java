package com.example.rps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView computerMoveImg;
    private ImageView userMoveImg;
    private TextView winnerTv;
    private TextView playerScoreTv;
    private TextView computerScoreTv;

    private int playerScore = 0;
    private int computerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        computerMoveImg = findViewById(R.id.computer_move_img);
        userMoveImg = findViewById(R.id.user_move_img);
        winnerTv = findViewById(R.id.winner_tv);
        playerScoreTv = findViewById(R.id.player_score);
        computerScoreTv = findViewById(R.id.computer_score);

        // Set click listeners for move buttons
        findViewById(R.id.rock_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame("rock");
            }
        });

        findViewById(R.id.paper_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame("paper");
            }
        });

        findViewById(R.id.scissors_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame("scissors");
            }
        });

        // Set click listener for restart button
        findViewById(R.id.restart_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }

    private void playGame(String playerMove) {
        // Generate random computer move
        String[] moves = {"rock", "paper", "scissors"};
        int randomIndex = (int) (Math.random() * moves.length);
        String computerMove = moves[randomIndex];

        // Update UI with player and computer moves
        int playerMoveResId = getResources().getIdentifier(playerMove + "_icon", "drawable", getPackageName());
        int computerMoveResId = getResources().getIdentifier(computerMove + "_icon", "drawable", getPackageName());
        userMoveImg.setImageResource(playerMoveResId);
        computerMoveImg.setImageResource(computerMoveResId);

        // Determine the winner
        String winner = determineWinner(playerMove, computerMove);

        // Update UI with the winner and scores
        winnerTv.setText(winner);
        updateScores(winner);
    }

    private String determineWinner(String playerMove, String computerMove) {
        // Game logic to determine the winner
        if (playerMove.equals(computerMove)) {
            return "Draw";
        } else if ((playerMove.equals("rock") && computerMove.equals("scissors")) ||
                (playerMove.equals("paper") && computerMove.equals("rock")) ||
                (playerMove.equals("scissors") && computerMove.equals("paper"))) {
            return "Player wins";
        } else {
            return "Computer wins";
        }
    }

    private void updateScores(String winner) {
        // Update player and computer scores based on the winner
        if (winner.equals("Player wins")) {
            playerScore++;
        } else if (winner.equals("Computer wins")) {
            computerScore++;
        }

        // Update UI with the scores
        playerScoreTv.setText(String.valueOf(playerScore));
        computerScoreTv.setText(String.valueOf(computerScore));
    }

    private void restartGame() {
        // Reset scores and UI elements
        playerScore = 0;
        computerScore = 0;
        playerScoreTv.setText("0");
        computerScoreTv.setText("0");
        winnerTv.setText("");
        userMoveImg.setImageResource(R.drawable.question_mark);
        computerMoveImg.setImageResource(R.drawable.question_mark);
    }
}



