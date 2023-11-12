package com.example.omat.files.exporting;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.example.omat.common.Month;
import com.example.omat.students.Group;
import com.example.omat.students.Student;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PDFFileExport extends CommonFileExport {
    public PDFFileExport(String exportDirectory, Object extraData) {
        super(exportDirectory, extraData);
    }

    @Override
    public void export(ArrayList<Group> groups, ArrayList<Student> students) {
        try {
            var document = new Document();
            var stream = new FileOutputStream(getTargetDirectory());
            var writer = PdfWriter.getInstance(document, stream);

            document.open();

            document.setPageSize(PageSize.A3.rotate());
            document.newPage();

            var month = (Month) getExtraData();
            document.add(new Paragraph("Student attendance table"));
            document.add(new Paragraph("Month: " + month.getFormatedString()));

            int days = month.getDays() + 1;
            PdfPTable table = new PdfPTable(days);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            float[] columnWidths = new float[days];
            Arrays.fill(columnWidths, 1f);
            columnWidths[0] = 2.5f;
            table.setWidths(columnWidths);

            table.addCell(new PdfPCell(new Paragraph("Name")));
            // table.addCell(new PdfPCell(new Paragraph("Surname")));

            for (int i = 0; i < month.getDays(); ++i) {
                PdfPCell cell = new PdfPCell(new Paragraph("Day " + (i + 1)));
                table.addCell(cell);
            }

            for (Student student : students) {
                table.addCell(student.getName());

                for (int i = 1; i < table.getNumberOfColumns(); ++i) {
                    boolean value = student.getAttendanceData().months.get(month).getDay(i - 1);
                    table.addCell(value ? "+" : " ");
                }
            }

            document.add(table);
            document.close();
            writer.close();
        } catch(Exception ex) {

        }
    }
}
