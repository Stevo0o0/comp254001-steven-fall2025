    package comp254.lab4.ex2;

    import java.util.Stack;

    public class StackTransfer {

        public static <E> void transfer(Stack<E> S, Stack<E> T) {
            while (!S.isEmpty()) {
                T.push(S.pop());
            }
        }

        public static void main(String[] args) {
            Stack<Integer> S = new Stack<>();
            Stack<Integer> T = new Stack<>();
            S.push(1); S.push(2); S.push(3);   // bottom 1,2,3 top

            transfer(S, T);

            System.out.println("S: " + S);
            System.out.println("T: " + T);
        }
    }
