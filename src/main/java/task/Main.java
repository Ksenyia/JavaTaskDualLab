package task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static final String POSH = "Posh";
	private static final boolean EFFICIENT = false;
	private static final String GROTTY = "Grotty";

	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		List<Servise> servises = new ArrayList<Servise>();
		String path = "E:\\JavaEE\\JavaTaskAkula\\src\\main\\java\\task"; 
		ReadFile(br, servises, path);
		int size = servises.size();
		int start =1;
		for(Servise servise:servises){
			for (int i=start;i<size;i++ ){
				if(!servise.equals(servises.get(i))){
					AddEfficient(servises, servise, i);
				}
			}
		}
		servises.sort(null);
		List<Servise> poshServises =new ArrayList<Servise>();
		List<Servise> grottyServises =new ArrayList<Servise>();
		AddServise(servises, poshServises, grottyServises);
		WriteFile(bw, poshServises, grottyServises, path);
	}

	private static void AddServise(List<Servise> servises, List<Servise> poshServises, List<Servise> grottyServises) {
		for(Servise servise:servises){
			if(servise.isEfficient()){
				if(POSH.equalsIgnoreCase(servise.getCompany())){
					poshServises.add(servise);
				}
				if(GROTTY.equalsIgnoreCase(servise.getCompany())){
					grottyServises.add(servise);
				}
			}
		}
	}

	private static void ReadFile(BufferedReader br, List<Servise> servises, String path) {
		try {   
			File dir = new File(path);
			String inFileName = "input.txt";
			File inFile = new File(dir, inFileName);
			br = new BufferedReader(new FileReader(inFile)); 
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				Servise servise = new Servise(tmp);
				servises.add(servise);		
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally {  
			if (br != null) {
				try {    
					br.close();  
				} catch (IOException e) { 
					e.printStackTrace();
				}   
			} 
		}
	}
	
	private static void WriteFile(BufferedWriter bw, List<Servise> poshServises, List<Servise> grottyServises, String path) {
		try {   
			File dir = new File(path);
			String outFileName = "output.txt";
			File outFile = new File(dir, outFileName);
			bw = new BufferedWriter(new FileWriter(outFile)); 
			for(Servise servise:poshServises) {
				bw.write(servise.toString());
				bw.newLine();				
			}
			bw.newLine();
			for(Servise servise:grottyServises) {
				bw.write(servise.toString());
				bw.newLine();				
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally {    
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
			} 
		}

	private static void AddEfficient(List<Servise> servises, Servise servise, int i) {
		boolean serviseIsLeterDep = servise.getDepartureTime().isAfter(servises.get(i).getDepartureTime());
		boolean serviseIsEarlierDep = servise.getDepartureTime().isBefore(servises.get(i).getDepartureTime());
		boolean serviseIsLeterArr = servise.getArrivalTime().isAfter(servises.get(i).getArrivalTime());
		boolean serviseIsEqalseDep = servise.getDepartureTime().equals(servises.get(i).getDepartureTime());
		boolean serviseIsEqalseArr = servise.getArrivalTime().equals(servises.get(i).getArrivalTime());
		if(serviseIsEqalseDep&&serviseIsLeterArr){
			servise.setEfficient(EFFICIENT);
		}
		else{
			if(serviseIsEarlierDep&&serviseIsLeterArr){
				servise.setEfficient(EFFICIENT);
			}
			else{
				if(serviseIsLeterDep&&serviseIsEqalseArr){
					servise.setEfficient(EFFICIENT);
				}
				else{
					if(serviseIsEqalseDep&&serviseIsEqalseArr&&GROTTY.equalsIgnoreCase(servise.getCompany())){
							servise.setEfficient(EFFICIENT);

					}
				}
			}
		}
	}

}
