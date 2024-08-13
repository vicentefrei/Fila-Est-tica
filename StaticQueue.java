public class StaticQueue<T> {
    private int top = -1;
    private int base = 0;
    private T[] data;

    @SuppressWarnings("unchecked")
    public StaticQueue(int size) {
        data = (T[]) new Object[size];
    }

    public void add(T element) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        if (top == -1) {
            top = 0; // Iniciar a fila corretamente
            base = 0;
        } else {
            top = (top + 1) % data.length;
        }
        data[top] = element;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T element = data[base];
        if (base == top) { // Se o último elemento foi removido
            base = 0;
            top = -1;
        } else {
            base = (base + 1) % data.length;
        }
        return element;
    }

    public void clear() {
        top = -1;
        base = 0;
        data = (T[]) new Object[data.length];  // Resetar o array
    }

    public boolean isFull() {
        return (top + 1) % data.length == base && top != -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        StaticQueue<Integer> queue = new StaticQueue<>(5);

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println(queue.remove()); // 1
        System.out.println(queue.remove()); // 2

        queue.add(6);
        queue.add(7);

        System.out.println(queue.remove()); // 3
        System.out.println(queue.remove()); // 4
        System.out.println(queue.remove()); // 5
        System.out.println(queue.remove()); // 6
    }
}
