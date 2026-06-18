# 🚀 Java Web Scraper

A Java command-line application that extracts structured data from multiple web sources and exports the results into JSON files.

## 📋 Overview

This project implements several web scraping tasks using direct HTTP requests and HTML/JSON parsing.

The application collects structured data from different types of web pages:

### 📚 Books Scraper

Extracts all books from the Fiction category, including pagination.

Collected information:
- Book title
- Price
- Availability
- Rating
- Product details

### 🔄 Infinite Scroll Quotes Scraper

Retrieves quotes from an infinite scroll page by analyzing network requests and using direct HTTP/API calls.

No browser automation is used.

Collected information:
- Quote text
- Author
- Tags

### ⚡ JavaScript Quotes Scraper

Extracts quote data from a JavaScript-driven page by parsing embedded JSON data inside script elements.

The scraper works without Selenium, Playwright, or any browser automation tools.

Collected information:
- Quote text
- Author
- Tags

## 🛠️ Tech Stack

| Technology | Purpose                            |
| ---------- | ---------------------------------- |
| ☕ Java 17  | Core language                      |
| 📦 Maven   | Dependency management & build      |
| 🌿 Jsoup   | HTML parsing                       |
| 📄 Jackson | JSON serialization/deserialization |

---

## 📂 Project Structure

```text
src/main/java

com/webscraper

├── model
│   ├── Book.java
│   └── Quote.java
│
├── parser
│   ├── BookDetailParser.java
│   ├── BookPageParser.java
│   ├── QuoteJsPageParser.java
│   └── QuoteParser.java
│
├── scraper
│   ├── BookScraper.java
│   ├── QuoteJsScraper.java
│   └── QuoteScrollScraper.java
│
├── service
│   ├── HttpClientService.java
│   └── JsonParserService.java
│
├── util
│   └── JsonWriter.java
│
└── Main.java
```

## 👩‍💻 Author

**Khrystyna Shvachko**
