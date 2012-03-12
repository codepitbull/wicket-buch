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
package de.codepitbull.weld;

import net.ftlines.wicket.cdi.CdiConfiguration;
import org.apache.wicket.protocol.http.WebApplication;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 *
 * @see de.codepitbull.weld.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	@Override
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public void init() {
		super.init();
		try {
			BeanManager manager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
			new CdiConfiguration(manager).configure(this);
		} catch (NamingException ex) {
			throw new RuntimeException(ex);
		}
	}
}
