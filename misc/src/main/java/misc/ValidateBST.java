package misc;

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
}

/*
    Elements to the left of root should be strictly smaller
    Elements to the right of root should be strictly greater
 */
public class ValidateBST {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        TreeNode left = new TreeNode(Integer.MIN_VALUE);
        root.left = left;
        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST(TreeNode root) {

        return isValidBST(root, null, null);
    }

    public static boolean isValidBST(TreeNode root, Integer min, Integer max) {

        if(root == null) return true;
        if((min != null && root.val <= min) || (max != null && root.val >= max)) return false;
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
