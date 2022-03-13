import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider()
    {
        return Stream.of(
                arguments(new int[]{1,2,3}, new int[]{1,2,3}),
                arguments(new int[]{3,1,2}, new int[]{1,2,3}),
                arguments(new int[]{-3,-1,-2,5}, new int[]{-3,-2,-1,5}),
                arguments(new int[]{3,-2,-5,-1,2}, new int[]{-5,-2,-1,2,3}),
                arguments(new int[]{-3,1,11,0,9,3}, new int[]{-3,0,1,3,9,11})
        );
    }

    @ParameterizedTest(name="#{index} - argv={0}, {1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i=0; i<random_array.length; i++) {
            pq.add(random_array[i]);
        }

        int[] result = new int[random_array.length];
        for(int i=0; i<random_array.length; i++) {
            result[i] = (int)pq.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void exception_NullPointerException()
    {
        Exception exception = assertThrows(NullPointerException.class,()->{
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
            pq = null;
            Object[] arr = pq.toArray();
        });
    }

    @Test
    public void exception_ArrayStoreException()
    {
        Exception exception = assertThrows(ArrayStoreException.class,()->{
            PriorityQueue<String> pq = new PriorityQueue<String>();
            pq.add("123");
            Integer[] arr1 = new Integer[5];
            Integer[] arr2 = pq.toArray(arr1);
        });
    }
    @Test
    public void exception_ClassCastException()
    {
        Exception exception = assertThrows(ClassCastException.class,()->{
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>();
            int arr[] = new int[5];
            pq.add(arr);
        });
    }

}