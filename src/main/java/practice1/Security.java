package practice1;

import com.google.common.collect.ImmutableList;

public class Security {

    private SecurityChecker securityChecker;

    public Security(SecurityChecker checker) {
        this.securityChecker = checker;
    }

    public boolean hasAccess(User user, Permission permission, ImmutableList<Permission> permissions) {
        if (isEmpty(user, permission, permissions)) {
            return false;
        }
        return isLegalIdentity(user, permission, permissions);
    }

    private boolean isLegalIdentity(User user, Permission permission, ImmutableList<Permission> permissions) {
        boolean identity = false;
        if (securityChecker.isAdmin()) {
            identity = true;
        }

        if (securityChecker.checkPermission(user, permission) || permissions.contains(permission)) {
            identity = true;
        }
        return identity;
    }

    private boolean isEmpty(User user, Permission permission, ImmutableList<Permission> permissions) {
        if (user == null || permission == null || permissions.size() == 0) {
            return true;
        }
        return false;
    }
}
