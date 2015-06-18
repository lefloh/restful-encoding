/**
 * Copyright (C) 2014 Florian Hirsch
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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Florian Hirsch
 */
@Path("/enc")
public class EncodingResource {

	private Logger LOG = LoggerFactory.getLogger(EncodingResource.class);
	
	@POST
	@Consumes("application/xml;charset=UTF-8")
	@Produces("application/xml;charset=UTF-8")
	public EncodingDTO encode(EncodingDTO dto) {
		LOG.info(String.format("Received [%s]", dto.getName()));
		return dto;
	}
	
	@GET
	@Produces("application/xml;charset=UTF-8")
	public EncodingDTO get() {
		return new EncodingDTO("Государственный академический");
	}
	
}
