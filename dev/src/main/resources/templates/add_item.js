const apiBaseUrl = "http://localhost:8080/api/v1";

document.addEventListener("DOMContentLoaded", () => {
  const token = localStorage.getItem("token");
  if (!token) {
    window.location.href = "login.html";
  }
});

function addItem() {
  const token = localStorage.getItem("token");
  const name = document.getElementById("name").value;
  const description = document.getElementById("description").value;
  const price = document.getElementById("price").value;
  const auctionEndTime = document.getElementById("auctionEndTime").value;

  const createAuctionItemRequest = {
    name: name,
    description: description,
    price: price,
    auctionEndTime: auctionEndTime,
  };

  fetch(`${apiBaseUrl}/auction`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(createAuctionItemRequest),
  })
    .then((response) => response.json())
    .then((data) => {
      alert("Auction item added successfully");
      document.getElementById("add-item-form").reset();
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
