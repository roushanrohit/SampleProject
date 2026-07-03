package org.firstclub.membership;

import org.firstclub.user.Role;

/*
    Allows multiple users to share one membership
 */
public class UserMembership {

    private long userId;
    private long membershipId;
    private Role role;

    public UserMembership(long userId, long membershipId, Role role) {
        this.userId = userId;
        this.membershipId = membershipId;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserMembership{" +
                "userId=" + userId +
                ", membershipId=" + membershipId +
                ", role=" + role +
                '}';
    }
}
