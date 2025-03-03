/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.StatPack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Class InsertionSort.
 *
 * @param <X> the underlying comparable type.
 */
public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    public static void main(String[] args){
        final Random random = new Random();
        int n=1600;
        final Supplier<Integer[]> intsSupplier = () ->
        {
            Integer[] result = (Integer[]) Array.newInstance(Integer.class, n);
            for (int i = 0; i < n; i++) result[i] = random.nextInt();
            return result;

        };

//        final Supplier<Integer[]> partiallyOrderedIntsSupplier = () ->
//        {
//            Integer[] result = (Integer[]) Array.newInstance(Integer.class, n);
//            for (int i = 0; i < n; i++) {
//                if (random.nextDouble() < 0.8) {
//                    result[i] = i;
//                } else {
//                    result[i] = random.nextInt();
//                }
//            }
//            return result;
//        };

//        final Supplier<Integer[]> reverseOrderedIntsSupplier = () ->
//        {
//            Integer[] result = (Integer[]) Array.newInstance(Integer.class, n);
//            for (int i = 0; i < n; i++) {
//                result[i] = n - i;
//            }
//            return result;
//        };



        final Config config = Config.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);

        final double t1 = new Benchmark_Timer<Integer[]>(
                helper.getDescription(),
                (xs)->Arrays.copyOf(xs,xs.length),
                (xs)->{
                    SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
                    sorter.sort(xs,0,xs.length);
                    },
                null
        ).runFromSupplier(intsSupplier, 100);

        System.out.println(t1);
    }
    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        int start=from;
        int end=to;
        for(int i=start;i<end;i++){
            int j=i;
            while(j> start && helper.swapStableConditional(xs,j)){
                j--;
            }
//            for(int j=i;j>start;j--){
//                if(helper.compare(xs,i,j)<0){
//                    helper.swap(xs,i,j);
//                }
//                helper.swapStableConditional(xs,j);
//            }
        }
        // TO BE IMPLEMENTED 




//throw new RuntimeException("implementation missing");
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }
}