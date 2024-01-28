package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadraticWithCalipers implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     *
     * @param a a sorted array.
     */
    public ThreeSumQuadraticWithCalipers(int[] a) {
        this.a = a;
        length = a.length;
    }

    /**
     * Get an array or Triple containing all of those triples for which sum is zero.
     *
     * @return a Triple[].
     */
    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        Collections.sort(triples); // ???
        for (int i = 0; i < length - 2; i++)
            triples.addAll(calipers(a, i, Triple::sum));
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a set of candidate Triples such that the first index is the given value i.
     * Any candidate triple is added to the result if it yields zero when passed into function.
     *
     * @param a        a sorted array of ints.
     * @param i        the index of the first element of resulting triples.
     * @param function a function which takes a triple and returns a value which will be compared with zero.
     * @return a List of Triples.
     */
    public static List<Triple> calipers(int[] a, int i, Function<Triple, Integer> function) {
        List<Triple> triples = new ArrayList<>();
        int left=0;
        int right=a.length-1;
        if(left==i){
            left++;
        }else if(right==i){
            right--;
        }
        while(right< a.length && left<right){
            int sum = a[left]+a[right];
            Triple t =new Triple(a[left],a[i],a[right]);
            if(function.apply(t)==0){

                triples.add(t);
                left++;
                right--;
            } else if(sum<a[i] && left!=i){
                left++;
            }else if(sum>a[i] && right!=i){
                right--;
            }else{
                left++;
                right--;
            }
            }
//        while()
//        for(int itr=0;itr<mid;itr++){
//            if(a[itr]==a[itr+1] && itr<mid-1){
//                itr++;
//            }
//            for(int j=a.length-1;j>mid;j--){
//                if(a[j]==a[j-1] && j>mid+1){
//                    j++;
//                }
//                Triple t =new Triple(a[itr],a[mid],a[j]);
//                if(function.apply(t)==0){
//                    triples.add(t);
//                }
//            }
//        }
        return  triples;
        // TO BE IMPLEMENTED  : use function to qualify triples and to navigate otherwise.
    }

    private final int[] a;
    private final int length;
}