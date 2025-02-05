<template>
  <div class="gradient-bg">
    <div id="cart" class="container py-5">
      <h1 class="text-center  total-label cart-title fw-bold">
        <font-awesome-icon icon="fa-solid fa-shopping-cart" /> Your Cart
      </h1>

      <div v-if="cart.items.length === 0" class="alert alert-warning text-center">
        <p class="fw-bold fs-4">Your cart is empty. Start shopping now!</p>
        <router-link to="/products" class="btn btn-primary fw-bold">
          <font-awesome-icon icon="fa-solid fa-store" /> Browse Products
        </router-link>
      </div>

      <div v-else class="table-responsive cart-table-container">
        <table class="table table-hover text-center cart-table">
          <thead>
            <tr>
              <th>Product</th>
              <th>Quantity</th>
              <th>Price</th>
              <th>Total</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in cart.items" :key="item.cartItemId">
              <td class="fw-bold clickable text-neon" @click="viewProductDetails(item.product)">
                {{ item.product.name }}
              </td>
              <td>
                <span class="badge bg-secondary">{{ item.quantity }}</span>
              </td>
              <td class="text-success fw-bold">{{ formatCurrency(item.product.price) }}</td>
              <td class="text-success fw-bold">{{ formatCurrency(item.product.price * item.quantity) }}</td>
              <td>
                <button class="btn btn-danger btn-sm fw-bold" @click="removeFromCart(item.cartItemId)">
                  <font-awesome-icon icon="fa-solid fa-trash" />
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div v-if="cart.items.length > 0" class="cart-summary">
        <p class="fw-bold fs-5 total-label">Subtotal: <span class="text-primary">{{ formatCurrency(cartSubtotal) }}</span></p>
        <p class="fw-bold fs-5 total-label">Tax: <span class="text-danger">{{ formatCurrency(cartTax) }}</span></p>
        <p class="fw-bold fs-4 total-label">Total: <span class="text-success">{{ formatCurrency(cartTotal) }}</span></p>

        <button class="btn clear-cart-btn fw-bold fs-5 py-2 mt-3" @click="clearCart">
          <font-awesome-icon icon="fa-solid fa-trash-alt" /> Clear Cart
        </button>
      </div>
    </div>

    <!-- Product Details -->
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
        <p><strong>Price:</strong> {{ formatCurrency(selectedProduct.price) }}</p>
        <button v-if="isLoggedIn" class="btn btn-warning fw-bold" @click="addToCart(selectedProduct.productId)">
          <font-awesome-icon icon="fa-solid fa-cart-plus" /> Add to Cart
        </button>
      </div>
    </div>

    <footer class="text-center py-3 bg-dark text-white">
      <p class="mb-0">© 2024 SS Geeks Store</p>
    </footer>
  </div>
</template>

<script>
import CartService from "../services/CartService";

export default {
  data() {
    return {
      cart: { items: [] },
      selectedProduct: null,
    };
  },
  async created() {
    this.cart = await CartService.getCart();
  },
  computed: {
    cartSubtotal() {
      return this.cart.items.reduce((sum, item) => sum + item.product.price * item.quantity, 0);
    },
    cartTax() {
      return this.cartSubtotal * 0.07;
    },
    cartTotal() {
      return this.cartSubtotal + this.cartTax;
    },
    isLoggedIn() {
      return this.$store.state.token.length > 0;
    },
  },
  methods: {
    async removeFromCart(cartItemId) {
      await CartService.removeFromCart(cartItemId);
      this.cart = await CartService.getCart();
    },
    async clearCart() {
      await CartService.clearCart();
      this.cart = await CartService.getCart();
    },
    formatCurrency(value) {
      return new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
      }).format(value);
    },
    viewProductDetails(product) {
      console.log("Opening product details for:", product);
      this.selectedProduct = product;
    },
    closeProductDetails() {
      this.selectedProduct = null;
    },
    async addToCart(productId) {
      await CartService.addToCart(productId);
      alert("Product added to cart!");
    },
  },
};
</script>

<style scoped>
/* Background */
.gradient-bg {
  background: linear-gradient(-90deg, #3b0087, #000000, #4b0082, #a3a4be);
  background-size: 400% 400%;
  animation: gradient 10s ease infinite;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}

@keyframes gradient {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

/* Clickable */
.clickable {
  cursor: pointer;
}

.clickable:hover {
  text-decoration: underline;
  color: #FFD700;
}

/* Cart Summary */
.cart-summary {
  background: rgba(20, 20, 20, 0.8);
  padding: 20px;
  text-align: center;
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(255, 255, 255, 0.2);
}

/* Table */
.cart-table-container {
  background: rgba(0, 0, 0, 0.7);
  padding: 15px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(255, 255, 255, 0.2);
}

.cart-table {
  color: white;
}

.cart-table thead {
  background: rgba(255, 255, 255, 0.1);
}

.cart-table tbody tr:hover {
  background: rgba(255, 255, 255, 0.15);
}

.total-label {
  font-size: 1.5rem;
  font-weight: bold;
  color: white;
}

/* Clear Cart Button */
.clear-cart-btn {
  background: #ff4444;
  color: white;
  border-radius: 10px;
  transition: all 0.3s ease;
}

.clear-cart-btn:hover {
  background: #cc0000;
  transform: scale(1.05);
}

/* Modal */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background: #111;
  color: white;
  padding: 20px;
  border-radius: 10px;
  width: 400px;
  max-width: 90%;
  text-align: center;
  box-shadow: 0px 0px 15px rgba(255, 215, 0, 0.8);
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
