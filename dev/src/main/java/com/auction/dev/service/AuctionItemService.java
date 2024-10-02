package com.auction.dev.service;

import com.auction.dev.repository.AuctionItemRepository;
import com.auction.dev.model.AuctionItem;
import com.auction.dev.request.CreateAuctionItemRequest;
import com.auction.dev.request.PlaceBidRequest;
import com.auction.dev.model.User;
import com.auction.dev.repository.UserRepository;
import com.auction.dev.request.UpdateAuctionItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionItemService {

    private final AuctionItemRepository auctionItemRepository;
    private final UserRepository userRepository;

    public AuctionItem createAuctionItem(CreateAuctionItemRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        AuctionItem auctionItem = AuctionItem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(new BigDecimal(request.getPrice()))
                .createdBy(user)
                .auctionEndTime(LocalDateTime.parse(request.getAuctionEndTime()))
                .isOpen(true)
                .build();

        return auctionItemRepository.save(auctionItem);
    }

    public List<AuctionItem> getOpenAuctions() {
        List<AuctionItem> auctions = auctionItemRepository.findAll();
        auctions.forEach(this::checkAndUpdateAuctionStatus);
        return auctions.stream()
                .filter(AuctionItem::isOpen)
                .collect(Collectors.toList());
    }

    public AuctionItem placeBid(PlaceBidRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        AuctionItem auctionItem = auctionItemRepository.findById(request.getAuctionId())
                .orElseThrow(() -> new IllegalArgumentException("Auction item not found"));

        checkAndUpdateAuctionStatus(auctionItem);

        if (!auctionItem.isOpen()) {
            throw new IllegalArgumentException("Auction is closed");
        }

        if (auctionItem.getCreatedBy().getEmail().equals(email)) {
            throw new IllegalArgumentException("You cannot bid on your own item");
        }

        if (auctionItem.getPrice().compareTo(new BigDecimal(request.getBidAmount())) >= 0) {
            throw new IllegalArgumentException("Bid amount must be higher than current price");
        }

        auctionItem.setPrice(new BigDecimal(request.getBidAmount()));
        auctionItem.setHighestBidUser(user);

        return auctionItemRepository.save(auctionItem);
    }

    public List<AuctionItem> getUserItems() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return auctionItemRepository.findAllByCreatedBy(user);
    }

    public AuctionItem updateAuctionItem(Long id, UpdateAuctionItemRequest request) {
        AuctionItem auctionItem = auctionItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Auction item not found"));

        auctionItem.setName(request.getName());
        auctionItem.setDescription(request.getDescription());
        auctionItem.setAuctionEndTime(LocalDateTime.parse(request.getAuctionEndTime()));
        checkAndUpdateAuctionStatus(auctionItem);

        return auctionItemRepository.save(auctionItem);
    }

    private void checkAndUpdateAuctionStatus(AuctionItem auctionItem) {
        if (auctionItem.getAuctionEndTime().isBefore(LocalDateTime.now())) {
            auctionItem.setOpen(false);
            auctionItemRepository.save(auctionItem);
        }
    }
}
