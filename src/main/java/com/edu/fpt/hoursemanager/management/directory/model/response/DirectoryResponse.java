package com.edu.fpt.hoursemanager.management.directory.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectoryResponse {
    private String role;
    private String directoryName;
    private String directoryUrl;
    private String mainDirectoryName;
    private String imageName;
}
