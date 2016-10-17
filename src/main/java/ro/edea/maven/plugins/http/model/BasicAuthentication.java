package ro.edea.maven.plugins.http.model;

import lombok.Data;

@Data
public class BasicAuthentication {

    private String username;
    private String password;

}
