package leetcode.array;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> list1 = Arrays.asList("东方财富1","东方财富2","东方财富3");
        List<String> list2 = Arrays.asList("东方财富2","东方财富3");
        List<String> list3 = Arrays.asList("东方财富3");
        List<List<String>> list = Arrays.asList(list1, list2, list3);
        Map<String, List<String>> ans = s.getKeysDivideByType(list);
        System.out.println(ans);
    }
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        List<Integer> list = Arrays.asList(1);
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        for (int i = 1; i < n; ++i) {
            int next = Math.min(list.get(index2) * 2, Math.min(list.get(index3) * 3, list.get(index5) * 5));
            if (list.get(index2) * 2 == next) {
                index2++;
            }
            if (list.get(index3) * 3 == next) {
                index3++;
            }
            if (list.get(index5) * 5 == next) {
                index5++;
            }
            list.add(next);
        }
        return list.get(n - 1);
    }
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int h = n - mid + 1;
            if (citations[mid] >= h) {
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return left;


    }
    public Map<String, List<String>> getKeysDivideByType(List<List<String>> typeList) {
        Map<String, List<Integer>> map1 = new HashMap<>();
        for (int i = 1; i <= typeList.size(); ++i) {
            List<String> list = typeList.get(i - 1);
            for (String str : list) {
                map1.putIfAbsent(str, new ArrayList<>());
                map1.get(str).add(i);
            }
        }
        Map<String, List<String>> ans = new HashMap<>();
        for (String key : map1.keySet()) {
            List<Integer> list = map1.get(key);
            String str = new String();
            for (Integer value : list) {
                str += value;
                str += "_";
            }
            if (!str.isEmpty()) {
                str = str.substring(0, str.length() - 1);
            }
            ans.putIfAbsent(str, new ArrayList<>());
            ans.get(str).add(key);
        }
        return ans;
    }

    public int trailingZeroes(int n) {
        int ans = 0;
        int pow = 5;
        while (n >= pow) {
            ans += n / pow;
            pow = 5 * pow;
        }
        return ans;
    }
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int ans[] = new int[2];
        while (left <= right) {
            int cur = numbers[left] + numbers[right];
            if (cur > target) {
                right = right - 1;
            } else if (cur < target) {
                left = left + 1;
            }else {
                ans[0] = left + 1;
                ans[1] = right + 1;
                return ans;
            }
        }
        ans[0] = -1;
        ans[1] = -1;
        return ans;
    }
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean bigLeft = false;
            boolean bigRight = false;
            if (mid == 0) {
                bigLeft = true;
            } else if (nums[mid] > nums[mid - 1]) {
                bigLeft = true;
            }
            if (mid == nums.length - 1) {
                bigRight = true;
            } else if (nums[mid] > nums[mid + 1]) {
                bigRight = true;
            }
            if (bigLeft && bigRight) {
                return mid;
            }
            if (bigLeft) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return 0;
    }
    public int findMin2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int ans = nums[0];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            ans = Math.min(nums[mid], ans);
            ans = Math.min(nums[left], ans);
            if (nums[left] < nums[mid]) {
                left = mid + 1;
            } else if (nums[left] > nums[mid]) {
                right = mid - 1;
            } else {

                left = left + 1;
            }
        }
        return ans;
    }
    public int findMin(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int ans = nums[0];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            ans = Math.min(nums[mid], ans);
            if (nums[left] <= nums[mid]) {
                ans = Math.min(nums[left], ans);
                left = mid + 1;
            }else{
                ans = Math.min(nums[left], ans);
                right = mid - 1;
            }
        }
        return ans;
    }
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int minValue = nums[0];
        int maxValue = nums[0];
        int result = maxValue;
        for (int i = 1; i < n; ++i) {
            int num = nums[i];
            if (num < 0) {
                int temp = minValue;
                minValue = maxValue;
                maxValue = temp;
            }
            minValue = Math.min(minValue, minValue * num);
            maxValue = Math.min(maxValue, maxValue * num);
            result = Math.max(maxValue, result);
        }
        return result;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        int n = s.length();
        boolean dp[] = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (set.contains(s.substring(0, i+1))) {
                dp[i] = true;
                continue;
            }
            for (int j = 0; j < i; ++j) {
                if (set.contains(s.substring(j + 1, i+1))) {
                    dp[i] = dp[i] || dp[j];
                }
            }
        }
        return dp[n - 1];
    }

    public int numDecodings(String s) {
        int n = s.length();
        if (n == 1) {
            return 1;
        }
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                dp[i + 1] = dp[i + 1] + dp[i];
            }
            if (i >= 1 ) {
                if ((s.charAt(i - 1) == '1') || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {
                    dp[i + 1] = dp[i + 1] + dp[i - 1];
                }
            }
        }
        return dp[n];
    }

    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right-left) / 2;
            long temp = (long)mid * mid;
            if (temp <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target <= nums[mid] ) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        int ans1 = -1;
        if (left < nums.length && nums[left] == target) {
            ans1 = left;
        }
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target >= nums[mid] ) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        int ans2 = -1;
        if (right >= 0 && nums[right] == target) {
            ans2 = right;
        }
        int result [] =new int[2];
        result[0] = ans1;
        result[1] = ans2;
        return result;
    }
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean dp[][] = new boolean[s.length()][s.length()];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        String ans = new String();
        int begin = 0;
        int maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j) {
                        dp[i][j] = true;
                    } else if (j == i + 1) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 1) {
            return (double) kthArrays(nums1, nums2, len / 2 + 1);
        }else{
            return (kthArrays(nums1, nums2, len / 2) + kthArrays(nums1, nums2, len / 2 + 1)) / 2.0;
        }
    }

    public int kthArrays(int[] nums1, int[] nums2,int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int index1 = 0;
        int index2 = 0;
        while (true) {
            if (index1 == len1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == len2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1],nums2[index2]);
            }
            int end1 = Math.min(index1 + k / 2 - 1, len1 - 1);
            int end2 = Math.min(index2 + k / 2 - 1, len2 - 1);
            if (nums1[end1] <= nums2[end2]) {
                index1 = end1 + 1;
                k = k - (end1 - index1 + 1);
            }else{
                index2 = end2 + 1;
                k = k - (end2 - index2 + 1);
            }
        }
    }
}
