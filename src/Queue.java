public abstract class Queue<T> {

    //Постусловие: Добавлен новый элемент в хвост очереди
    public abstract void enqueue(T item);

    //Предусловие: очередь непустая
    public abstract T getElement();

    //Предусловие: очередь непустая
    //Постусловие: из головы удален элемент
    public abstract void removeElement();

    public abstract int size();

    public abstract int getGetElementStatus();//успешно или очередь пустая
    public abstract int getRemoveElementStatus(); //успешно или очередь пустая

}