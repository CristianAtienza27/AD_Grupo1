package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.example.demo.entity.Oferta;
import com.example.demo.models.CicloModel;
import com.lowagie.text.pdf.PdfAction;

@Service
public class PdfService {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public void createPdf(List<Oferta> ofertas, CicloModel ciclo) throws IOException {
		
		Context context = new Context();
		context.setVariable("ciclo", ciclo);
		context.setVariable("ofertas", ofertas);
		String processHtml = templateEngine.process("pdfOfertas",context);
		String filePdfName = "ofertasCiclo_" + ciclo.getNombre() + "_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date())  + ".pdf";
		
		OutputStream outputStream = new FileOutputStream(filePdfName);
		
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(processHtml);
		renderer.layout();
		renderer.createPDF(outputStream,false);
		renderer.finishPDF();
		outputStream.close();

		try {
		    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(filePdfName).getAbsolutePath());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
