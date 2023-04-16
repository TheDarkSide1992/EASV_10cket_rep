package bll;
// com.itextpdf.maven:itextdoc:2.0.0
// using itextpdf library

import be.Event;
import be.Ticket;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.DirectoryChooser;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Stream;

public class TicketGenerator {
    private Document doc;
    public TicketGenerator() {

    }
    public void makeTicket(Event event, Ticket ticket) throws Exception{
        //setFilepath();
        //TODO if posible make it posible to sellect ones own filepath

        generateTicket(event, ticket);
        //TODO make pdf hold data of event

        System.out.println("succes");
    }

    private void generateTicket(Event event, Ticket ticket) throws Exception{
        try {
            //Makes pdf file document
            doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream( "Ticket_" + event.getEventTitle() + ".PDF"));

            doc.open();

            //Prints information into PDF
            var bold = new Font(Font.FontFamily.HELVETICA, 20, Font.NORMAL);
            var paragraph1 = new Paragraph(" You have bought ticket " + event.getEventLocation() + "" +
                    "\n congratulation \n \n" +
                    "Location: \n" + event.getEventLocation() +
                    "TIME: \n" + event.getEventDate() +" : "+ event.getEventStartTime() + "\n \n" +
                    "Description: \n" + event.getEventDescription() + "\n \n" + event.getEventImage() +
                    "Extra: " + ticket.getTicketID() + "\n\n" + ticket.getTicketBarCode() +"\n"+ ticket.getTicketQRCode() +
                    "\n\n Organised by: " + event.getEventCoordinator() +" + "+ event.getEventCollaborator()
            );

            /*
            var table = new PdfPTable(2);
            //Creates a table where there can be inserted data, might not be neded
            Stream.of("Ticket", "Content").forEach(table::addCell);

            Arrays.stream(ChronoUnit.values()).forEach(val -> {
                table.addCell(val.toString());
                table.addCell(val.getDuration().toString());
            });
            paragraph.add(table);
             */
            doc.add(paragraph1);
            if (event.getImageByte().length > 12) doc.add(Image.getInstance(event.getImageByte()));

            Path barCode = Path.of("data/Images/RickQR.PNG");
            Image img = Image.getInstance(String.valueOf(barCode));
            doc.add(img);

            //Classes file to avoid memory leak
            doc.close();

        } catch (Exception e){
            e.printStackTrace();
            doc.close();
            throw new Exception(e);
        } finally {
            doc = null;
        }
    }
}