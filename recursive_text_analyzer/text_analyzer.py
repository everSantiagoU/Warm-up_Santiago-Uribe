#importamos la libreria os que nos ayudara a utilizar algunas caracteristicas del sistema operativo
import os

#definimos una funcion que se encargue de contar lineas, palabras y caracteres :)
def count_file_stats(filepath):
    try:
        with open(filepath, "r", encoding="utf-8") as f:
            lines = f.readlines()
            num_lines = len(lines)
            num_words = sum(len(line.split()) for line in lines)
            num_chars = sum(len(line) for line in lines)
        return num_lines, num_words, num_chars
    except Exception as e:
        print(f"Error leyendo {filepath}: {e}")
        return 0, 0, 0
    
#definimos esta funcion que nos ayudara a analizar recursivamente el archivo txt
def scan_folder(folder_path):
    stats = []
    for root, _, files in os.walk(folder_path):
        for file in files:
            if file.endswith(".txt"):
                filepath = os.path.join(root, file)
                lines, words, chars = count_file_stats(filepath)
                relative_path = os.path.relpath(filepath, folder_path)  # ruta relativa
                stats.append((relative_path, lines, words, chars))
    return stats

#esta funcion nos ayudara a imprimir la tabla con todas las caracteristicas requeridas
def print_summary(stats):
    print(f"{'File Name':<30} {'Lines':<10} {'Words':<10} {'Characters':<10}")
    print("-" * 65)

    total_lines = total_words = total_chars = 0
    for file, lines, words, chars in stats:
        print(f"{file:<30} {lines:<10} {words:<10} {chars:<10}")
        total_lines += lines
        total_words += words
        total_chars += chars

    print("-" * 65)
    print(f"{'TOTAL':<30} {total_lines:<10} {total_words:<10} {total_chars:<10}")

#main de la app
def main():
    folder_path = input("📂 Ingrese la ruta de la carpeta a analizar: ").strip()
    if not os.path.exists(folder_path):
        print("❌ La ruta especificada no existe.")
        return

    stats = scan_folder(folder_path)
    if not stats:
        print("⚠️ No se encontraron archivos .txt en la carpeta.")
        return

    print_summary(stats)


if __name__ == "__main__":
    main()
