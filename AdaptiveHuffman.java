package com.company;


import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.HashMap;

public class AdaptiveHuffman {
    Tree HuffmanTree;
    String Sentence;
    String toFile;
    ArrayList Occurance = new ArrayList(); //array
    HashMap<String, String> shortCode = new HashMap<>();

    public AdaptiveHuffman (int Max){
        HuffmanTree = new Tree(Max);
        makeShortMap();
    }

    public void makeShortMap(){
        for (int i=0; i<128; i++) {
            int dec = i;
            String result = "0000000";
            int j = result.length()-1;
            while (dec!=0){
                char a[] = result.toCharArray();
                a[j--]= String.valueOf(dec%2).charAt(0);
                result = new String(a);
                dec = dec/2;
            }
            if(result.length() == 7)
                result = "0" + result;
            shortCode.put((char)i+"", result);
        }
    }

    public String getShortCode(String character){
        String ShortCode = shortCode.get(character);
        return ShortCode;
    }

    public String getCharacter (String ShortCode){
        for (String o : shortCode.keySet()) {
            if(shortCode.get(o).equals(ShortCode))
                return o;
        }
        return null;
    }

    public void Compression(String input){
        Sentence = input;
        String Tag = "";
        String x = "";
        for (int i=0; i<input.length(); i++){
            x = x + Sentence.charAt(i);
            if(i==0){
                Occurance.add(Sentence.charAt(i));
                Tag = Tag + getShortCode(x);
        //        System.out.println(getShortCode(x));
                HuffmanTree.add(x);
//                System.out.println(Tag);
            }
            else if(Occurance.contains(Sentence.charAt(i)))
            {
                String tempString = HuffmanTree.getCode(x);
                Tag = Tag + tempString;
                HuffmanTree.add(x);
          //      System.out.println(tempString);
//                System.out.println(Tag);
            }
            else{
                Occurance.add(Sentence.charAt(i));
                //System.out.println(HuffmanTree.getNYT());
               // System.out.println(getShortCode(x));
                Tag = Tag + HuffmanTree.getNYT();
                Tag = Tag + getShortCode(x);
                HuffmanTree.add(x);
//                System.out.println(Tag);
            }
            x = "";
        }
        System.out.println(Tag);
    }


    public void Decompression(String input){
        Sentence = input;
        String Tag = "";
        String x = "";
        for (int i=0; i<input.length(); i++){
            if (i==0){
                x = input.substring(0, 8);
                String sentence = getCharacter(x);
                //System.out.println(x);
                Occurance.add(sentence);
                Tag = Tag + getCharacter(x);
                HuffmanTree.add(sentence);
                i = i + 7 ;
                //System.out.println(i);
                //System.out.println(Tag);
                x="";
            }
            else {
                x = x + input.charAt(i);
            //    System.out.println(x);//String sentence = getCharacter(x);
                String tempString = HuffmanTree.getSymbol(x);
                if(tempString != ""){

                    if (tempString.equals(""))
                        continue;
                    else if(tempString.equals("NYT")){
                        x="";
                        x=input.substring(i+1, i+9);
                        tempString = getCharacter(x);
                        Tag += tempString;
                        i = i+8;
                    }
                    else {
                        Tag += tempString;
                    }
                    HuffmanTree.add(tempString);
                    x ="";
                    if (!Occurance.contains(tempString))
                        Occurance.add(tempString);

                }

            }
        }
        System.out.println(Tag);
    }

    //todo function to get char short code

}
