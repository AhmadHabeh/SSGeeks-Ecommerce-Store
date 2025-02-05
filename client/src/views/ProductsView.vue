<template>
   <div class="gradient-bg">
    
  <div id="products" class="container py-5">
    <h1 class="text-center text-secondary fw-bold">
      <font-awesome-icon icon="fa-solid fa-shopping-cart" /> Products
    </h1>

     <!-- Search & View Toggle -->
     <div class="d-flex justify-content-between align-items-center mb-4 search-container">
      <input v-model="searchTerm" placeholder="Search products..." class="search-bar" />
      <div class="btn-group">
        <button class="btn btn-outline-primary" :class="{ active: cardView }" @click="toggleView('card')">
          <font-awesome-icon icon="fa-solid fa-grip" />
        </button>
        <button class="btn btn-outline-primary" :class="{ active: !cardView }" @click="toggleView('table')">
          <font-awesome-icon icon="fa-solid fa-table" />
        </button>
      </div>
    </div>

    <!-- Product Details Modal -->
    <div v-if="selectedProduct" class="modal-overlay" @click.self="closeProductDetails">
      <div class="modal-content">
        <button class="btn btn-danger close-btn" @click="closeProductDetails">
          <font-awesome-icon icon="fa-solid fa-times" />
        </button>
        <h2 class="text-center">{{ selectedProduct.name }}</h2>
        <img :src="selectedProduct.imageName" class="product-img" alt="Product Image" />
        <p><strong>ID:</strong> {{ selectedProduct.productId }}</p>
        <p><strong>SKU:</strong> {{ selectedProduct.productSku }}</p>
        <p><strong>Description:</strong> {{ selectedProduct.description }}</p>
        <p><strong>Price:</strong> ${{ selectedProduct.price | currency }}</p>
        <button v-if="isLoggedIn" class="btn btn-warning fw-bold" @click="addToCart(selectedProduct.productId)">
          <font-awesome-icon icon="fa-solid fa-cart-plus" /> Add to Cart
        </button>
      </div>
    </div>

    <!-- Card View -->
    <div v-if="cardView" class="row g-4">
      <div v-for="product in filteredProducts" :key="product.productId" class="col-md-3">
        <div class="card h-100 shadow-sm border-0 product-card" @click="viewDetails(product)">
          <img :src="product.imageName" class="card-img-top" alt="Product Image" />
          <div class="card-body text-center">
            <h5 class="card-title fw-bold">{{ product.name }}</h5>
            <p class="card-text text-success fw-bold">${{ product.price | currency }}</p>
            <button v-if="isLoggedIn" class="btn btn-warning fw-bold" @click.stop="addToCart(product.productId)">
              <font-awesome-icon icon="fa-solid fa-cart-plus" /> Add to Cart
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- Table View -->
    <div v-else class="table-responsive">
      <table class="table table-hover text-center">
        <thead class="bg-primary text-white">
          <tr>
            <th>Product Name</th>
            <th>Price</th>
            <th v-if="isLoggedIn">Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in filteredProducts" :key="product.productId">
            <td @click="viewDetails(product)" class="fw-bold text-primary clickable">
              {{ product.name }}
            </td>
            <td class="text-success fw-bold">${{ product.price | currency }}</td>
            <td v-if="isLoggedIn">
              <button class="btn btn-warning fw-bold" @click="addToCart(product.productId)">
                <font-awesome-icon icon="fa-solid fa-cart-plus" /> Add to Cart
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  

  <!-- Footer -->
  <footer class="text-center py-3 bg-dark text-white">
    <p class="mb-0">© 2024 SS Geeks Store</p>
  </footer>
</div>
</template>

<script>
import ProductService from "../services/ProductService";
import CartService from "../services/CartService";

export default {
  data() {
    return {
      products: [],
      searchTerm: "",
      cardView: true,
      selectedProduct: null, 
    };
  },
  computed: {
    isLoggedIn() {
      return this.$store.state.token.length > 0;
    },
    filteredProducts() {
      return this.products.filter((p) => p.name.toLowerCase().includes(this.searchTerm.toLowerCase()));
    },
  },
  methods: {
    toggleView(view) {
      this.cardView = view === "card";
    },
    async fetchProducts() {
      this.products = await ProductService.getProducts();
    },
    async addToCart(productId) {
      try {
        await CartService.addToCart(productId);
        alert("Product added to cart!");
      } catch (error) {
        alert("Failed to add product to cart.");
      }
    },
    viewDetails(product) {
      this.selectedProduct = product;
    },
    closeProductDetails() {
      this.selectedProduct = null;
    },
  },
  async created() {
    await this.fetchProducts();
  },
  filters: {
    currency(value) {
      return new Intl.NumberFormat("en-US", {
        style: "decimal",
      }).format(value);
    },
  },
};
</script>

<style scoped>
/* Hover Glow Effect for Product Cards */
@property --rotation {
  syntax: "<angle>";
  inherits: false;
  initial-value: 0deg;
}

/* Gradient Background */
.gradient-bg {
  background: linear-gradient(-90deg, #7218d3, #000000, #3403a8, #a3a4be);
  background-size: 400% 400%;
  animation: gradient 10s ease infinite;
  min-height: 100vh;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
}

@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
.product-card {
  --border-radius: 20px;
  --color: rgb(113, 39, 173);
  margin: auto;
  padding: 1.5rem;
  position: relative;
  background: rgba(212, 191, 235, 0);
  border-radius: var(--border-radius);
  transition: 0.4s;
  cursor: pointer;
}

.product-card:after,
.product-card:before {
  content: "";
  display: block;
  height: 100%;
  width: 100%;
  background: conic-gradient(
    from var(--rotation),
    black,
    rgb(76, 7, 90),
    rgba(99, 23, 185, 0.705),
    var(--color),
    rgb(0, 0, 0),
    rgb(87, 13, 170),
    rgb(0, 0, 0),
    var(--color),
    black
  );
  padding: 0rem;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  border-radius: calc(var(--border-radius) + 2px);
  z-index: -1;
  transition: 0.5s;
  animation: rotate-glow 15s infinite linear;
  animation-play-state: paused;
  opacity: 0;
}

.product-card:before {
  filter: blur(20px);
}
.product-card h5.card-title {
  color: #ffd000; /* Yellow text */
}

.product-card .card-text {
  color: #4CAF50; /* Green text for price */
}

@keyframes rotate-glow {
  from {
    --rotation: 0deg;
  }
  to {
    --rotation: 360deg;
  }
}

.product-card:hover {
  padding: 2rem;
}

.product-card:hover:after,
.product-card:hover:before {
  padding: 0.2rem;
  animation-play-state: running;
  opacity: 0.5;
}

/* Clickable Text */
.clickable {
  cursor: pointer;
}
.clickable:hover {
  text-decoration: underline;
  color: #1251da;
}

/* Modal Styling */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 10px;
  width: 400px;
  max-width: 90%;
  text-align: center;
  box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.3);
  position: relative;
}

.product-img {
  width: 100%;
  height: 250px;
  object-fit: cover;
  border-radius: 8px;
  margin-bottom: 15px;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
}
/* Search Bar */
.search-container {
  display: flex;
  margin-left: 20%;
  align-items: center;
  justify-content: center;
  width: 100%;
}

.search-bar {
  width: 60%;
  height: 50px;
  padding: 10px;
  border: none;
  border-radius: 25px;
  font-size: 18px;
  background: rgba(0, 0, 0, 0.8);
  color: #FFD700;
  box-shadow: 0 0 10px #FFD700;
  transition: all 0.3s ease-in-out;
}

.search-bar::placeholder {
  color: #FFD700;
  opacity: 0.8;
}

.search-bar:focus {
  outline: none;
  box-shadow: 0 0 15px #FFD700;
  transform: scale(1.05);
}
h1.text-secondary {
  font-size: 3rem; /* Make it bold and prominent */
  font-weight: bold;
  text-align: center;
  text-transform: uppercase;
  color: #672dc4; /* Golden yellow to match other elements */
  text-shadow: 0 0 15px #ffd90000, 0 0 30px rgba(255, 217, 0, 0); /* Glowing Effect */
  letter-spacing: 2px; /* Slight spacing for futuristic feel */
  transition: all 0.3s ease-in-out;
  
}

/* Subtle hover effect */
h1.text-secondary:hover {
  text-shadow: 0 0 20px #5b3bb3, 0 0 40px rgba(124, 110, 32, 0);
  transform: scale(1.05);
 
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
