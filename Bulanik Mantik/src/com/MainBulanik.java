package com;

import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

import net.sourceforge.jFuzzyLogic.FIS;

public class MainBulanik {

	public static void main(String[] args) {
		String fileName = "C:\\Users\\ZÝYA\\Desktop\\Bulanik Mantik\\Files\\deneme.fcl";
		FIS fis = FIS.load(fileName, true);
		
		Scanner scan = new Scanner(System.in);
		SerialPort ports[] = SerialPort.getCommPorts();
        

        if (fis == null) {
            System.err.println("Belirtilen dosya yüklenemedi : '"
                    + fileName + "'");
            return;
        }
        else{
        	//fis.chart();
        	if(ports.length != 0){
    			System.out.println("Lütfen bir port seçiniz : ");
    			
    			int i=0;
    			for(SerialPort port : ports){
    				System.out.println((i+1)+"."+port.getSystemPortName());
    				i++;
    			}
    			
    			int selectPort = scan.nextInt()-1;
    			SerialPort port = ports[selectPort];
    			port.setBaudRate(9600);
    			
    			if(port.openPort()){
    				System.out.println("Baðlantý Baþarýlý");
    				
    				port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 10000, 0);
    				Scanner data = new Scanner(port.getInputStream());
    				
    				double temp = Double.parseDouble(data.next().substring(1));
    				while(data.hasNextLine()){
    					temp = Double.parseDouble(data.next());
    					System.out.println("Lütfen hedef sýcaklýk deðerini giriniz : ");
    					double target = scan.nextDouble();
    					
    					fis.setVariable("disortamsicaklik", target);
    			        fis.setVariable("icortamsicaklik", temp);
    			        fis.evaluate();
    			        
    			        double fuzzyValue = fis.getVariable("klima").getLatestDefuzzifiedValue();
    			        //fis.getVariable("klima").chartDefuzzifier(true);
    			        
    			        if(fuzzyValue<5.0){
    			        	System.out.println("Klimayý az sýcak çalýþtýr");
    			        }
    			        else if(fuzzyValue>=5.0 && fuzzyValue<10.0){
    			        	System.out.println("Klimayý sýcak çalýþtýr");
    			        }
    			        else if(fuzzyValue>=10.0 && fuzzyValue<15.0){
    			        	System.out.println("Klimayý sýfýr durumunda çalýþtýr");
    			        }
    			        else if(fuzzyValue>=15.0 && fuzzyValue<20.0){
    			        	System.out.println("Klimayý sýfýr durumunda çalýþtýr");
    			        }
    			        else if(fuzzyValue>=20.0 && fuzzyValue<25){
    			        	System.out.println("Klimayý az soðuk çalýþtýr");
    			        }
    			        else if(fuzzyValue>=25.0 && fuzzyValue<=30){
    			        	System.out.println("Klimayý soðuk çalýþtýr");
    			        }
    			        else{
    			        	System.out.println("Aralýkta bir deðer deðil");
    			        }
    				}
    			}
    			else{
    				System.out.println("Baðlantý Baþarýsýz");
    			}
    		}
    		else{
    			System.out.println("Þuanda aktif port mevcut deðil !!!");
    		}
        }
	}

}
