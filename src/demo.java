import java.util.Arrays;

public class demo {

    public static void main(String[] args) {


        long[] averages = new long[10];
        long[] individual = new long[30];
        int k = 0;

        for (int i = 1; i <= 10; i++) {

            long total = 0;

            for (int j = 0; j < 3; j++) {
                long start = System.currentTimeMillis();

                new Searchanator().process("voinaimir.txt", i);

                long end = System.currentTimeMillis() - start;
                total += end;
                individual[k++] = end;
            }
            averages[i - 1] = total / 3;


            System.out.println(Arrays.toString(averages));
            System.out.println(Arrays.toString(individual));
        }
    }
}

        /*-------------------------------------BENCHMAKRS-------------------------------------
        nThreads = 1 ->
            Attempt 1: 1763ms
            Attempt 2: 1281ms -------- Average -> 1318ms
            Attempt 3: 912ms
        nThreads = 2 ->
            Attempt 1: 802ms
            Attempt 2: 587ms -------- Average -> 697ms
            Attempt 3: 602ms
        nThreads = 3 ->
            Attempt 1: 597ms
            Attempt 2: 632ms -------- Average -> 610ms
            Attempt 3: 601ms
        nThreads = 4 ->
            Attempt 1: 580ms
            Attempt 2: 566ms -------- Average -> 565ms
            Attempt 3: 551ms
        nThreads = 5 ->
            Attempt 1: 651ms
            Attempt 2: 765ms -------- Average -> 670ms
            Attempt 3: 595ms
        nThreads = 6 ->
            Attempt 1: 547ms
            Attempt 2: 539ms -------- Average -> 542ms
            Attempt 3: 541ms
        nThreads = 7 ->
            Attempt 1: 537ms
            Attempt 2: 555ms -------- Average -> 548ms
            Attempt 3: 554ms
        nThreads = 8 ->
            Attempt 1: 526ms
            Attempt 2: 528ms -------- Average -> 525ms
            Attempt 3: 521ms
        nThreads = 9 ->
            Attempt 1: 642ms
            Attempt 2: 529ms -------- Average -> 563ms
            Attempt 3: 519ms
        nThreads = 10->
            Attempt 1: 553ms
            Attempt 2: 552ms -------- Average -> 545ms
            Attempt 3: 531ms
         */