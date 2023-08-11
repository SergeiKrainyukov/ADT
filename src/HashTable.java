public abstract class HashTable<T> {

    //конструктор
    //Постусловие: создана новая пустая таблица указанного размера
    public HashTable(int sz) {

    }

    //команды

    //Предусловие: В таблице есть свободное место для значения
    //Постусловие: В таблицу добавлено новое значение
    public abstract void put(T value); //добавление значения в таблицу

    //Постусловие: Из таблицы удалено переданное значение
    public abstract void remove(T value); // удаление значения из таблицы


    //запросы

    //Предусловие: таблица непустая
    public abstract int get(T value); //получение элемента из таблицы

    public abstract int getPutStatus(); //успешно; хэш-функция не нашла свободного места

    public abstract int getGetStatus(); //успешно; таблица пуста

}