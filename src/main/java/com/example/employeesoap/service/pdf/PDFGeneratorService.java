package com.example.employeesoap.service.pdf;

import com.example.employeesoap.dto.EmployeeDto;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class PDFGeneratorService {

    private static final String DEFAULT_VALUE = "Undefined";
    private static final int FONT_TITLE_SIZE = 18;
    private static final String TITLE_TEXT = "Employee";
    private static final String FILENAME = "images.jpg";
    private static final String DATE_FORMAT_PATTERN = "HH:mm:ss dd.MM.yyyy";
    private static final String UID = "Uid: ";
    private static final String NAME = "Name: ";
    private static final String SURNAME = "Surname: ";
    private static final String POSITION = "Position: ";
    private static final String GRADE = "Grade: ";
    private static final String DESCRIPTION = "Description: ";
    private static final String AGE = "Age: ";
    private static final String SALARY = "Salary: ";
    private static final String TASKS_UID = "Tasks UID: ";
    private static final String CONTENT_TYPE = "application/pdf";
    private static final String DATE_FORMAT = "yyyy-MM-dd:hh:mm:ss";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String HEADER_VALUE = "attachment; filename=pdf_";
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    public static final int FONT_DATE_OF_DOWNLOAD_SIZE = 11;

    @SneakyThrows
    public void export(HttpServletResponse response, EmployeeDto employeeDto) {
        responseInit(response);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.add(getDateOfDownload());
        document.add(getImage());
        document.add(getParagraph());
        document.add(listGenerate(employeeDto));
        document.close();
    }

    private void responseInit(HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = HEADER_VALUE + currentDateTime + ".pdf";
        response.setHeader(CONTENT_DISPOSITION, headerValue);
    }

    private Jpeg getImage() throws IOException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + FILENAME);
        byte[] bytes = Files.readAllBytes(Paths.get(file.getPath()));
        Jpeg image = new Jpeg(bytes, WIDTH, HEIGHT);
        image.setAlignment(Image.ALIGN_RIGHT);
        image.normalize();
        return image;
    }

    private Paragraph getDateOfDownload() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(FONT_DATE_OF_DOWNLOAD_SIZE);

        Paragraph paragraph = new Paragraph(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)), font);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        return paragraph;
    }

    private Paragraph getParagraph() {
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(FONT_TITLE_SIZE);

        Paragraph paragraph = new Paragraph(TITLE_TEXT, fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        return paragraph;
    }

    private List listGenerate(EmployeeDto employeeDto) {
        List list = new List();
        list.setListSymbol(new Chunk(" "));
        list.add(" ");
        list.setListSymbol(new Chunk(UID));
        list.add(getContent(employeeDto.getUid()));
        list.setListSymbol(new Chunk(NAME));
        list.add(getContent(employeeDto.getName()));
        list.setListSymbol(new Chunk(SURNAME));
        list.add(getContent(employeeDto.getSurname()));
        list.setListSymbol(new Chunk(POSITION));
        list.add(getContent(employeeDto.getPosition()));
        list.setListSymbol(new Chunk(GRADE));
        list.add(getContent(employeeDto.getGrade()));
        list.setListSymbol(new Chunk(DESCRIPTION));
        list.add(getContent(employeeDto.getDescription()));
        list.setListSymbol(new Chunk(AGE));
        list.add(getContent(employeeDto.getAge()));
        list.setListSymbol(new Chunk(SALARY));
        list.add(getContent(employeeDto.getSalary()));
        list.setListSymbol(new Chunk(TASKS_UID));
        list.add(getContent(employeeDto.getTasksUID()));
        return list;
    }

    private String getContent(String content) {
        return content == null || content.isEmpty() ? DEFAULT_VALUE : content;
    }
}