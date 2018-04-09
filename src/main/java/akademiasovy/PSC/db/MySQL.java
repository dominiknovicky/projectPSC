package akademiasovy.PSC.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQL {

    private Connection conn;
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String dbName = "projectPSC";
    private final String driver = "com.mysql.jdbc.Driver";
    private final String root="root";
    private final String passwordRoot = "";

    public List<String> getZip(String city){
        List<String> zipCodes=new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName, root, passwordRoot);
            String queryCmd="SELECT ZipCode from psc where City like  '" + city + "'";

            PreparedStatement preparedStatement=conn.prepareStatement(queryCmd);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                String Zip=resultSet.getString("ZipCode");
                zipCodes.add(Zip);
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println("Err " +e.getMessage());
        }
        return zipCodes;
    }

    public List<String> getCity(String zip){
        List<String> cities=new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName, root, passwordRoot);
            String queryCmd="SELECT City from psc where ZipCode like '" +zip+ "'";

            PreparedStatement preparedStatement=conn.prepareStatement(queryCmd);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                String City=resultSet.getString("City");
                cities.add(City);
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println("Err " +e.getMessage());
        }
        return cities;
    }

}
