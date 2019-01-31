import Exceptions.InvalidNumberOfThreadsException;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Searchanator {

    private static final String KEYWORD_VOINA = "война";
    private static final String KEYWORD_MIR = "мир";

    private File file;
    private AtomicInteger commaCounter = new AtomicInteger(0);
    private AtomicInteger voinaCounter = new AtomicInteger(0);
    private AtomicInteger mirCounter = new AtomicInteger(0);
    private ConcurrentHashMap<String, Integer> finalMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, String> tempMap = new ConcurrentHashMap<>();


    private ConcurrentHashMap<Integer, String> splitter(String text, int size){

        ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>();
        final int lengthOfPart = text.length() / size;
        int start = 0;
        int end = lengthOfPart;

        for (int i = 0; i < size; i++) {
            tempMap.put(i+1, text.substring(start, end));
            start = end;
            end += lengthOfPart;
        }
        return map;
    }

    public void process(String filepath, int numberOfThreads) {

        if (numberOfThreads < 1) {
            try {
                throw new InvalidNumberOfThreadsException();
            } catch (InvalidNumberOfThreadsException e) {
                System.out.println("Ops, something went wrong :( " + e.getMessage());
                return;
            }
        }

        this.file = new File(filepath);
        StringBuilder warAndPeace = new StringBuilder();
        Thread t = null;

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filepath)))) {

            while (br.read() != -1) {
                String text = br.readLine();
                warAndPeace.append(text);
            }
        }
        catch (IOException ex) {
            System.out.println("Ops, something went wrong :(");
        }

        tempMap.putAll(splitter(warAndPeace.toString(), numberOfThreads));

        for (Map.Entry<Integer, String> i : tempMap.entrySet()) {

            t = new Thread(() -> {

                String[] words = i.getValue().replaceAll("[!@#$%^&“)№*.(=-_\"„?:{}|<…’>;-]", " ").split(" ");

                for (int j = 0; j < words.length; j++) {
                    if (words[j].contains(KEYWORD_VOINA) || words[j].equalsIgnoreCase(KEYWORD_VOINA)) {
                        voinaCounter.getAndIncrement();
                    }
                    if (words[j].contains(KEYWORD_MIR) || words[j].equalsIgnoreCase(KEYWORD_MIR)) {
                        mirCounter.getAndIncrement();
                    }
                    if (!(finalMap.containsKey(words[j]))) {
                        finalMap.put(words[j].replace(",", ""), 1);
                    }
                    else {
                        finalMap.put(words[j].replace(",", ""), finalMap.get(words[j]) +1);
                    }
                }

                for (int j = 0; j < i.getValue().length(); j++) {

                    char c = ',';
                    if (i.getValue().charAt(j) == c) {
                        commaCounter.getAndIncrement();
                    }
                }
            });
            t.start();
        }

        try {
            if (t != null) {
                t.join();
            }
        }
        catch (InterruptedException e) {
            System.out.println("Oops, someone interrupted: " + t.getName() + " :(");
        }

        for (Map.Entry<String, Integer> entry : finalMap.entrySet()) {
            System.out.println("Word: " + entry.getKey() + " count: " + entry.getValue());
        }

        System.out.println();
        System.out.println("Comma counter: " + commaCounter);
        System.out.println("Words that contain 'война': " + voinaCounter);
        System.out.println("Words that contain 'мир': " + mirCounter);

    }
}
