package com.selt.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.selt.model.Raport;
import com.selt.model.Toner;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ExportPDF {

    public static ByteArrayInputStream tonerRaport(List<Raport> raportList){

        LocalDate today = LocalDate.now();
        LocalTime actualTime = LocalTime.now();

        Document document;
        document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED,15);
            Font data = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED,15);

            Paragraph p = new Paragraph();
            Paragraph tableHeader=new Paragraph();

            //p.setFont(data);
            tableHeader.setFont(headFont);
            tableHeader.setAlignment(Element.ALIGN_CENTER);

            p.add("Raport wygenerowany o godzinie " +actualTime.getHour()+":"+actualTime.getMinute()+":"+actualTime.getSecond()+ " dnia "+ today.toString() + " ".replaceAll("\\s+", "\n"));

            tableHeader.add("Spis wniosków");
            tableHeader.add(Chunk.NEWLINE);
            tableHeader.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(90);
            table.setWidths(new int[]{5, 3, 5, 5, 3, 8, 5});
            PdfPCell hcell;


            hcell=new PdfPCell(new Phrase("Toner"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Ilość"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Drukarka"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Dział"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("MPK"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Nr. inwentarzowy"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Data"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (Raport raport :raportList) {

                PdfPCell cell;
                cell=new PdfPCell(new Phrase(String.valueOf(raport.getPrinters().getToner().getTonerName())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);


                cell=new PdfPCell(new Phrase(String.valueOf(raport.getCount())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase(String.valueOf(raport.getPrinters().getModel())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase(String.valueOf(raport.getPrinters().getDepartment().getNameOfDepartment())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase(String.valueOf(raport.getPrinters().getDepartment().getMPK())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase(String.valueOf(raport.getPrinters().getInventoryNumber())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase(String.valueOf(raport.getDate())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);




            }

            PdfWriter.getInstance(document, out);
            document.open();
           // document.add(header);
            document.add(p);
            document.add(tableHeader);
            document.add(table);
            document.close();

        }   catch (DocumentException | IOException ex) {

        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
