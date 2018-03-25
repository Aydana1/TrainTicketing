
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class DatabaseClient 
{
	// Database connection details
	String userName = "o.bakhtiyar";	// username
	//String userName = "mona";
	String password = "C1Q1VT02";				// password
	//String password = "";
	String dbms = "mysql";
	String serverName = "46.101.171.158";
	//String serverName = "46.101.171.158";
	String portNumber = "80";
	//String portNumber = "80";
	String dbname = "ospanov_bakhtiyar";		// your schema name 
	//String dbname = "employeedbnew";
	
	Connection conn = null;
	
	/*
	 * In this constructor, connect to the mysql database and exit if it doesn't work
	 */
	public DatabaseClient()
	{
		try 
		{
			conn = getConnection();
		} 
		catch (SQLException e) 
		{	
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/*
	 * Make the connection, pass the exception to the caller
	 */
	public Connection getConnection() throws SQLException 
	{

	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);

	    if (this.dbms.equals("mysql")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + "://" +
	                   this.serverName +
	                   ":" + this.portNumber + "/" + this.dbname,
	                   connectionProps);
	    }
	    System.out.println("Connected to database");
	    return conn;
	}
	
	public boolean runQueryLogIn(String username, String password)
	{
	     
	        try
	        {
	        		Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(
	            		"select Email, Password " +
						"from User " +
	            		"where Email='" + username + "'" +" and Password='" + password + "'" + ";"
	            );
	            if (rs.next() == true){
	            	return true;
	            }   
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	        return false;
	 }
	
	
	public void runQueryListStations(JComboBox<String> comboBoxStation)
	{
	        try
	        {
	        	Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(
	            		"select Name " +
						"from Station;"
	            );
	            while(rs.next())
	            {
	            	comboBoxStation.addItem(rs.getString("Name"));
	            }
	        } 
	        catch (Exception e){
	            e.printStackTrace();
	        }
	 }
	
		public void runQueryLoadTrains(JTable table, String depStation, String arrStation, String date) {
			try
			{
				Statement stmt = conn.createStatement();

	            ResultSet rs = stmt.executeQuery(
	            		"select TrainNum as \'Train No.\', s1.Name as \'Dep. Station\', "
	            		+ "s2.Name as \'Arriv. Station\', Train_type \'Train type\', DATE_FORMAT(Dep_time_date, '%d/%m/%Y %H:%i') \' Dep. Time\', "
	            		+ "DATE_FORMAT(Arriv_time_date, '%d/%m/%Y %H:%i') \' Arriv. Time \', Available_seats \' Available No \'" +
	            		"from Train, Station s1, Station s2 "
	            		+ "where Dep_Station_StationId = s1.StationID and Arriv_Station_StationId = s2.StationID "
	            		+ "and s1.Name = '" + depStation + "' and s2.Name = '" + arrStation + "' and "
	            				+ "DATE_FORMAT(Dep_time_date, '%d/%m/%Y') = '" + date + "';"
	            		
	            );
	            table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
			
		}
		
		
		
		public void runQueryChangePassword(String login, String newpassword) {
			try
			{
				Statement stmt = conn.createStatement();
	            stmt.executeUpdate(
	            		"update User set Password='" + newpassword + "' where Email='" + login + "';");
			} catch(Exception e) {
	            e.printStackTrace();
	        }
		}
		
		
		
		
		public void runQueryLoadOrders(JTable table, String login) {
			try
			{
				Statement stmt = conn.createStatement();

	            ResultSet rs = stmt.executeQuery(
	            		"select TicketID, Purchase_date as \'Purchase date \', User_Email as \'User Email\', "
	            		+ "Passenger_DocumentID as \'Passenger DocumentID\', Fname as \' First name\', "
	            		+ "Lname as \'Last name\', Seat_Train_TrainNum as \'Train No.\', Seat_SeatNum as \'Seat No\' " +
	            		"from Ticket, Passenger "
	            		+ "where Passenger_DocumentID = DocumentID and User_Email='" + login + "';"   		
	            );
	            table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
		}
		
		
		
		public boolean runQueryTicketNum(int tn) {
			try
			{
			    Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(
	            		"select TicketID " +
	            		"from Ticket "
	            		+ "where TicketID=" + tn + ";");
	            if (rs.next() == true){
	            	return true;
	            }
			} catch(Exception e) {
	            e.printStackTrace();
	        }
				return false;
		}
		
		
		public void runQueryLoadSeats(JTable table, String tn) {
			try
			{
				Statement stmt = conn.createStatement();

	            ResultSet rs = stmt.executeQuery(
	            		"select SeatNum \'Seat No.\', Type, Price " +
	            		"from Seat "
	            		+ "where Available = 'Available' and Train_TrainNum=" + Integer.parseInt(tn) + ";"   		
	            );
	            table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }	
		}
		
		
		public String runQueryFindSeats(String tn) {
			try
			{
				Statement stmt = conn.createStatement();

	            ResultSet rs = stmt.executeQuery(
	            		"select Available_seats " +
	            		"from Train "
	            		+ "where TrainNum=" + Integer.parseInt(tn) + ";"   		
	            );
	            while (rs.next()) {
	                String em = rs.getString("Available_seats");
	                return em;
	            }
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
			return "";	
		}
		
		
		
		public boolean runQueryInsertPassenger(String docId, String fn, String ln, String sn, String tn) {
			try
			{
				Statement stmt = conn.createStatement();
				int count = stmt.executeUpdate(
	            		"insert into Passenger values (" + Integer.parseInt(docId) + ",'" + fn + "','" 
	            		+ ln + "'," + Integer.parseInt(sn) + "," + Integer.parseInt(tn) + ");"
	            );
	            if (count > 0) {
	            	return true;
	            }
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
			return false;	
		}
		
		public boolean runQueryBook(int ti, String pd, String l, String di) {
			try
			{
				Statement stmt = conn.createStatement();
				int count = stmt.executeUpdate(
	            		"insert into Ticket values (" + ti + ",'" + pd + "','" + l + "'," + Integer.parseInt(di) + ");"
	            );
	            if (count > 0) {
	            	return true;
	            }
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
			return false;
		}
		
		
	
		public boolean runQueryDeleteBooked(String di) {
			try
			{
				Statement stmt = conn.createStatement();
	            int count = stmt.executeUpdate(
	            		"delete from Passenger where DocumentID=" + Integer.parseInt(di) + ";"
	            );
	            if (count > 0) {
	            	return true;
	            }
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
			return false;
		}
		
		
		public boolean runQueryDeleteTicket(String ti) {
			try
			{
				Statement stmt = conn.createStatement();
	            int count = stmt.executeUpdate(
	            		"delete from Ticket where TicketID=" + Integer.parseInt(ti) + ";"
	            );
	            if (count > 0) {
	            	return true;
	            }
			} catch(Exception e) {
	            e.printStackTrace();
	        }
			return false;
		}
		
		
		
		
		public boolean runQueryUpdateSeat(String sn, String tn, String status) {
			try
			{
				Statement stmt = conn.createStatement();
	            int count = stmt.executeUpdate(
	            		"update Seat set Available = '" + status + "' where SeatNum=" + Integer.parseInt(sn) + " and Train_TrainNum=" + Integer.parseInt(tn) + ";"
	            );
	            if (count > 0) {
	            	return true;
	            }
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
			return false;		
		}
		
		
		public void runQuerUpdateTrain(String tn, int n) {
			try
			{
				Statement stmt = conn.createStatement();
	            stmt.executeUpdate(
	            		"update Train set Available_seats=" + n + " where TrainNum=" + Integer.parseInt(tn) + ";"
	            );
	            
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }	
		}
		
		public String runQueryFindSQ(String login) {
			try
			{
				Statement stmt = conn.createStatement();

	            ResultSet rs = stmt.executeQuery(
	            		"select Security_question " +
	            		"from User "
	            		+ "where Email='" + login + "';"   		
	            );
	            while (rs.next()) {
	            	String em = rs.getString("Security_question");
	                return em;
	            }
	            
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
			return "";
		}
		
		public boolean runQueryCorr(String ans, String login)
		{
		        try
		        {
		        	Statement stmt = conn.createStatement();
		            ResultSet rs = stmt.executeQuery(
		            		"select Security_answer " +
							"from User " +
		            		"where Email='" + login + "'" +" and Security_answer='" + ans + "'" + ";"
		            );
		            if (rs.next() == true){
		            	return true;
		            }
		         
		        } 
		        catch (Exception e) 
		        {
		            e.printStackTrace();
		        }
		        return false;
		        
		}
		
		public boolean runQueryUsername(String username)
		{  
		        try
		        {
		        	Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(
		            		"select Email " +
						"from User " +
		            		"where Email='" + username  + "';"
		        ); 
		            if (rs.next() == true){
		            	return true;
		            }
		        } 
		        catch (Exception e) 
		        {
		            e.printStackTrace();
		        }
		        return false;
		 }
		
		public boolean runQuerySecurityAnswer(String security_answer)
		{
		        try
		        {
		        	Statement stmt = conn.createStatement();
		            ResultSet rs = stmt.executeQuery(
		            		"select Security_answer " +
						"from User " +
		            		"where Security_answer='" + security_answer + "';"
		            );
		            if (rs.next() == true){
		            	return true;
		            }
		         
		        } 
		        catch (Exception e) 
		        {
		            e.printStackTrace();
		        }
		        return false;
		}
		
		public String runQuerySecurtiyQuestion(String username)
		{
		        try
		        {
		        	Statement stmt = conn.createStatement();
		        	String query = "select Security_question from User " +
		        			"where Email='" + username + "';";
		         ResultSet rs = stmt.executeQuery(query);
		
		            if (rs.next() == true){
		            	return rs.getString("Security_question"); 
		            }    
		        } 
		        catch (Exception e) 
		        {
		            e.printStackTrace();
		        }
		        return "";
		 }
		
		
		public void runQuerySignUp(String text, String text1, String text2, String text3, String text4, String text6,
				String text7) {
			try
	        {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO User(Email, Fname, Lname, PhoneNum, Password, Security_question, Security_answer)"
						+ "VALUES ('"+ text + "','" + text1 +"','" + text2 + "','" + text3 +"','" + text4 +"','" + text6 +"','" + text7 + "');");
				
	        } catch(Exception e) {
	            e.printStackTrace();
	        }				
		}

		public boolean runQueryEmail(String username)
		{  
		        try
		        {
		        	Statement stmt = conn.createStatement();
		            ResultSet rs = stmt.executeQuery(
		            		"select Email " +
							"from User " +
		            		"where Email='" + username  + "';"
		            );
		            
		         
		            if (rs.next() == true){
		            	return true;
		            }
		        } 
		        catch (Exception e) 
		        {
		            e.printStackTrace();
		        }
		        return false;
		 }
		
		
	
	}

