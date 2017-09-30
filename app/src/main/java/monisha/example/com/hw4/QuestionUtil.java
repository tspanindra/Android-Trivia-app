package monisha.example.com.hw4;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionUtil {
    static public class QuestionJSONParser {
        static ArrayList<Question> parseQuestion(String in) throws JSONException {
            ArrayList<Question> questionArrayList = new ArrayList<>();

            JSONObject root = new JSONObject(in);
            JSONArray jsonArrayQuestion = root.getJSONArray("questions");
            for (int i = 0; i < jsonArrayQuestion.length(); i++) {
                ArrayList<String> choiceList = new ArrayList<>();
                JSONObject jsonObjectQuestion = jsonArrayQuestion.getJSONObject(i);
                Question questionJSON = new Question();
                questionJSON.setId(jsonObjectQuestion.getInt("id"));
                questionJSON.setQuestion(jsonObjectQuestion.getString("text"));
                questionJSON.setAnswer(jsonObjectQuestion.getJSONObject("choices").getInt("answer"));
                if (jsonObjectQuestion.has("image")) {
                    questionJSON.setImage(jsonObjectQuestion.getString("image"));
                } else {
                    questionJSON.setImage(null);
                }
                JSONObject obj = jsonObjectQuestion.getJSONObject("choices");
                JSONArray choices = obj.getJSONArray("choice");
                System.out.print("he");

                for(int j=0; j<choices.length(); j++) {
                    choiceList.add(choices.get(j).toString());
                }
                questionJSON.setChoice(choiceList);
                questionArrayList.add(questionJSON);
            }
            Log.d("demo", "JSONArrayList" + questionArrayList.toString());
            return questionArrayList;
        }
    }
}
