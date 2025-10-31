/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.impl.build;

import org.gradle.api.provider.Property;
import org.gradle.api.provider.ValueSourceParameters;

public abstract class CommitHashValueSource extends AbstractGitValueSource<String, CommitHashValueSource.Parameters> {
	public interface Parameters extends ValueSourceParameters {
		Property<String> getDirectory();
	}

	@Override
	public String obtain() {
		String dir = getParameters().getDirectory().get();
		// Try the directory-specific last commit first
		String hash = "";
		try {
			hash = git("log", "-1", "--format=%H", "--", dir);
		} catch (Exception e) {
			// ignore, we'll try fallback
		}

		// If nothing touched that directory, fall back to repository HEAD
		if (hash == null || hash.isEmpty()) {
			try {
				hash = git("rev-parse", "HEAD");
			} catch (Exception e) {
				// Could not determine a repo-wide HEAD (e.g., no commits). Return empty.
				hash = "";
			}
		}

		return hash != null ? hash : "";
	}
}