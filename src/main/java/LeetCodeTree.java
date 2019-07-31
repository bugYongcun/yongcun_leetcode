import java.util.*;

public class LeetCodeTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static int minDepth(TreeNode root) {
        int minDepth = 1;

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> indexList = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        indexList.add(1);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int index = indexList.removeFirst();
            int childrenNum = 0;
            size--;

            if (node.left != null) {
                queue.offer(node.left);
                indexList.add(2 * index);
                childrenNum++;
            }

            if (node.right != null) {
                queue.offer(node.right);
                indexList.add(2 * index + 1);
                childrenNum++;
            }

            if (childrenNum == 0) {
                return minDepth;
            }

            if (size == 0) {
                size = indexList.size();
                minDepth++;
            }
        }

        return minDepth;
    }


    private static int widthOfBinaryTree(TreeNode root) {
        int maxWidth = 1;

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> indexList = new LinkedList<>();
        int size = 1;
        queue.offer(root);
        indexList.add(1);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int index = indexList.removeFirst();
            size--;

            if (node.left != null) {
                queue.offer(node.left);
                indexList.add(2 * index);
            }

            if (node.right != null) {
                queue.offer(node.right);
                indexList.add(2 * index + 1);
            }

            if (size == 0) {
                if (indexList.size() >= 2) {
                    maxWidth = Math.max(maxWidth, indexList.getLast() - indexList.getFirst() + 1);
                }

                size = indexList.size();
            }

        }

        return maxWidth;
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        TreeNode last = root;
        TreeNode nextLast = null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        ArrayList<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
                nextLast = node.left;
            }

            if (node.right != null) {
                queue.offer(node.right);
                nextLast = node.right;
            }

            if (node == last) {
                result.add(list);
                last = nextLast;
                list = new ArrayList<>();
            }
        }

        return result;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();

            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }

            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        Set<TreeNode> ancestor = new HashSet<>();

        while (p != null) {
            ancestor.add(p);
            p = parent.get(p);
        }

        while (!ancestor.contains(q)) {
            q = parent.get(q);
        }

        return q;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        System.out.println(levelOrder(root));
    }
}
