给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null&&t==null) return true;
        if(s==null||t==null) return false;
        
        return isSameTree(s,t)||isSubtree(s.left,t)||isSubtree(s.right,t);
    }
    private boolean isSameTree(TreeNode p,TreeNode q){
        if(p==null&&q==null) return true;
        if(p==null||q==null) return false;
        return p.val==q.val&&isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
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
    public boolean isBalanced(TreeNode root) {
        return isBalancedTree(root)>=0;
    }
    private int isBalancedTree(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=isBalancedTree(root.left);
        int right=isBalancedTree(root.right);
        if(left>=0&&right>=0&&Math.abs(left-right)<=1){
            return Math.max(left,right)+1;
        }
        return -1;
    }
}

给定一个二叉树，检查它是否是镜像对称的
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
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root,root);
    }
    private boolean isSymmetric(TreeNode p,TreeNode q){
        if(p==null&&q==null) return true;
        if(p==null||q==null) return false;
        return p.val==q.val&&isSymmetric(p.left,q.right)&&isSymmetric(p.right,q.left);
    }
}

给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        List<List<Integer>> res=new LinkedList<>();
        if(root==null) return res;
        queue.offer(root);;
        while(!queue.isEmpty()){
            List<Integer> row=new LinkedList<>();
            int size=queue.size();
            while(size--!=0){
                TreeNode cur=queue.poll();
                row.add(cur.val);
                if(cur.left!=null) queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
            }
            res.add(row);
        }
        return res;
    }
}

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> sp=new Stack<>();
        Stack<TreeNode> sq=new Stack<>();
        getPath(root,p,sp);
        getPath(root,q,sq);
        int lenp=sp.size();
        int lenq=sq.size();
        while(lenp!=lenq){
            if(lenp>lenq){
                sp.pop();
                lenp--;
            }else{
                sq.pop();
                lenq--;
            }
        }
        while(sp.peek()!=sq.peek()){
            sp.pop();
            sq.pop();
        }
        return sp.peek();
    }
    private boolean getPath(TreeNode root,TreeNode q,Stack<TreeNode> stack){
        if(root==null) return false;
        stack.push(root);
        if(stack.peek()==q) return true;
        if(getPath(root.left,q,stack)||getPath(root.right,q,stack)){
            return true;
        }
        stack.pop();
        return false;
    }

}

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root==p||root==q) return root;
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left!=null&&right!=null){
            return root;
        }
        if(left!=null){
            return left;
        }
        if(right!=null){
            return right;
        }
        return null;
    }
}


