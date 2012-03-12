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
package de.codepitbull.behavior;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
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
import org.apache.wicket.util.time.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private Integer roundtrips = 0;


	public HomePage(final PageParameters parameters) {
		final List<String> choices = new ArrayList<String>();
		final IModel<String> input = Model.of("");
		add(new Label("roundtrips", new PropertyModel<Integer>(this, "roundtrips"))
				.add(new AjaxSelfUpdatingTimerBehavior(Duration.ONE_SECOND) {
					@Override
					protected void onPostProcessTarget(AjaxRequestTarget target) {
						roundtrips++;
					}}));
		add(new Form("inputForm") {
					@Override
					protected void onSubmit() {
						super.onSubmit();
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
	}
}

