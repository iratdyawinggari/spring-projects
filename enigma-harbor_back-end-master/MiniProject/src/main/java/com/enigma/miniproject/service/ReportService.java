package com.enigma.miniproject.service;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.Report;
import com.enigma.miniproject.repository.ReportRepository;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	@Autowired
	ReportRepository reportRepository;

    public byte[] doGetReport() {
        List<Report> reportData = getReport();
        HashMap<String, Object> parameters = new HashMap<>();
        InputStream jrxmlInput = null;
        try {
            jrxmlInput = this.getClass().getClassLoader().getResource("report/HarborReport.jrxml").openStream();
            JasperReport report = JasperCompileManager.compileReport(jrxmlInput);
            JRDataSource datasource = new JRBeanCollectionDataSource(reportData, true);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, datasource);
            return JasperExportManager.exportReportToPdf(print);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        } catch (JRException e) {
            System.out.println(e);
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

	
	public List<Report> getReport(){
		List<Object[]> reports = reportRepository.getReport();
		List<Report> report = new ArrayList<Report>();
		for (Object[] obj : reports) {
			Report rep = new Report();
			rep.setId(Integer.valueOf(obj[0].toString()));
			rep.setTransactionHeaderId(obj[1].toString());
			rep.setShipName(obj[2].toString());
			rep.setEntryDate((Date) obj[3]);
			rep.setExitDate((Date) obj[4]);
			rep.setCaptainName(obj[5].toString());;
			rep.setShipStatusName(obj[6].toString());
			rep.setWeight(Integer.valueOf(obj[7].toString()));
			rep.setHarborName(obj[8].toString());
			rep.setDockName(obj[9].toString());
			rep.setTransactionStatus(obj[10].toString());
			report.add(rep);
		}
		return report;
	}
}
