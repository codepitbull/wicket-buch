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

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class KaffeeWebSession extends WebSession {
	private String lieferOrt = "Entwickleroffice";
	private Integer anzahlKaffee = 10;

	public KaffeeWebSession(Request request) {
		super(request);
	}

	public String getLieferOrt() {
		return lieferOrt;
	}

	public void setLieferOrt(String lieferOrt) {
		this.lieferOrt = lieferOrt;
	}

	public Integer getAnzahlKaffee() {
		return anzahlKaffee;
	}

	public void mehrKaffee() {
		anzahlKaffee++;
	}

	public void wenigerKaffee() {
		if (anzahlKaffee > 0) {
			anzahlKaffee--;
		}
	}

	public void bestellungAbschicken() {
		System.out.println("Bestellung von " + anzahlKaffee
				+ " Kaffe für " + lieferOrt
				+ " wurde abgeschickt und wird ignoriert.");
		bestellungLeeren();
	}

	public void bestellungLeeren() {
		anzahlKaffee = 0;
	}
}
