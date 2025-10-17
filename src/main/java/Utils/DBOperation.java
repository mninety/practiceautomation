package Utils;

import java.sql.*;

public class DBOperation {


    public Connection MysqlConnectionOwn()
    {
        //long startTime = System.currentTimeMillis();
        Connection connt = null;
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");


            connt = DriverManager.getConnection("jdbc:mysql://localhost:3306/alquran", "root", "");
        }catch(Exception e) {
            e.printStackTrace();
        }


/*	    public void close() throws SQLException {
	        databaseConnection.close();
	    }*/
        //CommonAction.ExecuteTimeMeasure(startTime,"ProcessParserforCPU");
        return connt;
    }

    public void getDBResult(Connection conn) throws SQLException {
        // Step 3: Create a statement
        Statement stmt = conn.createStatement();

        // Step 4: Execute a query
        String sql = "SELECT usUsername,usFullname_en FROM user";
        ResultSet rs = stmt.executeQuery(sql);

        // Step 5: Process the result set
        while (rs.next()) {
            String name = rs.getString("usUsername");
            String fullName = rs.getString("usFullname_en");

            System.out.println("Name: " + name + ", Full Name: " + fullName);
        }

        // Step 6: Close resources
        rs.close();
        stmt.close();
    }
}
