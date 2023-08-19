import java.lang.reflect.Array;

public abstract class DynArray<T> {

    //Конструктор
    //Постусловие: создан пустой массив размером 16 элементов
    public DynArray() {

    }

    // команды

    // предусловие: i лежит в допустимых границах массива;
    // постусловие: значение элемента i изменено на value
    public abstract void put(int i, T value);

    // предусловие: i лежит в допустимых границах массива;
    // постусловие: перед элементом i добавлен
    // новый элемент с значением value;
    public abstract void putLeft(int i, T value);

    // предусловие: i лежит в допустимых границах массива;
    // постусловие: после элемента i добавлен
    // новый элемент с значением value;
    public abstract void putRight(int i, T value);

    //Постусловие: в конец массива добавлен новый элемент
    public abstract void append(T itm);

    //Предусловие: индекс больше либо равен нуля и меньше размера массива
    //Постусловие: из массива удален элемент, находившийся в заданной позиции
    public abstract void remove(int index);


    //Запросы:

    //Предусловие: индекс больше либо равен нуля и меньше размера массива
    public abstract T getItem(int index);

    public abstract int size(); // текущий размер массива

    // запросы статусов (возможные значения статусов)
    public abstract int get_put_status(); // успешно; индекс за пределами массива

    public abstract int get_put_left_status(); // успешно; индекс за пределами массива

    public abstract int get_put_right_status(); // успешно; индекс за пределами массива

    public abstract int get_remove_status(); // успешно; индекс за пределами массива

    public abstract int get_get_status(); // успешно; индекс за пределами массива

}


//реализация

class DynArrayImpl<T> extends DynArray<T> {

    private int putStatus;
    private int putLeftStatus;
    private int putRightStatus;
    private int removeStatus;
    private int getStatus;

    private static final int PUT_NIL = 0; //put() еще не вызывалась
    private static final int PUT_OK = 1; //последняя put() отработала успешно
    private static final int ERROR_INDEX_OUT_OF_BOUND = 2; //индекс за пределами массива

    private static final int PUT_LEFT_NIL = 0; //putLeft() еще не вызывалась
    private static final int PUT_LEFT_OK = 1; //последняя putLeft() отработала успешно

    private static final int PUT_RIGHT_NIL = 0; //putRight() еще не вызывалась
    private static final int PUT_RIGHT_OK = 1; //последняя putRight() отработала успешно

    private static final int REMOVE_NIL = 0; //remove() еще не вызывалась
    private static final int REMOVE_OK = 1; //последняя remove() отработала успешно

    private static final int GET_NIL = 0; //putLeft() еще не вызывалась
    private static final int GET_OK = 1; //последняя get() отработала успешно

    public T[] array;
    public int count;
    public int capacity;
    @SuppressWarnings({"rawtypes"})
    Class clazz;

    public DynArrayImpl(Class clz) {
        putStatus = PUT_NIL;
        putLeftStatus = PUT_LEFT_NIL;
        putRightStatus = PUT_RIGHT_NIL;
        removeStatus = REMOVE_NIL;
        getStatus = GET_NIL;

        clazz = clz;
        count = 0;
        makeArray(16);
    }

    private void makeArray(int new_capacity) {
        T[] newArray = (T[]) Array.newInstance(this.clazz, new_capacity);
        if (array != null) {
            System.arraycopy(array, 0, newArray, 0, new_capacity > capacity ? capacity : new_capacity);
        }
        array = newArray;
        capacity = new_capacity;
    }

    @Override
    public void put(int index, T itm) {
        if (index > capacity || index > count || index < 0) {
            putStatus = ERROR_INDEX_OUT_OF_BOUND;
            return;
        }
        if (count == capacity) makeArray(capacity * 2);
        if (index == count) {
            append(itm);
            putStatus = PUT_OK;
            return;
        }
        if (index < count) {
            moveArrayToEnd(index);
            array[index] = itm;
            count += 1;
        }
        putStatus = PUT_OK;
    }

    @Override
    public void putLeft(int index, T value) {
        put(index - 1, value);
    }

    @Override
    public void putRight(int index, T value) {
        put(index + 1, value);
    }

    @Override
    public void append(T itm) {
        if (count == capacity) {
            makeArray(capacity * 2);
        }
        for (int i = 0; i < capacity; i++) {
            if (array[i] != null) continue;
            array[i] = itm;
            count += 1;
            return;
        }
    }

    @Override
    public void remove(int index) {
        if (index >= capacity || index >= count || index < 0) {
            removeStatus = ERROR_INDEX_OUT_OF_BOUND;
            return;
        }
        count -= 1;
        array[index] = null;
        moveArrayToStart();
        if (count < capacity / 2 && capacity > 16) {
            makeArray((int) (capacity / 1.5 >= 16 ? capacity / 1.5 : 16));
        }
        removeStatus = REMOVE_OK;
    }

    @SuppressWarnings({"unchecked"})
    private void moveArrayToStart() {
        T[] newArray = (T[]) Array.newInstance(this.clazz, array.length);
        int newArrayIndex = 0;
        for (T t : array) {
            if (t == null) continue;
            newArray[newArrayIndex] = t;
            newArrayIndex += 1;
        }
        array = newArray;
    }

    @SuppressWarnings({"unchecked"})
    private void moveArrayToEnd(int fromIndex) {
        T[] newArray = (T[]) Array.newInstance(this.clazz, capacity);
        if (capacity - 1 - fromIndex >= 0)
            System.arraycopy(array, fromIndex, newArray, fromIndex + 1, capacity - 1 - fromIndex);
        if (fromIndex >= 0) System.arraycopy(array, 0, newArray, 0, fromIndex);
        array = newArray;
    }

    @Override
    public T getItem(int index) {
        if (!(index >= 0 && index < capacity)) {
            getStatus = ERROR_INDEX_OUT_OF_BOUND;
            return null;
        }
        getStatus = GET_OK;
        return array[index];
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public int get_put_status() {
        return putStatus;
    }

    @Override
    public int get_put_left_status() {
        return putLeftStatus;
    }

    @Override
    public int get_put_right_status() {
        return putRightStatus;
    }

    @Override
    public int get_remove_status() {
        return removeStatus;
    }

    @Override
    public int get_get_status() {
        return getStatus;
    }
}
