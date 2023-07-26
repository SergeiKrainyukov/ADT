import java.util.ArrayList;
import java.util.List;

public class BoundedStack<T> {

    private List<T> stack; // основное хранилище стека
    private int peek_status; // статус запроса peek()
    private int pop_status; // статус команды pop()
    private int push_status; // статус команды push()

    private static final int POP_NIL = 0;
    private static final int POP_OK = 1;
    private static final int POP_ERR = 2;

    private static final int PEEK_NIL = 0;
    private static final int PEEK_OK = 1;
    private static final int PEEK_ERR = 2;

    private static final int PUSH_NIL = 0;
    private static final int PUSH_OK = 1;
    private static final int PUSH_ERROR = 2;

    private static final int DEFAULT_MAX_SIZE = 32;

    private int maxSize = DEFAULT_MAX_SIZE;

    public BoundedStack() {
        clear();
    }

    public BoundedStack(int maxSize) {
        this.maxSize = maxSize;
        clear();
    }

    /// команды:

    // предусловие: стек заполнен менее максимально возможного заполнения
    // постусловие: в стек добавлено новое значение
    public void push(T value) {
        if (size() < maxSize) {
            stack.add(value);
            push_status = PUSH_OK;
        } else {
            push_status = PUSH_ERROR;
        }
    }

    // предусловие: стек не пустой;
    // постусловие: из стека удалён верхний элемент
    public void pop() {
        if (size() > 0) {
            stack.remove(size() - 1);
            pop_status = POP_OK;
        } else
            pop_status = POP_ERR;
    }

    // постусловие: из стека удалятся все значения
    public void clear() {

        stack = new ArrayList<>(maxSize);

        // начальные статусы для предусловий peek() и pop()
        peek_status = PEEK_NIL;
        pop_status = POP_NIL;
        push_status = PUSH_NIL;
    }

    /// запросы:

    // предусловие: стек не пустой
    public T peek() {
        T result;
        if (size() > 0) {
            result = stack.get(stack.size() - 1);
            peek_status = PEEK_OK;
        } else {
            result = null;
            peek_status = PEEK_ERR;
        }
        return result;
    }

    public int size() {
        return stack.size();
    }

    /// запросы статусов:

    public int get_pop_status() {
        return pop_status;
    }

    public int get_peek_status() {
        return peek_status;
    }

    public int get_push_status() {
        return push_status;
    }
}