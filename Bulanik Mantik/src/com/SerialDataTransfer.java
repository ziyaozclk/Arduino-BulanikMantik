package com;

import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

import net.sourceforge.jFuzzyLogic.FIS;

public class SerialDataTransfer {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String fileName = "C:\\Users\\ZÝYA\\Desktop\\BulanikMantik\\Files\\deneme.fcl";
		FIS fis = FIS.load(fileName, true);
		
		if (fis == null) {
            System.err.println("Belirtilen dosya yüklenemedi : '"
                    + fileName + "'");
            return;
        }
		fis.chart();
		boolean devam = true;
		while(devam){
			double dis = scan.nextDouble();
			double ic = scan.nextDouble();
			
			fis.setVariable("disortamsicaklik", dis);
	        fis.setVariable("icortamsicaklik", ic);
	        fis.evaluate();
	        
	        System.out.println(fis.getVariable("klima").getLatestDefuzzifiedValue());
	        fis.getVariable("klima").getLatestDefuzzifiedValue();
	        fis.getVariable("klima").chartDefuzzifier(true);
		}
		
	}
}
