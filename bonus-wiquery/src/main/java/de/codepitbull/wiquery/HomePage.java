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
package de.codepitbull.wiquery;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.odlabs.wiquery.ui.accordion.Accordion;
import org.odlabs.wiquery.ui.dialog.Dialog;
import org.odlabs.wiquery.ui.tabs.Tabs;

import java.util.ArrayList;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		add(createAccordion("accordion"));
		add(createTabs("tabs"));
		add(createDialog("dialog"));
	}

	private Component createAccordion(String wicketId) {
		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add("text1");
		stringList.add("text2");
		stringList.add("text3");
		return new Accordion(wicketId)
				.add(new ListView<String>("listView", Model.<ArrayList<String>>of(stringList)) {
					@Override
					protected void populateItem(ListItem<String> stringListItem) {
						stringListItem.setRenderBodyOnly(true);
						stringListItem.add(new Label("header", "Header " + stringListItem.getIndex()).setRenderBodyOnly(true));
						stringListItem.add(new Label("text", stringListItem.getModelObject()));
					}
				});
	}

	private Component createTabs(String wicketId) {
		final WebMarkupContainer conatiner = new WebMarkupContainer("tabs");
		final Tabs tabs = new Tabs("tabs");
		tabs.add(new Label("default", "Content"));
		conatiner.add(new WebMarkupContainer("hidden").setOutputMarkupId(true).setVisible(false));
		conatiner.add(new AjaxLink<Void>("add") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				Component component = conatiner.get("hidden");
				component.setVisible(true);
				target.add(component);
				tabs.add(target, 1, "Content", component);
			}
		});
		conatiner.add(tabs);
		return conatiner;
	}

	private Component createDialog(String wicketId) {
		WebMarkupContainer container = new WebMarkupContainer(wicketId);
		final Dialog dialog = new Dialog("dialog");
		container.add(new AjaxLink<Void>("openDialog") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				target.appendJavaScript(dialog.open().render());
			}
		});
		container.add(dialog);
		return container;
	}
}
