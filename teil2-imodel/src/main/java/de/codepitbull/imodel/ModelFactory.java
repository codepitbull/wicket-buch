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

import ch.lambdaj.function.argument.Argument;
import ch.lambdaj.function.argument.ArgumentsFactory;
import org.apache.wicket.model.PropertyModel;

/* www.starjar.com
* Copyright (c) 2011 Peter Henderson. All Rights Reserved.
* Licensed under the Apache License, Version 2.0
*/
//Taken from https://cwiki.apache.org/WICKET/working-with-wicket-models.html#WorkingwithWicketmodels-LambdaJ
public class ModelFactory {
	public static <T> PropertyModel<T> model(Object value, T proxiedValue) {
		Argument<T> a = ArgumentsFactory.actualArgument(proxiedValue);
		String invokedPN = a.getInkvokedPropertyName();
		PropertyModel<T> m = new PropertyModel<T>(value, invokedPN);
		return m;
	}
}



