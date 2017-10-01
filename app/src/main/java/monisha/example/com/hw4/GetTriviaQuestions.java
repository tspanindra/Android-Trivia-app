package monisha.example.com.hw4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by MONISHA on 29-09-2017.
 */

public class GetTriviaQuestions extends AsyncTask<String, Void, ArrayList<Question>> {
    ITrivia trivia;

    public GetTriviaQuestions(ITrivia trivia) {
        this.trivia = trivia;
    }

    @Override
    protected void onPreExecute() {
        trivia.preView();
    }

    @Override
    protected void onPostExecute(ArrayList<Question> s) {
        trivia.changeView(s);
        if (s != null) {
            Log.d("demo", "String Builder Contents" + s.toString());
        } else {
            Log.d("demo", "Something went wrong!!");
        }
    }

    @Override
    protected ArrayList<Question> doInBackground(String... params) {
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                //Log.d("demo", "String Builder Contents" + stringBuilder.toString());
                return QuestionUtil.QuestionJSONParser.parseQuestion(stringBuilder.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    static public interface ITrivia {
        void preView();
        void changeView(ArrayList<Question> list);
    }
}

