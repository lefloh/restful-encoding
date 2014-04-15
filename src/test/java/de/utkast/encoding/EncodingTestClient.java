/**
 * Copyright (C) 2014 Florian Hirsch fhi@adorsys.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.utkast.encoding;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @author Florian Hirsch
 */
public class EncodingTestClient {

	@Test
	public void testEncoding() throws Exception {
		
		String input = "Государственный академический";
		String url = "http://localhost:8080/restful-encoding/enc";
		
		EncodingDTO dto = new EncodingDTO(input);
		
		JAXBContext ctx = JAXBContext.newInstance(EncodingDTO.class);
		StringWriter writer = new StringWriter();
		ctx.createMarshaller().marshal(dto, writer);
		
		System.out.println(String.format("Will send: %s", writer.toString()));
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		HttpEntity entity = new ByteArrayEntity(writer.toString().getBytes("UTF-8"));
		post.setEntity(entity);
		post.setHeader("Content-Type", "application/xml;charset=UTF-8");
		post.setHeader("Accept", "application/xml;charset=UTF-8");
		
		HttpResponse response = client.execute(post);
		String output = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		assertEquals(writer.toString(), output);
	}
	
}
