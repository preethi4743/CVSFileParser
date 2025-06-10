package com.CSVFile.Controller;


import com.CSVFile.Service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CSVController {
    @Autowired
    CSVService csvService;
    @PostMapping("/upload")
     public String uploadCSVFile(@RequestParam("file") MultipartFile file){

         if(file.isEmpty()){
             return "Please upload a CSV file.";
         }
         csvService.saveCSV(file);
         return "file uploaded and data saved successsfully!!";
     }
     @GetMapping("/view")
    public String getAllData(){
        return csvService.getAllData().toString();
     }
}
