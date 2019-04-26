package beans.converters.impl;

import beans.dtos.TicketDTO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class TicketsToPdfMessageConverter extends AbstractHttpMessageConverter<List<TicketDTO>> {

	public TicketsToPdfMessageConverter() {
		super(new MediaType("application", "pdf"));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	protected List<TicketDTO> readInternal(Class<? extends List<TicketDTO>> aClass, HttpInputMessage inputMessage)
			throws HttpMessageNotReadableException {
		return null;
	}

	@Override
	protected void writeInternal(List<TicketDTO> tickets, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, baos);
			buildDocument(document, tickets);
			outputMessage.getBody().write(baos.toByteArray());
		} catch (DocumentException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void buildDocument(Document document, List<TicketDTO> tickets) throws DocumentException {
		document.open();
		document.add(createPdfTable(tickets));
		document.close();
	}

	private PdfPTable createPdfTable(List<TicketDTO> tickets) {
		PdfPTable table = new PdfPTable(4);
		try {
			table.setWidths(new int[]{180, 300, 200, 200});
			table.addCell("Event name");
			table.addCell("Date");
			table.addCell("User email");
			table.addCell("Seats");
		} catch (DocumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		tickets.forEach(ticket -> fillTable(table, ticket));
		return table;
	}

	private void fillTable(PdfPTable table, TicketDTO ticketDTO) {
		table.addCell(ticketDTO.getEventName());
		table.addCell(ticketDTO.getEventDate());
		table.addCell(ticketDTO.getUserEmail());
		table.addCell(ticketDTO.getSeats());
	}

}
