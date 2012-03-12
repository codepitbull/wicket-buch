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
package de.codepitbull.sessionstore.component;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class TogglebarerText extends Panel {
	public TogglebarerText(String id, IModel<String> text) {
		super(id, text);
		add(new Label("text", text).setOutputMarkupPlaceholderTag(true).setOutputMarkupId(true));
		add(new AjaxLink<Void>("textToggle") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				Component text = TogglebarerText.this.get("text");
				if (text.isVisible())
					text.setVisible(false);
				else
					text.setVisible(true);
				target.add(text);
				text.modelChanged();
			}
		});
	}
}
