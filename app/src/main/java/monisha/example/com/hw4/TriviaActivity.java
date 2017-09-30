package monisha.example.com.hw4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity {

    ArrayList<Question> questionsResult = new ArrayList<>();
    TextView questionNumber, timer, question, progressText;
    ProgressBar progressBar;
    ImageView imageView;
    Button next;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        getSupportActionBar().setTitle("Trivia");
        questionNumber = (TextView) findViewById(R.id.textViewQuestNum);
        timer = (TextView) findViewById(R.id.textViewTimer);
        question = (TextView) findViewById(R.id.textViewQuestion);
        next = (Button) findViewById(R.id.buttonNext);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressText = (TextView) findViewById(R.id.textViewProgress);

        if (getIntent().getExtras().getStringArrayList("questions") != null) {
            questionsResult = (ArrayList) getIntent().getExtras().get("questions");
        }
        questionNumber.setText("Q" + (questionsResult.get(count).getId()+1)+"");
        question.setText(questionsResult.get(count).getQuestion());
        if (questionsResult.get(count).getImage() != null) {
            new GetImage().execute(questionsResult.get(count).getImage());
        } else {
            imageView.setImageBitmap(null);
        }
        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("Time Left: " + millisUntilFinished / 1000 + " seconds");
            }

            public void onFinish() {
                timer.setText("Done!");
                Toast.makeText(TriviaActivity.this, "Time up!!", Toast.LENGTH_LONG).show();
            }

        }.start();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                questionNumber.setText("Q" + (questionsResult.get(count).getId()+1)+"");
                question.setText(questionsResult.get(count).getQuestion());
                if (questionsResult.get(count).getImage() != null) {
                    imageView.setImageBitmap(null);
                    progressBar.setVisibility(View.VISIBLE);
                    progressText.setVisibility(View.VISIBLE);
                    new GetImage().execute(questionsResult.get(count).getImage());
                } else {
                    imageView.setImageBitmap(null);
                }
            }
        });
    }

    private class GetImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            progressBar.setVisibility(View.INVISIBLE);
            progressText.setVisibility(View.INVISIBLE);
            imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
        }
    }
}
