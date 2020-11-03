# Postgres - JDBC CURD per data type Example 

## mainly used

[Testcontainers](https://www.testcontainers.org/)

## Test Class

### StudentJsonTest.java : test for column type `jsonb`

if you didn't convert type `::JSON`, you will meet this error.

```
org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar []; nested exception is org.postgresql.util.PSQLException: ERROR: column "test_jsonb" is of type jsonb but expression is of type character varying
  Hint: You will need to rewrite or cast the expression.
  Position: 45
	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:239)
	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)
```