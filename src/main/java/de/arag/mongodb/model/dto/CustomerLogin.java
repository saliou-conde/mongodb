package de.arag.mongodb.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerLogin {
    private String username;
    private String password;
}