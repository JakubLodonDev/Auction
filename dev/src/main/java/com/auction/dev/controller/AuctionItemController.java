package com.auction.dev.controller;

import com.auction.dev.model.AuctionItem;
import com.auction.dev.request.UpdateAuctionItemRequest;
import com.auction.dev.service.AuctionItemService;
import com.auction.dev.request.CreateAuctionItemRequest;
import com.auction.dev.request.PlaceBidRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v1/auction")
@RequiredArgsConstructor
public class AuctionItemController {

    private final AuctionItemService auctionItemService;

    @PostMapping
    public ResponseEntity<AuctionItem> createAuctionItem(@RequestBody CreateAuctionItemRequest request) {
        AuctionItem auctionItem = auctionItemService.createAuctionItem(request);
        return ResponseEntity.ok(auctionItem);
    }

    @GetMapping("/open")
    public ResponseEntity<List<AuctionItem>> getOpenAuctions() {
        List<AuctionItem> openAuctions = auctionItemService.getOpenAuctions();
        return ResponseEntity.ok(openAuctions);
    }

    @GetMapping("/user-items")
    public ResponseEntity<List<AuctionItem>> getUserItems() {
        List<AuctionItem> userItems = auctionItemService.getUserItems();
        return ResponseEntity.ok(userItems);
    }

    @PostMapping("/bid")
    public ResponseEntity<AuctionItem> placeBid(@RequestBody PlaceBidRequest request) {
        AuctionItem auctionItem = auctionItemService.placeBid(request);
        return ResponseEntity.ok(auctionItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuctionItem> updateAuctionItem(@PathVariable Long id, @RequestBody UpdateAuctionItemRequest request) {
        AuctionItem auctionItem = auctionItemService.updateAuctionItem(id, request);
        return ResponseEntity.ok(auctionItem);
    }
}
