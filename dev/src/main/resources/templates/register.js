const apiBaseUrl = "http://localhost:8080/api/v1";

function register() {
  const firstname = document.getElementById("register-firstname").value;
  const lastname = document.getElementById("register-lastname").value;
  const email = document.getElementById("register-email").value;
  const password = document.getElementById("register-password").value;

  fetch(`${apiBaseUrl}/auth/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ firstname, lastname, email, password }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.token) {
        localStorage.setItem("token", data.token);
        window.location.href = "login.html";
      } else {
        alert("Registration failed");
      }
    })
    .catch((error) => console.error("Error:", error));
}
