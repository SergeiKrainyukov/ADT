public abstract class Queue<T> {

    // конструктор
    // постусловие: создана пустая очередь
    public Queue() {

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