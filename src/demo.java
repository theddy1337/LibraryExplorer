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

                new Searchanator().process("/Users/theddy/Desktop/voinaimir.txt", i);

                long end = System.currentTimeMillis() - start;
                total += end;
                individual[k++] = end;
            }
            averages[i-1] = total / 3;
        }

        System.out.println(Arrays.toString(averages));
        System.out.println(Arrays.toString(individual));
    }
}

        /*-------------------------------------BENCHMAKRS-------------------------------------
        nThreads = 1 ->
            Attempt 1: 1843ms
            Attempt 2: 1271ms -------- Average -> 1442ms
            Attempt 3: 1214ms
        nThreads = 2 ->
            Attempt 1: 1195ms
            Attempt 2: 1283ms -------- Average -> 1211ms
            Attempt 3: 1156ms
        nThreads = 3 ->
            Attempt 1: 1240ms
            Attempt 2: 1160ms -------- Average -> 1230ms
            Attempt 3: 1292ms
        nThreads = 4 ->
            Attempt 1: 1304ms
            Attempt 2: 1176ms -------- Average -> 1229ms
            Attempt 3: 1209ms
        nThreads = 5 ->
            Attempt 1: 1204ms
            Attempt 2: 1188ms -------- Average -> 1186ms
            Attempt 3: 1167ms
        nThreads = 6 ->
            Attempt 1: 1293ms
            Attempt 2: 1304ms -------- Average -> 1249ms
            Attempt 3: 1152ms
        nThreads = 7 ->
            Attempt 1: 1167ms
            Attempt 2: 1174ms -------- Average -> 1209ms
            Attempt 3: 1288ms
        nThreads = 8 ->
            Attempt 1: 1169ms
            Attempt 2: 1162ms -------- Average -> 1187ms
            Attempt 3: 1230ms
        nThreads = 9 ->
            Attempt 1: 1168ms
            Attempt 2: 1195ms -------- Average -> 1207ms
            Attempt 3: 1259ms
        nThreads = 10->
            Attempt 1: 1258ms
            Attempt 2: 1865ms -------- Average -> 1464ms
            Attempt 3: 1269ms
         */