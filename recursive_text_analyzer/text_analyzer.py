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