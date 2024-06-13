import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        int[] arr = {1, 2, 3, -6, 5, 4, 0};
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        boolean found = false;

        map.put(0, -1);
        
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                int start = map.get(sum) + 1;
                int end = i;
                ans.add(start);
                ans.add(end);
                found = true;
                break;
            }

            map.put(sum, i);

            if(found) {
                break;
            }
        }

        System.out.println(ans);
    }
}
