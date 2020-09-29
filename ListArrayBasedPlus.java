/*
 * Purpose: DSA Project - RAB
 * Status: Complete and thoroughly tested
 * Last update: 12/03/19 (Chris Herras-Antig)
 * Submitted:  12/05/19
 * Comment: Reworked and resubmitted for lab REDO
 * @author Dylan Ott
 * @author Christopher Herras-Antig
 * @version 2019.03.12
 */
public class ListArrayBasedPlus<T> extends ListArrayBased<T> implements ListInterface<T> {

    public ListArrayBasedPlus()
    {
        super();
    }

    public void add(int index, T item)
    {
        if(numItems == items.length)
        {
            resize();
        }

        super.add(index, item);
    }
    
    // Needed for swap
    public void set(int index, T item) {
    	 if (index >= 0 && index < numItems)
         {
             items[index] = item;
         }
    }

    private void resize()
    {
        //Allocate larger arr memory
        T []newArr = (T[]) new Object[(items.length*2)];
        //copy all references from prior array into larger array
        for(int index = 0; index < items.length; index++)
        {
            newArr[index] = items[index];
        }

        //have items set to new arr reference
        items = newArr;
    }

    public void reverse()
    {
        T []tempArr = (T[]) new Object[numItems];
        for(int i = 0; i < numItems; i++)
        {
            for(int k = numItems - 1; k >= 0; k--)
            {
                tempArr[i] = items[k];
            }
        }

        items = tempArr;
    }

    public String toString()
    {
        StringBuilder resultString = new StringBuilder();
        for(int i = 0; i < numItems; i++)
        {
            resultString.append(items[i] + "\n"); //REDO FIXED
        }
        return resultString.toString();
    }
    
}
