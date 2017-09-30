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
                questionJSON.setAnswer(jsonObjectQuestion.getJSONObject("choices").getInt("answer"));
                if (jsonObjectQuestion.has("image")) {
                    questionJSON.setImage(jsonObjectQuestion.getString("image"));
                } else {
                    questionJSON.setImage(null);
                }
//                }
//                JSONArray arrayChoice = jsonObjectQuestion.getJSONObject("choices").getJSONArray("choice");
//                for(int j=0; j<arrayChoice.length(); j++) {
//                    String choice = arrayChoice.getString(j);
//                    if(choice!=null){
//                        choiceList.add(choice);
//                    }else{
//                        choiceList.add("");
//                    }
//                    Log.d("choicedata",choice);
//                }
//                questionJSON.setChoice();
                questionArrayList.add(questionJSON);
            }
            Log.d("demo", "JSONArrayList" + questionArrayList.toString());
            return questionArrayList;
        }
    }
}
