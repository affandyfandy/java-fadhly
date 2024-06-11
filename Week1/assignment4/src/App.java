import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        int[] arr = {1, 2, 3, -6, 5, 4, 0};
        ArrayList<Integer> ans = new ArrayList<>();
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = i; k < j; k++) {
                    sum += arr[k];
                }

                if (sum == 0) {
                    ans.add(i);
                    ans.add(j - 1);
                    found = true;
                    break;
                }
                sum = 0;
            }

            if(found) {
                break;
            }
        }

        System.out.println(ans);
    }
}
