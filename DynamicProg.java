package com.company;

import java.util.ArrayList;
import java.util.List;

public class DynamicProg implements Algorithm {
    Knapsack knapsack;
    List<Item> items;

    public DynamicProg(Knapsack knapsack, List<Item> items) {
        this.knapsack = knapsack;
        this.items = items;
    }

    public void rezolva() {
        int[][] dp = new int[items.size() + 1][(int) knapsack.getCapacity() + 1];
        for (int i = 1; i <= items.size(); ++i) {
            for (int w = 1; w <= (int) knapsack.getCapacity(); ++w) {

                int faraObiectCurent = w - (int) items.get(i - 1).getWeight();
                if (faraObiectCurent >= 0) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][faraObiectCurent] + (int) items.get(i - 1).getValue());
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        /*                     //AFISARE MATRICE DP
        for(int i=1;i<=items.size();++i){
            for(int w=1;w<=knapsack.getCapacity();++w){
                System.out.print(dp[i][w]+" ");
            }
            System.out.println(" ");
        }
        */
        int i = items.size();
        int j = (int) knapsack.getCapacity();
        while (i != 0) {
            if (dp[i][j] == dp[i - 1][j]) {
                i = i - 1;
            } else {
                knapsack.addItem(items.get(i - 1));
                j = j - (int) items.get(i - 1).getWeight();
                i = i - 1;
            }
        }
    }
}
