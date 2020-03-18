根据一棵树的前序遍历与中序遍历构造二叉树。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int index;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,inorder.length,map);
    }
    private TreeNode buildTree(int[] preorder,int[] inorder,int left,int right,
    Map<Integer,Integer> map){
        if(index>=inorder.length||left>=right){
            return null;
        }
        TreeNode root=new TreeNode(preorder[index]);
        int inorder_root=map.get(preorder[index++]);
        root.left=buildTree(preorder,inorder,left,inorder_root,map);
        root.right=buildTree(preorder,inorder,inorder_root+1,right,map);
        return  root;
    }
}

根据一棵树的中序遍历与后序遍历构造二叉树
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        index=inorder.length-1;
        return buildTree(inorder,postorder,0,inorder.length,map);
    }
    private TreeNode buildTree(int[] inorder,int[] postorder,int left,int right,
    Map<Integer,Integer> map){
        if(index<0||left>=right){
            return null;
        }
        TreeNode root=new TreeNode(postorder[index]);
        int inorder_root=map.get(postorder[index--]);
        root.right=buildTree(inorder,postorder,inorder_root+1,right,map);
        root.left=buildTree(inorder,postorder,left,inorder_root,map);
        return root;
    }

}

