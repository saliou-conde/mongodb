package com.trianel.handel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class NetworkOperator implements Serializable {

    @Id
    private String networkOperationId;
    @Indexed(unique = true)
    private String name;
    private String networkArea;
}
