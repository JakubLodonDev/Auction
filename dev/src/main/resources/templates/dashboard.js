const apiBaseUrl = "http://localhost:8080/api/v1";

document.addEventListener("DOMContentLoaded", () => {
  const token = localStorage.getItem("token");
  if (token) {
    fetchDashboard(token);
  } else {
    window.location.href = "login.html";
  }
});

function fetchDashboard(token) {
  fetch(`${apiBaseUrl}/dashboard`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => response.text())
    .then((data) => {
      document.getElementById("dashboard-message").innerText = data;
    })
    .catch((error) => {
      console.error("Error:", error);
      window.location.href = "login.html";
    });
}

function viewUserDetails() {
  window.location.href = "user_details.html";
}

function viewAuctions() {
  window.location.href = "view_auctions.html";
}

function addItem() {
  window.location.href = "add_item.html";
}

function viewUserItems() {
  window.location.href = "view_user_items.html";
}

function logout() {
  localStorage.removeItem("token");
  window.location.href = "login.html";
}
