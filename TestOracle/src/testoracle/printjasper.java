/*
 Created By Ahmed Elgamal - 30/07/2018
 */
package testoracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author elgamal
 */
public class printjasper {

    public static void main(String[] args) {
        try{
        printjasper ps = new printjasper();
        ps.getMyClass();
        }catch(Exception e){
        }
        
        
        try {
            System.out.println("Start Printing...");
            // Get jasper report from the local drive
            String jrxmlFileName = "D:\\bss_receipt.jrxml";  
            String jasperFileName = "D:\\bss_receipt.jasper";
            String pdfFileName = "D:\\inandoutir_mysql.pdf";

            JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);

            String DB_DRIVER = "oracle.jdbc.OracleDriver";
            String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:ORCL ";
            String DB_USER = "BSS_ERP";
            String DB_PASSWORD = "PassWord";
            

            // Load the JDBC driver
            Class.forName(DB_DRIVER);
            // Get the connection
            Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            // Create arguments
            int l =  Integer.parseInt(args[0]);

            HashMap hm = new HashMap();
            hm.put("PAR_HSALES", l);

            // Generate jasper print
            JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, conn);
            JasperViewer.viewReport(jprint, false);
			JasperViewer.setDefaultLookAndFeelDecorated(true);
			
			// Auto Print 
            JasperPrintManager.printReport(jprint, false);         
            
			// Export pdf file
            JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
            
			System.out.println("Done!");
           

        } catch (Exception e) {
            System.out.println("Exception" + e);

        }

    }
   

}
