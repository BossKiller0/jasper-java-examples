/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Nitin
 */
public class MyReport extends HttpServlet {

    static JasperReport jasReport; //holds compiled jrxml file
    static JasperPrint jasPrint;   //contains report after result filling process
    private InputStream inputStream;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String path = getServletContext().getRealPath("/reports/report1.jrxml");
            jasReport = JasperCompileManager.compileReport(path);
            System.out.println("Jasper Report : " + jasReport);

            Connection con = MyConnFactory.getConnection();
            System.out.println(con);

            jasPrint = JasperFillManager.fillReport(jasReport,null,con);//, mapParam, con);
            System.out.println("Jasper Print : " + jasPrint);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

//        JasperExportManager.exportReportToPdfFile(jasPrint, "f:/nn.pdf");
//            JasperExportManager.exportReportToPdfStream(jasPrint, baos);
//            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//            inputStream = bais;

            ServletOutputStream sos=resp.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, sos);

            try {
                MyConnFactory.getConnection().close();
                sos.close();
            } catch (SQLException ex) {
                Logger.getLogger(MyReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JRException ex) {
            Logger.getLogger(MyReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public String execute() throws JRException {
//
////         String path = "C:/Users/Nitin/Documents/NetBeansProjects/JasperReports/src/javaapplication7/classwise.jrxml";
//        String path = ServletActionContext.getServletContext().getRealPath("/reports/report1.jrxml");
//        jasReport = JasperCompileManager.compileReport(path);
//
//        System.out.println("Jasper Report : " + jasReport);
//        Connection con = MyConnFactory.getConnection();
//        System.out.println(con);
//
//        Map mapParam = new HashMap();
//        String x = idCardgroups.toString();
//        x = x.substring(1, x.length() - 1);
//        System.out.println("parameters passed : " + x);
//        mapParam.put("paramidcardgroup", x);
//
//        //filling the report with data from dataSource
////        jasPrint = JasperFillManager.fillReport(jasReport, null, con);
//        jasPrint = JasperFillManager.fillReport(jasReport, mapParam, con);
//        System.out.println("Jasper Print : " + jasPrint);
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
////        JasperExportManager.exportReportToPdfFile(jasPrint, "f:/nn.pdf");
//        JasperExportManager.exportReportToPdfStream(jasPrint, baos);
//        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//        inputStream = bais;
//        try {
//            MyConnectionFactory.getConnection().close();
//        } catch (SQLException ex) {
//            Logger.getLogger(CardgroupReport.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return SUCCESS;
//
//    }
}
