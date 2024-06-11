import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
		int[] arr = {1,4,3,-6,5,4};
		int numMax1 = Integer.MIN_VALUE;
		int numMax2 = Integer.MIN_VALUE;
		
		for(int i=0; i < arr.length; i++) {
		    if(arr[i] > numMax1) {
		        numMax2 = numMax1;
		        numMax1 = arr[i];
		    }else if(arr[i] > numMax2 && arr[i] != numMax1) {
		        numMax2 = arr[i];
		    }
		}

        ArrayList<Integer> indexArr = new ArrayList<>();

        for(int i=0; i < arr.length; i++) {
            if(arr[i] == numMax2) {
                indexArr.add(i);
            }
        }
        
        System.out.println(indexArr);
    }
}
