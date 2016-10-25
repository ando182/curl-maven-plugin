package ro.edea.maven.plugins.http;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import ro.edea.maven.plugins.http.common.Constants;
import ro.edea.maven.plugins.http.model.BasicAuthentication;
import ro.edea.maven.plugins.http.model.Cookie;
import ro.edea.maven.plugins.http.model.Field;
import ro.edea.maven.plugins.http.model.Header;

import java.util.List;

@Deprecated
@Mojo(name = "http", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class CurlMojo extends AbstractMojo {

    @Parameter
    private List<Cookie> cookies;
    @Parameter
    private List<Header> headers;
    @Parameter
    private List<Field> form;
    @Parameter
    private String userAgent;
    @Parameter
    private BasicAuthentication basic;

    /**
     * Set to {@code true} if you want this goal to be skipped, otherwise {@code false}.
     */
    @Parameter(defaultValue = "false", property = Constants.SKIP)
    private boolean skip;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (skip) {
            getLog().debug(String.format("Skipping add-resource with address"));
            return;
        }
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        try {
            HttpGet httpget = new HttpGet("https://someportal/");
            CloseableHttpResponse response1 = httpclient.execute(httpget);
        } catch (Exception e) {

        }
    }
}
