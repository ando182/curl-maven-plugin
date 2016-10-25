package ro.edea.maven.plugins.http;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.junit.Test;

public class PostMojoUnitTest extends AbstractUnitTest {
	
	@Test
	public void testMojo() throws Exception {
		File testPom = new File(getBasedir(), "/src/test/resources/ro/edea/maven/plugins/http/post-test-plugin-config.xml");
		assertTrue("File does not exists", testPom.exists());
		PostMojo mojo = (PostMojo) lookupMojo("post", testPom);
		assertNotNull("Plugin not found", mojo);
	}
	
	@Test
	public void testGet() throws Exception {
		server.register("/test/**", new HttpRequestHandler() {
			@Override
			public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
				// TODO Auto-generated method stub
			}
		});
		File testPom = new File(getBasedir(), "/src/test/resources/ro/edea/maven/plugins/http/post-test-plugin-config.xml");
		PostMojo mojo = (PostMojo) lookupMojo("post", testPom);
		setVariableValueToObject(mojo, "uri", super.getUrl() + "/test");
		
		mojo.execute();
		
	}

}
