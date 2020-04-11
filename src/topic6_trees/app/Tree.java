package topic6_trees.app;

public interface Tree<T> {

    void insert(T data);
    void traversal();
    T getMax();
    T getMin();
    boolean search(T data);

}
