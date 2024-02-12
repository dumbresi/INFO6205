package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UnionFind_Client {

    public static void main(String[] args){
        int n=1000;
        System.out.println(count(n));

    }

    public static int count(int sites){
        int connections=0;
        Random random= new Random();
        UF_HWQUPC union= new UF_HWQUPC(sites);
        while (union.components()>1){
            int p= random.nextInt(sites);
            int q= random.nextInt(sites);
            if(p!=q){
                if(!union.isConnected(p,q)){
                    union.union(p,q);
                    connections++;
                }
            }
        }
        return connections;
    }
}
