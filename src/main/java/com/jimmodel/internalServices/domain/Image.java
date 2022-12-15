package com.jimmodel.internalServices.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.MediaType;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "image")
@ToString
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",strategy = "com.jimmodel.internalServices.config.UUIDGenerator")
    @EqualsAndHashCode.Include private UUID id;
    private String fileName;
    private MediaType type;

}
