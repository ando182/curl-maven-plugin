package ro.edea.maven.plugins.http.model;

import lombok.Data;

import java.io.File;

@Data
public class FormField {

    private String name;
    private String type;
    private File file;
}
