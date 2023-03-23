package TriviaGame;

import java.util.List;

/**
 * This class gets the response code and a list with Trivia questions, question type, correct answer etc.
 * @link https://opentdb.com/api_config.php
 */
public class Trivia {
    private int response_code;
    private List <Results> results;

    public List<Results> getResults() {
        return results;
    }

}
