package BINF8380_Lab9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class NucleoCounter {
	private static int adenosine = 0;
	private static int thymine = 0;
	private static int cytosine = 0;
	private static int guanine = 0;
	private static int unknown = 0;
	public NucleoCounter(File[] listofFiles){
		for (File file : listofFiles) {
		    if (file.isFile()) {
		    	try {
				BufferedReader readfasta = new BufferedReader(new FileReader(file));
				for (String fasLine = readfasta.readLine(); fasLine != null; fasLine = readfasta.readLine())
					{
					if(!fasLine.startsWith(">")) {
						getBases(fasLine);
					}
				}
				readfasta.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
	}
	public void getBases(String fas) {
		for(int i = 0;i < fas.length() ; i++ ) {
			char base = fas.charAt(i);
			if(base == 'G'){
				guanine++;
			}else if(base == 'C') {
				cytosine++;
			}else if(base == 'T') {
				thymine++;
			}else if(base == 'A') {
				adenosine++;
			}else {
				unknown++;
			}
		}
	}	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		File[] files = new File("/Users/mitchnodzak/adenomasRelease/fasta").listFiles();
		new NucleoCounter(files);
		System.out.println("Count of A: "+adenosine);
		System.out.println("Count of T: "+thymine);
		System.out.println("Count of C: "+cytosine);
		System.out.println("Count of G: "+guanine);
		System.out.println("Unknown nucleotides: "+unknown);
		long stopTime = System.currentTimeMillis();
		float elapsedTime = ((stopTime - startTime)/1000f);
	     System.out.println("The method took: "+elapsedTime+ " seconds to run.");
	}

}
