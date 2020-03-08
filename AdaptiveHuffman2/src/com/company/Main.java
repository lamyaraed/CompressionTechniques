package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner inputSent = new Scanner(System.in);
        int x = 0, y;
        String Sentence;
        AdaptiveHuffman Huffman;
        System.out.println("Welcome To Adaptive Huffman Compression and Decompression");
        System.out.println("\t 1- Compress");
        System.out.println("\t 2- Decompress");
        x = input.nextInt();
        if(x == 1)
        {
            System.out.println("What is your Maximum Node Number");
            y = input.nextInt();
            Huffman = new AdaptiveHuffman(y);
            System.out.println("Write the Sentence you want to Compress :");
            Sentence = inputSent.nextLine();
            Huffman.Compression(Sentence);
        }

        else if (x == 2){
            System.out.println("What is your Maximum Node Number");
            y = input.nextInt();
            Huffman = new AdaptiveHuffman(y);
            System.out.println("Write the Tags you want to Decompress :");
            Sentence = inputSent.nextLine();
            Huffman.Decompression(Sentence);
        }
        else
            System.out.println("Incorrect Choice");

    }
}
