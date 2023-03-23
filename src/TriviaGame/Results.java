package TriviaGame;

import org.unbescape.html.HtmlEscape;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all the questions, answers and other details such as type, category and difficulty
 * The getters of the question, correct_answer and incorrect_answers are using the unbescape HTML decoder to unescape the HTML characters
 */
public class Results {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        final String unescapedQuestion = HtmlEscape.unescapeHtml(question);
        return unescapedQuestion;
    }

    public String getCorrect_answer() {
        final String unescapedCorrectAnswer = HtmlEscape.unescapeHtml(correct_answer);
        return unescapedCorrectAnswer;
    }

    public List<String> getIncorrect_answers() {
        ArrayList<String> unescapedIncorrectAnswers = new ArrayList<>();
        for (String s: incorrect_answers) {
            final String unescapedIncorrectAnswer = HtmlEscape.unescapeHtml(s);
            unescapedIncorrectAnswers.add(unescapedIncorrectAnswer);
        }
        return unescapedIncorrectAnswers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
