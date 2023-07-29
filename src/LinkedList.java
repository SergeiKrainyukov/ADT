public abstract class LinkedList<T> {

    public static final int HEAD_NIL = 0; //putRight() или addTail() еще не вызывались
    public static final int HEAD_OK = 1; //последняя head() отработала нормально
    public static final int HEAD_ERR = 2; //список пуст

    public static final int TAIL_NIL = 0; //putRight() или addTail() еще не вызывались
    public static final int TAIL_OK = 1; //последняя tail() отработала нормально
    public static final int TAIL_ERR = 2; //список пуст

    public static final int RIGHT_NIL = 0; //putRight() или addTail() еще не вызывались
    public static final int RIGHT_OK = 1; //последняя right() отработала нормально
    public static final int RIGHT_ERR_EMPTY_LIST = 2; //список пуст
    public static final int RIGHT_ERR_LAST_ELEMENT = 3; //элемент, на котором курсор, последний в списке

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


    // Команды:

    //Предусловие: список непустой
    //Постусловие: курсор установлен на первый узел в списке
    public abstract void head();

    //Предусловие: список непустой
    //Постусловие: курсор установлен на последний узел в списке
    public abstract void tail();

    //Предусловие: список непустой
    //Предусловие: элемент, на котором курсор, непоследний
    //Постусловие: курсор сдвинут на один узел вправо
    public abstract void right();

    //Постусловие: справа от текущего узла добавлен новый узел, либо голова
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

    //Предусловие: список непустой
    //Предусловие: узел с заданным значением существует
    //Постусловие: курсор установлен на узел с искомым значением
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
