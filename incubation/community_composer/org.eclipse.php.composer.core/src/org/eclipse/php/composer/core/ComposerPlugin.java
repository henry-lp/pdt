/*******************************************************************************
 * Copyright (c) 2012, 2016 PDT Extension Group and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     PDT Extension Group - initial API and implementation
 *******************************************************************************/
package org.eclipse.php.composer.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.php.composer.core.model.ModelAccess;
import org.eclipse.php.composer.core.resources.IComposerProject;
import org.eclipse.php.composer.internal.core.resources.ComposerProject;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class ComposerPlugin extends AbstractUIPlugin {

	private static ComposerPlugin plugin;

	public static final String ID = "org.eclipse.php.composer.core";

	private static final String DEBUG = "org.eclipse.php.composer.core/debug";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);

		plugin = this;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IResourceChangeListener listener = new IResourceChangeListener() {
			public void resourceChanged(IResourceChangeEvent event) {
				if (event.getType() == IResourceChangeEvent.PRE_DELETE
						&& event.getResource() instanceof IProject) {
					ModelAccess.getInstance().getPackageManager()
							.removeProject((IProject) event.getResource());
				}
			}
		};
		workspace.addResourceChangeListener(listener);
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {

		super.stop(bundleContext);
		plugin = null;
	}

	public static ComposerPlugin getDefault() {
		return plugin;
	}

	public static void debug(String message) {
		if (plugin == null) {
			// tests
			return;
		}

		String debugOption = Platform.getDebugOption(DEBUG);

		if (plugin.isDebugging() && "true".equalsIgnoreCase(debugOption)) {
			plugin.getLog().log(new Status(Status.INFO, ID, message));
		}
	}

	public static void logException(Exception e) {
		IStatus status = new Status(Status.ERROR, ComposerPlugin.ID,
				e.getMessage(), e);
		plugin.getLog().log(status);
	}

	public IEclipsePreferences getProjectPreferences(IProject project) {
		ProjectScope ps = new ProjectScope(project);
		return ps.getNode(ID);
	}

	public boolean isBuildpathContainerEnabled() {
		return getPreferenceStore().getBoolean(
				ComposerPluginConstants.PREF_BUILDPATH_ENABLE);
	}
	
	public IComposerProject getComposerProject(IScriptProject project) {
		if (project == null) {
			return null;
		}
		return new ComposerProject(project);
	}
	
	public IComposerProject getComposerProject(IProject project) {
		if (project == null) {
			return null;
		}
		return new ComposerProject(project);
	}
}