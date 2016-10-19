package ro.edea.maven.plugins.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
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

public abstract class AbstractHttpMojo extends AbstractMojo {

	/**
     * Url where the request should be sent
     */
    @Parameter(defaultValue = "false", property = Constants.URL)
    protected String uri;
    /**
     * Represents the file where the output should be stored
     */
    @Parameter(defaultValue = "false", property = Constants.OUTPUT_FILE)
    protected File output;
    /**
     * Set to {@code true} if you want this goal to be skipped, otherwise {@code false}.
     */
    @Parameter(defaultValue = "false", property = Constants.SKIP)
    private boolean skip;
    /**
     * Headers
     */
    @Parameter
    private List<Header> headers;
    /**
     * Cookies
     */
    @Parameter
    private List<Cookie> cookies;
    /**
     * User agent
     */
    @Parameter
    private String userAgent;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (skip) {
            getLog().debug(String.format("Skipping add-resource with address"));
            return;
        }
        
        CloseableHttpClient httpclient = getHttpClient();
                
        try {
        	final HttpRequestBase httpRequest = createHttpRequest();
            final HttpResponse response = httpclient.execute(httpRequest);
            
            
            if (output != null) {
            	try (OutputStream os = new FileOutputStream(output)) {
            		IOUtils.copy(response.getEntity().getContent(), os);
            	} 
            } 
        } catch (Exception e) {
    		getLog().error("Error executing url", e);
        } finally {
        	IOUtils.closeQuietly(httpclient);
		}
    }

    protected abstract HttpRequestBase createHttpRequest();
    
    protected CloseableHttpClient getHttpClient() {
    	
    	final CookieStore cookieStore = new BasicCookieStore();
        addCookies(cookieStore, cookies);
        
    	return HttpClients.custom()
        	.setDefaultCookieStore(cookieStore)
        	.setUserAgent(userAgent)
        	.build();
    }

    protected void addHeaders(final HttpMessage message, Collection<Header> headers) {
        IteratorUtils.forEach(headers.iterator(), new Closure<Header>() {
            @Override
            public void execute(Header header) {
                message.addHeader(header.getName(), header.getValue());
            }
        });
    }

    protected void addCookies(final CookieStore cookieStore, Collection<Cookie> cookies) {
        IteratorUtils.forEach(cookies.iterator(), new Closure<Cookie>() {
            @Override
            public void execute(Cookie header) {
                BasicClientCookie cookie = new BasicClientCookie(header.getName(), header.getValue());
                cookieStore.addCookie(cookie);
            }
        });
    }
    
}
