package akademiasovy.PSC.db;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.text.Normalizer;
import java.text.Normalizer.Form;

public class Excel {

        public static void main(String[] args) throws Exception {

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectPSC", "root", "");
                String query = "INSERT INTO psc(City,District,ZipCode)VALUES(?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);


                FileInputStream FIS = new FileInputStream("C:\\Users\\domin\\IdeaProjects\\projectPSCobci\\PSCobci.xlsx");
                XSSFWorkbook wb = new XSSFWorkbook(FIS);
                XSSFSheet sh = wb.getSheetAt(0);

                Row row;
                String ID;
                String OKRES;
                String OBEC;
                String PSC;

                for (int i = 1; i <= sh.getLastRowNum(); i++) {
                    row = (Row) sh.getRow(i);

                    if (row.getCell(0) == null) {
                        OBEC = "null";
                    } else {
                        OBEC = Normalizer.normalize(row.getCell(0).toString(),Form.NFD)
                                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
                        ps.setString(1, OBEC);
                    }


                    if (row.getCell(1) == null) {
                        OKRES = "null";
                    } else {
                        OKRES = Normalizer.normalize(row.getCell(1).toString(),Form.NFD)
                                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
                        ps.setString(2, OKRES);
                    }


                    if (row.getCell(2) == null) {
                        PSC = "null";
                    } else {
                        PSC = row.getCell(2).toString().replaceAll("\\s+","");
                        ps.setString(3, PSC);
                    }

                    ps.executeUpdate();

                }
            }

            catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException ie){
                ie.printStackTrace();
            }catch (Exception exc){
                System.out.println("Err : "+exc.getMessage());
            }
        }
}