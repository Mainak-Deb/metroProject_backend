package com.mainak.functions;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*; 


public class ReadFile {
  private ArrayList<String> li = new ArrayList<String>();  
  public ReadFile(String filename) throws IOException {
    try {
    	Resource resource = new ClassPathResource(filename);

    	//InputStream input = resource.getInputStream();

    	File myObj  = resource.getFile();


        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            li.add(data);
            //System.out.println(data);
        }
        myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public ArrayList<String[]> getData() {
    ArrayList<String[]> data = new ArrayList<String[]>();
    //System.out.println(li);
    for(String line : li) {
        data.add(line.split(","));
    }   
    return data;    
  }
  public TreeSet<String> getStations(){
	  ArrayList<String[]> data =this.getData();
	  TreeSet<String>  stations = new TreeSet<String>();
	  for(String[] s: data) {
		  stations.add(s[0]);
		  stations.add(s[1]);
	  }
	  return stations;
  }
  

}