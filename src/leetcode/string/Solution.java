package leetcode.string;

public class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        String str = new String("0123456789abcdef");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            int count = (num >> (4 * i)) & 0xf;
            sb.append(str.charAt(count));
        }
        String str1 = sb.reverse().toString();
        int i = 0;
        while (i < str1.length()) {
            if (str1.charAt(i) != '0') {
                break;
            }
        }
        return str1.substring(i);
    }
}
