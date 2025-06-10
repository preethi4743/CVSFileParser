package com.CSVFile.Service;


import com.CSVFile.Model.CSVFileData;
import com.CSVFile.Repository.FileRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CSVService {

    @Autowired
    private FileRepository fileRepo;

    @Transactional // all inserted records will be rolled back(exception) and prevents partial data insertion and ensures operations are atomic.
    public void saveCSV(MultipartFile file) {
        List<CSVFileData> fileDataList = new ArrayList<>();

        try (
                BufferedReader bReader = new BufferedReader(new InputStreamReader(file.getInputStream()));//Reads the file using BR
                CSVParser csvParser = new CSVParser(bReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())//1st is treated as Header,Headers are case-insensitve,spaces are removed
        ) {
            for (CSVRecord record : csvParser)//to read each record
                 {
                Map<String, String> dataMap = record.toMap();

                CSVFileData csvFileData = new CSVFileData();
                csvFileData.setData(dataMap); // Ensure CSVFileData entity supports this

                fileDataList.add(csvFileData);
            }

            // Batch save for performance improvement
            fileRepo.saveAll(fileDataList);

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse the CSV file: " + e.getMessage());
        }
    }

    public List<CSVFileData> getAllData() {
        return fileRepo.findAll();
    }
}
