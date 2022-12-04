import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestUnixTime {
	
	public HttpResponse sendRequest(String url) throws IOException {
		HttpUriRequest request = new HttpGet(url);
		return (HttpResponse) HttpClientBuilder.create().build().execute(request);
	}
	
}
