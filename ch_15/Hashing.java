public interface Hashing {
    void add(Object o) throws HashTableOverflowException; 
    boolean contains(Object o);
}