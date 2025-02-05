<template>

  <div id="login" class="d-flex justify-content-center align-items-center min-vh-100 bg-light">
    
    <div class="login-container card shadow-lg p-4 rounded">
      <section class="banner text-center">
      <img src="../assets/banner.jpg" alt="SS Geeks Store Banner" class="img-fluid w-100 shadow" />
    </section>
      
      <div class="card-body">
        <h2 class="login-title text-center text-primary fw-bold">
          <font-awesome-icon icon="fa-solid fa-user-circle" /> Sign In
        </h2>

        <form v-on:submit.prevent="login" class="mt-4">
          <!-- Username -->
          <div class="input-group mb-3">
            <span class="input-group-text bg-primary text-white">
              <font-awesome-icon icon="fa-solid fa-user" />
            </span>
            <input
              type="text"
              class="form-control"
              placeholder="Enter your username"
              v-model="user.username"
              required
              autofocus
            />
          </div>

          <!-- Password -->
          <div class="input-group mb-3">
            <span class="input-group-text bg-primary text-white">
              <font-awesome-icon icon="fa-solid fa-lock" />
            </span>
            <input
              type="password"
              class="form-control"
              placeholder="Enter your password"
              v-model="user.password"
              required
            />
          </div>

          <!-- Sign In Button -->
          <button type="submit" class="btn btn-primary w-100 fw-bold shadow-sm">
            <font-awesome-icon icon="fa-solid fa-sign-in-alt" /> Sign In
          </button>
        </form>

        <!-- Register Link -->
        <div class="text-center mt-3">
          <p class="text-secondary fw-bold">
            Need an account?
            <router-link to="/register" class="text-primary fw-bold">Register!</router-link>
          </p>
        </div>
      </div>
    </div>
  </div>

  <!-- Footer -->
  <footer class="text-center py-3 bg-dark text-white mt-4">
    <p class="mb-0">© 2024 SS Geeks Store</p>
  </footer>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username or password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
/* Centering & Background */
#login {
  min-height: 100vh; /* Ensures it covers the full viewport height */
  background: url("../assets/pewdiepie.jpg") no-repeat center center;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
}


/* Login Card */
.login-container {
  width: 400px;
  background: rgb(0, 0, 0);
  border-radius: 10px;
  transition: transform 0.3s ease-in-out;
}

.login-container:hover {
  transform: scale(1.02);
}

/* Input Group */
.input-group-text {
  border-radius: 5px 0 0 5px;
}

.form-control {
  border-radius: 0 5px 5px 0;
}

/* Sign In Button */
.btn-primary {
  transition: all 0.3s ease-in-out;
}

.btn-primary:hover {
  background-color: #0056b3;
  transform: scale(1.05);
}

/* Register Link */
.text-primary {
  text-decoration: none;
}

.text-primary:hover {
  text-decoration: underline;
}

/* Footer */
footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: red;
  color: white;
  text-align: center;
}
</style>
