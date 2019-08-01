import java.util.Stack;

public class StackByStackSort {

    private static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();

        while (!stack.isEmpty()) {
            int cur = stack.pop();

            while (!help.isEmpty() && help.peek() > cur) {
                stack.push(help.pop());
            }

            help.push(cur);
        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(5);
        s.push(3);
        s.push(4);
        s.push(2);
        s.push(5);
        s.push(3);

        sortStackByStack(s);
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
