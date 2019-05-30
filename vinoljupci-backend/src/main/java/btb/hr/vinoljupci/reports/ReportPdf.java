package btb.hr.vinoljupci.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import btb.hr.vinoljupci.model.FilterQueryToClient;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;



public class ReportPdf {
	
	@Autowired 
	ResourceLoader resourceLoader;
	
	private static final Logger log = LoggerFactory.getLogger(ReportPdf.class);
	
	@SuppressWarnings("deprecation")
	public byte[] generateVinarPdfReport(FilterQueryToClient filteredDataToSend) {
		
		
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file=null;
//		URL url = classLoader.getResource("\\btb\\hr\\vinoljupci\\reports\\izvjestaj-vinar.jrxml");
//		URL url = classLoader.getResource("C:\\Users\\Public\\izvjestaj-vinar.jrxml");
		ClassPathResource url = new ClassPathResource("izvjestaj.jrxml");
//		file = new File(url.getFile());
//	
		 try {
			file = url.getFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	     
		FileInputStream fis = null;
		byte[] bytesFile = null;

		try {
			fis = new FileInputStream(file);
			
			ArrayList<FilterQueryToClient> filteredDataToSendList = new ArrayList<FilterQueryToClient>();
				filteredDataToSendList.add(filteredDataToSend);
			
			
			JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(filteredDataToSendList);
			
			Map<String, Object> parameters = new HashMap<String, Object>();

			int prihodVinar = filteredDataToSendList.get(0).getBillSum() - (filteredDataToSendList.get(0).getBillSum()*(filteredDataToSend.getPercentage()/100));
			
			int sum = 0;
			for (FilterQueryToClient item : filteredDataToSendList) {
				sum += item.getBillSum();
			}
				parameters.put("wineEventName", filteredDataToSendList.get(0).getWineEventName());
			    parameters.put("timestamp", filteredDataToSendList.get(0).getTimestamp());
			    parameters.put("firmName", filteredDataToSendList.get(0).getFirmName());
				parameters.put("billSum", sum);
				parameters.put("percentage", filteredDataToSendList.get(0).getPercentage());
				parameters.put("prihod_vinar", prihodVinar);
				
			System.out.println("Compiling file...");
			JasperReport report = JasperCompileManager.compileReport(fis);
			System.out.println("Compile Success!");
			System.out.println("Reporting file...");
			System.out.println("****Report " + report);
			System.out.println("****Parameters " + parameters);
			System.out.println("****itemsJrBean " + itemsJRBean);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, itemsJRBean);
			System.out.println("Report Success!");
			System.out.println("");
			System.out.println("");
			
			OutputStream outputStream = new ByteArrayOutputStream();
			 JasperExportManager.exportReportToPdfFile(print,
		               "vinoljupci-izvjestaj.pdf");
			 
			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			exporter.setConfiguration(configuration);

			exporter.exportReport();
			
			bytesFile = ((ByteArrayOutputStream) outputStream).toByteArray();
			
			
				return bytesFile;
			
			
		} catch (Exception e) {
			System.out.println("Report pdf error");
			e.printStackTrace();
			return null;
		}
			
 }
}
