package monisha.example.com.hw4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    ArrayList<Question> result = new ArrayList<>();
    ArrayList<Question> resultFromTrivia = new ArrayList<>();
    ArrayList<Question> wrongResult = new ArrayList<>();
    ProgressBar progressBarPercent;
    TextView percentRsult, review;
    int qList, wList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        percentRsult = (TextView) findViewById(R.id.textViewResPercent);
        progressBarPercent = (ProgressBar) findViewById(R.id.progressBarResult);
        review = (TextView) findViewById(R.id.textViewResReview);

        if (getIntent().getExtras().getStringArrayList("stats") != null) {
            resultFromTrivia = (ArrayList) getIntent().getExtras().get("stats");
        }

        for (Question question : resultFromTrivia) {
            if (!question.getChoice().get(Integer.parseInt(question.getAnswer()) - 1)
                    .equalsIgnoreCase(question.getSelectedAnswer())) {
                wrongResult.add(question);
            }
        }

        qList = resultFromTrivia.size();
        wList = wrongResult.size();
        double percent = ((qList - wList) * 100) / qList;
        percentRsult.setText(percent + "%");

        progressBarPercent.setMax(qList);
        progressBarPercent.setProgress(qList - wList);

        if (percent != 100) {
            review.setText("Try again and see if you can get all \n the correct answers!");
        }
        findViewById(R.id.buttonQuitStats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quitIntent = new Intent(StatsActivity.this, MainActivity.class);
                startActivity(quitIntent);
            }
        });

        findViewById(R.id.buttonTryAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getExtras().getStringArrayList("stats") != null) {
                    result = (ArrayList) getIntent().getExtras().get("stats");
                }
                Intent tryAgainIntent = new Intent(StatsActivity.this, TriviaActivity.class);
                tryAgainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tryAgainIntent.putExtra("questions", result);
                startActivity(tryAgainIntent);
            }
        });
    }
}
