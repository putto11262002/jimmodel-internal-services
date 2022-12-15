package com.jimmodel.internalServices.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "role")
@Entity
public class Role {
    @Id
    @EqualsAndHashCode.Include private ERole name;

}
