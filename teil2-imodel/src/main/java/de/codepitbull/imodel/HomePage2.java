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
package de.codepitbull.imodel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.ComponentModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage2 extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final String ANZAHL_KAFFEE = "anzahlKaffee";
	private static final String LIEFER_ORT = "lieferOrt";
	private static final String KAFFEE_FORMULAR = "kaffeeFormular";

	private String textField = "default";

	public HomePage2(final PageParameters parameters) {

		ComponentModel componentModel = new ComponentModel();
		add(new Form<Void>("testForm").
				add(new Label("testText", new ResourceModel("beispielText"))).
				add(new TextField<String>("testInput", new PropertyModel<String>(this, "textField"))).
				add(new AjaxSubmitLink("submit") {
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						System.out.println("Value: " + HomePage2.this.textField);
					}

					@Override
					protected void onError(AjaxRequestTarget target, Form<?> form) {
					}
				})
		);
	}
}
