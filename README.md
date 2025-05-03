
# Text Similarity Engine

This is a Spring Boot-based application that compares a target text file (`A.txt`) with a pool of other text files to determine the similarity between them. The application calculates a matching score for each file in the pool based on how many words it shares with the target file, regardless of word order.

##  Features

- Compares one file against multiple others.
- Computes a similarity score between files based on shared words.
- Ignores word order and non-alphabetic tokens.
- Scales to large files (up to 10 million words each).
- Supports 1 to 20 comparison files in the pool.
- Fast and efficient processing.

##  Technologies Used

- Java 17+
- Spring Boot
- Maven

##  How to Use

1. Clone the repository:
   git clone https://github.com/eslamAborya1/TextSimilarityEngine.git

2. Open the project in your IDE and run the application.
3. Configure the file paths in application.properties:
   base.file.path=D:\\files\\A.txt
   pool.directory=D:\\files\\poolfiles
   # Replace these paths with your actual local paths to the target file and pool directory.
4. Once the app is running, make a GET request to the following endpoint using Postman or your browser:
   http://localhost:8080/files/compare
   #The response will return a list of all pool files along with their similarity scores compared to baseFile

##  Scoring Logic
A word is valid if it only contains alphabetic characters.

Word order is ignored in comparison.

A file with all and only the same words as A.txt scores 100%.

A file with no overlapping words scores 0%.

Partial matches score proportionally.

