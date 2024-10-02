package com.auction.dev.repository;

import com.auction.dev.model.AuctionItem;
import com.auction.dev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
    List<AuctionItem> findAllByCreatedBy(User user);
}
