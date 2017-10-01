package monisha.example.com.hw4;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MONISHA on 29-09-2017.
 */

public class QuestionUtil {
    static public class QuestionJSONParser {
        static ArrayList<Question> parseQuestion(String in) throws JSONException {
            ArrayList<Question> questionArrayList = new ArrayList<>();
            ArrayList<String> choiceList = new ArrayList<>();

            JSONObject root = new JSONObject(in);
            JSONArray jsonArrayQuestion = root.getJSONArray("questions");
            for (int i = 0; i < jsonArrayQuestion.length(); i++) {
                JSONObject jsonObjectQuestion = jsonArrayQuestion.getJSONObject(i);
                Question questionJSON = new Question();
                questionJSON.setId(jsonObjectQuestion.getInt("id"));
                questionJSON.setQuestion(jsonObjectQuestion.getString("text"));
                //questionJSON.setSelectedAnswer(jsonObjectQuestion.getString("0"));
                questionJSON.setAnswer(jsonObjectQuestion.getJSONObject("choices").getString("answer"));
                if (jsonObjectQuestion.has("image")) {
                    questionJSON.setImage(jsonObjectQuestion.getString("image"));
                } else {
                    questionJSON.setImage(null);
                }
                JSONObject obj = jsonObjectQuestion.getJSONObject("choices");
                //Log.d("demo","ChoiceObject"+obj);
                JSONArray choice = obj.getJSONArray("choice");
               // Log.d("demo","ChoiceArray"+choice);

                choiceList = new ArrayList<>();
                for(int j=0; j<choice.length(); j++) {
                    choiceList.add(choice.get(j).toString());
                }

                Log.d("demo","ChoiceList"+choiceList);
                questionJSON.setChoice(choiceList);
                questionArrayList.add(questionJSON);
            }
            //Log.d("demo", "JSONArrayList" + questionArrayList.toString());
            return questionArrayList;
        }
    }
}
