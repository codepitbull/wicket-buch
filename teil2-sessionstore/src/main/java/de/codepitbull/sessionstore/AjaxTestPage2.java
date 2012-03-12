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
package de.codepitbull.sessionstore;

import de.codepitbull.sessionstore.component.TogglebarerText;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AjaxTestPage2 extends WebPage {
	private static final long serialVersionUID = 1L;

	public AjaxTestPage2(final PageParameters parameters) {
		add(new TogglebarerText("text1", Model.<String>of("text1")));
		add(new TogglebarerText("text2", Model.<String>of("text2")));
		add(new TogglebarerText("text3", Model.<String>of("text3")));
		add(new AjaxLink<Void>("weiter") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(AjaxTestPage1.class);
			}
		});
	}
}
