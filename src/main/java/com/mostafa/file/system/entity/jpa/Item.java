package com.mostafa.file.system.entity.jpa;

import com.mostafa.file.system.constants.ItemType;
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
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ItemType type;
    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item", fetch = FetchType.LAZY)
    private List<File> files;
    @JoinColumn(name = "permission_group_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PermissionGroup permissionGroup;
    @OneToMany(mappedBy = "parentItem", fetch = FetchType.LAZY)
    private List<Item> childrenItems;
    @JoinColumn(name = "parent_item_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item parentItem;

    public Item(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
