package com.auction.dev.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuctionItemRequest {
    private String name;
    private String description;
    private String price;
    private String auctionEndTime;
}

