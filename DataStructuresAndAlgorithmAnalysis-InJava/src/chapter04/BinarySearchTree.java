package chapter04;


import java.util.Comparator;

/**
 * @author xiaoxiya
 * @date 2020/3/29 19:24
 * @describe 二叉查找树:对于树中的每个节点X，它的左子树中所有的项的值小于X中的项，右子树中所有的项大于X中的项
 */
public class BinarySearchTree<T extends Comparable<? extends T>> {
    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
        BinaryNode(T theElement) {
            this(theElement, null, null);
        }
        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
        }
    }

    private BinaryNode<T> root;
    private Comparator<? super T> cmp;

    public BinarySearchTree(){
        root = null;
    }
    public BinarySearchTree(Comparator<? super T> c){
        this.root = null; this.cmp = c;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpyt() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        if (isEmpyt()) {
            throw new NullPointerException();
        }
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpyt()) {
            throw new NullPointerException();
        }
        return findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }
    public void remove(T x) {
        root = remove(x, root);
    }
    public void printTree() {

    }

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    private int myCompare(T lhs, T rhs) {
        if (cmp != null) {
            return cmp.compare(lhs, rhs);
        } else {
            return ((Comparable) lhs).compareTo(rhs);
        }
    }


    /**
     * 递归调用查找最小值，只需要找到左子树的最左节点即可
     * @param t
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> t) {
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
    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return t;
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if(t == null) {
            return t;
        }
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
            //删除的节点有两个子节点
            //删除策略：用右子树的最小数据代替该节点的数据，然后递归的删除那个节点最小节点
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
            //删除的节点有一个子节点
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

}
