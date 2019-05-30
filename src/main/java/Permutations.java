import java.util.ArrayList;
import java.util.List;

class Solution2 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, new ArrayList<>(), nums);

        return result;
    }

    private void backtracking(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int num : nums) {
                if (tempList.contains(num))
                    continue;
                tempList.add(num);
                backtracking(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> list = new Solution2().permute(nums);

        System.out.println(list.toString());
    }
}
