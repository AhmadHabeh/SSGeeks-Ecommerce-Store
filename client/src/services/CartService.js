import axios from "axios";

export default {
  async getCart() {
    const response = await axios.get("/cart");
    return response.data;
  },
  async addToCart(productId) {
    const response = await axios.post("/cart/items", { productId, quantity: 1 });
    return response.data;
  },
  async removeFromCart(cartItemId) {
    await axios.delete(`/cart/items/${cartItemId}`);
  },
  async clearCart() {
    await axios.delete("/cart");
  },
};
