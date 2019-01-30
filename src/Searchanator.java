import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Searchanator {

    private static final String KEYWORD_VOINA = "война";
    private static final String KEYWORD_MIR = "мир";

    private File file;
    private StringBuilder warAndPeace;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private AtomicInteger keywordVoinaCounter = new AtomicInteger(0);
    private AtomicInteger keywordMirCounter = new AtomicInteger(0);
    private Map<String, Integer> finalMap = new TreeMap<>();
    private ConcurrentHashMap<Integer, String> tempMap = new ConcurrentHashMap<>();


    private ConcurrentHashMap<Integer, String> splitter(String text, int size) {

        ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>();
        final int lengthOfPart = text.length() / size;
        int start = 0;
        int end = lengthOfPart;

        for (int i = 0; i < size; i++) {
            tempMap.put(i+1, warAndPeace.substring(start, end));
            start = end;
            end += lengthOfPart;
        }
        return map;
    }

    public void process(String filepath, int numberOfThreads) {

        this.file = new File(filepath);
        warAndPeace = new StringBuilder();
        BufferedReader br;

        try {

            br = new BufferedReader(new FileReader(new File(filepath)));
            while (br.read() != -1) {

                String text = br.readLine();
                warAndPeace.append(text.replaceAll("[!@#$%^&*()“=-_.\"„?№\":{}“|<…’>;-]", ""));
                String[] words = text.split(" ");

                for (int i = 0; i < words.length; i++) {
                    if (words[i].contains(KEYWORD_VOINA)) {
                        keywordVoinaCounter.getAndIncrement();
                    }
                    if (words[i].contains(KEYWORD_MIR)) {
                        keywordMirCounter.getAndIncrement();
                    }
                    if (!(finalMap.containsKey(words[i]))) {
                        finalMap.put(words[i], 1);
                    }
                    else {
                        finalMap.put(words[i], finalMap.get(words[i]) +1);
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("Oopsie, something went wrong :(");
        }

        tempMap.putAll(splitter(warAndPeace.toString(), numberOfThreads));

        for (Map.Entry<Integer, String> i : tempMap.entrySet()) {

            Thread t = new Thread(() -> {

                for (int j = 0; j < i.getValue().length(); j++) {
                    char c = ',';
                    if (i.getValue().charAt(j) == c) {
                        atomicInteger.getAndIncrement();
                    }
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Oops, someone interrupted: " + t.getName() + " :(");
            }
        }

        for (Map.Entry<String, Integer> entry : finalMap.entrySet()) {
            System.out.println("Word: " + entry.getKey() + " count: " + entry.getValue());
        }

        System.out.println();
        System.out.println("Comma counter: " + atomicInteger);
        System.out.println("Words that contain 'война': " + keywordVoinaCounter); // 282  * need to find 5 more
        System.out.println("Words that contain 'мир': " + keywordMirCounter); //507 * need to find 10 more

    }

}
