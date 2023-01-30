package com.edu.fpt.hoursemanager.management.directory.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainDirectoryFilResponse {
    private String name;
    private String imageName;
    private List<SubDirectoryResponse> listSubDirectory;
}
