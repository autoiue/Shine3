// ModulesPermissions.java

package procsynth.shine3;

import java.security.AllPermission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.util.logging.Logger;

/**
 * Sets permissions application wide
 *
 * @author procsynth - Antoine Pintout
 * @since v0.0.1
 */

public class ModulesPermissions extends Policy {

	private final Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * Called by the security manager if a module tries a restricted action
	 * 
	 * @param domain
	 *            the protection domain of the code responsible for the action
	 * @return the permissions associated with this code
	 */
	@Override
	public PermissionCollection getPermissions(ProtectionDomain domain) {
		System.out.println("Got permissions request, is application: " + isApplication(domain));
		if (isApplication(domain)) {
			return applicationPermissions();
		} else {
			return modulePermissions();
		}
	}

	/**
	 * Determine if the code responsible for an action belongs to the application or
	 * an external module
	 * 
	 * @param domain
	 *            the protection domain of the code responsible for the action
	 * @return true if the code belongs to the application
	 */
	private boolean isApplication(ProtectionDomain domain) {
		System.out.println(domain.getClassLoader() + " vs " + ClassLoader.getSystemClassLoader());
		return domain.getClassLoader() == ClassLoader.getSystemClassLoader();
	}

	/**
	 * 
	 * @return the permissions associated at module code
	 */
	private PermissionCollection modulePermissions() {
		Permissions permissions = new Permissions(); // No permissions
		return permissions;
	}

	/**
	 * 
	 * @return the permissions associated with application code
	 */
	private PermissionCollection applicationPermissions() {
		Permissions permissions = new Permissions();
		permissions.add(new AllPermission());
		return permissions;
	}
}