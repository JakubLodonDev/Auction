const apiBaseUrl = "http://localhost:8080/api/v1";

document.addEventListener("DOMContentLoaded", () => {
  const token = localStorage.getItem("token");
  if (token) {
    fetchUserItems(token);
  } else {
    window.location.href = "login.html";
  }
});

function fetchUserItems(token) {
  fetch(`${apiBaseUrl}/auction/user-items`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => response.json())
    .then((data) => {
      const itemList = document.getElementById("item-list");
      itemList.innerHTML = "";
      data.forEach((item) => {
        const row = document.createElement("tr");
        row.innerHTML = `
                <td><input type="text" value="${item.name}" id="name-${
          item.id
        }"></td>
                <td><input type="text" value="${
                  item.description
                }" id="description-${item.id}"></td>
                <td><input type="datetime-local" value="${new Date(
                  item.auctionEndTime
                )
                  .toISOString()
                  .slice(0, 16)}" id="auctionEndTime-${item.id}"></td>
                <td><button onclick="updateItem(${item.id})">Edit</button></td>
            `;
        itemList.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error:", error);
      window.location.href = "login.html";
    });
}

function updateItem(itemId) {
  const token = localStorage.getItem("token");
  const name = document.getElementById(`name-${itemId}`).value;
  const description = document.getElementById(`description-${itemId}`).value;
  const auctionEndTime = document.getElementById(
    `auctionEndTime-${itemId}`
  ).value;

  const updateAuctionItemRequest = {
    name: name,
    description: description,
    auctionEndTime: auctionEndTime,
  };

  fetch(`${apiBaseUrl}/auction/${itemId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(updateAuctionItemRequest),
  })
    .then((response) => response.json())
    .then((data) => {
      alert("Item updated successfully");
      fetchUserItems(token);
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
