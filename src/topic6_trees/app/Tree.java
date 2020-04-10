package topic6_trees.app;

public interface Tree<T> {

    void insert(T data);
    void traversal();
    boolean search(T data);

}
