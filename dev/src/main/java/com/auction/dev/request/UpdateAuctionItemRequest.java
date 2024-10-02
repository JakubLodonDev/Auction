package com.auction.dev.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuctionItemRequest {
    private String name;
    private String description;
    private String auctionEndTime;
}
