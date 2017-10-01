package monisha.example.com.hw4;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MONISHA on 29-09-2017.
 */

public class Question implements Serializable {
    int id;
    String question, image, answer, selectedAnswer;
    ArrayList<String> choice = new ArrayList<>();


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", answer=" + answer +
                ", question='" + question + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSelectedAnswer() {return selectedAnswer;}

    public void setSelectedAnswer(String selectedAnswer) {this.selectedAnswer = selectedAnswer;}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getChoice() {
        return choice;
    }

    public void setChoice(ArrayList<String> choice) {
        this.choice = choice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
