/*
 * Purpose: Data Structures and Algorithms Lab 6 Problem 0
 * Status: Complete and thoroughly tested
 * Last Updated: 10/11/19
 * Submitted: 10/11/19
 * Comment: Test suite and sample runs attached
 * @author Dylan Ott
 * @version 2019.10.11
 */

public class QueueRAB<T> implements QueueInterface<T> {

    protected int front = 0;
    protected int back = 0;
    protected int numItems = 0;

    protected T[] items;

    public QueueRAB()
    {
        items = (T[]) new Object[3];

    }
    @Override
    public boolean isEmpty() {
        return (numItems == 0);
    }


    @Override
    public void enqueue(Object newItem) throws QueueException {
        if(numItems == items.length)
        {
            resize();
        }

        items[back] = (T)newItem;
        back = (back + 1)%items.length;

        numItems++;

    }


    @Override
    public T dequeue() throws QueueException {
        if(numItems == 0)
        {
            return null;
        }
        else
        {
            Object item = items[front];

            items[front] = null;

            front = (front + 1)%items.length;
            numItems--;
            return (T)item;
        }

    }


    @Override
    public void dequeueAll() {
        items = (T[]) new Object[3];
        front = 0;
        back = 0;
        numItems = 0;
    }


    @Override
    public T peek() throws QueueException {
        if(numItems == 0)
        {
            throw new QueueException("QueueException on peek");
        }
        else
        {
            return items[front];
        }
    }

    public String toString()
    {
        StringBuilder resultString = new StringBuilder();

        for(int i = 0; i < numItems; i++)
        {
            resultString.append(items[((front + i)%items.length)].toString() + "\n");
        }

        return resultString.toString();
    }

    protected void resize()
    {
        T[] newArr = (T[]) new Object[numItems*2];

        for(int i = 0; i < numItems ; i++)
        {
            newArr[i] = items[(front + i)%items.length];
        }

        front = 0;
        back = numItems;

        items = newArr;
    }
}
