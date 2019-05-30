class Solution5 {
    public String longestCommonPrefix(String[] strs) {
        String result = "";

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];

            if (i == 0) {
                result = str;
            } else {
                int subLength = str.length() < result.length() ? str.length() : result.length();

                if (subLength == 0)
                    return "";

                for (int j = 0; j < subLength; j++) {
                    if (result.charAt(j) != str.charAt(j)) {
                        if (j == 0) {
                            return "";
                        }

                        result = str.substring(0, j);
                        break;
                    }

                    if (j == subLength - 1 && str.length() < result.length()) {
                        result = str;
                    }
                }
            }
        }

        return result;
    }
}


public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"abab", "aba", ""};
        String result = new Solution5().longestCommonPrefix(strs);

        System.out.println(result);
    }
}
