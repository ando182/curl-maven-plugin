package ro.edea.maven.plugins.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.apache.maven.plugins.annotations.Parameter;

import ro.edea.maven.plugins.http.model.Field;

public abstract class AbstractEntityHttpMojo extends AbstractHttpMojo {

	@Parameter
    protected List<Field> form;
	
	protected void populateRequest(HttpEntityEnclosingRequestBase request) {
		final List<NameValuePair> parameters = new ArrayList<>();
		for (Field field : form) {
			parameters.add(new BasicNameValuePair(field.getName(), field.getValue()));
		}
		request.setEntity(new UrlEncodedFormEntity(parameters, Consts.UTF_8));
	}

}
