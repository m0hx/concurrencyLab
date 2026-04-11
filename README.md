# Concurrent CSV Data Processor

read employees from CSV, process salaries with threads, write to a new CSV file.

## How to run

start project and it'll auto process the input csv file:

```
concurrency\src\main\resources\test_employees.csv
```

## Output file

The processed file name is in **`application.properties`** (`app.csv.output`, default **`processed_employees.csv`**).

located here: \concurrency  
next to **pom.xml**

## Change settings

Edit **`src/main/resources/application.properties`**:

- `app.csv.input` input file name
- `app.csv.output`output file name
- `app.pool.size` thread pool size
- `app.semaphore.maxConnections` semaphore max permits/connections.
