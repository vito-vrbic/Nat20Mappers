# Koristi Node.js za build React aplikacije
FROM node:18-alpine AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

# Koristi Nginx za posluživanje statičkih datoteka
FROM nginx:1.23-alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 4173
CMD ["nginx", "-g", "daemon off;"]