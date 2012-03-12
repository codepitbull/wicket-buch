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
package de.codepitbull.markup;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private boolean renderPanel1 = true;

	public HomePage(final PageParameters parameters) {
		add(new Label("erstesLabel","Erstes Label").setOutputMarkupId(true));
		add(new Label("zweitesLabel","Zweites Label").setOutputMarkupId(true).setVisible(false));
		add(new Label("drittesLabel","Drittes Label").setOutputMarkupId(true));
		add(new Fragment("panel", "fragment1", this).setOutputMarkupId(true));
		add(new AjaxLink<Void>("changeVisibility") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				Component fragment;
				if (renderPanel1) {
					renderPanel1 = false;
					fragment = new Fragment("panel", "fragment2", HomePage.this).setOutputMarkupId(true);
				}
				else {
					renderPanel1 = true;
					fragment = new Fragment("panel", "fragment1", HomePage.this).setOutputMarkupId(true);
				}
				HomePage.this.get("panel").replaceWith(fragment);
				target.add(fragment);
			}
		});

	}
}

