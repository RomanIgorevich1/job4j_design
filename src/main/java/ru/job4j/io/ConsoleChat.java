package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "Закончить";
    private static final String STOP = "Стоп";
    private static final String CONTINUE = "Продолжить";
    private final String path;
    private final String botAnswer;

    public ConsoleChat(String path, String botAnswer) {
        this.path = path;
        this.botAnswer = botAnswer;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/UserQuestions.txt"))) {
            String str;
            boolean command = true;
            List<String> dialog = new ArrayList<>();
            while ((str = reader.readLine()) != null) {
                if (str.equals(STOP)) {
                    dialog.add(str);
                    command = false;
                    continue;
                }
                if (str.equals(CONTINUE)) {
                    dialog.add(str);
                    command = true;
                }
                if (str.equals(OUT)) {
                    dialog.add(str);
                    break;
                }
                if (command) {
                    for (String answer : readPhrases()) {
                        if (dialog.contains(answer)) {
                            continue;
                        } else {
                            if (!dialog.contains(str)) {
                                dialog.add(str);
                            }
                            dialog.add(answer);
                            break;
                        }
                    }
                }
                if (!dialog.contains(str)) {
                    dialog.add(str);
                }
            }
            saveLog(dialog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> text = new ArrayList<>();
        try (BufferedReader bufferedReaderBot = new BufferedReader(new FileReader("./data/BotAnswers.txt"))) {
            String str2;
            while ((str2 = bufferedReaderBot.readLine()) != null) {
                text.add(str2);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return text;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./data/Dialog.txt"))) {
            for (String text : log) {
                writer.write(text + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/Dialog.txt", "./data/BotAnswers.txt");
        //System.out.println(consoleChat.readPhrases());
        consoleChat.run();
    }
}
