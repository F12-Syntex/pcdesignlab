import os
import pyperclip

# Your list of folders
folders = [r"src\main\java\com\syntex"]

result = []

for folder in folders:
    for root, dirs, files in os.walk(folder):
        for filename in files:
            filepath = os.path.join(root, filename)
            try:
                with open(filepath, 'r', encoding='utf-8') as f:
                    content = f.read()
            except Exception as e:
                # If file can't be read as text (e.g., binary), skip it
                content = f"[ERROR READING FILE: {e}]"
            result.append(f"===== {filepath} =====\n{content}\n")

output = "\n".join(result)

# Print to console
print(output)

# Copy to clipboard
pyperclip.copy(output)
print("\nAll file paths and contents have been copied to clipboard!")