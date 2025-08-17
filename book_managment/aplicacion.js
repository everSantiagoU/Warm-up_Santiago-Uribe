const fs = require("fs");
const readline = require("readline");

const DATA_FILE = "books.json";

// iniciar archivo si no existe
if (!fs.existsSync(DATA_FILE)) {
  fs.writeFileSync(DATA_FILE, "[]", "utf-8");
}

//leer libro desde json
function loadBooks() {
  return JSON.parse(fs.readFileSync(DATA_FILE, "utf-8"));
}

//guardar libros en json
function saveBooks(books) {
  fs.writeFileSync(DATA_FILE, JSON.stringify(books, null, 2), "utf-8");
}

// Crear interfaz para leer inputs
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

// Mostrar menú
function showMenu() {
  console.log("\n=== Book Management App ===");
  console.log("1. Add a new book");
  console.log("2. List all books");
  console.log("3. Search books by title");
  console.log("4. Remove a book by title");
  console.log("5. Exit");
  rl.question("Choose an option: ", handleMenu);
}

// Manejar opciones
function handleMenu(option) {
  let books = loadBooks();

  switch (option) {
    case "1": // Add
      rl.question("Title: ", (title) => {
        rl.question("Author: ", (author) => {
          rl.question("Year: ", (year) => {
            rl.question("Genre: ", (genre) => {
              books.push({ title, author, year, genre });
              saveBooks(books);
              console.log("✅ Book added!");
              showMenu();
            });
          });
        });
      });
      break;

    case "2": // List
      if (books.length === 0) {
        console.log("📂 No books found.");
      } else {
        console.log("\n📚 Your books:");
        books.forEach((b, i) =>
          console.log(`${i + 1}. ${b.title} - ${b.author} (${b.year}) [${b.genre}]`)
        );
      }
      showMenu();
      break;

    case "3": // Search
      rl.question("Enter title to search: ", (query) => {
        let results = books.filter((b) =>
          b.title.toLowerCase().includes(query.toLowerCase())
        );
        if (results.length === 0) {
          console.log("❌ No books found.");
        } else {
          console.log("\n🔎 Search results:");
          results.forEach((b) =>
            console.log(`${b.title} - ${b.author} (${b.year}) [${b.genre}]`)
          );
        }
        showMenu();
      });
      break;

    case "4": // Remove
      rl.question("Enter title to remove: ", (title) => {
        let newBooks = books.filter(
          (b) => b.title.toLowerCase() !== title.toLowerCase()
        );
        if (newBooks.length === books.length) {
          console.log("❌ No book found with that title.");
        } else {
          saveBooks(newBooks);
          console.log("🗑️ Book removed.");
        }
        showMenu();
      });
      break;

    case "5": // Exit
      console.log("👋 Goodbye!");
      rl.close();
      break;

    default:
      console.log("⚠️ Invalid option, try again.");
      showMenu();
      break;
  }
}

// Iniciar
showMenu();
