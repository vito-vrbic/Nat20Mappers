# Token Verification

Endpoint: 
  `GET /api/auth/verify-token`

Header:
  ```
  Content-Type: application/json
  Authorization: Bearer {auth_token}   // The token to be verified for validity
  ```

Body: (No body content is required for this request)
  ```json
  {}
  ```

## Expected Responses:

### 1. SUCCESS

   Status Code: `200 OK`
   
   Body:
   ```json
   {
     "message": "Token is valid",
     "userData": 
     {
       "id": "a104",
       "username": "john_doe",
       "email": "john.doe@example.com",
       "role": "private",
       "organizationName": null
     }
   }
   ```

### 2. FAILURE - Unauthorized

   Status Code: `401 Unauthorized`

   Body:
   ```json
   {
     "message": "Invalid or expired token."
   }
   ```

### 3. FAILURE - Bad Request

   Status Code: `400 Bad Request`

   Body:
   ```json
   {
     "message": "Token missing"
   }
   ```

# Login Submission

Endpoint: 
  `POST /api/auth/login`

Headers:
```
  Content-Type: application/json
  Authorization: Bearer {auth_token}
```

Body:
```json
  {
    "username": "john_doe",
    "password": "password123"
  }
```

## Expected Responses:

### 1. SUCCESS

   Status Code: `200 OK`

   Body:
   ```json
    {
      "message": "Login successful",
      "token": "9472037428374928372387498237498237",
      "userData": 
      {
        "id": "a104",
        "username": "john_doe",
        "email": "john.doe@example.com",
        "role": "private",
        "organizationName": null
      }
    }
   ```

## 2. FAILURE - Bad Request
   Status Code: `400 Bad Request`

   Body:
   ```json
   {
     "message": "Issue to showcase on frontend to user."
   }
   ```

# Logout Submission

Endpoint: 
  `POST /api/auth/logout`

Headers:
  ```
  Content-Type: application/json
  Authorization: Bearer {auth_token}
  ```

Body: (No additional body content is required for logout)
  ```json
  {}
  ```

## Expected Responses:

### 1. SUCCESS

   Status Code: `200 OK`

   Body:
   ```json
   {
     "message": "Logout successful."
   }
   ```

### 2. FAILURE - Bad Request

   Status Code: `400 Bad Request`

   Body:
   ```json
   {
     "message": "Logout failed, please try again."
   }
   ```

### 3. FAILURE - Unauthorized

   Status Code: `401 Unauthorized`

   Body:
   ```json
   {
     "message": "Authentication required."
   }
   ```

# Signup Submission

Endpoint: 
  ```POST /api/auth/signup```

Headers:
```
  Content-Type: application/json
  Authorization: Bearer {auth_token}
```

Body:
```json
  {
    "username": "john_doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "role": "business || private",
    "organizationName": "name || null"
  }
```

## Expected Responses:

### 1. SUCCESS

   Status Code: `201 Created`

   Body:
   ```json
   {
     "message": "Signup successful",
     "userData": 
     {
       "id": "generated_id",
       "username": "john_doe",
       "email": "john.doe@example.com",
       "role": "business",
       "organizationName": "name || null"
     }
   }
   ```

### 2. FAILURE - Bad Request
   Status Code: `400 Bad Request`

   Body:
   ```json
   {
     "message": "Issue to showcase on frontend to user."
   }
   ```

Search Submission
===================

Endpoint: 
  `POST /api/data/search`

Headers:
```
  Content-Type: application/json
  Authorization: Bearer {auth_token}
```

Body:
```json
  {
    "gameTitle": "game_name || null",
    "gameType": "online || local || all types",
    "includeFullGames": true || false,
    "applicationRequired": true || false,
    "includeUserMadeGames": true || false,
    "includeBusinessMadeGames": true || false,
    "gameAvailability": "public || private || all games",
    "mapLocation": { "lat": 45.8131, "lng": 15.978 },
    "radius": "10",   // Optional, in kilometers
    "page": 1         // Optional, for pagination (default: 1)
  }
```

## Expected Responses:

### 1. SUCCESS
   Status Code: `200 OK`

   Body:
   ```json
   {
  "games": [
    {
      "title": "Game Title Example",
      "description": "A brief description of the game.",
      "image": "https://example.com/image.jpg",
      "genre": "Adventure",
      "releaseDate": "2023-01-01",
      "complexity": "Medium",
      "estimatedLength": "2 hours",
      "startTimestamp": "2023-01-01T12:00:00Z",
      "pravilnik": "Link to rules or guidelines",
      "requiresForm": true,
      "currentPlayerCount": 3,
      "maxPlayerCount": 5,
      "communicationChannel": "Discord",
      "isHomebrew": false
    },
    {
      "title": "Another Game Title",
      "description": "Another game description.",
      "image": "https://example.com/another-image.jpg",
      "genre": "Puzzle",
      "releaseDate": "2022-06-15",
      "complexity": "High",
      "estimatedLength": "3 hours",
      "startTimestamp": "2022-06-15T14:00:00Z",
      "pravilnik": "Another link to rules",
      "requiresForm": false,
      "currentPlayerCount": 2,
      "maxPlayerCount": 4,
      "communicationChannel": "Zoom",
      "isHomebrew": true
    }
    // More game objects as needed
  ]
}
   ```

### 2. FAILURE - Bad Request
   Status Code: `400 Bad Request`

   Body:
   ```json
   {
     "message": "Invalid search filters or missing parameters"
   }
   ```

### 3. FAILURE - Internal Server Error
   Status Code: `500 Internal Server Error`

   Body:
   ```json
   {
     "message": "Something went wrong on the server. Please try again later."
   }
   ```
