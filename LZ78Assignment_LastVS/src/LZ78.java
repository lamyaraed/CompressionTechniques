import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.*;
public class LZ78 {
	 public void Compression (String S){
	        String Sentence;
	        String toFile;
	        String Tag ="";
	        Sentence = S.replaceAll("\\s+", "");
	        Sentence = Sentence.toLowerCase();
	        HashMap<Integer, String> Dictionary = new HashMap<>();
	        String Code;
	        char d;
	        int Index;
	        String w;
	        String l ="";
	        int num=0;
	        int k;
            int counter = 0 ; 

	        Dictionary.put(0, "null");
	        for (int i=0; i<Sentence.length(); i++)
	        {
	            k=0;
	            num = num+1;
	            d = Sentence.charAt(i);
	            Code = Character.toString(d);
	            Index =0;
	            while (Dictionary.containsValue(Code)) {
	                if (i == Sentence.length()-1)
	                    break;
	                d = Sentence.charAt(++i);
	                Code = Code + d;
	                k++;
	            }
	            if (k >=1) {
	                l = Code.substring(0, Code.length()-1);
	            }
	            for (int y =0;  y <Dictionary.size() ; y++)
	            {
	                w = Dictionary.get(y);

	                if (Objects.equals(w,l))
	                    Index = y;
	            }
	            if ((i+1==Sentence.length()) && Dictionary.containsValue(Code))
	            {
	                Tag += "<0," + d + ">";
	                counter++ ; 
	            }
	            else {
	                Dictionary.put(num, Code);
	                Tag += "<" + Index + "," + d + "> "; 
	                counter++ ; 
	            }
	        }
	        
	        System.out.println(Tag);
	        System.out.println("\nYour Dictionary is as follows  :");
	        System.out.print("\n");
	        System.out.println(Dictionary);
	        System.out.print("\n") ; 
	        System.out.print("Compression Ratio : " + "\n");
	        int numberofsymbols = Sentence.length() ; 
            int bitsusedtoStoreOneSymbol = 8 ; 
            int OrginalSize = numberofsymbols * bitsusedtoStoreOneSymbol ;  
            System.out.println("Orginal Size = " + OrginalSize);
            System.out.println("Number Of Tags = " + counter ) ; 
            int maxSymbolbits = 8 ; 
            int maxIndexbits = 4 ; 
            int CompressedSize = counter*(maxSymbolbits+maxIndexbits) ; 
            System.out.print("Compressed Size = " + CompressedSize + "\n");
            double CompressionRatio = CompressedSize/OrginalSize ; 
            System.out.print("Compression Ratio = "+CompressionRatio);
	        try {
	            File file = new File ("Dictionary.txt");
	            file.createNewFile();
	            FileWriter fileWriter = new FileWriter(file);
	            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
	            bufferWriter.write("Sentence to Compress : ");
	            bufferWriter.append(Sentence);
	            bufferWriter.append("\r\n");
	            bufferWriter.append("Tags : ");
	            bufferWriter.append(Tag);
	            bufferWriter.append("\r\n");
	            bufferWriter.append("Your Dictionary : ");
	            bufferWriter.append("\r\n");
	            for (int o=0; 0<Dictionary.size(); o++) {
	                bufferWriter.append(Integer.toString(o));
	                toFile = Dictionary.get(o);
	                bufferWriter.append("\t");
	                //System.out.println(toFile);
	                bufferWriter.append(toFile);
	                bufferWriter.append("\r\n");
	                if (++o == Dictionary.size())
	                    break;
	                else
	                    --o;
	            }
	            bufferWriter.append("Compression Ratio : " + "\n") ; 
	            bufferWriter.append("Orginal Size = " + OrginalSize + "\n") ; 
	            bufferWriter.append("Number Of Tags = " + counter + "\n") ; 
	            bufferWriter.append("Compressed Size = " + CompressedSize + "\n") ; 
	            bufferWriter.append("Compression Ratio = "+CompressionRatio + "\n") ; 

	            bufferWriter.close();
	        }
	        catch (IOException ex){
	            System.out.println("Error writing to file Dictionary.txt");
	        }
	    }
	public void Decompression() 
	{
		Scanner Scan = new Scanner (System.in) ; 
		System.out.println("Please enter your tags to Decompress : ? ") ; 
		// String [] tokens = Scan.nextLine().split("[<,>]") ;
	    String [] tokens = Scan.nextLine().split(" ") ;
		ArrayList<String> dic = new ArrayList<String>() ; 
        String indx , ch ; 
        int counter = 0 ; 
        for (String c : tokens)
        {   
        	indx= c.substring(1, 2) ; // for index  
        	ch  = c.substring(3,c.length()-1) ;  // for char 
            if (Integer.parseInt(indx)==0) // if en indx == 0 we'll add chara in dictionary 
            {
            	dic.add(ch) ; 
            // System.out.println("your dictionary is " ) ; 
            // System.out.println(dic); 
            }
            else 
            {
            	 int index = Integer.parseInt(indx);
            //     System.out.println(index) ; 
            	 String getchar = dic.get(index-1) + ch ;  
            	 dic.add(getchar) ; 
                 // System.out.println("your dictionary is " ) ; 
                //  System.out.println(dic) ; 
            }
        }
        // print as Sequence 
        StringBuilder output = new StringBuilder();
        System.out.print("Your Sentence Is : ") ; 
        for(String string : dic){
            output.append(string);
        }
        System.out.println(output.toString()); 
        String toFile ; 
        try {
            File file = new File ("Dictionary.txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write("Your Tags Is  : " + "\n");
            for (String c : tokens)
            {
             bufferWriter.append(c);
             bufferWriter.append("\r\n");
            }
            bufferWriter.append("\r\n");
            bufferWriter.append("Your Dictionary : ");
            bufferWriter.append("\r\n");
            for (int o = 0 ; 0 < dic.size() ; o++) {
                bufferWriter.append(Integer.toString(o));
                toFile = dic.get(o);
                bufferWriter.append("\t");
                //System.out.println(toFile);
                bufferWriter.append(toFile);
                bufferWriter.append("\r\n");
                if (++o == dic.size())
                    break;
                else
                    --o;
            }
            bufferWriter.append("Your Sentence Is :")  ; 
            bufferWriter.append(output.toString()) ; 
            bufferWriter.close();
        }
        catch (IOException ex){
            System.out.println("Error writing to file Dictionary.txt");
        }
	}
	
	}


