import java.util.*;

class Solution {
    int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<int[]>[] priceList = new ArrayList[n];
        List<Integer> routeList = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            priceList[i] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            priceList[flight[0]].add(flight);
        }

        /*
            因为使用优先级队列 把比较的过程去掉了 所以没法输出最短路径。。。
            如果要输出最短路径 不要用pq 自己写比较判断
         */
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.add(new int[]{src, src, 0, -1});

        while (!pq.isEmpty()) {
            int[] curt = pq.poll();
            int start = curt[0];
            int arrival = curt[1];
            int money = curt[2];
            int stop = curt[3] + 1;

            System.out.println(start + "-->" + arrival);

            if (arrival == dst) {
                System.out.print("end");
                return money;
            }

            for (int[] flight : priceList[arrival]) {
                if (stop > K) {
                    break;
                } else {
                    pq.add(new int[]{flight[0], flight[1], flight[2] + money, stop});
                }
            }
        }

        return -1;
    }
}

public class CheapestFlightsWithKStops {

    public static void main(String[] args) {
        int n = 5;
        int[][] flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}, {0, 3, 200}, {2, 4, 100}, {3, 4, 200}};
        int src = 0;
        int dst = 4;
        int K = 2;

        int ret = new Solution().findCheapestPrice(n, flights, src, dst, K);

        String out = String.valueOf(ret);

        System.out.println();
        System.out.println(out);
    }
}
