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

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		add(new Label("demoLabel", "Klick mich!!!!!")
				.setOutputMarkupId(true)
				.add( new AjaxEventBehavior("onclick") {
						@Override
						protected void onEvent(AjaxRequestTarget target) {
							((IModel<String>)getComponent().getDefaultModel()).setObject("Danke");
							target.add(getComponent());
						}
					})
		);

	    add(new Label("demoLabel2", "Touch me!!")
				.setOutputMarkupId(true)
				.add(new AjaxEventBehavior("onmouseover") {
						@Override
						protected void onEvent(AjaxRequestTarget target) {
							MultiLineLabel demoLabel = new MultiLineLabel("demoLabel2", "Vielen Dank!\nErnsthaft!");
							getComponent().replaceWith(demoLabel);
							target.add(demoLabel);
						}
					})
	    );
    }
}
