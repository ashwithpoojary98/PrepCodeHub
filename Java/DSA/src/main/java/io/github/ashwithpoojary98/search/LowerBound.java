package main.java.io.github.ashwithpoojary98.search;

public class LowerBound {

    public static void main(String[] abc) {
        int[] nums = new int[] { 1, 3, 5, 6 };
        int target = 4;
        System.out.println(insertPosition(nums, target));
    }

    public static int insertPosition(int[] nums, int target) {
        int lowerBound = 0;
        int upperBound = nums.length - 1;
        int insertPostion = nums.length;
        while (lowerBound <= upperBound) {
            int mid = lowerBound + (upperBound - lowerBound) / 2;
            if (nums[mid] >= target) {
                insertPostion = mid;
                upperBound = mid - 1;
            } else {
                lowerBound = mid + 1;
            }
        }

        return insertPostion;
    }

}
