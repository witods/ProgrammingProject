package TriviaGame.src.TriviaGame;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Quiz game based with data coming from API
 * The user can set the difficulty level of the questions and the type of question.
 * Based on these settings the amount of credits that can be won or lost are calculated
 * In the game loop, the user is asked a question which they have to answer. If the answer is correct/incorrect an
 * amount of credits is added/deducted to their available credits.
 * After answering the question, the user is asked if they want to continue playing or not.
 * When the game loop is ended, the user can see the number of questions that they answered + the amount of credits won/lost
 * @link https://opentdb.com/api_config.php
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //TODO load the available credits of the user.
        int availableCredits = 500;

        //ask user for type of question TODO convert into filter on screen
        Scanner s = new Scanner(System.in);
        System.out.println("Which type of question do you want?");
        System.out.println("1. Multiple Choice");
        System.out.println("2. True or False");
        int typeChoice = s.nextInt();
        s.nextLine();

        //ask user for difficulty of question TODO convert into filter on screen
        System.out.println("Which difficulty level?");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        int difficultyChoice = s.nextInt();
        s.nextLine();

        //Create variables for filters to add to url
        String typeURL = "multiple";
        String diffcultyURL = "easy";

        //create variables to keep the question number and gameloop switch and
        // total amount of credits lost/won during the game
        int qNumber = 0;
        int nextQuestion = 1;
        int totalCreditsLostWonInGame = 0;

        /*start of game loop*/
        while (nextQuestion == 1) {
            //set variables of URL, which will also determine the amount of credits that can be won/lost
            int creditFactorType = 0;
            int creditFactorDifficulty = 0;
            int creditAmountWonLost;
            switch (typeChoice) {
                case 1:
                    typeURL = "multiple";
                    creditFactorType = 4;
                    break;
                case 2:
                    typeURL = "boolean";
                    creditFactorType = 2;
                    break;
            }

            switch (difficultyChoice) {
                case 1:
                    diffcultyURL = "easy";
                    creditFactorDifficulty=1;
                    break;
                case 2:
                    diffcultyURL = "medium";
                    creditFactorDifficulty=2;
                    break;
                case 3:
                    diffcultyURL = "hard";
                    creditFactorDifficulty=3;
                    break;
            }

            //calculate total credit amount that can be won/lost based on choise of type and difficulty
            //TODO discuss with team
            creditAmountWonLost = 5 * creditFactorType * creditFactorDifficulty;

            /*Depending on choice of type of question, difficulty and category a different URL is used
            open url and reader*/
            URL url = new URL("https://opentdb.com/api.php?amount=50" + "&difficulty=" + diffcultyURL + "&type=" + typeURL);
            InputStreamReader reader = new InputStreamReader(url.openStream());

            //make variabeles which contain the incoming info
            Trivia t = new Gson().fromJson(reader, Trivia.class);
            List<Results> results = t.getResults();

            //close the reader
            reader.close();

            //TODO once this has been coupled to a GUI, we can take most of the code above and put it outside of the
            // while loop. We can then couple those actions to the change of the type or difficulty filter. This will
            // improve the performance as the API doesn't need to be reopened for every new question. The the gNumber
            // will really serve its purpose.
            //Get the question and all related info
            Results result = results.get(qNumber);

            // Display the question
            System.out.println("\nQuestion: ");
            System.out.println(result.getQuestion()+ "\n");

            //put correct and incorrect answers in an arrayList and sort alphabetically
            ArrayList<String> allAnswers = new ArrayList<>();
            allAnswers.add(result.getCorrect_answer());
            for (int i = 0; i < result.getIncorrect_answers().size(); i++) {
                allAnswers.add(result.getIncorrect_answers().get(i));
            }
            allAnswers.sort(null);

            //display the answer options
            System.out.println("Options: ");
            for (int i = 0; i < allAnswers.size(); i++) {
                System.out.println(i + 1 + ": " + allAnswers.get(i));
            }

            //get answer
            System.out.println("\nAnswer: ");
            int answerInt = s.nextInt();
            s.nextLine();

            //check if correct answer
            //TODO add dialog
            if (allAnswers.get(answerInt - 1) == result.getCorrect_answer()) {
                System.out.println(("Correct answer! You won " + creditAmountWonLost + " credits"));
                //calc credits
                availableCredits += creditAmountWonLost;
                totalCreditsLostWonInGame += creditAmountWonLost;
            } else {
                System.out.println(("Wrong answer! You lost " + creditAmountWonLost + " credits"));
                //calc credits
                availableCredits -= creditAmountWonLost;
                totalCreditsLostWonInGame -= creditAmountWonLost;
            }

            //TODO gather this input with buttons?
            //check if player wants to continue playing
            System.out.println("Continue playing?");
            System.out.println("1: Yes");
            System.out.println("2: No");
            nextQuestion = s.nextInt();
            s.nextLine();

            //TODO add dialog
            if(availableCredits < creditAmountWonLost){
                System.out.println("You do not have enough credits to play the game. " +
                        "\nEither change the input credits of the game by changing the difficulty or type of question or " +
                        "stop playing the game.");
            }

            //set variable to get next question
            qNumber++;

        } // end of game loop


        //TODO Add Dialog
        System.out.println("See you next time for more quiz questions!");
        System.out.println("Total number of questions answered in the quiz: " + qNumber);
        if(totalCreditsLostWonInGame>=0) {
            System.out.println("Total number of credits won in the quiz: " + totalCreditsLostWonInGame);
        }else{
            System.out.println("Total number of credits lost in the quiz: " + totalCreditsLostWonInGame);
        }

    }
}
