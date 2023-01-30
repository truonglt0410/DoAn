package com.edu.fpt.hoursemanager.management.directory.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubDirectoryResponse {
    private String name;
    private String url;
}