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
package de.codepitbull.events;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.StringAutoCompleteRenderer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private List<String> choices = new ArrayList<String>();

	public HomePage(final PageParameters parameters) {
		final IModel<String> input = Model.of("");

		add(new Label("inhalt", new PropertyModel<Integer>(choices, "size")) {
				@Override
				public void onEvent(IEvent<?> event) {
					//Verwendung des Ajax-Default-Events zum Updaten
					if(event.getPayload() instanceof  AjaxRequestTarget){
						((AjaxRequestTarget)event.getPayload()).add(this);
					}
				}
			}
			.setOutputMarkupId(true));
		add(new Form("inputForm") {
					@Override
					protected void onSubmit() {
						super.onSubmit();
						//Neuese Element hinzufügen
						choices.add(input.getObject());
					}
				}
				.add(new TextField<String>("autocomplete", input)
						.add(new AutoCompleteBehavior<Object>(new StringAutoCompleteRenderer()) {
							@Override
							protected Iterator<Object> getChoices(String input) {
								List<Object> ret = new ArrayList<Object>();
								for (String choice : choices) {
									if(choice.startsWith(input)) {
										ret.add(choice);
									}
								}
								return ret.iterator();
							}
						})));
		add(new ResetEventLink("resetLink"));
	}

	@Override
	public void onEvent(IEvent<?> event) {
		if (event.getPayload() instanceof ResetEvent) {
			choices.clear();
			//Nach dem Löschen ein Default-Ajax-Event erzeugen und an alle Komponenten schicken
			send(this, Broadcast.BREADTH, ((ResetEvent) event.getPayload()).getTarget());
		}
	}
}

