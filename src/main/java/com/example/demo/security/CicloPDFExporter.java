package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Oferta;
import com.example.demo.service.OfertaService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CicloPDFExporter {
	
	@Autowired
	private OfertaService ofertaService;

	private List<Oferta> ofertas;

	public CicloPDFExporter(List<Oferta> ofertas) {
		super();
		this.ofertas = ofertas;
	}
	
	public void export(HttpServletResponse response, Ciclo ciclo) throws DocumentException, IOException{
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(15);
		
		Paragraph p = new Paragraph("Lista de ofertas por ciclo",font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		Paragraph p1 = new Paragraph("Ciclo: "+ ciclo.getNombre(),font);
		Paragraph p2 = new Paragraph("Tipo: "+ ciclo.getTipo(),font);
		
		Image img = Image.getInstance("src\\main\\resources\\static\\assets\\img\\icono.png");
		
		document.add(img);
		document.add(p);
		document.add(p1);
		document.add(p2);
		
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f,3f,3.5f,1.5f,3.5f,2.5f,3f});
		table.setSpacingBefore(10);
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		
		document.close();
	}
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(11);
		
		cell.setPhrase(new Phrase("ID",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Titular",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Descripcion",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Candidatos",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Requisitos",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Fecha maxima",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("RRHH",font));
		table.addCell(cell);
		
	}
	
	private void writeTableData(PdfPTable table) {
		for (Oferta oferta : ofertas) {
			table.addCell(String.valueOf(oferta.getId()));
			table.addCell(oferta.getTitular());
			table.addCell(oferta.getDescripcion());
			table.addCell(String.valueOf(oferta.getNumCandidatos()));
			table.addCell(oferta.getRequisitos());
			table.addCell(String.valueOf(oferta.getFechamax()));
			table.addCell(oferta.getRrhhid().getNombre()+" "+oferta.getRrhhid().getApellidos());
		}
	}
	
	
}
