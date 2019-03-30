package web.view;

import beans.models.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class TicketsPdfView extends AbstractPdfView {

	@Override
	@SuppressWarnings("unchecked")
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
									HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Ticket> tickets = (List<Ticket>) model.get("tickets");
		document.add(createPdfPTable(tickets));
	}

	private PdfPTable createPdfPTable(List<Ticket> tickets) {
		PdfPTable table = new PdfPTable(5);
		try {
			table.setWidths(new int[]{180, 300, 200, 200, 150});
			table.addCell("Event name");
			table.addCell("Date");
			table.addCell("User name");
			table.addCell("Seats");
			table.addCell("Price");
		} catch (DocumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		tickets.forEach(ticket -> fillTable(table, ticket));
		return table;
	}

	private void fillTable(PdfPTable table, Ticket ticket) {
		table.addCell(ticket.getEvent().getName());
		table.addCell(ticket.getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		table.addCell(ticket.getUser().getName());
		table.addCell(ticket.getSeats());
		table.addCell(String.valueOf(ticket.getPrice()));
	}

}
