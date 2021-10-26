package application.models;

public enum UserRole {
    USER(UserAuthority.READ, UserAuthority.WRITE),
    ADMIN(UserAuthority.values());


    public final UserAuthority[] AUTHORITIES;

    UserRole(UserAuthority... userAuthorities){

        AUTHORITIES = userAuthorities;
    }

}
