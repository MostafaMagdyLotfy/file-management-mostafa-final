package com.mostafa.file.system.entity.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "permission_groups")
public class PermissionGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "group_name")
    private String groupName;
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Permission> permissions;
    @OneToMany(mappedBy = "permissionGroup", fetch = FetchType.LAZY)
    private List<Item> items;

    public PermissionGroup(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionGroup that = (PermissionGroup) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
