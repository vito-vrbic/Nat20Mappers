import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:5000', //Adresa mock backenda
        changeOrigin: true,
        rewrite: (path) => path.replace(/ˇ\/api/, '')
      }
    } 
  },
})
