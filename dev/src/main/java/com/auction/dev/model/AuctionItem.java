package com.auction.dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auction_items")
public class AuctionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "highest_bid_user_id")
    private User highestBidUser;

    private LocalDateTime auctionEndTime;
    private boolean isOpen;
}
