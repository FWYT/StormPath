/*
 * Copyright 2013 Stormpath, Inc.
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
package StormpathShiro.src.main.java.com.stormpath.shiro.authz;

import java.util.Set;

/**
 * A {@code PermissionsEditor} allows one to read and manipulate (append or remove) permissions on an underlying data
 * structure.
 * <p/>
 * The primary default implementation of this interface is the {@link CustomDataPermissionsEditor}, which reads and
 * modifies a Set of permissions stored in an {@link com.stormpath.sdk.directory.CustomData CustomData} instance.
 *
 * @see CustomDataPermissionsEditor
 * @since 0.5
 */
public interface PermissionsEditor {

    /**
     * Adds a Shiro permission String to the associated Set of permission strings.
     *
     * @param perm the permission string to add to the associated Set of permission strings.
     * @return this object for method chaining.
     */
    PermissionsEditor append(String perm);

    /**
     * Removes the specified Shiro permission String from the associated Set of permission Strings.
     *
     * @param perm the permission string to remove from the associated Set of permission strings.
     * @return this object for method chaining.
     */
    PermissionsEditor remove(String perm);

    /**
     * Returns a read-only (immutable) view of the stored permission strings.  An immutable empty set will be returned
     * if there are not any currently stored.
     *
     * @return an immutable view of the stored permission strings.
     */
    Set<String> getPermissionStrings();

}
