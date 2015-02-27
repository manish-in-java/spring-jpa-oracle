# Background

If a text value is stored in an Oracle column with the datatype `CHAR(n)`, the database pads the text value up to `n` characters before saving the value to the column.  Later, attempting to search on that column with the same value using a framework like Hibernate or JPA fails because the search term is not padded up to `n`, leading to a mismatch between the search term and the value stored in the column.  For example, consider the following table definition:

```
CREATE TABLE CITY (
    ...
    NAME CHAR(40) NOT NULL
    ...
);
```

If the query `INSERT INTO CITY (..., NAME, ...) VALUES (..., 'Manchester', ...)` is executed, the database pads up the string `Manchester` to 40 characters before storing it in the `NAME` column.  Later, if the query `SELECT * FROM CITY WHERE NAME = 'Manchester'` is fired, it returns without any match because the database cannot find the string `Manchester` in the `NAME` column for any of the rows in the `CITY` table.

The easiest way out of this is to convert the `CHAR` column to a `VARCHAR` column.  However, in many cases this is not possible due to the database in question being managed by a third-party that does not wish to change the table structure.  In such cases, application developers are forced to pad text strings before firing queries on the database.  Performing this padding for every single `CHAR` column is cumbersome, error-prone and time-consuming.

# Hibernate user types

This sample application demonstrates how the above problem can be solved using Hibernate user types.  A new user type is introduced that pads text strings automatically to the desired length.  This approach centralizes the task of padding the text strings, thereby making the solution simple and maintainable.

# Running the application

This application requires JDK 8, Maven 3.1.0 and Oracle 12c ojbdc7 driver (earlier drivers will not work).  Since the Oracle JDBC drivers are not available in the Maven central repository, the Oracle 12c ojbdc7 driver had to be [manually downloaded](http://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html) and installed in the local Maven repository as `com.oracle:ojdbc7:12.1.0`.

After intalling JDK 8, Maven 3.1.0 and the Oracle 12c ojbdc7 driver, check out the application code and run test cases as `mvn clean test`.
