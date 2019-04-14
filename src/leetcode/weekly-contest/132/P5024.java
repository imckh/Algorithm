public class P5024 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 0; i < 50; i++) {
            System.out.println("solution.divisorGame() = " + solution.divisorGame(i));
        }
    }

    static class Solution {
        public boolean divisorGame(int N) {
            if (N == 2) {
                return true;
            }
            if (N == 3 || N <= 1) {
                return false;
            }
            //System.out.println("    " + N);
            boolean alice = divisorGame(N - 2);
            //boolean bob = divisorGame(N - 2);
            return alice;
        }
    }
}
