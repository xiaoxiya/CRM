package chapter04;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author xiaoxiya
 * @date 2020/4/21 20:19
 * @describe 相关迭代器使用二叉查找树
 */
public class MyTreeSet<T extends Comparable<? extends T>>{
    private static class BinaryNode<T> {

        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
        BinaryNode<T> parent;

        BinaryNode(T element) {
            this(element, null, null, null);
        }

        public BinaryNode(T element, BinaryNode<T> lt, BinaryNode<T> rt, BinaryNode<T> pt) {
            this.element = element;
            this.left = lt;
            this.right = rt;
            this.parent = pt;
        }
    }

    private BinaryNode<T> root;
    int modCount = 0;

    public MyTreeSet() {
        root = null;
    }
    public void makeEmpty() {
        modCount++;
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }
    public T finMin() throws UnderflowException{
        if (isEmpty()) {
            throw new UnderflowException();
        } else {
            return finMin(root).element;
        }
    }

    public T finMax() throws UnderflowException{
        if (isEmpty()) {
            throw new UnderflowException();
        } else {
            return finMax(root).element;
        }
    }

    public void insert(T x) {
        root = insert(x, root, null);
    }
    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("empty tree");
        } else {
            printTree(root);
        }
    }
    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        int compareResult = ((Comparable<T>) x).compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    private void printTree(BinaryNode<T> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return t;
        }
        int compareResult = ((Comparable<T>) x).compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            // two children
            t.element = finMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            modCount++;
            BinaryNode<T> oneChild;
            oneChild = (t.left != null) ? t.left : t.right;
            oneChild.parent = t.parent;
            t = oneChild;
        }
        return t;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t, BinaryNode<T> pt) {
        if (t == null) {
            modCount++;
            return new BinaryNode<T>(x, null, null, pt);
        }
        int compareResult = ((Comparable<T>) x).compareTo(t.element);
        if (compareResult < 0) {
           t.left = insert(x, t.left, t);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right, t);
        } else {
            ;
        }
        return t;
    }

    private BinaryNode<T> finMin(BinaryNode<T> t) {
        if ( t == null ){
            return null;
        } else if (t.left == null) {
            return t;
        }
        return finMin(t.left);
    }

    private BinaryNode<T> finMax(BinaryNode<T> t) {
        if ( t == null ){
            return null;
        } else if (t.right == null) {
            return t;
        }
        return finMax(t.right);
    }


    public Iterator<T> iterator(){
        return new MyTreeSetIterator();
    }

    private class MyTreeSetIterator implements Iterator<T> {
        private BinaryNode<T> current = finMin(root);
        private BinaryNode<T> previous;
        private int expecteModCount = modCount;
        private boolean okToRemove = false;
        private boolean atEnd = false;


        @Override
        public boolean hasNext() {
            return !atEnd;
        }

        @Override
        public T next() {
            if (modCount != expecteModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = current.element;
            previous = current;
            //if there is a right child, next node is min in right subtree
            if (current.right != null) {
                current = finMin(current.right);
            } else {
                // else ,find ancestor that it is left of
                BinaryNode<T> child = current;
                current = current.parent;
                while(current != null && current.left != child) {
                    child = current;
                    current = current.parent;
                }
                if (current == null) {
                    atEnd = true;
                }
            }
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expecteModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyTreeSet.this.remove(previous.element);
            okToRemove = false;
        }
    }


}
