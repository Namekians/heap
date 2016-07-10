import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by yinch_000 on 7/9/2016.
 */
public class heap<T> {
    private ArrayList<T> arr = new ArrayList<T>();
    private Comparator<T> comparator;

    heap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T t) {
        arr.add(t);
        int n = arr.size();
        while (n != 1) {
            if (comparator.compare(arr.get(n - 1), arr.get(n / 2 - 1)) < 0) {
                swap(n - 1, n / 2 - 1);
                n = n / 2;
            } else {
                break;
            }
        }
    }

    public void add(T[] array) {
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }

    public void removeTop() {
        if (!arr.isEmpty()) {

            arr.set(0, arr.get(arr.size() - 1));
            arr.remove(arr.size() - 1);

            int n = 1;
            while (true) {
                // if has both left and right node
                if (2 * n + 1 < arr.size()) {
                    // current bigger than left or right
                    if (comparator.compare(arr.get(n), arr.get(2 * n - 1)) > 0 || comparator.compare(arr.get(n), arr.get(2 * n)) > 0) {
                        if (comparator.compare(arr.get(2 * n - 1), arr.get(2 * n)) > 0) {
                            // left bigger than right node, swap current and right
                            swap(2 * n, n - 1);
                            n = 2 * n + 1;
                        } else {
                            // swap current and left
                            swap(2 * n - 1, n - 1);
                            n = 2 * n;
                        }
                    } else {
                        break;
                    }
                }
                // if only has left node
                else if (2 * n < arr.size()) {
                    if (comparator.compare(arr.get(n - 1), arr.get(2 * n - 1)) > 0) {
                        // current bigger than left go to left
                        swap(2 * n - 1, n - 1);
                        n = 2 * n;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    private void swap(int a, int b) {
        System.out.println("swap :" + arr.get(a) + "  " + arr.get(b));
        if (a < arr.size() && b < arr.size()) {
            T temp = arr.get(a);
            arr.set(a, arr.get(b));
            arr.set(b, temp);
            System.out.println(this);

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public T peek() {
        if (!arr.isEmpty()) {
            return arr.get(0);
        } else {
            return null;
        }
    }

    public int size() {
        return arr.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int n = 1;
        for (int i = 0; i < arr.size(); i++) {
            if (i + 1 == Math.pow(2, n) - 1) {
                sb.append(arr.get(i) + "\n");
                n++;
            } else {
                sb.append(arr.get(i) + " ");
            }
        }
        return sb.toString();
    }

    public boolean checkSelfIntegrity() {
        if (arr.size() == 0) {
            return true;
        }

        return checkSelfIntegrityHelper(1, arr.get(0)) && checkSelfIntegrityHelper(2, arr.get(0));


    }

    private boolean checkSelfIntegrityHelper(int node, T parent) {
        if (2 * node - 1 >= arr.size()) {
            return true;
        }

        T current = arr.get(node - 1);

        if (comparator.compare(current, parent) < 0) {
            return false;
        }
        boolean left = checkSelfIntegrityHelper(2 * node, current);
        boolean right = checkSelfIntegrityHelper(2 * node + 1, current);
        return left && right;
    }

}
