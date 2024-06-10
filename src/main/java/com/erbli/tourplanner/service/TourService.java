package com.erbli.tourplanner.service;

import com.erbli.tourplanner.dtos.TourDto;
import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.mapper.TourDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
@Slf4j
public class TourService {
    @Autowired
    TourDtoMapper tourDtoMapper;
    public TourDto getTours() {
        Tour tour = Tour.builder()
                .name("This is a tour")
                .tourDescription("Description")
                .fromm("From")
                .too("To")
                .transportType("Train")
                .tourDistance("2km")
                .estimatedTime("2h")
                .build();

        TourDto tourDto = tourDtoMapper.mapTourToTourDto(tour);

        return tourDto;
    }

    public static byte[] summarizeReport(List<Tour> tourList) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Tours PDF Generated Report").setBold().setFontSize(20));

        for (Tour tour : tourList) {
            document.add(new Paragraph("Tour:").setBold());
            document.add(new Paragraph("ID: " + tour.getId()));
            document.add(new Paragraph("Name: " + tour.getName()));
            document.add(new Paragraph("Tour Description: " + tour.getTourDescription()));
            document.add(new Paragraph("From: " + tour.getFromm()));
            document.add(new Paragraph("Too: " + tour.getToo()));
            document.add(new Paragraph("Transport Type: " + tour.getTransportType()));
            document.add(new Paragraph("Tour Distance: " + tour.getTourDistance()));
            document.add(new Paragraph("Estimated Time: " + tour.getEstimatedTime()));
            document.add(new Paragraph("\n"));
        }

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] tourReport(Tour tour) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Tour PDF Generated Report").setBold().setFontSize(20));

        document.add(new Paragraph("Tour:").setBold());
        document.add(new Paragraph("ID: " + tour.getId()));
        document.add(new Paragraph("Name: " + tour.getName()));
        document.add(new Paragraph("Tour Description: " + tour.getTourDescription()));
        document.add(new Paragraph("From: " + tour.getFromm()));
        document.add(new Paragraph("To: " + tour.getToo()));
        document.add(new Paragraph("Transport Type: " + tour.getTransportType()));
        document.add(new Paragraph("Tour Distance: " + tour.getTourDistance()));
        document.add(new Paragraph("Estimated Time: " + tour.getEstimatedTime()));

        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}