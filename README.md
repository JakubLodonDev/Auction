# Auction Application

## Overview

This is a web-based auction application where users can register, log in, and participate in auctions by listing items and placing bids on others' items. Users can view active auctions, place bids, and manage their own items.

The backend is built using **Java** and **Spring Boot**, with a RESTful API that handles user authentication, auction management, and bidding logic. The frontend interacts with the backend through API calls.

## Features

- **User Authentication**: Users can register, log in, and manage their account details.
- **Auction Management**: Users can add new auction items with a description, starting price, and an auction end time.
- **Bidding System**: Users can place bids on items listed by other users.
- **Auction Status**: The application tracks the status of auctions, ensuring users can only place bids on active auctions.

## Technologies Used

- **Backend**:
  - **Java**: Core language used for backend development.
  - **Spring Boot**: Framework used for building the backend API.
  - **Spring Security**: Used for handling user authentication and authorization.
  - **JPA/Hibernate**: For database interactions.
  
- **Frontend**:
  - **HTML/CSS**: For the structure and styling of the web pages.
  - **JavaScript**: Handles client-side logic and API interaction.
  - **Fetch API**: Used to communicate with the backend.

- **Database**: PostgreSQL database to store user and auction item data.

