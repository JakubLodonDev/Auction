const apiBaseUrl = "http://localhost:8080/api/v1";

document.addEventListener("DOMContentLoaded", () => {
  const token = localStorage.getItem("token");
  if (token) {
    fetchOpenAuctions(token);
  } else {
    window.location.href = "login.html";
  }
});

function fetchOpenAuctions(token) {
  fetch(`${apiBaseUrl}/auction/open`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => response.json())
    .then((data) => {
      const auctionList = document.getElementById("auction-list");
      auctionList.innerHTML = "";
      data.forEach((auction) => {
        const row = document.createElement("tr");
        row.innerHTML = `
                <td>${auction.name}</td>
                <td>${auction.description}</td>
                <td>${auction.createdBy.email}</td>
                <td>${auction.auctionEndTime}</td>
                <td>$${auction.price}</td>
                <td>${
                  auction.highestBidUser ? auction.highestBidUser.email : "None"
                }</td>
                <td><input type="number" step="0.01" min="${
                  auction.price
                }" id="bid-${auction.id}"></td>
                <td><button onclick="placeBid(${auction.id}, ${
          auction.price
        })">Place Bid</button></td>
            `;
        auctionList.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error:", error);
      window.location.href = "login.html";
    });
}

function placeBid(auctionId, currentPrice) {
  const token = localStorage.getItem("token");
  const bidAmount = document.getElementById(`bid-${auctionId}`).value;

  if (parseFloat(bidAmount) <= parseFloat(currentPrice)) {
    alert("Bid amount must be higher than current price");
    return;
  }

  const placeBidRequest = {
    auctionId: auctionId,
    bidAmount: bidAmount,
  };

  fetch(`${apiBaseUrl}/auction/bid`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(placeBidRequest),
  })
    .then((response) => response.json())
    .then((data) => {
      alert("Bid placed successfully");
      fetchOpenAuctions(token);
    })
    .catch((error) => console.error("Error:", error));
}

function logout() {
  localStorage.removeItem("token");
  window.location.href = "login.html";
}

function goBack() {
  window.location.href = "dashboard.html";
}
