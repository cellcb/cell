package org.anne.junittest;

import org.anne.blog.BlogService;
import org.anne.image.imageGroup.Imagegroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class T {
	@Autowired
	TestServiceImpl service;

	@Autowired
	BlogService blogService;

	@SuppressWarnings("rawtypes")
	@Test
	public void test() {
		org.anne.junittest.Test test = new org.anne.junittest.Test();
		test.setB(123);
		test.setA("Imagegroup");
		service.insert(test, Imagegroup.class);
		// for (Iterator iterator = query.iterator(); iterator.hasNext();) {
		// org.anne.test.Test t = (org.anne.test.Test) iterator.next();
		// System.out.println(t.getA());
		// }
	}

	@Test
	public void addBlogTest() {
	}
}
