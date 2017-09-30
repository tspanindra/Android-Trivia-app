package monisha.example.com.hw4;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  GetTriviaQuestions.ITrivia{

    public static ProgressDialog progressDialog;
    ProgressBar progressBarTrivia;
    ImageView imageViewTrivia;
    TextView textViewTriviaReady, triviaProgress;
    Button buttonStartTrivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Welcome to Trivia");
        progressDialog = new ProgressDialog(this);
        if (isConnectedOnline()) {
            new GetTriviaQuestions(this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
        } else {
            Toast.makeText(MainActivity.this, "Not Connected to the Internet", Toast.LENGTH_LONG).show();
        }
        findViewById(R.id.buttonExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean isConnectedOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void preView() {
        progressBarTrivia = (ProgressBar) findViewById(R.id.progressBarLoadTrivia);
        progressBarTrivia.setIndeterminate(false);
    }

    @Override
    public void changeView(final ArrayList<Question> questions) {
        triviaProgress = (TextView) findViewById(R.id.textViewLoadTrivia);
        imageViewTrivia = (ImageView) findViewById(R.id.imageViewTrivia);
        textViewTriviaReady = (TextView) findViewById(R.id.textViewTriviaReady);
        buttonStartTrivia = (Button) findViewById(R.id.buttonStartTrivia);
        imageViewTrivia.setVisibility(View.VISIBLE);
        textViewTriviaReady.setVisibility(View.VISIBLE);
        buttonStartTrivia.setEnabled(true);
        progressBarTrivia.setVisibility(View.INVISIBLE);
        triviaProgress.setVisibility(View.INVISIBLE);
        buttonStartTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TriviaActivity.class);
                intent.putExtra("questions",questions);
                startActivity(intent);
            }
        });
    }
}
