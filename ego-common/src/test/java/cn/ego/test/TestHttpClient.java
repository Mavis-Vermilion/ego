package cn.ego.test;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {

	@Test
	public void doGet() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet("https://www.baidu.com");
		HttpClient client = HttpClients.createDefault();
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, Charset.defaultCharset());
		System.out.println(string);
	}
}
