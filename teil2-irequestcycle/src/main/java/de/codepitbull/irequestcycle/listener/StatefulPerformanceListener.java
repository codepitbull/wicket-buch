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
package de.codepitbull.irequestcycle.listener;

import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (C) 2012 by Jochen Mader (pflanzenmoerder@gmail.com)
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
public class StatefulPerformanceListener extends AbstractRequestCycleListener{
	private static final Logger LOG = LoggerFactory.getLogger(StatefulPerformanceListener.class);

	private Map<RequestCycle, Long> requestCycleToStartTimeMap =  new ConcurrentHashMap<RequestCycle, Long>();
	@Override
	public void onBeginRequest(RequestCycle cycle) {
		requestCycleToStartTimeMap.put(cycle, System.currentTimeMillis());
	}

	@Override
	public void onEndRequest(RequestCycle cycle) {
		super.onEndRequest(cycle);
		if(requestCycleToStartTimeMap.containsKey(cycle)) {
			LOG.debug("Request ("+cycle.getRequest().getUrl()+") benötigte "+(System.currentTimeMillis()-requestCycleToStartTimeMap.remove(cycle))+" ms");
		}
		else
			LOG.warn("Request ("+cycle.getRequest().getUrl()+") abgebrochen");
	}
}
