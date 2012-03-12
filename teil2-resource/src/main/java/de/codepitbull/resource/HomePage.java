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
package de.codepitbull.resource;

import de.codepitbull.resource.css.CssResources;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.PackageResourceReference;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		//Erzeugung eines dynamischen Bildes.
		//Der Browser wird das Bild aggressiv cachen, eine gute Gelegenheit
		//IResourceSettings.setCachingStrategy(IResourceCachingStrategy) auszuprobieren
		add(new Image("dynamicImage", new DynamicImageResource() {
			@Override
			protected byte[] getImageData(Attributes attributes) {
				BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = image.createGraphics();
				g2d.setColor(Color.red);
				g2d.fill(new Ellipse2D.Float(0, 0, 50, 50));
				g2d.setColor(Color.blue);
				g2d.fill(new Rectangle2D.Float(25, 25, 50, 50));
				g2d.dispose();
				return toImageData(image);
			}
		}));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderCSSReference(new PackageResourceReference(CssResources.class, "style.css"));
	}
}

