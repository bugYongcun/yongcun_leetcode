import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution3 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(result, new ArrayList<>(), nums, new boolean[nums.length]);

        return result;
    }

    private void backtracking(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums, boolean[] flags) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (flags[i] || (i > 0 && nums[i] == nums[i - 1] && !flags[i - 1]))
                    continue;
                tempList.add(nums[i]);
                flags[i] = true;
                backtracking(list, tempList, nums, flags);
                tempList.remove(tempList.size() - 1);
                flags[i] = false;
            }
        }
    }
}

public class PermutationsII {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        List<List<Integer>> list = new Solution3().permute(nums);

        System.out.println(list.toString());
    }
}
