package topic6_trees.app;

public interface Tree<T> {

    Node<T> getRoot();
    void insert(T data);
    void traversal();
    T getMax();
    T getMin();
    boolean search(T data);

}
