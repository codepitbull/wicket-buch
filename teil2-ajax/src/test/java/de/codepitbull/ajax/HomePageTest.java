/**
 * Copyright (C) 2011-2012 by Jochen Mader (pflanzenmoerder@gmail.com)
 * ==============================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.codepitbull.ajax;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class HomePageTest
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		tester.startPage(HomePage.class);

		tester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void demoLabelClick()
	{
		tester.startPage(HomePage.class);

		tester.executeAjaxEvent("demoLabel","onclick");

		tester.assertComponentOnAjaxResponse("demoLabel");
	}

	@Test
	public void demoLabel2mouseOver()
	{
		tester.startPage(HomePage.class);

		tester.executeAjaxEvent("demoLabel2","onmouseover");

		tester.assertComponentOnAjaxResponse("demoLabel2");
	}
}
