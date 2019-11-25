package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String DecompressionLZW(String str) {
        ArrayList<String> dictionary = new ArrayList<String>();
        String[] tagsString = str.split(",");
        String output = "";
        for (int i=0; i<128; i++) {
            dictionary.add((char)i+"");
        }
        int old=Integer.parseInt(tagsString[0]);
        int n;
        String s = dictionary.get(old);
        String character = new String();
        character += s.charAt(0);
        output += s;

        for (int i=0 ; i< tagsString.length-1 ; i++) {
            n = Integer.parseInt(tagsString[i+1]);
            if (n >= dictionary.size()) {
                s = dictionary.get(old);
                s = s+character;
            }
            else {
                s = dictionary.get(n);
            }
            output += s;
            character = "";
            character+=s.charAt(0);
            dictionary.add(dictionary.get(old) + character);
            old = n;
        }

        return output;

    }
    public static void main(String[] args) {
        String x = "65,66,65,128,128,129,131,134,130,129,66,138,139,138";
        String y = DecompressionLZW(x);
        System.out.println(y);
    }
}

