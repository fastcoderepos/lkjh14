package com.fastcode.lkjh14.domain.core.test6;

import com.fastcode.lkjh14.domain.core.abstractentity.AbstractEntity;
import java.time.*;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test6")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Test6Entity extends AbstractEntity {

    @Basic
    @Column(name = "version", nullable = false)
    private Long version;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
}
