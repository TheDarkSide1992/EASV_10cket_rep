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

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Stream;

public class TicketGenerator {
    private Document doc;
    private String codeID;
    public TicketGenerator() {
        codeID = "";
    }
    public void makeTicket(Event event, Ticket ticket) throws Exception{
        //setFilepath();
        //TODO if possible make it possible to select ones own filepath
        generateCodeID(event, ticket);

        generateTicket(event, ticket);
        //TODO make pdf hold data of event

        System.out.println("success");
    }

    private void generateCodeID(Event event, Ticket ticket){
        int id = ticket.getTicketID();
        int date = event.getEventDate().hashCode();
        int name = event.getEventTitle().hashCode();

        StringBuilder stb = new StringBuilder();

        stb.append(id);
        stb.append(date);
        stb.append(name);

        codeID = stb.toString();
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
                    "\nOrganised by: " + event.getEventCoordinator() +" + "+ event.getEventCollaborator() +
                    "\n\nExtra: " +
                    "\n          Qr - Code"
            );

            var paragraph2 = new Paragraph("\n\n" + codeID);

            doc.add(paragraph1);
            if (event.getImageByte().length > 12) doc.add(Image.getInstance(event.getImageByte()));

            Path qrCode = Path.of("data/Images/RickQR.PNG");
            Image img = Image.getInstance(String.valueOf(qrCode));
            img.scaleAbsoluteWidth(100);
            img.scaleAbsoluteHeight(100);
            doc.add(img);
            doc.add(paragraph2);
            Path barCode = Path.of("data/Images/barcode.jpg");
            Image img2 = Image.getInstance(String.valueOf(barCode));
            img2.scaleAbsoluteWidth(200);
            img2.scaleAbsoluteHeight(50);
            doc.add(img2);

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
