package com.jimmodel.internalServices.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "_user")
public class User extends BaseEntity{
    public interface NewUserInfo {}
    public interface ExistingUserInfo {}
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",strategy = "com.jimmodel.internalServices.config.UUIDGenerator")
    @EqualsAndHashCode.Include private UUID id;
    @Column(unique = true)
    @NotBlank(message = "Username cannot be blank.", groups = {NewUserInfo.class})
    private String username;
    @NotBlank(message = "Password cannot be blank.", groups = {NewUserInfo.class})
    private String password;
    @NotBlank(message = "First name cannot be blank.", groups = {NewUserInfo.class, ExistingUserInfo.class})
    private String firstName;
    @NotBlank(message = "Last name cannot be blank.", groups = {NewUserInfo.class, ExistingUserInfo.class})
    private String lastName;
    private String emailAddress;
    @NotNull(message = "User roles cannot be blank.")
    @Size(min = 1, message = "User roles must contain at least one valid role.", groups = {NewUserInfo.class})
    @Valid
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

}
