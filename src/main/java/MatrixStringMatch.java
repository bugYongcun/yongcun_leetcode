import java.util.ArrayList;
import java.util.List;

public class MatrixStringMatch {
    private static boolean[][] visits;

    private static boolean dfs(char[][] matrix, int rows, int cols, int row, int col, String str, int count) {
        if (count == str.length()) //匹配完所有字符
            return true;

        if (row < 0 || row >= rows || col < 0 || col >= cols)
            return false;

        if (visits[row][col])
            return false;

        if (matrix[row][col] != str.charAt(count)) //不匹配
            return false;

        //匹配后，进入递归四方向搜索
        visits[row][col] = true; //当前节点设置为已经访问

        if (dfs(matrix, rows, cols, row - 1, col, str, count + 1))
            return true;

        if (dfs(matrix, rows, cols, row + 1, col, str, count + 1))
            return true;

        if (dfs(matrix, rows, cols, row, col - 1, str, count + 1))
            return true;

        if (dfs(matrix, rows, cols, row, col + 1, str, count + 1))
            return true;

        //关键点，搜索失败返回上一层时 需要改回节点没有被访问
        visits[row][col] = false;

        return false;
    }

    private static boolean isMatch(char[][] matrix, int rows, int cols, String str) {
        visits = new boolean[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                visits[i][j] = false;
            }

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                int count = 0;
                if (dfs(matrix, rows, cols, i, j, str, count))
                    return true;
            }
        return false;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 's'}, {'a', 'd', 'e', 'e'}};
        int rows = 3, cols = 4;
        String str1 = "bcced";
        String str2 = "abcb";

        System.out.println(isMatch(matrix, rows, cols, str1));
        System.out.println(isMatch(matrix, rows, cols, str2));
    }
}
