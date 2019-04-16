package com.company;

import sun.nio.cs.CharsetMapping;

import javax.smartcardio.CardPermission;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public  final class Ranges {



    // Hand kombinációk prímszámait csoportosítva legenerálja, ez segít a rangek megadásában handId-k alapján
    public static void rangeHelper(){
        int number = 0;
        int container =0;
        String first = "";
        String second = "";
        HashMap<String,ArrayList<Integer>> combos = new HashMap<String,ArrayList<Integer>>();

        for(int i= 0; i < Constants.cardPrimes.length; i++){
            for(int j = Constants.cardPrimes.length-1; j > i; j--){
                int mod1 = (i+4)/4;
                int mod2 = (j+4)/4;
                switch (mod1){
                    case 1:
                        first = "2";
                        break;
                    case 2:
                        first = "3";
                    break;
                    case 3:
                        first = "4";
                        break;
                    case 4:
                        first = "5";
                        break;
                    case 5:
                        first = "6";
                        break;
                    case 6:
                        first = "7";
                        break;
                    case 7:
                        first = "8";
                        break;
                    case 8:
                        first = "9";
                        break;
                    case 9:
                        first = "10";
                        break;
                    case 10:
                        first = "J";
                        break;
                    case 11:
                        first = "Q";
                        break;
                    case 12:
                        first = "K";
                        break;
                    case 13:
                        first = "A";
                }

                switch (mod2){
                    case 1:
                        second = "2";
                        break;
                    case 2:
                        second = "3";
                        break;
                    case 3:
                        second = "4";
                        break;
                    case 4:
                        second = "5";
                        break;
                    case 5:
                        second = "6";
                        break;
                    case 6:
                        second = "7";
                        break;
                    case 7:
                        second = "8";
                        break;
                    case 8:
                        second = "9";
                        break;
                    case 9:
                        second = "10";
                        break;
                    case 10:
                        second = "J";
                        break;
                    case 11:
                        second = "Q";
                        break;
                    case 12:
                        second = "K";
                        break;
                    case 13:
                        second = "A";
                }

                container = Constants.cardPrimes[i] * Constants.cardPrimes[j];
                String combo = "";
                if(i%4 == j%4) {
                    combo = first + second + "s";
                }else{
                     combo = first + second + "o";
                }
                combos.putIfAbsent(combo, new ArrayList<Integer>());
                combos.get(combo).add(container);
                for (Map.Entry<String, ArrayList<Integer>> asd : combos.entrySet()) {
                    System.out.println(asd.getKey() + " ; " + asd.getValue());
                }
    number++;
            }
        }
        System.out.println(combos.entrySet());
        System.out.println(number);
    }


}
