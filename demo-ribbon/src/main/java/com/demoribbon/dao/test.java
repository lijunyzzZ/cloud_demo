package com.demoribbon.dao;
import java.util.*;
import sun.reflect.generics.tree.Tree;

public class test {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static String getPermutation(int n, int k) {
        if (n == 1) {
            return "1";
        }
        //新建一个长度为n的数组每个数组元素就表第i个使用没有。
        boolean[] visited = new boolean[n + 1];
        int[] dp = new int[n + 1];
        //dp数组存放阶乘。
        dp[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = dp[i - 1] * i;
        }
        return perm(n, k, visited, dp);
    }

    public static String perm(int n, int k, boolean[] visited, int[] dp) {
        if (n == 0) {
            return "";
        }
        int pos = 1;
        int cut = dp[n - 1];
        while (k > cut) {
            k -= cut;
            pos++;
        }
        String s = Integer.toString(getVisited(visited, pos));
        return s += perm(n - 1, k, visited, dp);
    }

    public static int getVisited(boolean[] visited, int index) {
        int o = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                o++;
            }
            if (o == index) {
                visited[i] = true;
                return i + 1;
            }
        }
        return 0;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        //收尾连接成环。
        cur.next = head;
        //求出
        k = k % len;
        ListNode l = head;
        while (l.next != null && k != 0) {
            if (k - 1 != 0) {
                l = l.next;
            }
            k--;
        }
        ListNode res = l.next;
        l.next = null;
        return res;
    }

    public int uniquePaths(int m, int n) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;

        return path(0, 0, res, visited);
    }

    public int path(int i, int j, int res, boolean[][] visited) {
        return 0;
//        if(i == visited.length-1 && j == visited[0].length-1) {
//            return 1;
//        }
//        if(i == visited.length || j == visited[0].length|| i<0 ||j<0){
//            return 0 ;
//        }
//        if (!visited[i][j]){
//            visited[i][j] = true;
//            int res = path(i+1,j,res,visited)+path(i-1,j,res,visited)+path(i,j+1,res,visited)+path(i,j-1,res,visited);
//            visited[i][j] = false;
//        }
//        return res ;
    }

    public static boolean exist(char[][] board, String word) {
        int[] map = new int[128];
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[board[i][j] - '0']++;
            }
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (path(0, visited, word, board, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean path(int len, boolean[][] visited, String word, char[][] board, int i, int j) {
        if (len == word.length()) {
            return true;
        }
        if (i < 0 || j > 0) {
            return false;
        }
        if (i < board.length && j < board[0].length && board[i][j] == word.charAt(len) && !visited[i][j]) {
            visited[i][j] = true;
            boolean flag = path(len + 1, visited, word, board, i + 1, j) ||
                    path(len + 1, visited, word, board, i - 1, j) ||
                    path(len + 1, visited, word, board, i, j + 1) ||
                    path(len + 1, visited, word, board, i, j - 1);
            visited[i][j] = false;

            return flag;
        }
        return false;
    }

    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < height.length; i++) {
            int p = i - 1;

            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode leftNext = left;
        ListNode rightNext = right;
        ListNode cur = head;
        while (cur != null) {

            if (cur.val < x) {
                System.out.print(cur.val);
                leftNext.next = cur;

                leftNext = cur;
            } else {
                rightNext.next = cur;
                rightNext = cur;
            }
            cur = cur.next;
        }
        leftNext.next = right.next;
        return left.next;

    }


    public static int maxPathSum(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res, 0);
        int max = 0;
        for (int i = 0; i < res.size(); i++) {
            max = Math.max(res.get(i), max);
        }
        return max;
    }

    private static void helper(TreeNode root, List<Integer> cur, int sum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            cur.add(sum);
        }
        helper(root.left, cur, sum);
        helper(root.right, cur, sum);
        helper(root.left, cur, 0);
        helper(root.right, cur, 0);
    }

    public static void main(String[] args) {
//        getPermutation(3, 3);
//        ListNode a = new ListNode(1);
//        ListNode b = new ListNode(2);
//        ListNode c = new ListNode(3);
//        ListNode d = new ListNode(4);
//        ListNode e = new ListNode(5);
//        a.next = b;
//        b.next = c;
//        c.next = d;
//        d.next = e;
//        e.next = null;
//        rotateRight(a,1);
//        uniquePaths(3,2);
//        char[][] a = {
//                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
////        exist(a, "ABCCED");
//        int[] a = {2,1,5,6,2,3};
//        largestRectangleArea(a);
//        reverseBetween(a, 2, 4);

        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;
        maxPathSum(a);
    }
}
