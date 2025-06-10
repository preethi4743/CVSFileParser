package com.CSVFile.Model;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Map;


@Document(collection = "csv_data")
public class CSVFileData {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
    @ElementCollection
    @CollectionTable(name = "csv_data", joinColumns = @JoinColumn(name = "file_id"))
    @MapKeyColumn(name = "column_name")
    @Column(name = "column_value")
    private Map<String, String> data;

    @Override
    public String toString() {
        return "CSVFileData{" +
                "id='" + id + '\'' +
                ", data=" + data +
                '}';
    }
}
