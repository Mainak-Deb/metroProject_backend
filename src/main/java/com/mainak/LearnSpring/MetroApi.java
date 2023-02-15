package com.mainak.LearnSpring;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.TreeSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mainak.APIclasses.Route;
import com.mainak.APIclasses.Ticket;
import com.mainak.functions.MetroGraph;
import com.mainak.functions.ReadFile;


@RestController
public class MetroApi {
	
	@GetMapping("/home")
	public String home() {
		return "Hello multiverse";
	}
	
	@PostMapping("/metroroute")
	public Ticket getStation(
			@RequestParam String startStation,
			@RequestParam String endStation,
			@RequestParam int personCount,
			@RequestParam boolean isReturn
			
	) throws IOException  {
		
        ReadFile r=new ReadFile("metro.txt");
        ArrayList<String[]> data = r.getData();
        MetroGraph m = new MetroGraph(data);
        ArrayList<String[]> path= m.bfsShortestPath(startStation,endStation);
        int stationCount=path.size();
        int ticketPrice=(((int)(path.size()/5)+1)*5)*personCount;
        if(isReturn) {
        	ticketPrice*=2;
        }
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        
        Date dt = new Date();
        String validFrom=dateFormat.format(dt);
        
        Calendar c = Calendar.getInstance(); 
        c.setTime(dt); 
        String id=dateFormat2.format(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        String validTo=dateFormat.format(dt);
        
        try {
	        Ticket t=new Ticket();
	        t.setId(id);
	        t.setSource(startStation);
	        t.setDestination(endStation);
	        t.setTicketPrice(ticketPrice);
	        t.setStationCount(stationCount);
	        t.setStations(path);
	        t.setPersonCount(personCount);
	        t.setValidFrom(validFrom);
	        t.setValidTo(validTo);
	        t.setReturn(isReturn);
	        
	        Connection conn= this.getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO ticket(id,`source`,destination,stationcount,ticketprice,personcount,validfrom,validuntil,isreturn) VALUES(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, id);
			stmt.setString(2, startStation);
			stmt.setString(3, endStation);
			stmt.setInt(4, stationCount);
			stmt.setInt(5, ticketPrice);
			stmt.setInt(6, personCount);
			stmt.setString(7, validFrom);
			stmt.setString(8, validTo);
			stmt.setBoolean(9, isReturn);
			
			
			@SuppressWarnings("unused")
			int i=stmt.executeUpdate();  
			
			
	        return t;
	      
	        
        }catch(Exception e){ 
        	Ticket t=new Ticket();
        	t.setId("Opration Failer, server problem");
        	 return t;
        	
        }  
        
        
		
	}

	@GetMapping("/stations")
	public Route allStations() throws IOException {
		Route r=new Route();
		ReadFile f=new ReadFile("metro.txt");
	    TreeSet<String>  data = f.getStations();
	    r.setStations(data);
	    r.setStationCount(data.size());
		return r;
	}
	
	@GetMapping("/verify/{vid}")
	public Ticket VerifyId(
			@PathVariable String vid
	) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, SQLException {
		Connection conn= this.getConnection();
		PreparedStatement stmt = conn.prepareStatement(" select * from ticket where id=?", ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1, vid);
		ResultSet rs= stmt.executeQuery();
		rs.absolute(1);  
		//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(2));  
		
		
		ReadFile r=new ReadFile("metro.txt");
        ArrayList<String[]> data = r.getData();
        MetroGraph m = new MetroGraph(data);
        ArrayList<String[]> path= m.bfsShortestPath(rs.getString(2),rs.getString(3));
        
        Ticket t=new Ticket();
        t.setId(rs.getString("id"));
        t.setSource(rs.getString("source"));
        t.setDestination(rs.getString( "destination"));
        t.setTicketPrice(rs.getInt("ticketprice"));
        t.setStationCount(rs.getInt("stationcount"));
        t.setStations(path);
        t.setPersonCount(rs.getInt("personCount"));
        t.setValidFrom(rs.getString("validFrom"));
        t.setValidTo(rs.getString("validuntil"));
        t.setReturn(rs.getBoolean("isreturn"));
        
		conn.close();
		return t;
		
	}
	
	@SuppressWarnings({ "unused" })
	private Connection getConnection() throws IOException,SQLException,ClassNotFoundException, InstantiationException, IllegalAccessException {
		String url="jdbc:mysql://localhost:3306/metroproject";
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url,"root","mainak");
		
	}

	
}
