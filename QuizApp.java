import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends JFrame implements ActionListener {
    // Components
    private JLabel questionLabel, timerLabel, resultLabel;
    private JRadioButton[] options;
    private JButton nextButton, submitButton;
    private ButtonGroup optionsGroup;
    private Timer timer;

    // Quiz Data
    private String[] questions = {
            " India is a federal union comprising twenty-nine states and how many union territories?",
            " What is the state flower of Haryana?",
            " Which of the following states is not located in the North?",
            " In which of the following state, the main language is Khasi?",
            " Which state has the largest area?",
            " In what state is Elephant Falls located?",
            " Which state of India celebrates Hunter Holi, an 800-year tradition?",
            " Which city is known as the “summer capital” of Jammu and Kashmir?",
            " Yakshagana is the folk dance of which state?",
            " What is the staple drink of Goa?"
    };

    private String[][] optionsData = {
            {"4","6","8","10"},
            {"Lotus","Rhododendron","Golden Shower","Not Declared"},
            {"Jharkhand","Jammu and Kashmir","Himachal Pradesh","Haryana"},
            {"Mizoram","Nagaland","Meghalaya","Tripura"},
            {"Maharashtra","Madhya Pradesh","Uttar Pradesh","Rajasthan"},
            {"Mizoram","Orissa","Skkim","Meghalaya"},
            {"Punjab","Haryana","Delhi","Uttar Pradesh"},
            {"Jammu","Srinagar","Shimla","Gangtok"},
            {"Kerala","Tamil nadu","Karnataka","Telangana"},
            {"Toddy","Feni","Thandai","Sattu Sharbat"}
    };

    private int[] answers = {2,0,0,2,3,3,1,1,2,1}; // Index of correct answer
    private int score = 0, currentQuestionIndex = 0, timeRemaining = 10;

    public QuizApp() {
        setTitle("Quiz Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Question Panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(6, 3));
        questionLabel = new JLabel();
        timerLabel = new JLabel("Time: " + timeRemaining);
        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            questionPanel.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        questionPanel.add(timerLabel);
        questionPanel.add(nextButton);
        questionPanel.add(submitButton);

        add(questionLabel, BorderLayout.NORTH);
        add(questionPanel, BorderLayout.CENTER);

        // Timer setup
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time: " + timeRemaining);

                if (timeRemaining == 0) {
                    submitAnswer();
                }
            }
        });

        loadNextQuestion();
    }

    public void loadNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);
            optionsGroup.clearSelection();
            for (int i = 0; i < options.length; i++) {
                options[i].setText(optionsData[currentQuestionIndex][i]);
            }
            timeRemaining = 10;
            timer.start();
        } else {
            showResult();
        }
    }

    public void submitAnswer() {
        timer.stop();
        int selectedOption = -1;

        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }

        if (selectedOption == answers[currentQuestionIndex]) {
            score++;
        }

        currentQuestionIndex++;
        loadNextQuestion();
    }

    public void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Over! Your score is: " + score);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton || e.getSource() == submitButton) {
            submitAnswer();
        }
    }

    public static void main(String[] args) {
        QuizApp app = new QuizApp();
        app.setVisible(true);
    }
}