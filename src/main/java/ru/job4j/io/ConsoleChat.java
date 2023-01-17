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
        List<String> botAnswer = new ArrayList<>(readPhrases());
        List<String> dialog = new ArrayList<>();
        boolean command = true;
        int point = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!(point == 10)) {
                String word = reader.readLine();
                if ("Закончить".equals(word)) {
                    dialog.add(word);
                    break;
                }
                if ("Стоп".equals(word)) {
                    dialog.add(word);
                    command = false;
                }
                if ("Продолжить".equals(word)) {
                    command = true;
                }
                if (command) {
                    dialog.add(word);
                    dialog.add(botAnswer.get(point));
                    System.out.println(botAnswer.get(point));
                    point++;
                }
                if (!dialog.contains(word)) {
                    dialog.add(word);
                }
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        saveLog(dialog);
    }

    private List<String> readPhrases() {
        List<String> text = new ArrayList<>();
        try (BufferedReader bufferedReaderBot = new BufferedReader(new FileReader(botAnswer))) {
            String str;
            while ((str = bufferedReaderBot.readLine()) != null) {
                text.add(str);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return text;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String text : log) {
                writer.write(text + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/Dialog.txt", "./data/BotAnswers.txt");
        consoleChat.run();
    }
}
