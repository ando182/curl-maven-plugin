package ro.edea.maven.plugins.http;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "delete", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class DeleteMojo extends AbstractHttpMojo {

	@Override
	protected HttpRequestBase createHttpRequest() {
		return new HttpDelete(uri);
	}

}
