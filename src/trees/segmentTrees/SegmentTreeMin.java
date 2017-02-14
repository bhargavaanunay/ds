package trees.segmentTrees;

/**
 * Basic implementation of segment tree to find the min element in the given range
 * Creation : O(nLogn)
 * getMin : O(Logn)
 * Created by anunay on 14/02/17.
 */

public class SegmentTreeMin {
    private int treeData[];

    /**
     * Create segment tree from given data
     *
     * @param data Array to be converted
     * @param n    Size of array
     */
    public SegmentTreeMin(int data[], int n) {
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int maxSize = (int) (2 * Math.pow(2, h) - 1);
        treeData = new int[maxSize];
        construct(data, 0, n - 1, 0);
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 2, 7, 9, 11};
        SegmentTreeMin segmentTreeMin = new SegmentTreeMin(arr, arr.length);
        System.out.println(segmentTreeMin.getMin(0, arr.length - 1, 3, 5, 0));
    }

    private int construct(int[] data, int start, int end, int currentNode) {
        if (start == end) {
            treeData[currentNode] = data[start];
            return data[start];
        }
        int mid = (start + end) / 2;
        treeData[currentNode] = Math.min(construct(data, start, mid, 2 * currentNode + 1),
                construct(data, mid + 1, end, 2 * currentNode + 2));
        return treeData[currentNode];
    }

    /**
     * Get min element for the given range
     *
     * @param start       start index of the tree data
     * @param end         end index of the tree data
     * @param rangeStart  start index for the range query
     * @param rangeEnd    end index for the range query
     * @param currentNode current node of the tree data
     * @return
     */
    public int getMin(int start, int end, int rangeStart, int rangeEnd, int currentNode) {
        if (rangeStart <= start && end <= rangeEnd) {
            return treeData[currentNode];
        }

        if (rangeStart > end || rangeEnd < start) {
            return Integer.MAX_VALUE;
        }

        int mid = (start + end) / 2;
        return Math.min(getMin(start, mid, rangeStart, rangeEnd, 2 * currentNode + 1),
                getMin(mid + 1, end, rangeStart, rangeEnd, 2 * currentNode + 2));

    }

}
