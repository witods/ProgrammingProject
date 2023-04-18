package Pages.TriviaGame;

import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TriviaGamePage extends JFrame {
//----------------------------------------------------------------------------------------------------------------------
// /*variables*/

    //GUI
    private JPanel gamePanel;
    private JPanel filterPanel;
    private JPanel answerOptionsPanel;
    private JPanel trueFalseAnswerOptionPanel;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    private JPanel mcAnswerOptionsPanel;
    private JPanel submitButtonPanel;
    private JButton submitAnswerButton;
    private JPanel correctAnswerPanel;
    private JLabel creditsGainedLabel;
    private JPanel questionPanel;
    private JTextArea questionTextArea;
    private JPanel wrongAnswerPanel;
    private JComboBox typeComboBox;
    private JComboBox difficultyComboBox;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JButton QUITButton;
    private JButton NEXTQUESTIONButton1;
    private JPanel buttonPanel;
    private JPanel nextQuestionPanel;
    private JLabel creditsLostLabel;
    private JLabel correctAnswer;
    private JButton QUITButton2;

    //Logic
    /*Haal username op en aantal credits bij begin van spel*/
    //String username = xyz
    private int availableCredits = 500;

    //variables for API
    private String typeURL = "boolean";
    private  String difficultyURL = "easy";

    //variable to keep the question number
    private int questionNumber = 0;

    //variable for (total) number of credits won/lost and the factors to calculate
    private int creditAmountWonLost;
    private int totalCreditsLostWonInGame = 0;
    private int creditFactorType = 2;
    private  int creditFactorDifficulty = 1;

    //arraylists
    ArrayList<Results> results = new ArrayList<>();
    ArrayList<String> allAnswers = new ArrayList<>();
    ArrayList <JRadioButton> radioButtonsAllList = new ArrayList<>();
    ArrayList <JRadioButton> radioButtonsMcList = new ArrayList<>();
    ArrayList <JRadioButton> radioButtonsTFList = new ArrayList<>();



//----------------------------------------------------------------------------------------------------------------------
// /*Constructor*/
   public TriviaGamePage (){
       //Load API first time (default settings)
       reloadQuestions();
       displayQuestion();


    //----------------------------------------------------------------------------------------------------------------------
    // /*Listeners*/

       /*Create itemlistener that when a radiobutton is changed, it checks whether there is one selected.
       If yes, the submit answer button becomes enabled*/

           //add radiobuttons to the arraylist that was created for it
           radioButtonsMcList.add(radioButton1);
           radioButtonsMcList.add(radioButton2);
           radioButtonsMcList.add(radioButton3);
           radioButtonsMcList.add(radioButton4);

           radioButtonsTFList.add(trueRadioButton);
           radioButtonsTFList.add(falseRadioButton);

           radioButtonsAllList.add(radioButton1);
           radioButtonsAllList.add(radioButton2);
           radioButtonsAllList.add(radioButton3);
           radioButtonsAllList.add(radioButton4);
           radioButtonsAllList.add(trueRadioButton);
           radioButtonsAllList.add(falseRadioButton);

           //create item listener - if a rdio button is selected, the submit button becomes enabled
           ItemListener listener = new ItemListener(){
               @Override
               public void itemStateChanged(ItemEvent e) {
                   boolean enabled = true;
                   for (JRadioButton jrf : radioButtonsAllList) {
                       if (jrf.isSelected()) {
                           enabled = true;
                       }
                   }
                   submitAnswerButton.setEnabled(enabled);
               }
           };

           //Add the listener to all radiobuttons in the list
           for (JRadioButton jrf : radioButtonsAllList) {
               jrf.addItemListener(listener);
           }


       /*When the type or difficulty of questions is being changed, the URL is reloaded and the question list is updated*/
       typeComboBox.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Object typeChoice = typeComboBox.getSelectedItem();
               if(typeChoice.equals("True / False")){
                   typeURL = "boolean";
                   creditFactorType = 2;

                   //Set correct panel to display question answer options
                   updatePanels(answerOptionsPanel, trueFalseAnswerOptionPanel);

               }else{
                   typeURL = "multiple";
                   creditFactorType = 4;

                   //Set correct panel to display question answer options
                   updatePanels(answerOptionsPanel, mcAnswerOptionsPanel);

               }

               //set the buttonpanel to the submit answer buttons
               updatePanels(buttonPanel, submitButtonPanel);

               //clear selections, reload API with new parameters and display question and answeroptions
               clearSelections(radioButtonsTFList);
               reloadQuestions();
               displayQuestion();

           }
       });


       difficultyComboBox.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               //Set variables for API
               Object difficultyChoice = difficultyComboBox.getSelectedItem();
               if(difficultyChoice.equals("Easy")){
                   difficultyURL = "easy";
                   creditFactorDifficulty=1;
               }else{
                   if(difficultyChoice.equals("Medium")){
                       difficultyURL = "medium";
                       creditFactorDifficulty=2;
                   }else{
                       difficultyURL = "hard";
                       creditFactorDifficulty=3;
                   }}

               //set the buttonpanel to the submit answer buttons
               updatePanels(buttonPanel, submitButtonPanel);

               //clear selections, reload API with new parameters and display question and answeroptions
               clearSelections(radioButtonsMcList);
               reloadQuestions();
               displayQuestion();
           }
       });


       submitAnswerButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(typeComboBox.getSelectedItem().equals("True / False")) {
                   checkAnswer(radioButtonsTFList);
               }else{
                   checkAnswer(radioButtonsMcList);
               }

               submitAnswerButton.setEnabled(false);

               updatePanels(buttonPanel, nextQuestionPanel);
           }
       });


       QUITButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //TODO
           }
       });

       QUITButton2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //TODO
           }
       });


       NEXTQUESTIONButton1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               questionNumber++;

               if(availableCredits >= creditAmountWonLost) {
                   clearSelections(radioButtonsAllList);
                   displayQuestion();

               }else{

               }

           }
       });
   }



//----------------------------------------------------------------------------------------------------------------------
/*Methods*/

    public void reloadQuestions(){

        //calculate total credit amount that can be won/lost based on choise of type and difficulty
        //TODO discuss with team
        creditAmountWonLost = 5 * creditFactorType * creditFactorDifficulty;

        /*Depending on choice of type of question, difficulty and category a different URL is used
        open url and reader*/
        URL url = null;
        try {
            url = new URL("https://opentdb.com/api.php?amount=50" + "&difficulty=" + difficultyURL + "&type=" + typeURL);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(url.openStream());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        //make variabeles which contain the incoming info
        Trivia t = new Gson().fromJson(reader, Trivia.class);
        results = t.getResults();

        //close the reader
        try {
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }



    public void displayQuestion(){
        //Get the question and all related info
        Results result = results.get(questionNumber);

       // Display the question
        questionTextArea.setText(result.getQuestion());
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);

        //put correct and incorrect answers in an arrayList, sort alphabetically and display answer options
        allAnswers.clear();
        allAnswers.add(result.getCorrect_answer());
        for (int i = 0; i < result.getIncorrect_answers().size(); i++) {
            allAnswers.add(result.getIncorrect_answers().get(i));
        }

        allAnswers.sort(null);

        //Set correct panel to display question and if MC set the text of the radiobuttons
        if(typeURL.equals("boolean")){
            updatePanels(answerOptionsPanel, trueFalseAnswerOptionPanel);

            //answer options are hardcoded
        }else{
            updatePanels(answerOptionsPanel, mcAnswerOptionsPanel);

            //Add answeroptions to radiobuttons
            radioButton1.setText(allAnswers.get(0));
            radioButton2.setText(allAnswers.get(1));
            radioButton3.setText(allAnswers.get(2));
            radioButton4.setText(allAnswers.get(3));
        }


        //set correct button panel
        updatePanels(buttonPanel, submitButtonPanel);
    }


    public void checkAnswer(ArrayList <JRadioButton> radioButtonsList){

       //check if answer is correct or not
        Results result = results.get(questionNumber);
        int answerArrayPos = 0;
        for (int i = 0; i < radioButtonsList.size(); i++) {
            if(radioButtonsList.get(i).isSelected()){
                answerArrayPos = i;
            }
        }

        //get correct answer and answer of user
        String correct_answer = result.getCorrect_answer();
        String userAnswer = radioButtonsList.get(answerArrayPos).getText();

        //if correct answer
       if ( correct_answer.equals(userAnswer)) {
           updatePanels(answerOptionsPanel,correctAnswerPanel);
           creditsGainedLabel.setText("+" + creditAmountWonLost +" credits");

           //calc credits
           availableCredits += creditAmountWonLost;
           totalCreditsLostWonInGame += creditAmountWonLost;

           //TODO update the available credits in top right corner
       } else {
           updatePanels(answerOptionsPanel,wrongAnswerPanel);
           creditsLostLabel.setText("-" + creditAmountWonLost +" credits");

           correctAnswer.setText(correct_answer);

           //calc credits
           availableCredits -= creditAmountWonLost;
           totalCreditsLostWonInGame -= creditAmountWonLost;

           //TODO update the available credits in top right corner
       }
    }


    public void clearSelections (ArrayList<JRadioButton> list){

       //deselect the radio buttons of the answer options
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        buttonGroup.add(trueRadioButton);
        buttonGroup.add(falseRadioButton);
        buttonGroup.clearSelection();

        //set the submit answer button again to disabled
       submitAnswerButton.setEnabled(false);

    }


    public void updatePanels (JPanel parentPanel, JPanel panelToAdd){
        parentPanel.removeAll();
        parentPanel.add(panelToAdd);
        parentPanel.revalidate();
        parentPanel.repaint();
    }


//----------------------------------------------------------------------------------------------------------------------
/*Getters and setters*/

    public JPanel getGamePanel() {
        return gamePanel;
    }

//    public ArrayList<Results> getResults() {
//        return results;
//    }
//
//    public void setResults(ArrayList<Results> results) {
//        this.results = results;
//    }


}
