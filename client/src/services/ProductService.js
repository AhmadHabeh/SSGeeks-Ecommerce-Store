import axios from "axios";

export default {
  async getProducts() {
    const response = await axios.get("/products");
    return response.data;
  },
  async getProductById(productId) {
    const response = await axios.get(`/products/${productId}`);
    return response.data;
  },
};
