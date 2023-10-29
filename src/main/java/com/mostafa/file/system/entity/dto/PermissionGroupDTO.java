package com.mostafa.file.system.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionGroupDTO {

    private Long id;
    private String groupName;

    public PermissionGroupDTO(Long id) {
        this.id = id;
    }
}
