package com.enigma.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.service.ReportService;

@RestController
public class ReportController {
	@Autowired
	ReportService reportService;
	
    @GetMapping("/report")
    public ResponseEntity<byte[]> getReport() {
        byte[] report = reportService.doGetReport();
        if (report != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("inline", "harbour.pdf");
            headers.add("reportName", "harbour.pdf");
            return ResponseEntity
                    .ok()
                    .contentLength(report.length)
                    .headers(headers)
                    .body(report);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
