package TwoWayList;

public abstract class ParentList<T> {

    public static final int HEAD_OK = 1; //последняя head() отработала нормально
    public static final int HEAD_ERR = 2; //список пуст

    public static final int TAIL_OK = 1; //последняя tail() отработала нормально
    public static final int TAIL_ERR = 2; //список пуст

    public static final int RIGHT_OK = 1; //последняя right() отработала нормально
    public static final int RIGHT_ERR_LAST_ELEMENT = 2; //элемент, на котором курсор, последний в списке

    public static final int PUT_LEFT_OK = 1; //последняя putLeft() отработала нормально
    public static final int PUT_LEFT_ERROR = 2; //список пуст

    public static final int REMOVE_OK = 1; //последняя remove() отработала нормально
    public static final int REMOVE_ERROR = 2; //список пуст

    public static final int REPLACE_OK = 1; //последняя replace() отработала нормально
    public static final int REPLACE_ERROR = 2; //список пуст

    public static final int FIND_OK = 1; //последняя find() отработала нормально
    public static final int FIND_ERROR = 2; //в списке не существует узла с заданным значением

    public static final int GET_OK = 1; //последняя get() отработала нормально
    public static final int GET_ERROR = 2; //список пуст

    // конструктор
    // постусловие: создан новый пустой список
    public ParentList() {

    }

    // Команды:

    //Предусловие: список непустой
    //Постусловие: курсор установлен на первый узел в списке
    public abstract void head();

    //Предусловие: список непустой
    //Постусловие: курсор установлен на последний узел в списке
    public abstract void tail();

    //Предусловие: элемент, на котором курсор, непоследний
    //Постусловие: курсор сдвинут на один узел вправо
    public abstract void right();

    // предусловие: список не пуст;
    // постусловие: следом за текущим узлом добавлен новый узел с заданным значением
    public abstract void putRight(T value);

    //Предусловие: список непустой
    //Постусловие: слева от текущего узла добавлен новый узел, либо голова
    public abstract void putLeft(T value);

    //Предусловие: список непустой
    //Постусловие: курсор смещен либо к правому, либо к левому соседу, либо список пуст
    public abstract void remove();

    //Постусловие: список пуст
    public abstract void clear();

    //Постусловие: последним элементом, либо головой списка является элемент с заданным значением
    public abstract void addTail(T value);

    //Предусловие: список непустой
    //Постусловие: значением текущего элемента списка является заданное значение
    public abstract void replace(T value);

    // постусловие: курсор установлен на следующий узел с искомым значением, если такой узел найден
    public abstract void find(T value);

    //Постусловие: из списка удалены все элементы с заданным значением
    public abstract void removeAll(T value);

    //Запросы:

    //Предусловие: список непустой
    public abstract T get();

    public abstract T size();

    public abstract boolean isHead();

    public abstract boolean isTail();

    public abstract boolean isValue();

    public abstract int get_head_status();

    public abstract int get_tail_status();

    public abstract int get_right_status();

    public abstract int get_put_left_status();

    public abstract int get_remove_status();

    public abstract int get_replace_status();

    public abstract int get_find_status();

    public abstract int get_get_status();
}

abstract class TwoWayList<T> extends ParentList<T> {
    public static final int LEFT_OK = 1; //последняя left() отработала нормально
    public static final int LEFT_ERROR = 2; //слева нет элементов

    //Предусловие: элемент, на котором курсор, не первый в списке
    //Постусловие: курсор сдвинут на один узел влево
    public abstract void left();

}

abstract class LinkedList<T> extends ParentList<T> {
}
