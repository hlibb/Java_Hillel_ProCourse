package lesson10;

public interface HomemadeList {

    Integer get(int index);

    void set(int index, Integer item);

    int size();

    boolean isEmpty();

    boolean contains(Integer item);

    Integer[] toArray();

    void add(Integer item);

    void add(int index, Integer item);

    void remove(int index);

    void remove(Integer index);

    void addAll(Integer[] arr);

    void addAll(int index, Integer[] arr);

    void clear();

    int indexOf(Integer item);

    int lastIndexOf(Integer item);

    void removeAll(Integer[] arr);
}