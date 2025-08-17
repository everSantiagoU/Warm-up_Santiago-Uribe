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

// interfaz para leer entradas
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

//menu
function showMenu() {
  console.log("\n app gestion de libros");
  console.log("1. Añadir un nuevo libro");
  console.log("2. Listar todos los libros");
  console.log("3. Buscar libros por titulo");
  console.log("4. Eliminar un libro por titulo");
  console.log("5. Salir");
  rl.question("escoja una opcion: ", handleMenu);
}

//manejar laas opciones
function handleMenu(option) {
  let books = loadBooks();

  switch (option) {
    case "1": // añadir
      rl.question("Titulo: ", (title) => {
        rl.question("Autor: ", (author) => {
          rl.question("Año: ", (year) => {
            rl.question("Genero: ", (genre) => {
              books.push({ title, author, year, genre });
              saveBooks(books);
              console.log("libro añadido correctamente");
              showMenu();
            });
          });
        });
      });
      break;

    case "2": // Listar
      if (books.length === 0) {
        console.log("no se encontro el libro");
      } else {
        console.log("\n Tus libros:");
        books.forEach((b, i) =>
          console.log(`${i + 1}. ${b.title} - ${b.author} (${b.year}) [${b.genre}]`)
        );
      }
      showMenu();
      break;

    case "3": // buscar
      rl.question("Escriba un titulo para buscar: ", (query) => {
        let results = books.filter((b) =>
          b.title.toLowerCase().includes(query.toLowerCase())
        );
        if (results.length === 0) {
          console.log(" No se encontraron libros.");
        } else {
          console.log("\n resultados de busqueda");
          results.forEach((b) =>
            console.log(`${b.title} - ${b.author} (${b.year}) [${b.genre}]`)
          );
        }
        showMenu();
      });
      break;

    case "4": // eliminar
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
