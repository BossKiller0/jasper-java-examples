/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.ws.Response;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.struts2.ServletActionContext;

/**
/**
 *
 * @author Nitin
 */
public class MyAction extends ActionSupport {

    static JasperReport jasReport; //holds compiled jrxml file
    static JasperPrint jasPrint;   //contains report after result filling process
    private InputStream inputStream;

    public String execute() throws JRException, SQLException, ClassNotFoundException {
        String path=ServletActionContext.getServletContext().getRealPath("/reports");
//        String path = "C:/Users/Nitin/Documents/NetBeansProjects/JasperReports/src/javaapplication7/classwise.jrxml";
//        jasReport = JasperCompileManager.compileReport(path);
        jasReport = JasperCompileManager.compileReport(path+"/classwise.jrxml");

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpaathshala", "root", "root");
        System.out.println(con);

        //filling the report with data from dataSource
        jasPrint = JasperFillManager.fillReport(jasReport, null, con);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

//        JasperExportManager.exportReportToPdfFile(jasPrint, "f:/nn.pdf");
        JasperExportManager.exportReportToPdfStream(jasPrint, baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        inputStream = bais;
        return SUCCESS;
    }


    

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public static JasperPrint getJasPrint() {
        return jasPrint;
    }

    public static void setJasPrint(JasperPrint jasPrint) {
        MyAction.jasPrint = jasPrint;
    }

    public static JasperReport getJasReport() {
        return jasReport;
    }

    public static void setJasReport(JasperReport jasReport) {
        MyAction.jasReport = jasReport;
    }
}
