public class demo {

    public static void main(String[] args) {

        Searchanator alexa = new Searchanator();

        long start = System.currentTimeMillis();
        alexa.process("/Users/theddy/Desktop/voinaimir.txt", 5);
        System.out.println("Completed in: " + (System.currentTimeMillis() - start));



        /*-------------------------------------BENCHMAKRS-------------------------------------
        nThreads = 1 ->
            Attempt 1: 2305ms
            Attempt 2: 2301ms
            Attempt 3: 2376ms
        nThreads = 2 ->
            Attempt 1: 2437ms
            Attempt 2: 2227ms
            Attempt 3: 2372ms
        nThreads = 3 ->
            Attempt 1: 2363ms
            Attempt 2: 2256ms
            Attempt 3: 2467ms
        nThreads = 4 ->
            Attempt 1: 2740ms
            Attempt 2: 2353ms
            Attempt 3: 2243ms
        nThreads = 5 ->
            Attempt 1: 2674ms
            Attempt 2: 2126ms
            Attempt 3: 2390ms
        nThreads = 6 ->
            Attempt 1: 2420ms
            Attempt 2: 2215ms
            Attempt 3: 2071ms
        nThreads = 7 ->
            Attempt 1: 2647ms
            Attempt 2: 2315ms
            Attempt 3: 2476ms
        nThreads = 8 ->
            Attempt 1: 2798ms
            Attempt 2: 2351ms
            Attempt 3: 2132ms
        nThreads = 9 ->
            Attempt 1: 2732ms
            Attempt 2: 2662ms
            Attempt 3: 2171ms
        nThreads = 10->
            Attempt 1: 2620ms
            Attempt 2: 2405ms
            Attempt 3: 2536ms
         */
    }
}
