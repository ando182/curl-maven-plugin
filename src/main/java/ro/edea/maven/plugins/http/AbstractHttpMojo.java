package ro.edea.maven.plugins.http;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.http.HttpMessage;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import ro.edea.maven.plugins.http.common.Constants;
import ro.edea.maven.plugins.http.model.Cookie;
import ro.edea.maven.plugins.http.model.Header;

import java.util.Collection;

/**
 * Created by vlada on 18.10.2016.
 */
public abstract class AbstractHttpMojo extends AbstractMojo {

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

    protected abstract HttpRequestBase createHttpRequest();

    protected void addHeaders(final HttpMessage message, Collection<Header> headers) {
        IteratorUtils.forEach(headers.iterator(), new Closure<Header>() {
            @Override
            public void execute(Header header) {
                message.addHeader(header.getName(), header.getValue());
            }
        });
    }

    protected void addCookies(final BasicCookieStore cookieStore, Collection<Cookie> cookies) {
        IteratorUtils.forEach(cookies.iterator(), new Closure<Cookie>() {
            @Override
            public void execute(Cookie header) {
                BasicClientCookie cookie = new BasicClientCookie(header.getName(), header.getValue());
                cookieStore.addCookie(cookie);
            }
        });
    }
}
