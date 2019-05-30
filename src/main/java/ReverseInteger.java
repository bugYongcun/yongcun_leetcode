class Solution4 {
    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            if (Math.abs(result) > Integer.MAX_VALUE / 10) {
                result = 0;
                break;
            }

            result = result * 10 + x % 10;
            x = x / 10;
        }

        return result;
    }
}

public class ReverseInteger {
    public static void main(String[] args) {
        int num = -123;
        int result = new Solution4().reverse(num);

        System.out.println(result);
    }
}
