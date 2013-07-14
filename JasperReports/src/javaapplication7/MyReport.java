/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nitin
 */
public class MyReport {

    static JasperReport jasReport; //holds compiled jrxml file
    static JasperPrint jasPrint;   //contains report after result filling process

    public static void main(String[] args) throws JRException, SQLException, ClassNotFoundException {
        JFileChooser fchoose = new JFileChooser();
        fchoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        fchoose.showOpenDialog(fchoose);
//        System.out.println(fchoose.getSelectedFile().getAbsolutePath());

        //Compiling the report
//        jasReport = JasperCompileManager.compileReport(fchoose.getSelectedFile().getAbsolutePath());
        String path = "C:/Users/XCoder/Documents/NetBeansProjects/JasperReports/src/javaapplication7/classwise.jrxml";
        jasReport = JasperCompileManager.compileReport(path);
//        jasReport = new JasperReport

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpaathshala", "root", "root");
        System.out.println(con);

        //filling the report with data from dataSource
        jasPrint = JasperFillManager.fillReport(jasReport, null, con);

        JasperExportManager.exportReportToHtmlFile(jasPrint, "d://studentList.html");

        JasperExportManager.exportReportToPdfFile(jasPrint, "d:/nn.pdf");

//        System.out.println("Done with html file");
//        JasperViewer.viewReport(jasPrint);

    }
}
/**
 * /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.HashMap;
import javassist.bytecode.stackmap.BasicBlock.Catch;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Administrator

public class RunReport {


public static Connection establishConnection()

{

Connection connection = null;

try

{
Class.forName("com.mysql.jdbc.Driver");



//String oracleURL = "jdbc:mysql://localhost:3306/dbpaathshala";

connection = DriverManager.getConnection("jdbc:mysql://server:3306/dbpaathshala","root","root");

connection.setAutoCommit(false);

}
catch(ClassNotFoundException exception)

{
System.out.println("Error1="+exception);
exception.printStackTrace();

}
catch(SQLException exception)

{
System.out.println("Error2="+exception);
exception.printStackTrace();

}

return connection;

}

public static void main(String ar[])
{
System.out.println(" first line of main");
JasperPrint jasperPrint;
JasperReport jasperReport;
String record = null;
DataInputStream dis=null;

int recCount = 0;
try
{
System.out.println(" in try blk of main");

Connection connection = establishConnection();
System.out.println(" after connecton of main");
HashMap jasperParameter = new HashMap();
System.out.println("after hashmap of main");
jasperReport = JasperCompileManager.compileReport("E://report1.jrxml");
System.out.println(" jasperReport="+jasperReport.getPropertyNames());
System.out.println(" after jasperReport of main");
// filling report with data from data source

jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection);
System.out.println(" After jasperPrint of main");
// exporting process
JasperExportManager.exportReportToHtmlFile(jasperPrint, "E://studentList.html" );
System.out.println("Done with html file");
JasperViewer.viewReport(jasperPrint);
// 1- export to PDF

//JasperExportManager.exportReportToPdfFile(jasperPrint, "E://studentList1.pdf");








// 3- export to Excel sheet

//JRXlsExporter exporter = new JRXlsExporter();
//
//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//
//exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "E://simple_report.xls" );
//
//exporter.exportReport();
File f = new File("E://studentList.html");
FileInputStream fis = new FileInputStream(f);
BufferedInputStream bis = new BufferedInputStream(fis);
dis = new DataInputStream(bis);
while ( (record=dis.readLine()) != null ) {
recCount++;
System.out.println(recCount + ": " + record);
}
}

catch(Exception e)
{
System.out.println("error3="+e);
}

finally {
// if the file opened okay, make sure we close it
if (dis != null) {
try {
dis.close();
} catch (IOException ioe) {
}
}
}
}

}

 */
