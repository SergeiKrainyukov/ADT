package Deque;

public abstract class ParentQueue<T> {
    // конструктор
    // постусловие: создана пустая очередь
    public ParentQueue() {

    }

    // команды

    //Постусловие: Добавлен новый элемент в хвост очереди
    public abstract void enqueue(T item);

    //Предусловие: очередь непустая
    //Постусловие: из головы удален элемент
    public abstract void dequeue();

    // запросы

    //Предусловие: очередь непустая
    public abstract T get(); // получить элемент из головы очереди;

    public abstract int size();  // текущий размер очереди

    public abstract int getGetStatus();//успешно или очередь пустая

    public abstract int getDequeueElementStatus(); //успешно или очередь пустая
}

abstract class DeQueue<T> extends ParentQueue<T> {

    //команды

    //Постусловие: Добавлен новый элемент в голову очереди
    public abstract void enqueueFront(T item);

    //Предусловие: очередь непустая
    //Постусловие: из хвоста удален элемент
    public abstract void dequeueTail();


    //запросы

    //Предусловие: очередь непустая
    public abstract T getTail(); // получить элемент из хвоста очереди;

    public abstract int getDequeueTailStatus(); //успешно или очередь пустая

    public abstract int getGetTailStatus(); //успешно или очередь пустая
}

abstract class Queue<T> extends ParentQueue<T> {

}
