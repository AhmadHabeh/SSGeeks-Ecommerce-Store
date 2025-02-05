<template>
  
    <div class="cardContainer" :class="{ active: isActive }" @click="toggleActive">
      <div class="card">
        <!-- Front Side -->
        <div class="side front">
          <div class="img" :style="{ backgroundImage: `url(${product.image})` }"></div>
          <div class="info">
            <h2>{{ product.name }}</h2>
            <p>{{ product.description }}</p>
          </div>
        </div>
  
        <!-- Back Side -->
        <div class="side back">
          <div class="info">
            <h2>At a glance</h2>
            <div class="reviews">
              <span v-for="n in product.rating" :key="n">⭐</span>
              <p>{{ product.reviews }} Reviews</p>
            </div>
            <ul>
              <li v-for="feature in product.features" :key="feature">{{ feature }}</li>
            </ul>
            <div class="btn">
              <h4>Learn More</h4>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      product: Object,
    },
    data() {
      return {
        isActive: false,
      };
    },
    methods: {
      toggleActive() {
        this.isActive = !this.isActive;
      },
    },
  };
  </script>
  
  <style scoped>
  /* Overall card styles */
  .cardContainer {
    position: relative;
    width: 300px;
    height: 400px;
    margin: 4px;
    perspective: 1000px;
  }
  
  .card {
    position: relative;
    width: 100%;
    height: 100%;
    transform-style: preserve-3d;
    transition: transform 0.4s ease-in-out;
    cursor: pointer;
  }
  
  .cardContainer.active .card {
    transform: rotateY(180deg);
  }
  
  /* Front and back styling */
  .side {
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 10px;
    background: white;
    box-shadow: 0 14px 50px -4px rgba(0, 0, 0, 0.15);
    backface-visibility: hidden;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  
  .front {
    z-index: 2;
  }
  
  .back {
    transform: rotateY(180deg);
    text-align: center;
  }
  
  /* Image styles */
  .img {
    width: 100%;
    height: 200px;
    background-size: cover;
    background-position: center;
    border-radius: 10px 10px 0 0;
  }
  
  /* Button */
  .btn {
    margin-top: 15px;
    padding: 10px;
    background: #ffb714;
    border-radius: 5px;
    cursor: pointer;
    text-align: center;
  }
  
  .btn:hover {
    background: #ff9800;
  }
  
  /* Reviews */
  .reviews {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .reviews p {
    margin-left: 10px;
    font-weight: bold;
  }
  </style>
  