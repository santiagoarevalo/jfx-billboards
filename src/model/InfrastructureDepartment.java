package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InfrastructureDepartment {
	public final static String BILLBOARD_FILE_NAME = "data/BillboardDataExported.csv";
	private ArrayList<Billboard> billboards;
	
	public InfrastructureDepartment() throws ClassNotFoundException, IOException {	
		billboards = new ArrayList<Billboard>();
		loadBillboards();
	}
	
	public void addBillboard(double w, double h, boolean iu, String b) {
		billboards.add(new Billboard(w,h,iu,b));
	}

	public ArrayList<Billboard> getBillboards() {
		return billboards;
	}
	
	public void saveBillboards() throws IOException{
	    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILLBOARD_FILE_NAME));
	    oos.writeObject(billboards);
	    oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadBillboards() throws IOException, ClassNotFoundException{
	    File f = new File(BILLBOARD_FILE_NAME);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      billboards = (ArrayList<Billboard>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	}
	
	public void exportDangerousBillboardReport(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fileName);
	    pw.println("===========================\nDANGEROUS BILLBOARD REPORT\n===========================");
	    pw.println("The dangerous billboard are:");
		
	    for(int i=0;i<billboards.size();i++){
	      Billboard myBillboard = billboards.get(i);
	      double area = myBillboard.calculateArea();
	      if(area >= 160000) {
	    	  pw.println(i+". Billboard"+myBillboard.getBrand()+" with area "+area);
	      }
	    }
	    pw.close();
	}
	
	public void importBillboards(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String line = br.readLine();
	    line = br.readLine();
	    while(line!=null) {
	      String[] parts = line.split("|");
	      double width = Double.parseDouble(parts[0]);
	      double height = Double.parseDouble(parts[1]);
	      boolean inUse = Boolean.parseBoolean(parts[2]);
	      
	      addBillboard(width, height, inUse, parts[3]);
	      line = br.readLine();
	    }
	    br.close();
	}
}
