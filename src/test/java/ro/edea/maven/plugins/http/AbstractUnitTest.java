package ro.edea.maven.plugins.http;

import org.apache.http.localserver.LocalTestServer;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractUnitTest extends AbstractMojoTestCase {

	protected LocalTestServer server = new LocalTestServer(null, null);

	@Before
	public void setUp() throws Exception {
		super.setUp();
		server.start();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		server.stop();
	}
	
	protected String getUrl() {
		return "http:/" + server.getServiceAddress();
	}

}
