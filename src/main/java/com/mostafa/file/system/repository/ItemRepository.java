package com.mostafa.file.system.repository;

import com.mostafa.file.system.entity.jpa.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
