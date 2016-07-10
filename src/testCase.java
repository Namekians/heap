import java.util.Comparator;

public class testCase {

    public static void main(String[] args) {
        heap<Integer> heap = new heap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });


//        Integer[] kickerNumbers = new Integer[10];
//        for(int i = 0; i < kickerNumbers.length; i++) {
//            kickerNumbers[i] = (int)(Math.random()*100+9);
//        }

        heap.add(new Integer[]{8,11,10,12,13,14,15});
        System.out.println(heap);
        System.out.println(heap.checkSelfIntegrity());
        heap.removeTop();

        System.out.println(heap);
        System.out.println(heap.checkSelfIntegrity());

    }
}
