package com.jimmodel.services.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "role")
@Entity
public class Role {
    @NotNull(message = "Invalid role name.")
    @Id
    @EqualsAndHashCode.Include private ERole name;

}
