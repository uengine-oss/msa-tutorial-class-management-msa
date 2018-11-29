/*
 * Copyright 2014-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hello;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.IsCloseTo.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryIntegrationTests {

	@Autowired CourseRepository repository;


	@Before
	public void setup() {
		repository.deleteAll();
	}

	@Test
	public void createsInvoiceViaAggregation() {

		Course course = new Course();
		course.setTitle("SOA");
		//course.setId(1L);

		course = repository.save(course);

		assertThat(course.getTitle(), is("SOA"));
	}
}
