package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * NOTE: The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadratic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadratic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++) triples.addAll(getTriples(i));
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a list of Triples such that the middle index is the given value j.
     *
     * @param j the index of the middle value.
     * @return a Triple such that
     */
    public List<Triple> getTriples(int j) {
        List<Triple> triples = new ArrayList<>();
        // TO BE IMPLEMENTED  : for each candidate, test if a[i] + a[j] + a[k] = 0.
        int left=0;
        int right=a.length-1;
//        System.out.println("mid:"+a[j]);
        while(left<j && right>j){
           int sum= a[left]+a[j]+a[right];
//            System.out.println("x:"+a[left]+",y:"+a[j]+",z:"+a[right]);
           if(sum==0){
               triples.add(new Triple(a[left],a[j],a[right]));
//               System.out.println("Tripple Added:"+"x:"+a[left]+",y:"+a[j]+",z:"+a[right]);
               left++;
               right--;
           }
           if(sum<0){
               left++;
//               if(left<j-1)left++;
//               else if(right>j+1) right--;
//               else{
//                   left++;
//                   right--;
//               }
           }
           if(sum>0){
               right--;
//               if(right>j+1)right--;
//               else if(left<j-1)left++;
//               else {
//                   left++;
//                   right--;
//               }
           }

        }
        return triples;
    }
//    [-40, -20, -10, 0, 5, 10, 30, 40]

    private final int[] a;
    private final int length;
}