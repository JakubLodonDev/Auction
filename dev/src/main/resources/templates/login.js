const apiBaseUrl = "http://localhost:8080/api/v1";

function login() {
  const email = document.getElementById("login-email").value;
  const password = document.getElementById("login-password").value;

  fetch(`${apiBaseUrl}/auth/authenticate`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ email, password }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.token) {
        localStorage.setItem("token", data.token);
        window.location.href = "dashboard.html";
      } else {
        alert("Login failed");
      }
    })
    .catch((error) => console.error("Error:", error));
}
