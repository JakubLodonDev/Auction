const apiBaseUrl = "http://localhost:8080/api/v1";

document.addEventListener("DOMContentLoaded", () => {
  const token = localStorage.getItem("token");
  if (token) {
    fetchUserDetails(token);
  } else {
    window.location.href = "login.html";
  }
});

function fetchUserDetails(token) {
  fetch(`${apiBaseUrl}/user`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => response.json())
    .then((data) => {
      document.getElementById("firstname").value = data.firstname;
      document.getElementById("lastname").value = data.lastname;
      document.getElementById("email").value = data.email;
    })
    .catch((error) => {
      console.error("Error:", error);
      window.location.href = "login.html";
    });
}

function updateUserDetails() {
  const token = localStorage.getItem("token");
  const firstname = document.getElementById("firstname").value;
  const lastname = document.getElementById("lastname").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  const updateUserRequest = {
    firstname: firstname,
    lastname: lastname,
    email: email,
    password: password,
  };

  fetch(`${apiBaseUrl}/user`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(updateUserRequest),
  })
    .then((response) => response.json())
    .then((data) => {
      alert("User details updated successfully");
      fetchUserDetails(token);
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
