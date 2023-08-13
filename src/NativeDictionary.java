import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class NativeDictionary<T> {

    //Постусловие: создан новый пустой словарь указанного размера
    public NativeDictionary(int size, Class clazz) {

    }

    //команды

    // постусловие: в массив добавлена новая пара ключ-значение если данный ключ отсутствовал;
    // в противном случае обновлено значение для соответствующего ключа
    public abstract void put(String key, T value);

    // предусловие: ключ key присутствует в массиве
    // постусловие: ключ удаляется вместе со своим значением
    public abstract void remove(String key);

    //запросы

    // данный запрос требуется отдельно,
    // чтобы не использовать вместо него
    // второстепенный запрос проверки статуса запроса get
    public abstract boolean isKey(String key);

    //Предусловие: ключ существует в словаре
    public abstract T get(String key);

    public abstract int size(); // текущий размер массива

    public abstract int getGetStatus(); // успешно; ключа не существует

    // запросы статусов (возможные значения статусов)
    public abstract int getPutStatus(); // успешно добавлен новый ключ и значение; успешно обновлено значение существующего ключа

    public abstract int getRemoveStatus(); // успешно; ключ отсутствует
}

//Реализация
class NativeDictionaryImpl<T> extends NativeDictionary<T> {

    private int get_status; // статус команды get()
    private int put_status; // статус команды put()
    private int remove_status; // статус команды remove()

    private static final int GET_NIL = 0; //get() еще не вызывалась
    private static final int GET_OK = 1; //последний get() отработал успешно
    private static final int GET_ERR = 2; //ключа не существует

    private static final int PUT_NIL = 0; //put() еще не вызывалась
    private static final int PUT_EMPTY = 1; //добавлена новая пара ключ-значение
    private static final int PUT_REPLACE = 2; //обновлено значение существующего ключа

    private static final int REMOVE_NIL = 0; //remove() еще не вызывалась
    private static final int REMOVE_OK = 1; //последний remove() отработал успешно
    private static final int REMOVE_ERR = 2; //ключа не существует

    private final int size;
    private final String[] slots;
    private final T[] values;

    public NativeDictionaryImpl(int size, Class clazz) {
        super(size, clazz);
        this.size = size;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        get_status = GET_NIL;
        put_status = PUT_NIL;
        remove_status = REMOVE_NIL;
    }

    @Override
    public void put(String key, T value) {
        int index = hashFun(key);
        slots[index] = key;
        if (values[index] != null) {
            values[index] = value;
            put_status = PUT_REPLACE;
            return;
        }
        put_status = PUT_EMPTY;
        values[index] = value;
    }

    @Override
    public void remove(String key) {
        if (Arrays.stream(slots).anyMatch(slot -> slot != null && slot.equals(key))) {
            int index = hashFun(key);
            slots[index] = null;
            values[index] = null;
            remove_status = REMOVE_OK;
            return;
        }
        remove_status = REMOVE_ERR;
    }

    private int hashFun(String key) {
        int sum = 0;
        byte[] bytes = key.getBytes();
        for (byte aByte : bytes) {
            sum += aByte;
        }
        return sum % size;
    }

    @Override
    public boolean isKey(String key) {
        return Arrays.stream(slots).anyMatch(slot -> slot != null && slot.equals(key));
    }

    @Override
    public T get(String key) {
        if (!isKey(key)) {
            get_status = GET_ERR;
            return null;
        }
        get_status = GET_OK;
        return values[hashFun(key)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getGetStatus() {
        return get_status;
    }

    @Override
    public int getPutStatus() {
        return put_status;
    }

    @Override
    public int getRemoveStatus() {
        return remove_status;
    }
}
