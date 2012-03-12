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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Page extends WebPage {
	private static final long serialVersionUID = 1L;

	public Page(final PageParameters parameters) {
		Bean bean = new Bean();

		add(new Form("form")
				.add(new Label("firstNameLabel", new ResourceModel("firstName")))
				.add(new TextField<String>("firstName", new PropertyModel<String>(bean, "firstName")))
				.add(new Label("lastNameLabel", new ResourceModel("lastName")))
				.add(new TextField<String>("lastName", new PropertyModel<String>(bean, "lastName")))
				.add(new AjaxSubmitLink("submit") {
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

					}

					@Override
					protected void onError(AjaxRequestTarget target, Form<?> form) {

					}
				}));

	}

	private class Bean {
		String firstName;
		String lastName;
	}
}
