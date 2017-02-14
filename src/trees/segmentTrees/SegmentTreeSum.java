package trees.segmentTrees;

/**
 * Basic Implementation of segment tree to query for sum in a given range.
 * Creation take : O(nLogn)
 * Get sum : O(Logn)
 * Update : O(Logn)
 * Created by anunay on 14/02/17.
 */

public class SegmentTreeSum {
    private int treeData[];

    /**
     * Creates the segment tree based on the given input array
     *
     * @param data Array that needs to converted
     * @param n    Size of the array
     */
    public SegmentTreeSum(int data[], int n) {
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int maxSize = (int) (2 * Math.pow(h, 2) - 1);
        treeData = new int[maxSize];
        construct(data, 0, n - 1, 0);
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        SegmentTreeSum segmentTreeSum = new SegmentTreeSum(arr, n);
        System.out.println(segmentTreeSum.getSum(0, n - 1, 1, 3, 0));
        segmentTreeSum.update(arr, n, 1, 10);
        System.out.println(segmentTreeSum.getSum(0, n - 1, 1, 3, 0));
    }

    /**
     * Update a specified index in the array and the tree
     *
     * @param arr    data array
     * @param n      size of array
     * @param i      index of array that needs to be replaced
     * @param newVal new value
     */
    public void update(int arr[], int n, int i, int newVal) {
        int diff = newVal - arr[i];
        arr[i] = newVal;
        update(0, n - 1, i, diff, 0);
    }

    private void update(int start, int end, int idx, int diff, int currentNode) {
        if (idx < start || idx > end) {
            return;
        }
        treeData[currentNode] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, idx, diff, 2 * currentNode + 1);
            update(mid + 1, end, idx, diff, 2 * currentNode + 2);
        }
    }

    private int construct(int[] data, int start, int end, int currentNode) {
        if (start == end) {
            treeData[currentNode] = data[start];
            return data[start];
        }
        int mid = (start + end) / 2;
        treeData[currentNode] = construct(data, start, mid, 2 * currentNode + 1) +
                construct(data, mid + 1, end, 2 * currentNode + 2);
        return treeData[currentNode];
    }

    /**
     * Get sum for a specified range
     *
     * @param start       start index of the tree data
     * @param end         end index of the tree data
     * @param rangeStart  query start index
     * @param rangeEnd    query end index
     * @param currentNode starting node of the tree data
     * @return sum of the range
     */
    public int getSum(int start, int end, int rangeStart, int rangeEnd, int currentNode) {
        if (rangeStart <= start && rangeEnd >= end) {
            return treeData[currentNode];
        }

        if (end < rangeStart || start > rangeEnd) {
            return 0;
        }
        int mid = (start + end) / 2;
        return getSum(start, mid, rangeStart, rangeEnd, 2 * currentNode + 1) +
                getSum(mid + 1, end, rangeStart, rangeEnd, 2 * currentNode + 2);
    }
}
