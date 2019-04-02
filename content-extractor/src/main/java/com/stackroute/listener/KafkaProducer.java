package com.stackroute.listener;

import com.stackroute.domain.PdfDocument;
import com.stackroute.exception.EmptyFileException;
import com.stackroute.exception.FileNotFoundException;
import com.stackroute.service.PdfExtractionService;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;

/*
Kafka Producer producing JSON Object for Paragraph tokenizer
 */
@Service
public class KafkaProducer {

    private PdfExtractionService pdfExtractionService;
    private KafkaTemplate<String, PdfDocument> kafkaTemplate2;

    @Autowired
    public KafkaProducer(PdfExtractionService pdfExtractionService , KafkaTemplate<String, PdfDocument> kafkaTemplate2) {
        this.pdfExtractionService = pdfExtractionService;
        this.kafkaTemplate2=kafkaTemplate2;
    }

    private static final String TOPIC="FileText";
    public String postservice(String fileurl) throws IOException, SAXException, NullPointerException, FileNotFoundException, EmptyFileException,
            TikaException
    {

        PdfDocument pdfDocument = pdfExtractionService.extractFromURL(fileurl);
        kafkaTemplate2.send(TOPIC, pdfDocument);
        return "Published successfully";
    }

}
