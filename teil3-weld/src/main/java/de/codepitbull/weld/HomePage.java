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

import de.codepitbull.weld.conversation.ConversationContainer;
import de.codepitbull.weld.service.TestService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@Inject
	TestService testService;
	@Inject
	ConversationContainer conversationContainer;
	@Inject
	Conversation conversation;

	public HomePage() {
		conversation.begin();
		add(new Label("testText", new AbstractReadOnlyModel<Object>() {
			@Override
			public Object getObject() {
				return testService.getName();
			}
		}));
		add(new Form("conversationForm")
				.add(new TextField<String>("inputField", new PropertyModel(conversationContainer, "storedValue")))
				.add(new AjaxSubmitLink("submit") {
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						target.add(HomePage.this.get("conversationForm"));
					}

					@Override
					protected void onError(AjaxRequestTarget target, Form<?> form) {
					}
				})
				.add(new AjaxSubmitLink("commit") {
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						conversation.end();
						setResponsePage(HomePage.class);
					}

					@Override
					protected void onError(AjaxRequestTarget target, Form<?> form) {
					}
				}));
	}
}
