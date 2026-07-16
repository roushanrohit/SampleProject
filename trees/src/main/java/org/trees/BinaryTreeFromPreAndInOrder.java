package org.trees;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
}

public class BinaryTreeFromPreAndInOrder {

    public static void main(String[] args) {

        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(root.val);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootData = preorder[preStart];
        TreeNode root = new TreeNode(rootData);

        int inRootIndex = inStart;
        while(inorder[inRootIndex] != rootData){
            inRootIndex++;
        }

        int leftInStart = inStart;
        int leftInEnd = inRootIndex - 1;
        int rightInStart = inRootIndex + 1;
        int rightInEnd = inEnd;

        int leftPreStart = preStart + 1;
        int leftPreEnd = leftPreStart + (leftInEnd - leftInStart);
        int rightPreStart = leftPreEnd + 1;
        int rightPreEnd = preEnd;

        root.left = buildTree(preorder, inorder, leftPreStart, leftPreEnd, leftInStart, leftInEnd);
        root.right = buildTree(preorder, inorder, rightPreStart, rightPreEnd, rightInStart, rightInEnd);

        return root;
    }
}
