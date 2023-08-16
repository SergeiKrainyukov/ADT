public abstract class HashTable<T> {

    //конструктор
    //Постусловие: создана новая пустая таблица указанного размера
    public HashTable(int sz) {

    }

    //команды

    //Предусловие: В таблице есть свободное место для значения
    //Постусловие: В таблицу добавлено новое значение
    public abstract void put(T value); //добавление значения в таблицу

    //Предусловие: в таблице имеется значение value
    //Постусловие: Из таблицы удалено переданное значение
    public abstract void remove(T value); // удаление значения из таблицы


    //запросы

    public abstract boolean get(T value); //содержится ли значение в таблице

    public abstract int size(); // количество элементов в таблице

    public abstract int getPutStatus(); //успешно; хэш-функция не нашла свободного места

    public abstract int getGetStatus(); //успешно; таблица пуста

}