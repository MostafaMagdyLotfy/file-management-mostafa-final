package com.mostafa.file.system.mapper;

import com.mostafa.file.system.entity.dto.*;
import com.mostafa.file.system.entity.jpa.Item;
import com.mostafa.file.system.entity.jpa.PermissionGroup;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItemMapper {
    private final PermissionGroupMapper permissionGroupMapper;

    public ItemMapper(PermissionGroupMapper permissionGroupMapper) {
        this.permissionGroupMapper = permissionGroupMapper;
    }

    public ItemDTO itemToItemDTO(Item item) {
        if (item != null) {
            return ItemDTO.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .type(item.getType())
                    .permissionGroup(permissionGroupMapper
                            .permissionGroupToPermissionGroupDTO(item.getPermissionGroup()))
                    .parentItem(itemToItemSimpleDTO(item.getParentItem()))
                    .build();
        }
        return null;
    }

    public ItemWithFileDTO itemToItemWithFileDTO(Item item) {
        if (item != null) {
            return ItemWithFileDTO.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .type(item.getType())
                    .permissionGroup(permissionGroupMapper
                            .permissionGroupToPermissionGroupDTO(item.getPermissionGroup()))
                    .parentItem(itemToItemSimpleDTO(item.getParentItem()))
                    .files(item.getFiles().stream()
                            .map(file -> FileSimpleDTO.builder()
                                    .id(file.getId())
                                    .binaryData(file.getBinaryData())
                                    .build())
                            .collect(Collectors.toList()))
                    .build();
        }
        return null;
    }

    public ItemSimpleDTO itemToItemSimpleDTO(Item item) {
        if (item != null) {
            return ItemSimpleDTO.builder()
                    .id(item.getId())
                    .type(item.getType())
                    .name(item.getName())
                    .build();
        }
        return null;
    }

    public Item itemRequestDTOToItem(ItemRequestDTO itemRequestDTO) {
        if (itemRequestDTO != null) {
            Item item = new Item();
            item.setType(itemRequestDTO.getType());
            item.setName(itemRequestDTO.getName());
            if (itemRequestDTO.getPermissionGroup() != null) {
                item.setPermissionGroup(new PermissionGroup(itemRequestDTO.getPermissionGroup().getId()));
            }
            if (itemRequestDTO.getParentItem() != null) {
                item.setParentItem(new Item(itemRequestDTO.getParentItem().getId()));
            }
            return item;
        }
        return null;
    }
}
