/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
package org.eclipse.php.internal.core.preferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: This class is a factory for ProjectPreferencePropagators. It is
 * a singleton that contains a Map of ProjectPreference propagators based on the
 * node qualifier (which is basically the plugin identifier for that preference)
 * 
 * @author Eden K.,2006
 * 
 */
public class PreferencePropagatorFactory {

	private static PreferencePropagatorFactory instance = new PreferencePropagatorFactory();
	// map of <String (Node Qualifier), ProjectPreferencePropagator>
	private final Map<String, PreferencesPropagator> propagatorsMap = new HashMap<>();

	private PreferencePropagatorFactory() {
	};

	/**
	 * Returns a single instance of the PreferencePropagatorFactory.
	 * 
	 * @return the singelton of ProjectPreferencePropagatorFactory
	 */
	public static PreferencePropagatorFactory getInstance() {
		return instance;
	}

	/**
	 * Returns a single instance of a {@link PreferencesPropagator} according to the
	 * given nodeQualifier and the preferences store.
	 * 
	 * @param nodeQualifier
	 * @return the ProjectPreferencesPropagator given a nodeQualifier and a
	 *         preference store
	 */
	public static PreferencesPropagator getPreferencePropagator(String nodeQualifier) {
		PreferencePropagatorFactory factory = getInstance();
		if (factory.propagatorsMap.containsKey(nodeQualifier)) {
			return factory.propagatorsMap.get(nodeQualifier);
		} else {
			PreferencesPropagator propagator = new PreferencesPropagator(nodeQualifier);
			factory.propagatorsMap.put(nodeQualifier, propagator);
			return propagator;
		}
	}

}
