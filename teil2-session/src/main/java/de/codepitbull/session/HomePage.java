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
package de.codepitbull.session;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final String ANZAHL_KAFFEE = "anzahlKaffee";
	private static final String LIEFER_ORT = "lieferOrt";
	private static final String KAFFEE_FORMULAR = "kaffeeFormular";

	public HomePage(final PageParameters parameters) {
		Form kaffeeFormular = new Form(KAFFEE_FORMULAR);
		kaffeeFormular.setOutputMarkupId(true);
		add(kaffeeFormular);

		kaffeeFormular.add(new Label(ANZAHL_KAFFEE, new PropertyModel<Integer>(this, "session.anzahlKaffee")).setOutputMarkupId(true));
		kaffeeFormular.add(new TextField<String>(LIEFER_ORT, new PropertyModel<String>(this, "session.lieferOrt")).setOutputMarkupId(true));
		kaffeeFormular.add(new AjaxLink<Void>("mehrKaffee") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				((KaffeeWebSession) KaffeeWebSession.get()).mehrKaffee();
				target.add(HomePage.this.get(KAFFEE_FORMULAR));
			}
		});
		kaffeeFormular.add(new AjaxLink<Void>("wenigerKaffee") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				((KaffeeWebSession) KaffeeWebSession.get()).wenigerKaffee();
				target.add(HomePage.this.get(KAFFEE_FORMULAR));
			}
		});
		kaffeeFormular.add(new AjaxSubmitLink("submitLink") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				((KaffeeWebSession) KaffeeWebSession.get()).bestellungAbschicken();
				target.add(HomePage.this.get(KAFFEE_FORMULAR));
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
			}
		});
	}
}
