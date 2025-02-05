<template>
  <div id="register" class="d-flex justify-content-center align-items-center min-vh-100">
    <div class="register-container card shadow-lg p-4 rounded">
      <div class="text-center">
        <img src="../assets/banner.jpg" alt="SS Geeks Store" class="img-fluid logo-img" />
      </div>
      
      <h2 class="text-center text-primary fw-bold mt-2">Create Account</h2>

      <form v-on:submit.prevent="register" class="mt-3">
        <!-- Username -->
        <div class="input-group mb-3">
          <span class="input-group-text bg-primary text-white">
            <font-awesome-icon icon="fa-solid fa-user" />
          </span>
          <input type="text" class="form-control" placeholder="Enter username" v-model="user.username" required />
        </div>

        <!-- Full Name -->
        <div class="input-group mb-3">
          <span class="input-group-text bg-primary text-white">
            <font-awesome-icon icon="fa-solid fa-id-card" />
          </span>
          <input type="text" class="form-control" placeholder="Enter full name" v-model="user.name" required />
        </div>

        <!-- Password -->
        <div class="input-group mb-3">
          <span class="input-group-text bg-primary text-white">
            <font-awesome-icon icon="fa-solid fa-lock" />
          </span>
          <input type="password" class="form-control" placeholder="Enter password" v-model="user.password" required />
        </div>

        <!-- Confirm Password -->
        <div class="input-group mb-3">
          <span class="input-group-text bg-primary text-white">
            <font-awesome-icon icon="fa-solid fa-lock" />
          </span>
          <input type="password" class="form-control" placeholder="Confirm password" v-model="user.confirmPassword" required />
        </div>

        <!-- Address -->
        <div class="input-group mb-3">
          <span class="input-group-text bg-primary text-white">
            <font-awesome-icon icon="fa-solid fa-map-marker-alt" />
          </span>
          <input type="text" class="form-control" placeholder="Enter address" v-model="user.address" />
        </div>

        <!-- City, State, ZIP -->
        <div class="row">
          <div class="col-md-4 mb-3">
            <input type="text" class="form-control" placeholder="City" v-model="user.city" />
          </div>
          <div class="col-md-4 mb-3">
            <input type="text" class="form-control" placeholder="State" v-model="user.stateCode" maxlength="2" required />
          </div>
          <div class="col-md-4 mb-3">
            <input type="number" class="form-control" placeholder="ZIP Code" v-model="user.zip" required minlength="5" maxlength="5" />
          </div>
        </div>

        <!-- Register Button -->
        <button type="submit" class="btn btn-success w-100 fw-bold shadow-sm">
          <font-awesome-icon icon="fa-solid fa-user-plus" /> Create Account
        </button>
      </form>

      <!-- Login Link -->
      <div class="text-center mt-3">
        <p class="text-secondary fw-bold">
          Have an account?
          <router-link to="/login" class="text-primary fw-bold">Sign in!</router-link>
        </p>
      </div>
    </div>
  </div>

  <!-- Footer -->
  <footer class="text-center py-3 bg-dark text-white">
    <p class="mb-0">© 2024 SS Geeks Store</p>
  </footer>
</template>

---

## **🔹 Updated Script**
```vue
<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        name: "",
        password: "",
        confirmPassword: "",
        address: "",
        city: "",
        stateCode: "",
        zip: "",
        role: "user",
      },
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
      if (this.user.password !== this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.success("Thank you for registering, please sign in.");
              this.$router.push({ path: "/login" });
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}': ${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
    },
  },
};
</script>


<style scoped>
/* Full-page background */
#register {
  height: 100vh;
  background: url("../assets/pewdiepie.jpg") no-repeat center center;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Registration Container */
.register-container {
  width: 450px;
  background: rgb(0, 0, 0);
  border-radius: 10px;
  transition: transform 0.3s ease-in-out;
  padding: 20px;
}

.register-container:hover {
  transform: scale(1.02);
}

/* Input Fields */
.input-group-text {
  border-radius: 5px 0 0 5px;
}

.form-control {
  border-radius: 0 5px 5px 0;
}

/* Register Button */
.btn-success {
  transition: all 0.3s ease-in-out;
}

.btn-success:hover {
  background-color: #218838;
  transform: scale(1.05);
}

/* Login Link */
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

/* Logo Image */
.logo-img {
  width: 150px;
  height: auto;
  margin-bottom: 10px;
}

</style>
