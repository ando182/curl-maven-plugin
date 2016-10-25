package ro.edea.maven.plugins.http;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Created by vlada on 18.10.2016.
 */
@Mojo(name = "post", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class PostMojo extends AbstractEntityHttpMojo {

    protected HttpRequestBase createHttpRequest() {
        HttpEntityEnclosingRequestBase request = new HttpPost(uri);
        populateRequest(request);
        return request;
    }

}