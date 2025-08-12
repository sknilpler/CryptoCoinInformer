# 📊 CryptoCoinInformer

**CryptoCoinInformer** is a Java/Spring Boot application that collects and provides up-to-date cryptocurrency data from popular centralized exchanges (CEX) via a convenient REST API and built-in Swagger UI.

---

## 🚀 Features

- Get price and 24h statistics for selected cryptocurrencies.
- Multiple exchange support in a single API.
- Unified data format regardless of source.
- Automatic API documentation via Swagger.
- Easy to extend for new exchanges.

---

## 📈 Supported Exchanges

- Binance
- BingX
- BitMart
- Bybit
- CoinEx
- Gate.io
- Huobi
- KuCoin
- Mexc
- OKX
- XT.com

---

## 🛠 Installation & Run

### 1. Clone the repository
```bash
git clone https://github.com/sknilpler/CryptoCoinInformer.git
cd CryptoCoinInformer
```

### 2. Build the project
```bash
mvn clean install
```

### 3. Run the application
```bash
mvn spring-boot:run
```

By default, the app runs on `http://localhost:8080`.

---

## 📡 API Documentation (Swagger)

After starting the app, open in your browser:

```
http://localhost:8080/swagger-ui.html
```
or (depending on SpringDoc version):
```
http://localhost:8080/swagger-ui/index.html
```

Here you can:
- View the list of available endpoints.
- Send test requests.
- See example responses.

---

## 📡 Example API Request

Request:
```
GET http://localhost:8080/api/crypto/tickers
```

Response:
```json
[
  {
    "exchange": "Binance",
    "symbol": "BTC/USDT",
    "price": 29450.12,
    "volume": 5123.45,
    "change24h": -0.85
  },
  {
    "exchange": "OKX",
    "symbol": "BTC/USDT",
    "price": 29448.95,
    "volume": 4891.02,
    "change24h": -0.87
  }
]
```

---

## ⚙️ Configuration

Config file:
```
src/main/resources/application.properties
```

You can configure:
- Application port.
- Request timeouts.
- API keys (if required for specific exchanges).

---

## 📂 Project Structure

```
src/
  main/
    java/com/sknilpler/cryptocoininformer/
      controller/        # REST API controllers
      enums/             # Exchange URL constants
      model/             # Data models
      service/           # Exchange integration services
      util/              # Utilities (HTTP, formatting)
    resources/           # Configuration files
  test/                  # Tests
```

---

## 🧩 Requirements

- Java 17+
- Maven 3.6+
- Internet access to query exchange APIs

---

## 🐳 Quick Start with Docker

If you have Docker installed, you can run the app without installing Java and Maven locally.

### 1. Build the Docker image
```bash
docker build -t cryptocoininformer .
```

### 2. Run the container
```bash
docker run -d -p 8080:8080 --name cryptocoininformer cryptocoininformer
```

### 3. Check the app
After startup, the app will be available at:
```
http://localhost:8080
```

Swagger UI:
```
http://localhost:8080/swagger-ui.html
```
or
```
http://localhost:8080/swagger-ui/index.html
```

---
