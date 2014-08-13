/**
 * Description: This is the client class which  generates random arrays of bars and then sorts them 
 *              with the selected method. 
 *              Bar container is where the visual representation of arrays (bars) is stored.
 *              
 *              This bar container is updated by the update method. 
 *              
 *              int red is to decide which bar to color red in quick sort.
 *              
 *              For Bubble sort the thread delay is 100ms, since it is very painful to watch at slow speed.
 *              
 *              PS: If you stop the sorter in b/w then start it again with some other sorter, 
 *                  it may take a delayed start (depending on the state of the array and the algorithm).
 *              
 *              PPS: If you are using blueJ, and the start button does not work, please reset your virtual machine
 *              (Ctrl+Shift+R on the main window). (This shouldn't happen often).
 *              
 *              Anytime the start button does not work means that 
 *              the virtual machine needs reseting (Something's still running and I couldn't figure out what
 *              , sorry for the trouble).
 * 
 * Ashlesh Gawande 
 * Project 2, Version 6
 * 28 April 2014 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Client3 extends JFrame
{
    private JPanel panel = new JPanel();
    private JPanel barContainer = new JPanel();

    private int [] a = new int[25];               //declaring array here so that it can be used anywhere else.
    private Bar [] bars = new Bar[a.length];      

    private String [] sorts = {"Bubble", "Selection", "Insertion", "Shell", "Merge", "Heap", "Quick"};
    private JComboBox box = new JComboBox(sorts);

    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");
    private JButton reset = new JButton("Reset");
    private JButton randomColor = new JButton("Random Color");

    private UpdateBarContainer currentThread;

    int red = 0;    //used in marking the pivot in quicksort.

    private Color currentColor = Color.BLACK; 

    public Client3()
    {
        setSize(800, 600);
        setLocationRelativeTo(null);   //centering the frame
        setResizable(false);
        
        panel.add(box);

        ButtonHandler bh = new ButtonHandler();
        start.addActionListener(bh);
        stop.addActionListener(bh);
        reset.addActionListener(bh);
        randomColor.addActionListener(bh);

        populateArray();

        barContainer.setLayout(new GridLayout(1, a.length));
        barContainer.setBorder(BorderFactory.createDashedBorder(Color.BLACK)) ;
        update(a);

        panel.add(barContainer);
        panel.add(start);
        panel.add(stop);
        panel.add(reset);
        panel.add(randomColor);
        add(panel);
        setVisible(true);
    }

    //Swing Worker to update bar container when needed.
    private class UpdateBarContainer extends SwingWorker<Void, Void>
    {
        protected Void doInBackground()
        {    
            if(box.getSelectedItem().equals("Bubble"))
            {
                bubbleSort(); 
            }

            else if(box.getSelectedItem().equals("Selection"))
            {
                selectionSort(); 
            }

            else if(box.getSelectedItem().equals("Insertion"))
            {
                insertionSort(); 
            }

            else if(box.getSelectedItem().equals("Shell"))
            {
                shellSort();
            }            

            else if(box.getSelectedItem().equals("Merge"))
            {
                mergeSort();
            }
            else if(box.getSelectedItem().equals("Heap"))
            {
                heapSort(); 
            }          
            else
            {
                quickSort();
                red = 0;        //to make sure that the red colored bar goes away.
            }
            return null;
        }

        protected void process()
        {
            //The update method could be here, I can call publish from within the sorts and then put 
            //update method here. But for the code is more readable this way. 
        }
    }

    //The method updates the bar container.
    public void update(int [] a)
    {
        barContainer.removeAll();
        for(int i=0; i<bars.length; i++)
        {                      
            if(red!=0&&box.getSelectedItem().equals("Quick")&&i==red)
            {
                if(currentColor!=Color.RED)
                {
                    bars[i] = new Bar(a[i], Color.RED);  
                }
                else
                {
                    bars[i] = new Bar(a[i], Color.BLACK);
                }
            }
            else
            {
                bars[i] = new Bar(a[i], currentColor);
            }
            barContainer.add(bars[i]);                
        }
        barContainer.revalidate();
    }


    //Button Listener
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {            
            Object src = e.getSource(); // determine which button was pressed
            if (src == start) 
            {
                currentThread = new UpdateBarContainer();
                currentThread.execute();
                start.setEnabled(false);
                stop.setEnabled(true);
                randomColor.setEnabled(false);
            }
            else if(src == stop)
            {
                currentThread.cancel(true);
                start.setEnabled(true);
                stop.setEnabled(false);
                randomColor.setEnabled(true);
            }
            else if(src == reset)
            {   
                if(currentThread!=null)
                {
                    currentThread.cancel(true);
                }
                populateArray();
                currentColor = Color.BLACK; 
                update(a);
                start.setEnabled(true);
                stop.setEnabled(false);
                randomColor.setEnabled(true);
            }
            else if(src == randomColor)
            {
                currentColor = randomColor(); 
                update(a);
            }
        }
    }

    public void populateArray()
    {
        for(int i=0; i<a.length; i++)
        {
            a[i] = (int)(40*Math.random()+2); 
        }
    }    

    //Generates Random Color.
    public Color randomColor()
    {
        int s1 = (int)(255*Math.random());

        int s2 = (int)(255*Math.random());

        int s3 = (int)(255*Math.random());

        return  new Color(s1, s2, s3);
    }

    //A little bit optimized bubble sort.
    public void bubbleSort()
    {
        int one = a.length;       
        for (int j = 0; j < a.length; j++) 
        {
            // for each pass, compare elements 0 and 1, 1 and 2, 2 and 3, etc., swapping them
            //  if the value at the smaller index is bigger
            // each pass "bubbles up" the largest element to the end of the array
            for (int i = 0; i < one-1; i++) 
            {
                if (a[i] > a[i+1]) 
                {
                    int temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                }

                if(!currentThread.isCancelled())
                {
                    update(a);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) { }
                }
                else
                {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) { }
                }
            }
            one--;
        }

    }

    //Selection Sort.
    public void selectionSort()
    {

        for (int j = 0; j < a.length - 1; j++) {    // finding the min element a.length-1 times guarantees that the last element will be in place
            // find the minimum element in indices [j, a.length-1]
            int min = a[j];
            int minIndex = j;
            for (int i = j + 1; i < a.length; i++) {
                if (a[i] < min) {
                    min = a[i];
                    minIndex = i;
                }
            }

            if(!currentThread.isCancelled())
            {
                update(a);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) { }
            }
            else
            {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) { }
            }

            // swap that minimum element with index j
            int temp = a[j];
            a[j] = a[minIndex];
            a[minIndex] = temp;
        }        
    }

    //Insertion Sort
    public void insertionSort()
    {
        for (int i = 1; i < a.length; i++) {
            int numToInsert = a[i];

            // search for the correct insertion point, shifting
            //  array elements down as we go
            int j = i - 1;
            while (j >= 0 && numToInsert < a[j]) {
                a[j+1] = a[j];
                j--;
            }

            if(!currentThread.isCancelled())
            {
                update(a);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) { }
            }
            else
            {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) { }
            }

            // insert the item
            a[j+1] = numToInsert;
        }
    }

    //ShellSort
    public void shellSort()
    {

        int gapSize = a.length/2;

        while(gapSize>0)
        {
            for(int i=gapSize; i<a.length; i++)
            {
                int toInsert = a[i];

                int j = i - gapSize; 
                while(j>=0 && toInsert< a[j])
                {
                    a[j+gapSize] = a[j];
                    j -= gapSize; 
                }

                if(!currentThread.isCancelled())
                {
                    update(a);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) { }
                }
                else
                {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) { }
                }

                a[j+gapSize] = toInsert; 
            }

            //if gapSize==2, the gapSize is 1, else gapSize =gapSize/2.2
            gapSize = (gapSize==2) ? 1 : (int) (gapSize/2.2);
        }

    }

    //MergeSort
    public void mergeSort()
    {
        mergeSort(a, 0, a.length - 1, "");
    }

    // Recursively merge sorts the array a between indices start and end, inclusive.
    private void mergeSort(int[] a, int start, int end, String s)
    {
        // base case is when start/end are the same -- in this case there's only one element, so no action is needed
        if (end - start > 0) {
            // find the middle index
            int mid = (start + end) / 2;

            // sort each half
            mergeSort(a, start, mid, s + " ");
            mergeSort(a, mid + 1, end, s + " ");

            // temp array to hold the merged elements
            int[] temp = new int[end - start + 1];

            // i tracks the position in the left half, j tracks position in right half, k tracks position in merged array
            int i = start, j = mid + 1, k = 0;
            while (i <= mid && j <= end) {
                if (a[i] < a[j])
                    temp[k++] = a[i++];
                else
                    temp[k++] = a[j++];
            }

            while (i <= mid)
                temp[k++] = a[i++];

            while (j <= end)
                temp[k++] = a[j++];

            // copy temp elements back into array
            for (i = 0; i < temp.length; i++)
                a[start + i] = temp[i];

            if(!currentThread.isCancelled())
            {
                update(a);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) { }
            }
            else
            {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) { }
            }    
        }
    }

    //HeapSort
    public void heapSort()
    {
        for (int i = 1; i < a.length; i++) {
            int cIndex = i, pIndex = (cIndex - 1)/2;

            while (cIndex > 0 && a[cIndex] > a[pIndex]) {
                int temp = a[cIndex];
                a[cIndex] = a[pIndex];
                a[pIndex] = temp;

                cIndex = pIndex;
                pIndex = (cIndex - 1)/2;
            }
        }

        // now repeatedly remove the top element from the max-heap, placing it at the end of the array
        for (int i = 0; i < a.length; i++) {

            // swap root with last element in the array
            int topHeapElement = a[0];
            a[0] = a[a.length - 1 - i];
            a[a.length - 1 - i] = topHeapElement;

            // work our way back down the heap, swapping as necessary
            int pIndex = 0;
            while (true) {
                // check for left child
                int lIndex = 2*pIndex + 1;
                if (lIndex >= a.length - 1 - i)     // no left child
                    break;

                int cIndex = lIndex;    // index of the greater child (initially we assume it's the left)

                // check for right child, and see if it's greater than the left
                int rIndex = lIndex + 1;
                if (rIndex < a.length - 1 - i && a[rIndex] > a[lIndex])
                    cIndex = rIndex;

                // check if parent is less than greater child, swap if so
                if (a[pIndex] < a[cIndex]) {
                    int temp = a[cIndex];
                    a[cIndex] = a[pIndex];
                    a[pIndex] = temp;

                    pIndex = cIndex;
                } else
                    break;      // heapify is done!

                if(!currentThread.isCancelled())
                {
                    update(a);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) { }
                }
                else
                {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) { }
                }    
            }

            if(!currentThread.isCancelled())
            {
                update(a);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) { }
            }
            else
            {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) { }
            }    
        }
    }

    //QuickSort
    public void quickSort()
    {
        quickSort(a, 0, a.length - 1);
    }

    //Recursively quickSorts the array a between indices start and end, inclusive
    private void quickSort(int[] a, int start, int end)
    {
        if (start < end) {  // base case is when start is no longer < end (i.e., nothing to sort)
            int j = quickSortPartition(a, start, end);
            quickSort(a, start, j - 1);
            quickSort(a, j + 1, end);
        }
    }

    // Partitions the array a between indices start and end, inclusive.  We use the start index as the
    //  pivot element, and arrange all the array elements such that the ones <= pivot are on the left,
    //  and the ones > pivot are on the right.
    private int quickSortPartition(int[] a, int start, int end)
    {
        // pick a pivot
        int pivot = a[start];

        red = start; 

        int lower = start, upper = end;
        while (lower < upper) {
            // look for the first element (from the left) that's greater than the pivot
            while (lower < end) {
                if (a[lower] > pivot)
                    break;
                lower++;
            }

            // look for the first element (from the right) that's less than or equal to the pivot
            while (upper > start) {
                if (a[upper] <= pivot)
                    break;
                upper--;
            }

            // swap lower/upper indices if lower < upper
            if (lower < upper) {
                int temp = a[lower];
                a[lower] = a[upper];
                a[upper] = temp;
            }

            if(!currentThread.isCancelled())
            {
                update(a);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) { }
            }
            else
            {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) { }
            }    
        }

        // swap the pivot (at index start) with index upper
        int temp = a[start];
        a[start] = a[upper];
        a[upper] = temp;

        if(!currentThread.isCancelled())
        {
            update(a);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) { }
        }
        else
        {
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) { }
        }    

        return upper;
    }   

    public static void main(String[] args)
    {
        new Client3();
    }
}
