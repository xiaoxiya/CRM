package chapter04;

/**
 * @author xiaoxiya
 * @date 2020/4/6 15:48
 * @describe 平衡二叉查找（搜索）树（AVL）实现
 */
public class AVLTree<T extends Comparable<? extends T>> {
    private static class AVLNode<T> {
        /**
         * the data in the node
         */
        T element;
        /**
         * left child
         */
        AVLNode<T> left;
        /**
         * right child
         */
        AVLNode<T> right;
        /**
         * Height of node
         * 节点的高度
         */
        int height;

        AVLNode(T theElement) {
            this(theElement, null, null);
        }

        AVLNode(T theElement, AVLNode<T> lt, AVLNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }
    }
    private AVLNode<T> root;
    /**
     * 节点左右子树允许相差的最大高度差
     */
    private static final int ALLOWED_IMBLANCE = 1;
    public AVLTree() {
        root = null;
    }

    /**
     * return the height of node t or -1 ,if null
     */
    private int height(AVLNode<T> t){
        return t == null ? -1 : t.height;
    }

    /**
     * 递归调用查找最小值，只需要找到左子树的最左节点即可
     * @param t
     * @return
     */
    private AVLNode<T> findMin(AVLNode<T> t) {
        if (t == null) {
            return  null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    /**
     * 非递归调用查找最大值，只需要到右子树的最右节点
     * @param t
     * @return
     */
    private AVLNode<T> findMax(AVLNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    private AVLNode<T> insert(T x, AVLNode<T> t) {
        if (t == null) {
            return new AVLNode<>(x, null, null);
        }
        int compareResult = ((Comparable) x).compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if ( compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return balance(t);
    }

    private AVLNode<T> remove(T x, AVLNode<T> t) {
        if (t == null) {
            return t;
        }
        int compareResult = ((Comparable) x).compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0 ) {
            t.right = remove(x, t.right);
        //删除节点包含两个子节点
        } else if (t.left != null && t.right != null) {
            t.element = this.findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    /**
     * assume t is either balanced or within one of being balanced
     * 假设t是平衡的，或者在一个平衡的范围内
     */
    private AVLNode<T> balance(AVLNode<T> t) {
        if (t == null) {
            return t;
        }
        //不平衡节点α（阿尔法）的左子树插入情况
        if (height(t.left) - height(t.right) > ALLOWED_IMBLANCE) {
            //不平衡节点α的左儿子的左子树进行一次插入
            if (height(t.left.left) >= height(t.left.right)) {
                t = rotateWithLeftChild(t);
             //不平衡节点α的左儿子的右子树进行一次插入
            } else {
                t = doubleWithLeftChild(t);
            }
        //不平衡节点α（阿尔法）的右子树插入情况
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBLANCE) {
            //不平衡节点α的右儿子的右子树进行一次插入
            if (height(t.right.right) >= height(t.right.left)) {
                t = rotateWithRightChild(t);
            //不平衡节点α的右儿子的左子树进行一次插入
            } else {
                t = doubleWithRightChild(t);
            }
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AVLNode<T> doubleWithRightChild(AVLNode<T> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    private AVLNode<T> rotateWithRightChild(AVLNode<T> k1) {
        AVLNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.right), height(k1.left)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k1;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights then return new root.
     * 双旋转
     * @param k3 插入后变得不平衡的节点
     * @return 对新根节点的引用
     */
    private AVLNode<T> doubleWithLeftChild(AVLNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     * 单旋转-左边的树变成右边的树
     * @param k2 不平衡的节点
     * @return 对新的根节点的引用
     */
    private AVLNode<T> rotateWithLeftChild(AVLNode<T> k2) {
        AVLNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * 直接双旋转
     * @param k3 不平衡节点
     * @return
     */
    private AVLNode<T> doubleRotateWithLeft(AVLNode<T> k3) {
        AVLNode<T> k1, k2;

        k1 = k3.left;
        k2 = k1.right;

        k1.right = k2.left;
        k3.left = k2.right;
        k2.left = k1;
        k2.right = k3;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k3.height = Math.max(height(k3.left), height(k3.right)) + 1;
        k2.height = Math.max(k1.height, k3.height) + 1;

        return k3;
    }

    private AVLNode<T> doubleRotateWithRight(AVLNode<T> k1) {
        AVLNode<T>  k2, k3;

        k3 = k1.right;
        k2 = k3.left;

        k3.left = k2.right;
        k1.right = k2.left;
        k2.left = k1;
        k2.right = k3;

        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k3.height = Math.max(height(k3.left), height(k3.right)) + 1;
        k2.height = Math.max(k1.height, k3.height) + 1;

        return k1;
    }

}
