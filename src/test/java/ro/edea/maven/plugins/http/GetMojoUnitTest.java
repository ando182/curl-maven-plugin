package ro.edea.maven.plugins.http;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.localserver.LocalTestServer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetMojoUnitTest extends AbstractMojoTestCase {
	
	private LocalTestServer server = new LocalTestServer(null, null);
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		server.start();
	}
	
	@Test
	public void testMojo() throws Exception {
		File testPom = new File(getBasedir(), "/src/test/resources/ro/edea/maven/plugins/http/get-test-plugin-config.xml");
		assertTrue("File does not exists", testPom.exists());
		GetMojo mojo = (GetMojo) lookupMojo("get", testPom);
		assertNotNull("Plugin not found", mojo);
	}
	
	@Test
	public void testGet() {
		server.register("/test/**", new HttpRequestHandler() {
			@Override
			public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
				// TODO Auto-generated method stub
			}
		});
		
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		server.stop();
	}

}
