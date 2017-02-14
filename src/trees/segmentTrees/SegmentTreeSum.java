package trees.segmentTrees;

/**
 * Created by anunay on 14/02/17.
 */

public class SegmentTreeSum {
    private int treeData[];

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
