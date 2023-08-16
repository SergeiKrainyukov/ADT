public abstract class PowerSet<T> extends HashTable<T> {

    //Постусловие: создано новое пустое множество
    public PowerSet(int size) {
        super(size);
    }

    //Запросы:

    public abstract PowerSet<T> intersection(PowerSet<T> set2); // пересечение текущего множества и set2

    public abstract PowerSet<T> union(PowerSet<T> set2); // объединение текущего множества и set2

    public abstract PowerSet<T> difference(PowerSet<T> set2); // разница текущего множества и set2

    public abstract boolean isSubset(PowerSet<T> set2); // возвращает true, если set2 есть подмножество текущего множества, иначе false
}

//Реализация
class PowerSetImpl<T> extends PowerSet<T> {

    private static int putStatus;
    private static int removeStatus;

    public static final int PUT_NIL = 0; //put() еще не вызывалась
    public static final int PUT_OK = 1; //последняя put() отработала успешно
    public static final int PUT_ERROR = 2; //put() еще не вызывалась

    public static final int REMOVE_NIL = 0; //remove() еще не вызывалась
    public static final int REMOVE_OK = 1; //последняя remove() отработала успешно
    public static final int REMOVE_ERROR = 2; //remove() еще не вызывалась

    private final int size;

    public PowerSetImpl(int size) {
        super(size);
        this.size = size;
    }

    @Override
    public void put(T value) {
    }

    @Override
    public void remove(T value) {

    }

    @Override
    public boolean get(T value) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int getPutStatus() {
        return putStatus;
    }

    @Override
    public int getGetStatus() {
        return removeStatus;
    }

    @Override
    public PowerSet<T> intersection(PowerSet<T> set2) {
        return null;
    }

    @Override
    public PowerSet<T> union(PowerSet<T> set2) {
        return null;
    }

    @Override
    public PowerSet<T> difference(PowerSet<T> set2) {
        return null;
    }

    @Override
    public boolean isSubset(PowerSet<T> set2) {
        return false;
    }
}
