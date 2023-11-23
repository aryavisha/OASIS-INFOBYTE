package JAVA_PROJECT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
    class Question {
        private String questionText;
        private List<String> options;
        private int correctOption;
        public Question (String questionText, List<String> options, int correctOption) {
            this.questionText = questionText;
            this.options = options;
            this.correctOption = correctOption;
        }
        public String getQuestionText() {
            return questionText;
        }
        public List<String> getOptions() {
            return options;
        }
        public int getCorrectOption() {
            return correctOption;
        }
    }
    class Exam {
        private List<Question> questions;
        public Exam(List<Question> questions) {
            this.questions = questions;
        }
        public List<Question> getQuestions() {
            return questions;
        }
    }
    class ExamSystem {
        private List<Exam> exams;
        private Scanner scanner;
        public ExamSystem() {
            this.exams = new ArrayList<>();
            this.scanner = new Scanner(System.in);
        }
        public void addExam(Exam exam) {
            exams.add(exam);
        }
        public void startExam() {
            System.out.println("Select an exam to start:");
            for (int i = 0; i < exams.size(); i++) {
                System.out.println((i + 1) + ". Exam " + (i + 1));
            }
            int selectedExamIndex = scanner.nextInt() - 1;
            if (selectedExamIndex < 0 || selectedExamIndex >= exams.size()) {
                System.out.println("Invalid exam selection");
                return;
            }
            Exam selectedExam = exams.get(selectedExamIndex);
            int score = conductExam(selectedExam);
            System.out.println("Your score: " + score);
        }
        private int conductExam(Exam exam) {
            int score = 0;

            for (Question question : exam.getQuestions()) {
                System.out.println(question.getQuestionText());
                List<String> options = question.getOptions();
                Collections.shuffle(options);
                for (int i = 0; i < options.size(); i++) {
                    System.out.println((i + 1) + ". " + options.get(i));
                }
                System.out.println("Your answer: ");
                int userAnswer = scanner.nextInt() - 1;

                if (userAnswer == question.getCorrectOption()) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. Correct answer: " + (question.getCorrectOption() + 1) + "\n");
                }
            }
            return score;
        }
    }
    public class Online_Examination {
        public static void main(String[] args) {
            // Create questions
            List<Question> questions = new ArrayList<>();
            questions.add(new Question("What is the capital of France?", List.of("Berlin", "Madrid", "Paris", "Rome"), 2));
            questions.add(new Question("What is the largest planet in our solar system?", List.of("Mars", "Jupiter", "Venus", "Saturn"), 1));
            questions.add(new Question("Who wrote 'Romeo and Juliet'?", List.of("William Shakespeare", "Jane Austen", "Charles Dickens", "Mark Twain"), 0));

            Exam exam = new Exam(questions);
            ExamSystem examSystem = new ExamSystem();
            examSystem.addExam(exam);
            examSystem.startExam();
        }
    }


