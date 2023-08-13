import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class NativeDictionary<T> {

    //Постусловие: создан новый пустой словарь указанного размера
    public NativeDictionary(int size, Class clazz) {

    }

    //команды

    //Постусловие: в словарь добавлено новое значение по указанному ключу
    public abstract void put(String key, T value);

    //запросы

    public abstract int hashFun(String key);

    public abstract boolean isKey(String key);

    //Предусловие: ключ существует в словаре
    public abstract T get(String key);

    public abstract int getGetStatus(); //успешно; ключа не существует
}

//Реализация
class NativeDictionaryImpl<T> extends NativeDictionary<T> {

    private int get_status; // статус команды get()

    private static final int GET_NIL = 0; //get() еще не вызывалась
    private static final int GET_OK = 1; //последний get() отработал успешно
    private static final int GET_ERR = 2; //ключа не существует

    public int size;
    public String[] slots;
    public T[] values;

    public NativeDictionaryImpl(int size, Class clazz) {
        super(size, clazz);
        this.size = size;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        get_status = GET_NIL;
    }

    @Override
    public void put(String key, T value) {
        int index = hashFun(key);
        slots[index] = key;
        values[index] = value;
    }

    @Override
    public int hashFun(String key) {
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
    public int getGetStatus() {
        return get_status;
    }
}
