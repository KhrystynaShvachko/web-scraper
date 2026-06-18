# 🚀 Web Scraping Test Task

A Java command-line application that extracts structured data from multiple web sources and exports the results into JSON files.

---

## 📋 Overview

This project implements three scraping tasks:

### 📚 Books Scraper

Extract all books from the **Fiction** category, including pagination.

### 🔄 Infinite Scroll Quotes

Inspect network requests and collect all available quotes using direct HTTP/API calls.

### ⚡ JavaScript Quotes

Extract quote data from a JavaScript-driven page without using browser automation tools.

---

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
