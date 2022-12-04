import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUnixTimeTest {

	private static String BASEURL = "https://helloacm.com/api/unix-timestamp-converter/?cached&s=";
	private static String STRINGGOODPARAM = "2016-01-01%202:3:22";
	private static String EMPTYIN = "";
	private static String NULLIN = null;
	private static String MALLFORMATTED = "hello";
	private static String NUMBER = "1451613802";
	private static String NUMBER0 = "0";
	private static String NUMBERNEG = "-1";
	private static String LARGENO = "1000000000000000";

	public static TestUnixTime tut;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tut = new TestUnixTime();
	}

	@Test
	public void testInputString() throws IOException {
		HttpResponse r = tut.sendRequest(BASEURL + STRINGGOODPARAM);
		assertEquals(200, r.getStatusLine().getStatusCode());
		assertEquals("OK", r.getStatusLine().getReasonPhrase());

		Header[] headers = r.getAllHeaders();

		String contentType = null;
		for (Header header : headers) {
			if(header.getName().equals("Content-Type")) {
				contentType = header.getValue();
				break;
			}
		}
		assertNotNull(contentType);
		assertEquals("application/json", contentType);

		String responseBody = EntityUtils.toString(r.getEntity());
		assertEquals("1451613802", responseBody);

	}

	@Test
	public void testEmptyInput() throws IOException {
		HttpResponse r = tut.sendRequest(BASEURL + EMPTYIN);
		assertEquals(200, r.getStatusLine().getStatusCode());
		assertEquals("OK", r.getStatusLine().getReasonPhrase());

		String responseBody = EntityUtils.toString(r.getEntity());

		assertTrue(!responseBody.isBlank());
		assertEquals("false", responseBody);		
	}

	@Test
	public void testNullInput() throws IOException {
		HttpResponse r = tut.sendRequest(BASEURL + NULLIN);
		assertEquals(200, r.getStatusLine().getStatusCode());
		assertEquals("OK", r.getStatusLine().getReasonPhrase());

		String responseBody = EntityUtils.toString(r.getEntity());

		assertTrue(!responseBody.isBlank());
		assertEquals("false", responseBody);		
	}

	@Test
	public void testMallformattedInput() throws IOException {
		HttpResponse r = tut.sendRequest(BASEURL + MALLFORMATTED);
		assertEquals(200, r.getStatusLine().getStatusCode());
		assertEquals("OK", r.getStatusLine().getReasonPhrase());

		String responseBody = EntityUtils.toString(r.getEntity());

		assertTrue(!responseBody.isBlank());
		assertEquals("false", responseBody);		
	}

	@Test
	public void testNumberInput() throws IOException {
		HttpResponse r = tut.sendRequest(BASEURL + NUMBER);
		assertEquals(200, r.getStatusLine().getStatusCode());
		assertEquals("OK", r.getStatusLine().getReasonPhrase());

		String responseBody = EntityUtils.toString(r.getEntity());

		assertTrue(!responseBody.isBlank());
		assertEquals("\"2016-01-01 02:03:22\"", responseBody);		
	}

	@Test
	public void testNumber0Input() throws IOException {
		HttpResponse r = tut.sendRequest(BASEURL + NUMBER0);
		assertEquals(200, r.getStatusLine().getStatusCode());
		assertEquals("OK", r.getStatusLine().getReasonPhrase());

		String responseBody = EntityUtils.toString(r.getEntity());

		assertTrue(!responseBody.isBlank());
		assertEquals("\"1970-01-01 12:00:00\"", responseBody);		
	}

	@Test
	public void testNumberNegInput() throws IOException {
		HttpResponse r = tut.sendRequest(BASEURL + NUMBERNEG);
		assertEquals(200, r.getStatusLine().getStatusCode());
		assertEquals("OK", r.getStatusLine().getReasonPhrase());

		String responseBody = EntityUtils.toString(r.getEntity());

		assertTrue(!responseBody.isBlank());
		assertEquals("\"1969-12-31 11:59:59\"", responseBody);		
	}

	@Test
	public void testLargeInput() throws IOException {
		final String regex = "\"\\d*-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\"";
		
		HttpResponse r = tut.sendRequest(BASEURL + LARGENO);
		assertEquals(200, r.getStatusLine().getStatusCode());
		assertEquals("OK", r.getStatusLine().getReasonPhrase());

		String responseBody = EntityUtils.toString(r.getEntity());

		assertTrue(!responseBody.isBlank());
		assertTrue(responseBody.matches(regex));
	}

}