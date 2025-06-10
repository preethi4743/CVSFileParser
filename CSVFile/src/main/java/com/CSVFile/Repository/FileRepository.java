package com.CSVFile.Repository;


import com.CSVFile.Controller.CSVController;
import com.CSVFile.Model.CSVFileData;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<CSVFileData, String> {
}
