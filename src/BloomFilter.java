public abstract class BloomFilter<T> {

    //Конструктор
    //Постусловие: создан битовый массив длиной length
    public BloomFilter(int length) {
    }

    //Команды:

    //Постусловие: в фильтр добавлено новое значение
    public abstract void add(T value);

    //Запросы:
    public abstract boolean isValue(T value); // проверка, имеется ли значение в фильтре

    public abstract int size(); //размер фильтра

}

class BloomFilterImpl extends BloomFilter<String> {

    public int filter_len;
    private int bitInt;

    public BloomFilterImpl(int length) {
        super(length);
        filter_len = length;
        bitInt = 0;
    }

    @Override
    public void add(String value) {
        int index1 = hash1(value);
        int index2 = hash2(value);
        bitInt |= (1 << index1);
        bitInt |= (1 << index2);
    }

    @Override
    public boolean isValue(String value) {
        int index1 = hash1(value);
        int index2 = hash2(value);

        return (bitInt & (1 << index1)) != 0 && (bitInt & (1 << index2)) != 0;
    }

    @Override
    public int size() {
        return filter_len;
    }

    private int hash1(String str1) {
        int result = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            result = (result * 17 + code) % filter_len;
        }
        return result;
    }

    private int hash2(String str1) {
        int result = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            result = (result * 223 + code) % filter_len;
        }
        return result;
    }
}
