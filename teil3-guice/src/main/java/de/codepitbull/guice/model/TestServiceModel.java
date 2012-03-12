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
package de.codepitbull.guice.model;

import com.google.inject.Inject;
import de.codepitbull.guice.service.TestService;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class TestServiceModel extends AbstractReadOnlyModel<String> {
	@Inject
	private TestService testService;
	{
		Injector.get().inject(this);
	}
	@Override
	public String getObject() {
		return testService.getName();
	}
}
